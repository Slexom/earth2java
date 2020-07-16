package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

public class FurnaceGolemEntity extends IronGolemEntity {
    public static final TrackedData<Boolean> IS_ANGRY = DataTracker.registerData(FurnaceGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int attackTimer;

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public FurnaceGolemEntity(EntityType<? extends IronGolemEntity> type, World worldIn) {
        super(type, worldIn);
        experiencePoints = 5;
        setAiDisabled(false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.add(2, new MoveTowardsVillageGoal(this, 0.6D));
        this.goalSelector.add(3, new MoveThroughVillageGoal(this, 0.6D, false, 4, () -> {
            return false;
        }));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new DefendVillageTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(3, new FollowTargetGoal(this, MobEntity.class, 5, false, false, (p_213619_0_) -> p_213619_0_ instanceof IMob && !(p_213619_0_ instanceof CreeperEntity) && !(p_213619_0_ instanceof TropicalSlimeEntity)));
    }

    public boolean tryAttack(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte) 4);
        float f = (float) this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).getValue();
        float f1 = f > 0.0F ? f / 2.0F + (float) this.random.nextInt((int) f) : 0.0F;
        boolean flag = entityIn.damage(DamageSource.ON_FIRE, f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, 0.4D, 0.0D));
            this.dealDamage(this, entityIn);
        }
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.isAngry()) {
            float rand = new Random().nextFloat();
            if (rand > 0.80F && rand <= 0.83F) {
                int x = MathHelper.floor(this.getX());
                int y = MathHelper.floor(this.getY());
                int z = MathHelper.floor(this.getZ());
                BlockPos pos = new BlockPos(x, y - 0.2D, z);
                BlockPos posRandom = pos.add(new Random().nextInt(3) - 1, 0, new Random().nextInt(3) - 1);
                if (!this.world.isAirBlock(posRandom) && this.world.isAirBlock(posRandom.up())) {
                    this.world.setBlockState(posRandom.up(), Blocks.FIRE.getDefaultState(), 3);
                }
            }
        }
        if (this.isInWater()) {
            this.damage(DamageSource.DROWN, 5.0F);
        }

        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_ANGRY, false);
    }

    public boolean isAngry() {
        return this.dataTracker.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        this.dataTracker.set(IS_ANGRY, angry);
    }

    private static final class FollowTargetGoal extends net.minecraft.entity.ai.goal.FollowTargetGoal<LivingEntity> {
        FurnaceGolemEntity golem;

        public FollowTargetGoal(FurnaceGolemEntity entity, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate targetPredicate) {
            super(entity, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
            this.golem = entity;
        }

        public void start() {
            this.golem.setAngry(true);
            super.start();
        }

        public void resetTask() {
            this.golem.setAngry(false);
            super.resetTask();
        }
    }


    public class DefendVillageTargetGoal extends net.minecraft.entity.ai.goal.DefendVillageTargetGoal {
        private final FurnaceGolemEntity golem;
        private LivingEntity villageAgressorTarget;

        public DefendVillageTargetGoal(FurnaceGolemEntity ironGolemIn) {
            super(ironGolemIn);
            this.golem = ironGolemIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        public void start() {
            this.golem.setAngry(true);
            this.golem.setTarget(this.villageAgressorTarget);
            super.start();
        }

        public void resetTask() {
            this.golem.setAngry(false);
            super.resetTask();
        }
    }

}
