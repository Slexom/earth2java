package slexom.earthtojava.mobs.entity.monster;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.init.SoundEventsInit;

import javax.annotation.Nullable;
import java.util.UUID;

public class SkeletonWolfEntity extends HostileEntity implements Angerable {

    protected static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(WolfEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);

    private float headRotationCourse;
    private float headRotationCourseOld;
    private UUID targetUuid;

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
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    protected SoundEvent getAmbientSound() {
        if (this.hasAngerTime()) {
            return SoundEventsInit.SKELETON_WOLF_GROWL;
        } else if (this.random.nextInt(3) == 0 && this.getHealth() < 10.0F) {
            return SoundEventsInit.SKELETON_WOLF_WHINE;
        } else {
            return SoundEventsInit.SKELETON_WOLF_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEventsInit.SKELETON_WOLF_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEventsInit.SKELETON_WOLF_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEventsInit.SKELETON_WOLF_STEP, 0.35F, 1.0F);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGER_TIME, 0);
    }

    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        this.writeAngerToNbt(compound);
    }

    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.readAngerFromNbt(this.world, compound);
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
        return this.getAngerTime() > 0;
    }

    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    public void setAngerTime(int ticks) {
        this.dataTracker.set(ANGER_TIME, ticks);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.isAlive() && this.isAffectedByDaylight()) {
            this.setOnFireFor(8);
        }
        if (!this.world.isClient) {
            this.tickAngerLogic((ServerWorld) this.world, true);
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
 
