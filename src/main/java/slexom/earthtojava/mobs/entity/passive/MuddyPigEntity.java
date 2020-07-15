package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.IPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;
import slexom.earthtojava.mobs.init.BlockInit;


public class MuddyPigEntity extends E2JBasePigEntity<MuddyPigEntity> {

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
        this.goalSelector.add(4, new GoToMudGoal(this, 1.2D));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new RandomWalkingGoal(this, 1.0D, 100));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
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

    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("IsInMud", this.isInMuddyState());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
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
            Block block = worldIn.getBlockState(pos).getBlock();
            return block == BlockInit.MUD_BLOCK.get();
        }
    }

//    static class RollGoal extends Goal {
//        private final MuddyPigEntity muddyPigEntity;
//
//        public RollGoal(MuddyPigEntity entity) {
//            this.muddyPigEntity = entity;
//            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
//        }
//
//        /**
//         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
//         * method as well.
//         */
//        public boolean shouldExecute() {
//            if ((this.muddyPigEntity.isBaby() || this.muddyPigEntity.isPlayful()) && this.muddyPigEntity.onGround) {
//                if (!this.muddyPigEntity.canPerformAction()) {
//                    return false;
//                } else {
//                    float f = this.muddyPigEntity.rotationYaw * ((float)Math.PI / 180F);
//                    int i = 0;
//                    int j = 0;
//                    float f1 = -MathHelper.sin(f);
//                    float f2 = MathHelper.cos(f);
//                    if ((double)Math.abs(f1) > 0.5D) {
//                        i = (int)((float)i + f1 / Math.abs(f1));
//                    }
//
//                    if ((double)Math.abs(f2) > 0.5D) {
//                        j = (int)((float)j + f2 / Math.abs(f2));
//                    }
//
//                    if (this.muddyPigEntity.world.getBlockState((new BlockPos(this.muddyPigEntity)).add(i, -1, j)).isAir()) {
//                        return true;
//                    } else if (this.muddyPigEntity.isPlayful() && this.muddyPigEntity.rand.nextInt(60) == 1) {
//                        return true;
//                    } else {
//                        return this.muddyPigEntity.rand.nextInt(500) == 1;
//                    }
//                }
//            } else {
//                return false;
//            }
//        }
//
//        /**
//         * Returns whether an in-progress EntityAIBase should continue executing
//         */
//        public boolean shouldContinueExecuting() {
//            return false;
//        }
//
//        /**
//         * Execute a one shot task or start executing a continuous task
//         */
//        public void startExecuting() {
//            this.muddyPigEntity.func_213576_v(true);
//        }
//
//        public boolean isPreemptible() {
//            return false;
//        }
//    }



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
 