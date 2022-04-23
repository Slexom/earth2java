package slexom.earthtojava.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
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
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveController;
import slexom.earthtojava.entity.ai.goal.TropicalSlimeAttackGoal;
import slexom.earthtojava.entity.ai.goal.TropicalSlimeFaceRandomGoal;
import slexom.earthtojava.entity.ai.goal.TropicalSlimeFloatGoal;
import slexom.earthtojava.entity.ai.goal.TropicalSlimeHopGoal;

public class TropicalSlimeEntity extends HostileEntity {

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
        this.moveControl = new TropicalSlimeMoveController(this);
        this.setAttributes();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SwimGoal(this));
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

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        if (itemstack.getItem() == Items.BUCKET && !player.getAbilities().creativeMode && !this.isBaby()) {
            if (!this.world.isClient) {
                this.remove(RemovalReason.KILLED);
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

    public int getJumpDelay() {
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
                this.applyDamageEffects(this, entityIn);
            }
        }

    }

    protected float getActiveEyeHeight(EntityPose poseIn, EntityDimensions sizeIn) {
        return 0.625F * sizeIn.height;
    }

    public boolean canDamagePlayer() {
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

    public boolean makesSoundOnJump() {
        return true;
    }

    protected void jump() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x, (double) this.getJumpVelocity(), vec3d.z);
        this.velocityDirty = true;
    }

    public SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_SLIME_JUMP;
    }

    protected boolean spawnCustomParticles() {
        return false;
    }

}
 
