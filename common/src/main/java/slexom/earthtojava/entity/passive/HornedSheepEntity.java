package slexom.earthtojava.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.HornedSheepActiveTargetGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepMeleeAttackGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepRevengeGoal;
import slexom.earthtojava.entity.base.E2JBaseSheepEntity;

import javax.annotation.Nullable;
import java.util.UUID;


public class HornedSheepEntity extends E2JBaseSheepEntity implements Angerable, Shearable {

    private static final TrackedData<Byte> DATA_FLAGS_ID = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private EatGrassGoal eatGrassGoal;
    private UUID lastHurtBy;

    public HornedSheepEntity(EntityType<? extends HornedSheepEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createHornedSheepAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_FLAGS_ID, (byte) 0);
        this.dataTracker.startTracking(ANGER_TIME, 0);
    }

    protected void initGoals() {
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.1D, Ingredient.ofItems(Items.WHEAT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.add(4, this.eatGrassGoal);
        this.goalSelector.add(5, new HornedSheepMeleeAttackGoal(this, 1.4D, true));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, (new HornedSheepRevengeGoal(this)).setGroupRevenge());
        this.targetSelector.add(2, new HornedSheepActiveTargetGoal(this));
    }

    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        this.writeAngerToNbt(compound);
    }

    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.readAngerFromNbt(this.world, compound);
    }

    public boolean tryAttack(Entity entityIn) {
        boolean flag = entityIn.damage(DamageSource.mob(this), (float) ((int) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));
        if (flag) {
            this.applyDamageEffects(this, entityIn);
        }
        return flag;
    }

    public void setAttacker(@Nullable LivingEntity livingBase) {
        super.setAttacker(livingBase);
        if (livingBase != null) {
            this.lastHurtBy = livingBase.getUuid();
        }
    }

    protected void mobTick() {
        if (this.isAngry()) {
            int i = this.getAnger();
            this.setAnger(i - 1);
            LivingEntity livingentity = this.getTarget();
            if (i == 0 && livingentity != null) {
                this.setSheepAttacker(livingentity);
            }
        }
    }

    public boolean isAngry() {
        return this.getAnger() > 0;
    }

    private int getAnger() {
        return this.dataTracker.get(ANGER_TIME);
    }

    private void setAnger(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    public void tickMovement() {
        super.tickMovement();
        if (!this.world.isClient) {
            boolean flag = this.isAngry() && this.getTarget() != null && this.getTarget().squaredDistanceTo(this) < 4.0D;
            this.setNearTarget(flag);
        }
    }

    private void setNearTarget(boolean p_226452_1_) {
        this.setSheepFlag(p_226452_1_);
    }

    private void setSheepFlag(boolean p_226404_2_) {
        if (p_226404_2_) {
            this.dataTracker.set(DATA_FLAGS_ID, (byte) (this.dataTracker.get(DATA_FLAGS_ID) | 2));
        } else {
            this.dataTracker.set(DATA_FLAGS_ID, (byte) (this.dataTracker.get(DATA_FLAGS_ID) & ~2));
        }
    }

    public Identifier getLootTableId() {
        if (this.isSheared()) {
            return this.getType().getLootTableId();
        } else {
            switch (this.getColor()) {
                case WHITE:
                default:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/white");
                case ORANGE:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/orange");
                case MAGENTA:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/magenta");
                case LIGHT_BLUE:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/light_blue");
                case YELLOW:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/yellow");
                case LIME:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/lime");
                case PINK:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/pink");
                case GRAY:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/gray");
                case LIGHT_GRAY:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/light_gray");
                case CYAN:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/cyan");
                case PURPLE:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/purple");
                case BLUE:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/blue");
                case BROWN:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/brown");
                case GREEN:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/green");
                case RED:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/red");
                case BLACK:
                    return new Identifier("earthtojavamobs", "entities/horned_sheep/black");
            }
        }
    }

    public boolean setSheepAttacker(Entity attacker) {
        this.setAnger(400 + this.random.nextInt(400));
        if (attacker instanceof LivingEntity) {
            this.setAttacker((LivingEntity) attacker);
        }
        return true;
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getAttacker();
            if (!this.world.isClient && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canSee(entity) && !this.isAiDisabled()) {
                this.setSheepAttacker(entity);
            }
            return super.damage(source, amount);
        }
    }

    private boolean isWithinDistance(BlockPos pos) {
        return pos.isWithinDistance(new BlockPos(this.getPos()), (double) 48);
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int ticks) {
        this.dataTracker.set(ANGER_TIME, ticks);
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.lastHurtBy;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.lastHurtBy = uuid;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }


}
