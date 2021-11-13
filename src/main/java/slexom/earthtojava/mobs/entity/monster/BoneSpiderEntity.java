package slexom.earthtojava.mobs.entity.monster;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.ai.goal.BoneSpiderMeleeAttackGoal;
import slexom.earthtojava.mobs.entity.ai.pathing.ClimberNavigation;
import slexom.earthtojava.mobs.entity.base.E2JBaseSpiderEntity;
import slexom.earthtojava.mobs.entity.projectile.BoneShardEntity;
import slexom.earthtojava.mobs.init.SoundEventsInit;

public class BoneSpiderEntity extends E2JBaseSpiderEntity<BoneSpiderEntity> implements RangedAttackMob {

    public BoneSpiderEntity(EntityType<BoneSpiderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static DefaultAttributeContainer.Builder createBoneSpiderAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
    }

    protected EntityNavigation createNavigation(World world) {
        return new ClimberNavigation(this, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(3, new ProjectileAttackGoal(this, 1.0D, 40, 12.0F));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(4, new BoneSpiderMeleeAttackGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.ARTHROPOD;
    }

    @Override
    public void attack(LivingEntity target, float distanceFactor) {
        BoneShardEntity boneShard = new BoneShardEntity(this.world, this);
        double d0 = target.getEyeY() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - boneShard.getY();
        double d3 = target.getZ() - this.getZ();
        double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
        boneShard.setVelocity(d1, d2 + f, d3, 1.6F, 8.0F);
        this.playSound(SoundEventsInit.BONE_SPIDER_SPIT, 1.0F, 1.2F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(boneShard);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEventsInit.BONE_SPIDER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEventsInit.BONE_SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEventsInit.BONE_SPIDER_WALK, 0.15F, 1.0F);
    }
}
