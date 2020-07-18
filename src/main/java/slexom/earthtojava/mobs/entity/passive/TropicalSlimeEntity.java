package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

public class TropicalSlimeEntity extends MobEntityWithAi {

    private final int size;
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean wasOnGround;

    public TropicalSlimeEntity(EntityType<TropicalSlimeEntity> type, World world) {
        super(type, world);
        this.size = 4;
        this.experiencePoints = this.size;
        setAiDisabled(false);
        this.moveControl = new MoveHelperController(this);
        this.setAttributes();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new FloatGoal(this));
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(3, new FaceRandomGoal(this));
        this.goalSelector.add(5, new HopGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this)));
    }

    protected void setAttributes() {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(16.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.getItem() == Items.BUCKET && !player.abilities.creativeMode && !this.isBaby()) {
            if (!this.world.isClient) {
                this.remove();
                this.world.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 0.0D, 0.0D, 0.0D);
                player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1.0F, 1.0F);
                spawnWater();
                giveTropicalFishBucket(player, hand, itemstack);
                return ActionResult.success(this.world.isClient);
            } else {
                return super.interactMob(player, hand);
            }

        } else {
            return super.interactMob(player, hand);
        }
    }

    private void giveTropicalFishBucket(PlayerEntity player, Hand hand, ItemStack itemstack) {
        itemstack.decrement(1);
        if (!player.inventory.insertStack(new ItemStack(Items.TROPICAL_FISH_BUCKET))) {
            player.dropItem(new ItemStack(Items.TROPICAL_FISH_BUCKET), false);
        }
    }

    private void spawnWater() {
        int x = MathHelper.floor(this.getX());
        int y = MathHelper.floor(this.getY());
        int z = MathHelper.floor(this.getZ());
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState waterState = Blocks.WATER.getDefaultState();
        this.world.removeBlock(blockPos, false);
        this.world.setBlockState(blockPos, waterState, 3);
    }

    protected ParticleEffect getSquishParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    public void tick() {
        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.tick();
        if (this.onGround && !this.wasOnGround) {
            int i = this.size;
            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
                this.world.addParticle(this.getSquishParticle(), this.getX() + (double) f2, this.getY(), this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
            }
            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.squishAmount = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.squishAmount = 1.0F;
        }
        this.wasOnGround = this.onGround;
        this.alterSquishAmount();
    }

    protected void alterSquishAmount() {
        this.squishAmount *= 0.6F;
    }

    protected int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    public EntityType<? extends TropicalSlimeEntity> getType() {
        return (EntityType<? extends TropicalSlimeEntity>) super.getType();
    }

    public void pushAwayFrom(Entity entityIn) {
        super.pushAwayFrom(entityIn);
        if (entityIn instanceof IronGolemEntity && this.canDamagePlayer()) {
            this.dealDamage((LivingEntity) entityIn);
        }
    }

    public void onPlayerCollision(PlayerEntity entityIn) {
        if (this.canDamagePlayer()) {
            this.dealDamage(entityIn);
        }

    }

    protected void dealDamage(LivingEntity entityIn) {
        if (this.isAlive()) {
            int i = this.size;
            if (this.squaredDistanceTo(entityIn) < 0.6D * (double) i * 0.6D * (double) i && this.canSee(entityIn) && entityIn.damage(DamageSource.mob(this), this.func_225512_er_())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.dealDamage(this, entityIn);
            }
        }

    }

    protected float getActiveEyeHeight(EntityPose poseIn, EntityDimensions sizeIn) {
        return 0.625F * sizeIn.height;
    }

    protected boolean canDamagePlayer() {
        return this.canMoveVoluntarily();
    }

    protected float func_225512_er_() {
        return (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    public int getLookPitchSpeed() {
        return 0;
    }

    protected boolean makesSoundOnJump() {
        return true;
    }

    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, (double) this.getJumpVelocity(), vec3d.z);
        this.velocityDirty = true;
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP;
    }

    protected boolean spawnCustomParticles() {
        return false;
    }

    static class AttackGoal extends Goal {
        private final TropicalSlimeEntity slime;
        private int growTieredTimer;

        public AttackGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        public boolean canStart() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.invulnerable ? false : this.slime.getMoveControl() instanceof MoveHelperController;
            }
        }

        public void start() {
            this.growTieredTimer = 300;
            super.start();
        }

        public boolean shouldContinue() {
            LivingEntity livingentity = this.slime.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.invulnerable) {
                return false;
            } else {
                return --this.growTieredTimer > 0;
            }
        }

        public void tick() {
            this.slime.lookAtEntity(this.slime.getTarget(), 10.0F, 10.0F);
            ((MoveHelperController) this.slime.getMoveControl()).look(this.slime.yaw, this.slime.canDamagePlayer());
        }
    }

    static class FaceRandomGoal extends Goal {
        private final TropicalSlimeEntity slime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public FaceRandomGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setControls(EnumSet.of(Goal.Control.LOOK));
        }

        public boolean canStart() {
            return this.slime.getTarget() == null && (this.slime.onGround || this.slime.isTouchingWater() || this.slime.isInLava() || this.slime.hasStatusEffect(StatusEffects.LEVITATION)) && this.slime.getMoveControl() instanceof MoveHelperController;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.slime.getRandom().nextInt(60);
                this.chosenDegrees = (float) this.slime.getRandom().nextInt(360);
            }
            ((MoveHelperController) this.slime.getMoveControl()).look(this.chosenDegrees, false);
        }

    }

    static class FloatGoal extends Goal {
        private final TropicalSlimeEntity slime;

        public FloatGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
            slime.getNavigation().setCanSwim(true);
        }

        public boolean canStart() {
            return (this.slime.isTouchingWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof MoveHelperController;
        }

        public void tick() {
            if (this.slime.getRandom().nextFloat() < 0.8F) {
                this.slime.getJumpControl().setActive();
            }
            ((MoveHelperController) this.slime.getMoveControl()).move(1.2D);
        }
    }

    static class HopGoal extends Goal {
        private final TropicalSlimeEntity slime;

        public HopGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        public boolean canStart() {
            return !this.slime.hasVehicle();
        }

        public void tick() {
            ((MoveHelperController) this.slime.getMoveControl()).move(1.0D);
        }
    }

    static class MoveHelperController extends MoveControl {
        private final TropicalSlimeEntity slime;
        private float yRot;
        private int jumpDelay;
        private boolean isAggressive;

        public MoveHelperController(TropicalSlimeEntity slimeIn) {
            super(slimeIn);
            this.slime = slimeIn;
            this.yRot = 180.0F * slimeIn.yaw / (float) Math.PI;
        }

        public void look(float yRotIn, boolean aggressive) {
            this.yRot = yRotIn;
            this.isAggressive = aggressive;
        }

        public void move(double speedIn) {
            this.speed = speedIn;
            this.state = MoveControl.State.MOVE_TO;
        }

        public void tick() {
            this.entity.yaw = this.changeAngle(this.entity.yaw, this.yRot, 90.0F);
            this.entity.headYaw = this.entity.yaw;
            this.entity.bodyYaw = this.entity.yaw;
            if (this.state != MoveControl.State.MOVE_TO) {
                this.entity.setForwardSpeed(0.0F);
            } else {
                this.state = MoveControl.State.WAIT;
                if (this.entity.isOnGround()) {
                    this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue()));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.slime.getJumpControl().setActive();
                        if (this.slime.makesSoundOnJump()) {
                            this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRandom().nextFloat() - this.slime.getRandom().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.slime.sidewaysSpeed = 0.0F;
                        this.slime.forwardSpeed = 0.0F;
                        this.entity.setMovementSpeed(0.0F);
                    }
                } else {
                    this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue()));
                }

            }
        }
    }


}
 
