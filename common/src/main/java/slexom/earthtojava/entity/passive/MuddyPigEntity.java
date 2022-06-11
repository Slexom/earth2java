package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.MuddyPigMoveToTargetGoal;
import slexom.earthtojava.entity.base.E2JBasePigEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.FluidInit;


public class MuddyPigEntity extends E2JBasePigEntity {

    private static final TrackedData<Boolean> MUDDY_STATE = DataTracker.registerData(MuddyPigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private int outOfMud = 0;
    private int finallyInMud = 0;

    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public MuddyPigEntity(EntityType<MuddyPigEntity> type, World world) {
        super(type, world);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.2D, Ingredient.ofItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.add(3, new TemptGoal(this, 1.2D, TEMPTATION_ITEMS, false));
        this.goalSelector.add(4, new MuddyPigMoveToTargetGoal(this, 1.2D));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new WanderAroundGoal(this, 1.0D, 100));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        int i = MathHelper.floor(this.getX());
        int j = MathHelper.floor(this.getY());
        int k = MathHelper.floor(this.getZ());
        BlockPos blockPos = new BlockPos(i, j, k);
        boolean condition = this.world.getFluidState(blockPos).getBlockState().getBlock().equals(BlockInit.MUD_BLOCK.get());
        if (condition) {
            if (!isInMuddyState()) {
                if (!isShaking) {
                    this.isShaking = true;
                    this.timeWolfIsShaking = 0.0F;
                    this.prevTimeWolfIsShaking = 0.0F;
                    this.world.sendEntityStatus(this, (byte) 8);
                }
                if (++finallyInMud > 60) {
                    setMuddyState(true);
                    finallyInMud = 0;
                    resetShake();
                }
            }
        } else {
            if (isInMuddyState()) {
                outOfMud++;
                if (outOfMud > 60) {
                    setMuddyState(false);
                    outOfMud = 0;
                }
            }
        }
    }

    private void resetShake() {
        this.isShaking = false;
        this.prevTimeWolfIsShaking = 0.0F;
        this.timeWolfIsShaking = 0.0F;
    }

    public void tick() {
        super.tick();
        if (this.isAlive()) {
            if (isShaking) {
                this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
                this.timeWolfIsShaking += 0.033F;
                if (this.prevTimeWolfIsShaking >= 2.0F) {
                    this.resetShake();
                }
            }
        }
    }

    public void onDeath(DamageSource cause) {
        this.resetShake();
        super.onDeath(cause);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MUDDY_STATE, false);
    }

    public boolean isInMuddyState() {
        return this.dataTracker.get(MUDDY_STATE);
    }

    public void setMuddyState(boolean inMud) {
        this.dataTracker.set(MUDDY_STATE, inMud);
    }

    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putBoolean("IsInMud", this.isInMuddyState());
    }

    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setMuddyState(compound.getBoolean("IsInMud"));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 8) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else {
            super.handleStatus(id);
        }
    }

    @Environment(EnvType.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
        float f = (MathHelper.lerp(p_70923_1_, this.prevTimeWolfIsShaking, this.timeWolfIsShaking) + p_70923_2_) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }
        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

}
 