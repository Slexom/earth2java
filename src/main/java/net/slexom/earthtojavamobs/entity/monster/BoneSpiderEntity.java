package net.slexom.earthtojavamobs.entity.monster;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slexom.earthtojavamobs.entity.base.E2JBaseSpiderEntity;
import net.slexom.earthtojavamobs.entity.projectile.BoneShardEntity;

public class BoneSpiderEntity extends E2JBaseSpiderEntity<BoneSpiderEntity> implements IRangedAttackMob {

    public BoneSpiderEntity(EntityType<BoneSpiderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.0D, 20,40, 8.0F));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new BoneSpiderEntity.AttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        //this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        BoneShardEntity boneShard = new BoneShardEntity(this.world, this);
        double d0 = target.getPosYEye() - (double) 1.1F;
        double d1 = target.getPosX() - this.getPosX();
        double d2 = d0 - boneShard.getPosY();
        double d3 = target.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        boneShard.shoot(d1, d2 + (double) f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(boneShard);
    }

    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(BoneSpiderEntity spider) {
            super(spider, 1.0D, true);
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && !this.attacker.isBeingRidden();
        }

        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = this.attacker.getAttackTarget();
            if (livingentity == null) {
                this.attacker.getNavigator().clearPath();
                return false;
            }
            return super.shouldContinueExecuting();
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double) (3.0F + attackTarget.getWidth());
        }
    }


//
//    static class RangedAttackGoal extends Goal {
//        private final MobEntity entityHost;
//        private final IRangedAttackMob rangedAttackEntityHost;
//        private LivingEntity attackTarget;
//        private int rangedAttackTime = -1;
//        private final double entityMoveSpeed;
//        private int seeTime;
//        private final int attackIntervalMin;
//        private final int maxRangedAttackTime;
//        private final float attackRadius;
//        private final float maxAttackDistance;
//
//        public RangedAttackGoal(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
//            this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
//        }
//
//        public RangedAttackGoal(IRangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
//            if (!(attacker instanceof LivingEntity)) {
//                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
//            } else {
//                this.rangedAttackEntityHost = attacker;
//                this.entityHost = (MobEntity) attacker;
//                this.entityMoveSpeed = movespeed;
//                this.attackIntervalMin = p_i1650_4_;
//                this.maxRangedAttackTime = maxAttackTime;
//                this.attackRadius = maxAttackDistanceIn;
//                this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
//                this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
//            }
//        }
//
//        public boolean shouldExecute() {
//            LivingEntity livingentity = this.entityHost.getAttackTarget();
//            if (livingentity != null && livingentity.isAlive()) {
//                this.attackTarget = livingentity;
//                double d0 = this.entityHost.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getPosY(), this.attackTarget.getPosZ());
//                return !(Math.sqrt(d0) < 3.0D);
//            } else {
//                return false;
//            }
//        }
//
//        public boolean shouldContinueExecuting() {
//            return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
//        }
//
//        public void resetTask() {
//            this.attackTarget = null;
//            this.seeTime = 0;
//            this.rangedAttackTime = -1;
//            this.entityHost.getNavigator().clearPath();
//        }
//
//        public void tick() {
//            double d0 = this.entityHost.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getPosY(), this.attackTarget.getPosZ());
//            boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
//            if (flag) {
//                ++this.seeTime;
//            } else {
//                this.seeTime = 0;
//            }
//
//            if (!(d0 > (double) this.maxAttackDistance) && this.seeTime >= 5) {
//                this.entityHost.getNavigator().clearPath();
//            } else {
//                if (!(d0 > 6.0D)) {
//                    this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
//                }
//            }
//
//            this.entityHost.getLookController().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
//            if (--this.rangedAttackTime == 0) {
//                if (!flag) {
//                    return;
//                }
//                float f = MathHelper.sqrt(d0) / this.attackRadius;
//                float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
//                this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, lvt_5_1_);
//                this.rangedAttackTime = MathHelper.floor(f * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
//            } else if (this.rangedAttackTime < 0) {
//                float f2 = MathHelper.sqrt(d0) / this.attackRadius;
//                this.rangedAttackTime = MathHelper.floor(f2 * (float) (this.maxRangedAttackTime - this.attackIntervalMin) + (float) this.attackIntervalMin);
//            }
//
//        }
//    }

}
