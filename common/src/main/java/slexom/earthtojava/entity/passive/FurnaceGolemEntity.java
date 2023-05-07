package slexom.earthtojava.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
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
import slexom.earthtojava.entity.ai.goal.TrackFurnaceGolemTargetGoal;
import slexom.earthtojava.init.SoundEventsInit;

public class FurnaceGolemEntity extends IronGolemEntity {
    public static final TrackedData<Boolean> IS_ANGRY = DataTracker.registerData(FurnaceGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final BlinkManager blinkManager;
    private int attackTimer;

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
        this.targetSelector.add(1, new TrackFurnaceGolemTargetGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(3, new FurnaceGolemActiveTargetGoal(this, MobEntity.class, 5, false, false, entity -> entity instanceof Monster && !(entity instanceof CreeperEntity) && !(entity instanceof TropicalSlimeEntity)));
    }

    @Override
    public boolean tryAttack(Entity target) {
        this.attackTimer = 10;
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        float attackDamage = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float f1 = attackDamage > 0.0F ? attackDamage / 2.0F + this.random.nextInt((int) attackDamage) : 0.0F;
        boolean flag = target.damage(target.getDamageSources().onFire(), f1);
        if (flag) {
            target.setVelocity(target.getVelocity().add(0.0D, 0.4D, 0.0D));
            this.applyDamageEffects(this, target);
        }
        this.playSound(SoundEventsInit.FURNACE_GOLEM_ATTACK.get(), 1.0F, 1.0F);
        return flag;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.isAngry()) {
            float rand = this.random.nextFloat();
            if (rand > 0.80F && rand <= 0.83F) {
                int x = MathHelper.floor(this.getX());
                int y = MathHelper.floor(this.getY());
                int z = MathHelper.floor(this.getZ());
                BlockPos pos = new BlockPos(x, y, z);
                BlockPos posRandom = pos.add(this.random.nextInt(3) - 1, 0, this.random.nextInt(3) - 1);
                if (!this.getWorld().isAir(posRandom) && this.getWorld().isAir(posRandom.up())) {

                    this.getWorld().setBlockState(posRandom.up(), Blocks.FIRE.getDefaultState(), Block.NOTIFY_ALL);
                }
            }
        }
        if (this.isInsideWaterOrBubbleColumn()) {
            this.damage(this.getDamageSources().drown(), 5.0F);
        }
        blinkManager.tickBlink();
    }

    @Override
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
