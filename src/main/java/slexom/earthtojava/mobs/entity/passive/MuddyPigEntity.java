package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;
import slexom.earthtojava.mobs.init.BlockInit;


public class MuddyPigEntity extends E2JBasePigEntity<MuddyPigEntity> {

    private static final DataParameter<Boolean> MUDDY_STATE = EntityDataManager.createKey(MuddyPigEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private int outOfMud = 0;
    private int finallyInMud = 0;

    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public MuddyPigEntity(EntityType<MuddyPigEntity> type, World world) {
        super(type, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.fromItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new MuddyPigEntity.GoToMudGoal(this, 1.2D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0D, 100));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        int i = MathHelper.floor(this.getPosX());
        int j = MathHelper.floor(this.getPosY());
        int k = MathHelper.floor(this.getPosZ());
        BlockPos blockPos = new BlockPos(i, j, k);
        boolean condition = this.world.getFluidState(blockPos).getBlockState().getBlock().equals(BlockInit.MUD_BLOCK.get());
        if (condition) {
            if (!isInMuddyState()) {
                if(!isShaking) {
                    this.isShaking = true;
                    this.timeWolfIsShaking = 0.0F;
                    this.prevTimeWolfIsShaking = 0.0F;
                    this.world.setEntityState(this, (byte) 8);
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

    protected void registerData() {
        super.registerData();
        this.dataManager.register(MUDDY_STATE, false);
    }

    public boolean isInMuddyState() {
        return this.dataManager.get(MUDDY_STATE);
    }

    public void setMuddyState(boolean inMud) {
        this.dataManager.set(MUDDY_STATE, inMud);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("IsInMud", this.isInMuddyState());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setMuddyState(compound.getBoolean("IsInMud"));
    }

    public static class GoToMudGoal extends MoveToBlockGoal {
        private final MuddyPigEntity muddyPig;

        public GoToMudGoal(MuddyPigEntity entity, double speedIn) {
            super(entity, speedIn, 16, 3);
            this.muddyPig = entity;
            this.field_203112_e = -1;
        }

        public boolean shouldExecute() {
            return !this.muddyPig.isInMuddyState() && super.shouldExecute();
        }

        public boolean shouldContinueExecuting() {
            return !this.muddyPig.isInMuddyState() && this.timeoutCounter <= 600 && this.shouldMoveTo(this.muddyPig.world, this.destinationBlock);
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 100 == 0;
        }

        @Override
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            return worldIn.getBlockState(pos).isIn(BlockInit.MUD_BLOCK.get());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 8) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else {
            super.handleStatusUpdate(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
        float f = (MathHelper.lerp(p_70923_1_, this.prevTimeWolfIsShaking, this.timeWolfIsShaking) + p_70923_2_) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
 