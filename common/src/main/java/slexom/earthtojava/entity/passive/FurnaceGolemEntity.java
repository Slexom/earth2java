package slexom.earthtojava.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.ai.goal.FurnaceGolemActiveTargetGoal;
import slexom.earthtojava.entity.ai.goal.FurnaceGolemDefendVillageTargetGoal;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.Random;

public class FurnaceGolemEntity extends IronGolemEntity {
    public static final TrackedData<Boolean> IS_ANGRY = DataTracker.registerData(FurnaceGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int attackTimer;

    public BlinkManager blinkManager;

    public FurnaceGolemEntity(EntityType<? extends IronGolemEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        experiencePoints = 5;
        setAiDisabled(false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6D, false));
        this.goalSelector.add(4, new IronGolemWanderAroundGoal(this, 0.6D));
        this.goalSelector.add(5, new IronGolemLookGoal(this));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new FurnaceGolemDefendVillageTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(3, new FurnaceGolemActiveTargetGoal(this, MobEntity.class, 5, false, false, (p_213619_0_) -> p_213619_0_ instanceof Monster && !(p_213619_0_ instanceof CreeperEntity) && !(p_213619_0_ instanceof TropicalSlimeEntity)));
    }

    public boolean tryAttack(Entity entityIn) {
        this.attackTimer = 10;
        this.world.sendEntityStatus(this, (byte) 4);
        float f = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float f1 = f > 0.0F ? f / 2.0F + (float) this.random.nextInt((int) f) : 0.0F;
        boolean flag = entityIn.damage(DamageSource.ON_FIRE, f1);
        if (flag) {
            entityIn.setVelocity(entityIn.getVelocity().add(0.0D, 0.4D, 0.0D));
            this.applyDamageEffects(this, entityIn);
        }
        this.playSound(SoundEventsInit.FURNACE_GOLEM_ATTACK.get(), 1.0F, 1.0F);
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
                if (!this.world.isAir(posRandom) && this.world.isAir(posRandom.up())) {
                    this.world.setBlockState(posRandom.up(), Blocks.FIRE.getDefaultState(), 3);
                }
            }
        }
        if (this.isInsideWaterOrBubbleColumn()) {
            this.damage(DamageSource.DROWN, 5.0F);
        }
        blinkManager.tickBlink();
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

}
