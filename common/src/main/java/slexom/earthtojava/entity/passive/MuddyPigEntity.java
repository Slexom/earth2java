package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityStatuses;
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


public class MuddyPigEntity extends E2JBasePigEntity {

    private static final TrackedData<Boolean> MUDDY_STATE = DataTracker.registerData(MuddyPigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private int outOfMud = 0;
    private int finallyInMud = 0;

    private boolean isShaking;
    private float timeMuddyPigIsShaking;
    private float prevTimeMuddyPigIsShaking;

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

    private boolean isOverMudBlock() {
        int x = MathHelper.floor(this.getX());
        int y = MathHelper.floor(this.getY());
        int z = MathHelper.floor(this.getZ());
        BlockPos blockPos = new BlockPos(x, y, z).down();
        return this.getWorld().getBlockState(blockPos).getBlock().equals(Blocks.MUD);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.isOverMudBlock()) {
            if (isInMuddyState()) return;

            if (!isShaking) {
                this.isShaking = true;
                this.timeMuddyPigIsShaking = 0.0F;
                this.prevTimeMuddyPigIsShaking = 0.0F;
                this.getWorld().sendEntityStatus(this, (byte) 8);
            }
            if (++finallyInMud > 60) {
                setMuddyState(true);
                finallyInMud = 0;
                resetShake();
            }
        } else {
            if (!isInMuddyState()) return;

            outOfMud++;
            if (outOfMud > 60) {
                setMuddyState(false);
                outOfMud = 0;
            }

        }
    }

    private void resetShake() {
        this.isShaking = false;
        this.prevTimeMuddyPigIsShaking = 0.0F;
        this.timeMuddyPigIsShaking = 0.0F;
    }

    public void tick() {
        super.tick();
        if (!this.isAlive()) return;
        if (!isShaking) return;

        this.prevTimeMuddyPigIsShaking = this.timeMuddyPigIsShaking;
        this.timeMuddyPigIsShaking += 0.033F;
        if (this.prevTimeMuddyPigIsShaking >= 2.0F) {
            this.resetShake();
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
    public void handleStatus(byte status) {
        if (status == EntityStatuses.SHAKE_OFF_WATER) {
            this.isShaking = true;
            this.timeMuddyPigIsShaking = 0.0F;
            this.prevTimeMuddyPigIsShaking = 0.0F;
        } else {
            super.handleStatus(status);
        }
    }

    @Environment(EnvType.CLIENT)
    public float getShakeAngle(float tickDelta, float d) {
        float f = (MathHelper.lerp(tickDelta, this.prevTimeMuddyPigIsShaking, this.timeMuddyPigIsShaking) + d) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }
        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

}
 