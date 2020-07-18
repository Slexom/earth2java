package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.projectile.MelonSeedProjectileEntity;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

public class MelonGolemEntity extends GolemEntity implements RangedAttackMob, Shearable {
    private static final TrackedData<Byte> MELON_EQUIPPED = DataTracker.registerData(MelonGolemEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> SHOOTING_TICKS = DataTracker.registerData(MelonGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(740) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public MelonGolemEntity(EntityType<? extends MelonGolemEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new MoveHelperController(this);
    }

    public static DefaultAttributeContainer.Builder createMelonGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new RangedAttack(this, 1.25D, 20, 10.0F));
        this.goalSelector.add(2, new FaceRandomGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new HopGoal(this));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, MobEntity.class, 10, true, false, (entity) -> entity instanceof Monster && !(entity instanceof TropicalSlimeEntity)));
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
            if (this.isInsideWaterOrBubbleColumn()) {
                this.damage(DamageSource.DROWN, 1.0F);
            }
            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
                this.damage(DamageSource.ON_FIRE, 1.0F);
            }

            BlockState blockState = Blocks.SNOW.getDefaultState();
            for (int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getY());
                k = MathHelper.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.world.getBlockState(blockPos).isAir() && this.world.getBiome(blockPos).getTemperature(blockPos) < 0.8F && blockState.canPlaceAt(this.world, blockPos)) {
                    this.world.setBlockState(blockPos, blockState);
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

    public void attack(LivingEntity target, float distanceFactor) {
        this.setShootingTicks();
        MelonSeedProjectileEntity melonSeedEntity = new MelonSeedProjectileEntity(this.world, this);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - melonSeedEntity.getY();
        double d3 = target.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        melonSeedEntity.setVelocity(d1, d2 + (double) f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(melonSeedEntity);
    }

    public float getEyeHeight(EntityPose poseIn) {
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

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.SHEARS) {
            if (!this.world.isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                itemStack.damage(1, player, ((playerEntity) -> playerEntity.sendToolBreakStatus(hand)));
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public boolean isShearable() {
        return this.isMelonEquipped();
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setMelonEquipped(false);
    }

    static class RangedAttack extends ProjectileAttackGoal {
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
            ((MoveHelperController) this.rangedAttackEntityHost.getMoveControl()).setDirection(this.rangedAttackEntityHost.yaw, true);
        }
    }

    static class HopGoal extends Goal {
        private final MelonGolemEntity melonGolem;

        public HopGoal(MelonGolemEntity entity) {
            this.melonGolem = entity;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        public boolean canStart() {
            return !this.melonGolem.hasVehicle();
        }

        public void tick() {
            ((MoveHelperController) this.melonGolem.getMoveControl()).move(1.0D);
        }
    }

    static class MoveHelperController extends MoveControl {
        private final MelonGolemEntity melonGolem;
        private float targetYaw;
        private int jumpDelay;
        private boolean jumpOften;

        public MoveHelperController(MelonGolemEntity entity) {
            super(entity);
            this.melonGolem = entity;
            this.targetYaw = 180.0F * entity.yaw / (float) Math.PI;
        }

        public void setDirection(float targetYaw, boolean jumpOften) {
            this.targetYaw = targetYaw;
            this.jumpOften = jumpOften;
        }

        public void move(double speedIn) {
            this.speed = speedIn;
            this.state = MoveControl.State.MOVE_TO;
        }

        public void tick() {
            this.entity.yaw = this.changeAngle(this.entity.yaw, this.targetYaw, 90.0F);
            this.entity.headYaw = this.entity.yaw;
            this.entity.bodyYaw = this.entity.yaw;
            if (this.state != MoveControl.State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0F);
            } else {
                this.state = MoveControl.State.WAIT;
                if (this.entity.isOnGround()) {
                    this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.melonGolem.getJumpDelay();
                        if (this.jumpOften) {
                            this.jumpDelay /= 3;
                        }
                        this.melonGolem.getJumpControl().setActive();

                    } else {
                        this.melonGolem.sidewaysSpeed = 0.0F;
                        this.melonGolem.forwardSpeed = 0.0F;
                        this.entity.setMovementSpeed(0.0F);
                    }
                } else {
                    this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                }

            }
        }
    }

    static class FaceRandomGoal extends Goal {
        private final MelonGolemEntity melonGolem;
        private float targetYaw;
        private int timer;

        public FaceRandomGoal(MelonGolemEntity melonGolem) {
            this.melonGolem = melonGolem;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        public boolean canStart() {
            return this.melonGolem.getTarget() == null && (this.melonGolem.onGround || this.melonGolem.isInsideWaterOrBubbleColumn() || this.melonGolem.isInLava() || this.melonGolem.hasStatusEffect(StatusEffects.LEVITATION)) && this.melonGolem.getMoveControl() instanceof MoveHelperController;
        }

        public void tick() {
            if (--this.timer <= 0) {
                this.timer = 40 + this.melonGolem.getRandom().nextInt(60);
                this.targetYaw = (float) this.melonGolem.getRandom().nextInt(360);
            }
            ((MoveHelperController) this.melonGolem.getMoveControl()).setDirection(this.targetYaw, false);
        }
    }
}
