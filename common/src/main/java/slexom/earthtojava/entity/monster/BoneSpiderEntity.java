package slexom.earthtojava.entity.monster;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.ai.goal.BoneSpiderMeleeAttackGoal;
import slexom.earthtojava.entity.ai.pathing.ClimberNavigation;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class BoneSpiderEntity extends SpiderEntity implements RangedAttackMob {
	public final BlinkManager blinkManager;

	public BoneSpiderEntity(EntityType<BoneSpiderEntity> type, World worldIn) {
		super(type, worldIn);
		blinkManager = new BlinkManager();
		experiencePoints = 3;
		setAiDisabled(false);
	}

	public static DefaultAttributeContainer.Builder createBoneSpiderAttributes() {
		return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 32.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		blinkManager.tickBlink();
	}

	@Override
	protected EntityNavigation createNavigation(World world) {
		return new ClimberNavigation(this, world);
	}

	@Override
	protected void initGoals() {
		goalSelector.add(1, new SwimGoal(this));
		goalSelector.add(3, new ProjectileAttackGoal(this, 1.0D, 40, 12.0F));
		goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
		goalSelector.add(4, new BoneSpiderMeleeAttackGoal(this));
		goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
		goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.add(6, new LookAroundGoal(this));
		targetSelector.add(1, new RevengeGoal(this));
		targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public void shootAt(LivingEntity target, float pullProgress) {
		BoneShardEntity boneShard = new BoneShardEntity(getWorld(), this);
		double d0 = target.getEyeY() - 1.1D;
		double d1 = target.getX() - getX();
		double d2 = d0 - boneShard.getY();
		double d3 = target.getZ() - getZ();
		double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
		boneShard.setVelocity(d1, d2 + f, d3, 1.6F, 8.0F);
		playSound(SoundEventsInit.BONE_SPIDER_SPIT.get(), 1.0F, 1.2F / (getRandom().nextFloat() * 0.4F + 0.8F));
		getWorld().spawnEntity(boneShard);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEventsInit.BONE_SPIDER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEventsInit.BONE_SPIDER_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		playSound(SoundEventsInit.BONE_SPIDER_WALK.get(), 0.15F, 1.0F);
	}
}
