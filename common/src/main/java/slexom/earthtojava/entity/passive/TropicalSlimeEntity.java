package slexom.earthtojava.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
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
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.ai.goal.*;

public class TropicalSlimeEntity extends HostileEntity implements Monster {

    private final int size;
    public float targetStretch;
    public float stretch;
    public float lastStretch;
    private boolean onGroundLastTick;

    public TropicalSlimeEntity(EntityType<TropicalSlimeEntity> type, World world) {
        super(type, world);
        this.size = 4;
        this.experiencePoints = this.size;
        setAiDisabled(false);
        this.moveControl = new TropicalSlimeMoveControl(this);
        this.setAttributes();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new TropicalSlimeSwimGoal(this));
        this.goalSelector.add(1, new TropicalSlimeFloatGoal(this));
        this.goalSelector.add(2, new TropicalSlimeAttackGoal(this));
        this.goalSelector.add(3, new TropicalSlimeFaceRandomGoal(this));
        this.goalSelector.add(5, new TropicalSlimeHopGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this)));
    }

    protected void setAttributes() {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(16.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {


        if (this.isBaby()) return super.interactMob(player, hand);
        if (player.getAbilities().creativeMode) return super.interactMob(player, hand);

        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.getItem() != Items.BUCKET) return super.interactMob(player, hand);

        if (this.getWorld().isClient) return super.interactMob(player, hand);

        this.remove(RemovalReason.KILLED);
        this.getWorld().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 0.0D, 0.0D, 0.0D);
        player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1.0F, 1.0F);
        spawnWater();
        giveTropicalFishBucket(player, itemstack);
        return ActionResult.success(this.getWorld().isClient);
    }

    private void giveTropicalFishBucket(PlayerEntity player, ItemStack itemstack) {
        itemstack.decrement(1);
        if (!player.getInventory().insertStack(new ItemStack(Items.TROPICAL_FISH_BUCKET))) {
            player.dropItem(new ItemStack(Items.TROPICAL_FISH_BUCKET), false);
        }
    }

    private void spawnWater() {
        int x = MathHelper.floor(this.getX());
        int y = MathHelper.floor(this.getY());
        int z = MathHelper.floor(this.getZ());
        BlockPos blockPos = new BlockPos(x, y, z);
        BlockState waterState = Blocks.WATER.getDefaultState();
        this.getWorld().removeBlock(blockPos, false);
        this.getWorld().setBlockState(blockPos, waterState, 3);
    }

    protected ParticleEffect getSquishParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    public void tick() {
        this.stretch += (this.targetStretch - this.stretch) * 0.5F;
        this.lastStretch = this.stretch;
        super.tick();

        if (this.isOnGround() && !this.onGroundLastTick) {
            int i = this.size;
            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float) Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * i * 0.5F * f1;
                this.getWorld().addParticle(this.getSquishParticle(), this.getX() + f2, this.getY(), this.getZ() + f3, 0.0D, 0.0D, 0.0D);
            }
            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetStretch = -0.5F;
        } else if (!this.isOnGround() && this.onGroundLastTick) {
            this.targetStretch = 1.0F;
        }
        this.onGroundLastTick = this.isOnGround();
        this.updateStretch();
    }

    protected void updateStretch() {
        this.targetStretch *= 0.6F;
    }

    public int getTicksUntilNextJump() {
        return this.random.nextInt(20) + 10;
    }

    @Override
    public void pushAwayFrom(Entity entityIn) {
        super.pushAwayFrom(entityIn);
        if (entityIn instanceof IronGolemEntity && this.canAttack()) {
            this.damage((LivingEntity) entityIn);
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.canAttack()) {
            this.damage(player);
        }

    }

    protected void damage(LivingEntity livingEntity) {
        if (this.isAlive()) {
            int i = this.size;
            if (this.squaredDistanceTo(livingEntity) < 0.6D * i * 0.6D * i && this.canSee(livingEntity) && livingEntity.damage(livingEntity.getDamageSources().mobAttack(this), this.getDamageAmount())) {
                this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.applyDamageEffects(this, livingEntity);
            }
        }

    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.625F * dimensions.height;
    }

    public boolean canAttack() {
        return this.canMoveVoluntarily();
    }

    protected float getDamageAmount() {
        return (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    public int getLookPitchSpeed() {
        return 0;
    }

    public boolean makesSoundOnJump() {
        return true;
    }

    @Override
    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, this.getJumpVelocity(), vec3d.z);
        this.velocityDirty = true;
    }

    public SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP;
    }

    protected boolean spawnCustomParticles() {
        return false;
    }

}
 
