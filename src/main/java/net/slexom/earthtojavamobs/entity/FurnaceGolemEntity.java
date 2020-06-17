package net.slexom.earthtojavamobs.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

public class FurnaceGolemEntity extends IronGolemEntity {
    public static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(FurnaceGolemEntity.class, DataSerializers.BOOLEAN);
    private int attackTimer;

    public FurnaceGolemEntity(EntityType<? extends IronGolemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new MoveTowardsVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 0.6D, false, 4, () -> {
            return false;
        }));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new DefendVillageTargetGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new FurnaceGolemEntity.NearestAttackableTargetGoal(this, MobEntity.class, 5, false, false, (p_213619_0_) -> p_213619_0_ instanceof IMob && !(p_213619_0_ instanceof CreeperEntity) && !(p_213619_0_ instanceof TropicalSlimeEntity)));
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        this.attackTimer = 10;
        this.world.setEntityState(this, (byte) 4);
        float f = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
        float f1 = f > 0.0F ? f / 2.0F + (float) this.rand.nextInt((int) f) : 0.0F;
        boolean flag = entityIn.attackEntityFrom(DamageSource.ON_FIRE, f1);
        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, 0.4D, 0.0D));
            this.applyEnchantments(this, entityIn);
        }
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    public void livingTick() {
        super.livingTick();
        if (this.isAngry()) {
            float rand = new Random().nextFloat();
            if (rand > 0.80F && rand <= 0.83F) {
                int x = MathHelper.floor(this.getPosX());
                int y = MathHelper.floor(this.getPosY());
                int z = MathHelper.floor(this.getPosZ());
                BlockPos pos = new BlockPos(x, y - 0.2D, z);
                BlockPos posRandom = pos.add(new Random().nextInt(3) - 1, 0, new Random().nextInt(3) - 1);
                if (this.world.isAirBlock(posRandom.up())) {
                    this.world.setBlockState(posRandom.up(), Blocks.FIRE.getDefaultState(), 3);
                }
            }
        }
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_ANGRY, false);
    }

    public boolean isAngry() {
        return this.dataManager.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        this.dataManager.set(IS_ANGRY, angry);
    }

    private static final class NearestAttackableTargetGoal extends net.minecraft.entity.ai.goal.NearestAttackableTargetGoal<LivingEntity> {
        FurnaceGolemEntity golem;

        public NearestAttackableTargetGoal(FurnaceGolemEntity entity, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate targetPredicate) {
            super(entity, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
            this.golem = entity;
        }

        public void startExecuting() {
            this.golem.setAngry(true);
            super.startExecuting();
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

        public void startExecuting() {
            this.golem.setAngry(true);
            this.golem.setAttackTarget(this.villageAgressorTarget);
            super.startExecuting();
        }

        public void resetTask() {
            this.golem.setAngry(false);
            super.resetTask();
        }
    }

}
