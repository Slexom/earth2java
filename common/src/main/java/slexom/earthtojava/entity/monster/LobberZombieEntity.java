package slexom.earthtojava.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slexom.earthtojava.entity.base.E2JBaseZombieEntity;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class LobberZombieEntity extends E2JBaseZombieEntity implements RangedAttackMob {

	public LobberZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
		super(entityType, world);
	}

	protected void initCustomGoals() {
		goalSelector.add(2, new ProjectileAttackGoal(this, 1.25D, 40, 10.0F));
		goalSelector.add(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
		targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge(ZombifiedPiglinEntity.class));
		targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
		targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, false));
		targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
		targetSelector.add(3, new ActiveTargetGoal<>(this, FurnaceGolemEntity.class, true));
		targetSelector.add(3, new ActiveTargetGoal<>(this, MelonGolemEntity.class, true));
		targetSelector.add(5, new ActiveTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
	}


	@Override
	public void attack(LivingEntity target, float pullProgress) {
		RottenFleshProjectileEntity rottenFleshProjectileEntity = new RottenFleshProjectileEntity(getWorld(), this);
		double d0 = target.getEyeY() - 1.1D;
		double d1 = target.getX() - getX();
		double d2 = d0 - rottenFleshProjectileEntity.getY();
		double d3 = target.getZ() - getZ();
		double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
		rottenFleshProjectileEntity.setVelocity(d1, d2 + f, d3, 1.6F, 12.0F);
		swingHand(Hand.MAIN_HAND);
		playSound(SoundEventsInit.LOBBER_ZOMBIE_ATTACK.get(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
		getWorld().spawnEntity(rottenFleshProjectileEntity);
	}
}
