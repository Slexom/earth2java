
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.EnumSet;

public class TropicalSlimeEntity extends CreatureEntity {

    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean wasOnGround;
    private final int size;

    public TropicalSlimeEntity(EntityType<TropicalSlimeEntity> type, World world) {
        super(type, world);
        this.size = 4;
        experienceValue = this.size;
        setNoAI(false);
        this.moveController = new TropicalSlimeEntity.MoveHelperController(this);
        this.registerAttributes();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TropicalSlimeEntity.FloatGoal(this));
        this.goalSelector.addGoal(2, new TropicalSlimeEntity.AttackGoal(this));
        this.goalSelector.addGoal(3, new TropicalSlimeEntity.FaceRandomGoal(this));
        this.goalSelector.addGoal(5, new TropicalSlimeEntity.HopGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
    }

    protected void registerAttributes() {
        this.getAttribute(Attributes.field_233818_a_).setBaseValue(16.0D);
        this.getAttribute(Attributes.field_233821_d_).setBaseValue(0.6D);
        this.getAttribute(Attributes.field_233823_f_).setBaseValue(4.0D);
    }

    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.BUCKET && !player.abilities.isCreativeMode && !this.isChild()) {
            if (!this.world.isRemote) {
                this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 0.0D, 0.0D, 0.0D);
                this.remove();
                player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1.0F, 1.0F);
                giveTropicalFishBucket(player, hand, itemstack);
                spawnWater();
                return ActionResultType.func_233537_a_(this.world.isRemote);
            } else {
                return super.func_230254_b_(player, hand);
            }
        } else {
            return super.func_230254_b_(player, hand);
        }
    }

    private void giveTropicalFishBucket(PlayerEntity player, Hand hand, ItemStack itemstack) {
        itemstack.shrink(1);
        if (!player.inventory.addItemStackToInventory(new ItemStack(Items.TROPICAL_FISH_BUCKET))) {
            player.dropItem(new ItemStack(Items.TROPICAL_FISH_BUCKET), false);
        }
    }

    private void spawnWater() {
        int x = MathHelper.floor(this.getPosX());
        int y = MathHelper.floor(this.getPosY());
        int z = MathHelper.floor(this.getPosZ());
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState waterState = Blocks.WATER.getDefaultState();
        this.world.destroyBlock(blockPos, false);
        this.world.setBlockState(blockPos, waterState, 3);
    }

    protected IParticleData getSquishParticle() {
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
                float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
                this.world.addParticle(this.getSquishParticle(), this.getPosX() + (double) f2, this.getPosY(), this.getPosZ() + (double) f3, 0.0D, 0.0D, 0.0D);
            }
            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
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
        return this.rand.nextInt(20) + 10;
    }

    public EntityType<? extends TropicalSlimeEntity> getType() {
        return (EntityType<? extends TropicalSlimeEntity>) super.getType();
    }

    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);
        if (entityIn instanceof IronGolemEntity && this.canDamagePlayer()) {
            this.dealDamage((LivingEntity) entityIn);
        }
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (this.canDamagePlayer()) {
            this.dealDamage(entityIn);
        }

    }

    protected void dealDamage(LivingEntity entityIn) {
        if (this.isAlive()) {
            int i = this.size;
            if (this.getDistanceSq(entityIn) < 0.6D * (double) i * 0.6D * (double) i && this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), this.func_225512_er_())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                this.applyEnchantments(this, entityIn);
            }
        }

    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.625F * sizeIn.height;
    }

    protected boolean canDamagePlayer() {
        return this.isServerWorld();
    }

    protected float func_225512_er_() {
        return (float) this.func_233637_b_(Attributes.field_233823_f_);
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

    protected ResourceLocation getLootTable() {
        return this.getType().getLootTable();
    }

    public int getVerticalFaceSpeed() {
        return 0;
    }

    protected boolean makesSoundOnJump() {
        return true;
    }

    protected void jump() {
        Vector3d vec3d = this.getMotion();
        this.setMotion(vec3d.x, (double) this.getJumpUpwardsMotion(), vec3d.z);
        this.isAirBorne = true;
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
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean shouldExecute() {
            LivingEntity livingentity = this.slime.getAttackTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                return livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.disableDamage ? false : this.slime.getMoveHelper() instanceof TropicalSlimeEntity.MoveHelperController;
            }
        }

        public void startExecuting() {
            this.growTieredTimer = 300;
            super.startExecuting();
        }

        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = this.slime.getAttackTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.disableDamage) {
                return false;
            } else {
                return --this.growTieredTimer > 0;
            }
        }

        public void tick() {
            this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
            ((TropicalSlimeEntity.MoveHelperController) this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, this.slime.canDamagePlayer());
        }
    }

    static class FaceRandomGoal extends Goal {
        private final TropicalSlimeEntity slime;
        private float chosenDegrees;
        private int nextRandomizeTime;

        public FaceRandomGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean shouldExecute() {
            return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.isPotionActive(Effects.LEVITATION)) && this.slime.getMoveHelper() instanceof TropicalSlimeEntity.MoveHelperController;
        }

        public void tick() {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
                this.chosenDegrees = (float) this.slime.getRNG().nextInt(360);
            }
            ((TropicalSlimeEntity.MoveHelperController) this.slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
        }

    }

    static class FloatGoal extends Goal {
        private final TropicalSlimeEntity slime;

        public FloatGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
            slimeIn.getNavigator().setCanSwim(true);
        }

        public boolean shouldExecute() {
            return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveHelper() instanceof TropicalSlimeEntity.MoveHelperController;
        }

        public void tick() {
            if (this.slime.getRNG().nextFloat() < 0.8F) {
                this.slime.getJumpController().setJumping();
            }
            ((TropicalSlimeEntity.MoveHelperController) this.slime.getMoveHelper()).setSpeed(1.2D);
        }
    }

    static class HopGoal extends Goal {
        private final TropicalSlimeEntity slime;

        public HopGoal(TropicalSlimeEntity slimeIn) {
            this.slime = slimeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            return !this.slime.isPassenger();
        }

        public void tick() {
            ((TropicalSlimeEntity.MoveHelperController) this.slime.getMoveHelper()).setSpeed(1.0D);
        }
    }

    static class MoveHelperController extends MovementController {
        private float yRot;
        private int jumpDelay;
        private final TropicalSlimeEntity slime;
        private boolean isAggressive;

        public MoveHelperController(TropicalSlimeEntity slimeIn) {
            super(slimeIn);
            this.slime = slimeIn;
            this.yRot = 180.0F * slimeIn.rotationYaw / (float) Math.PI;
        }

        public void setDirection(float yRotIn, boolean aggressive) {
            this.yRot = yRotIn;
            this.isAggressive = aggressive;
        }

        public void setSpeed(double speedIn) {
            this.speed = speedIn;
            this.action = MovementController.Action.MOVE_TO;
        }

        public void tick() {
            this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, this.yRot, 90.0F);
            this.mob.rotationYawHead = this.mob.rotationYaw;
            this.mob.renderYawOffset = this.mob.rotationYaw;
            if (this.action != MovementController.Action.MOVE_TO) {
                this.mob.setMoveForward(0.0F);
            } else {
                this.action = MovementController.Action.WAIT;
                if (this.mob.func_233570_aj_()) {
                    this.mob.setAIMoveSpeed((float) (this.speed * this.mob.getAttribute(Attributes.field_233821_d_).getValue()));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();
                        if (this.isAggressive) {
                            this.jumpDelay /= 3;
                        }

                        this.slime.getJumpController().setJumping();
                        if (this.slime.makesSoundOnJump()) {
                            this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                        }
                    } else {
                        this.slime.moveStrafing = 0.0F;
                        this.slime.moveForward = 0.0F;
                        this.mob.setAIMoveSpeed(0.0F);
                    }
                } else {
                    this.mob.setAIMoveSpeed((float) (this.speed * this.mob.getAttribute(Attributes.field_233821_d_).getValue()));
                }

            }
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
 
