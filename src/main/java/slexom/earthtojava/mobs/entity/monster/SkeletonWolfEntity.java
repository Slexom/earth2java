package slexom.earthtojava.mobs.entity.monster;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SkeletonWolfEntity extends HostileEntity {

    protected static final TrackedData<Boolean> ANGRY = DataTracker.registerData(SkeletonWolfEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private float headRotationCourse;
    private float headRotationCourseOld;

    public SkeletonWolfEntity(EntityType<SkeletonWolfEntity> type, World world) {
        super(type, world);
        experiencePoints = 5;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createSkeletonWolfAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
    }

    protected void initGoals() {
        this.goalSelector.add(2, new AvoidSunlightGoal(this));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SKELETON_STEP, 0.35F, 1.0F);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGRY, false);
    }

    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("Angry", this.isAngry());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        this.setAngry(compound.getBoolean("Angry"));
    }

    @Environment(EnvType.CLIENT)
    public float getTailAngle() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else {
            return ((float) Math.PI / 5F);
        }
    }

    public boolean isAngry() {
        return this.dataTracker.get(ANGRY);
    }

    public void setAngry(boolean angry) {
        this.dataTracker.set(ANGRY, angry);
    }

    public void setTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setTarget(entitylivingbaseIn);
        this.setAngry(entitylivingbaseIn != null);
    }

    public void tickMovement() {
        if (this.isAlive() && this.isInDaylight()) {
            this.setOnFireFor(8);
        }
        super.tickMovement();
        if (!this.world.isClient && this.getTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }
    }

    public void tick() {
        super.tick();
        if (this.isAlive()) {
            this.headRotationCourseOld = this.headRotationCourse;
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }
    }

    public float getInterestedAngle(float p_70917_1_) {
        return MathHelper.lerp(p_70917_1_, this.headRotationCourseOld, this.headRotationCourse) * 0.15F * (float) Math.PI;
    }

    public boolean tryAttack(Entity entityIn) {
        return super.tryAttack(entityIn);
    }


}
 
