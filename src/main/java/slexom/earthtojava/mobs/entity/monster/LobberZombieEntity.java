package slexom.earthtojava.mobs.entity.monster;

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
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseZombieEntity;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;
import slexom.earthtojava.mobs.entity.projectile.RottenFleshProjectileEntity;

public class LobberZombieEntity extends E2JBaseZombieEntity implements RangedAttackMob {

    public LobberZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initCustomGoals() {
        this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.25D, 40, 10.0F));
        this.goalSelector.add(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(1, (new RevengeGoal(this)).setGroupRevenge(ZombifiedPiglinEntity.class));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, MerchantEntity.class, false));
        this.targetSelector.add(3, new FollowTargetGoal(this, IronGolemEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, FurnaceGolemEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, MelonGolemEntity.class, true));
        this.targetSelector.add(5, new FollowTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }


    @Override
    public void attack(LivingEntity target, float pullProgress) {
        RottenFleshProjectileEntity rottenFleshProjectileEntity = new RottenFleshProjectileEntity(this.world, this);
        double d0 = target.getEyeY() - (double) 1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - rottenFleshProjectileEntity.getY();
        double d3 = target.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        rottenFleshProjectileEntity.setVelocity(d1, d2 + (double) f, d3, 1.6F, 12.0F);
        this.swingHand(Hand.MAIN_HAND);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(rottenFleshProjectileEntity);
    }
}
