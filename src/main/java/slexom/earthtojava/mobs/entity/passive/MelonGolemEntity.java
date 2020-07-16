package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.controller.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.potion.Effects;
import import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.projectile.MelonSeedProjectileEntity;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class MelonGolemEntity extends GolemEntity implements IRangedAttackMob, net.minecraftforge.common.IShearable {
    private static final TrackedData<Byte> MELON_EQUIPPED = DataTracker.registerData(MelonGolemEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> SHOOTING_TICKS = DataTracker.registerData(MelonGolemEntity.class, TrackedDataHandlerRegistry.VARINT);
    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(740) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public MelonGolemEntity(EntityType<? extends MelonGolemEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new MoveHelperController(this);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new RangedAttack(this, 1.25D, 20, 10.0F));
        this.goalSelector.add(2, new FaceRandomGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new HopGoal(this));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, MobEntity.class, 10, true, false, (entity) -> entity instanceof IMob && !(entity instanceof TropicalSlimeEntity)));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MELON_EQUIPPED, (byte) 16);
        this.dataTracker.startTracking(SHOOTING_TICKS, 0);
    }

    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("Pumpkin", this.isMelonEquipped());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        if (compound.contains("Pumpkin")) {
            this.setMelonEquipped(compound.getBoolean("Pumpkin"));
        }

    }

    public void tickMovement() {
        super.tickMovement();
        if (!this.world.isClient) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY());
            int k = MathHelper.floor(this.getZ());
            if (this.isInWaterRainOrBubbleColumn()) {
                this.damage(DamageSource.DROWN, 1.0F);
            }
            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
                this.damage(DamageSource.ON_FIRE, 1.0F);
            }
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                return;
            }
            BlockState blockstate = Blocks.SNOW.getDefaultState();
            for (int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getY());
                k = MathHelper.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);
                if (this.world.isAirBlock(blockpos) && this.world.getBiome(blockpos).getTemperature(blockpos) < 0.8F && blockstate.isValidPosition(this.world, blockpos)) {
                    this.world.setBlockState(blockpos, blockstate);
                }
            }
        }
        int currentShootingTicks = this.dataTracker.get(SHOOTING_TICKS);
        if (currentShootingTicks > 0) {
            this.dataTracker.set(SHOOTING_TICKS, --currentShootingTicks);
        }
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING_TICKS) > 0;
    }

    public void setShootingTicks() {
        this.dataTracker.set(SHOOTING_TICKS, 8);
    }

    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        this.setShootingTicks();
        MelonSeedProjectileEntity melonSeedEntity = new MelonSeedProjectileEntity(this.world, this);
        double d0 = target.getPosYEye() - (double) 1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - melonSeedEntity.getY();
        double d3 = target.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        melonSeedEntity.shoot(d1, d2 + (double) f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(melonSeedEntity);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.7F;
    }

    public boolean isMelonEquipped() {
        return (this.dataTracker.get(MELON_EQUIPPED) & 16) != 0;
    }

    public void setMelonEquipped(boolean melonEquipped) {
        byte b0 = this.dataTracker.get(MELON_EQUIPPED);
        if (melonEquipped) {
            this.dataTracker.set(MELON_EQUIPPED, (byte) (b0 | 16));
        } else {
            this.dataTracker.set(MELON_EQUIPPED, (byte) (b0 & -17));
        }
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SNOW_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_DEATH;
    }

    protected int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.WorldView world, BlockPos pos) {
        return this.isMelonEquipped();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        this.setMelonEquipped(false);
        return new java.util.ArrayList<>();
    }

    static class RangedAttack extends RangedAttackGoal {
        private final MelonGolemEntity rangedAttackEntityHost;

        public RangedAttack(MelonGolemEntity attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
            super(attacker, movespeed, maxAttackTime, maxAttackDistanceIn);
            this.rangedAttackEntityHost = attacker;
        }

        public RangedAttack(MelonGolemEntity attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
            super(attacker, movespeed, p_i1650_4_, maxAttackTime, maxAttackDistanceIn);
            this.rangedAttackEntityHost = attacker;
        }

        @Override
        public void tick() {
            super.tick();
            ((MoveHelperController) this.rangedAttackEntityHost.getMoveControl()).setDirection(this.rangedAttackEntityHost.rotationYaw, true);
        }
    }

    static class HopGoal extends Goal {
        private final MelonGolemEntity melonGolem;

        public HopGoal(MelonGolemEntity entity) {
            this.melonGolem = entity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canStart() {
            return !this.melonGolem.isPassenger();
        }

        public void tick() {
            ((MoveHelperController) this.melonGolem.getMoveControl()).setSpeed(1.0D);
        }
    }

    static class MoveHelperController extends MoveControl {
        private float yRot;
        private int jumpDelay;
        private final MelonGolemEntity melonGolem;
        private boolean isAggressive;

        public MoveHelperController(MelonGolemEntity entity) {
            super(entity);
            this.melonGolem = entity;
            this.yRot = 180.0F * entity.rotationYaw / (float) Math.PI;
        }

        public void setDirection(float yRotIn, boolean aggressive) {
            this.yRot = yRotIn;
            this.isAggressive = aggressive;
        }

        public void setSpeed(double speedIn) {
            this.speed = speedIn;
            this.action = MoveControl.Action.MOVE_TO;
        }

        public void tick() {
            this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, this.yRot, 90.0F);
            this.mob.rotationYawHead = this.mob.rotationYaw;
            this.mob.renderYawOffset = this.mob.rotationYaw;
            if (this.action != MoveControl.Action.MOVE_TO) {
                this.mob.setMoveForward(0.0F);
            } else {
                this.action = MoveControl.Action.WAIT;
                if (this.mob.onGround) {
                    this.mob.setAIMoveSpeed((float) (this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.melonGolem.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }
                        this.melonGolem.getJumpController().setJumping();

                    } else {
                        this.melonGolem.moveStrafing = 0.0F;
                        this.melonGolem.moveForward = 0.0F;
                        this.mob.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.mob.setAIMoveSpeed((float) (this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
                }

            }
        }
    }

    static class FaceRandomGoal extends Goal {
        private final MelonGolemEntity melonGolem;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public FaceRandomGoal(MelonGolemEntity melonGolem) {
            this.melonGolem = melonGolem;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canStart() {
            return this.melonGolem.getTarget() == null && (this.melonGolem.onGround || this.melonGolem.isInWater() || this.melonGolem.isInLava() || this.melonGolem.isPotionActive(Effects.LEVITATION)) && this.melonGolem.getMoveControl() instanceof MoveHelperController;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.melonGolem.getRandom().nextInt(60);
                this.chosenDegrees = (float)this.melonGolem.getRandom().nextInt(360);
            }
            ((MoveHelperController)this.melonGolem.getMoveControl()).setDirection(this.chosenDegrees, false);
        }
    }
}
