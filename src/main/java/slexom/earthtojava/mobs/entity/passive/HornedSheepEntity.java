package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.IPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import slexom.earthtojava.mobs.entity.base.E2JBaseSheepEntity;

import javax.annotation.Nullable;
import java.util.UUID;


public class HornedSheepEntity extends E2JBaseSheepEntity<HornedSheepEntity> {

    private EatGrassGoal eatGrassGoal;
    private static final TrackedData<Byte> DATA_FLAGS_ID = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.VARINT);
    private UUID lastHurtBy;


    public HornedSheepEntity(EntityType<? extends HornedSheepEntity> type, World world) {
        super(type, world);
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
        this.goalSelector.add(5, new ChargeGoal(this, 1.4D, true));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, (new AngerGoal(this)).setCallsForHelp());
        this.targetSelector.add(2, new AttackPlayerGoal(this));
    }

    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putInt("Anger", this.getAnger());
        if (this.lastHurtBy != null) {
            compound.putString("HurtBy", this.lastHurtBy.toString());
        } else {
            compound.putString("HurtBy", "");
        }
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        String s = compound.getString("HurtBy");
        if (!s.isEmpty()) {
            this.lastHurtBy = UUID.fromString(s);
            PlayerEntity playerentity = this.world.getPlayerByUuid(this.lastHurtBy);
            this.setRevengeTarget(playerentity);
            if (playerentity != null) {
                this.attackingPlayer = playerentity;
                this.recentlyHit = this.getRevengeTimer();
            }
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).getValue()));
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }
        return flag;
    }

    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        super.setRevengeTarget(livingBase);
        if (livingBase != null) {
            this.lastHurtBy = livingBase.getUniqueID();
        }
    }

    protected void mobTick() {
        if (this.isAngry()) {
            int i = this.getAnger();
            this.setAnger(i - 1);
            LivingEntity livingentity = this.getAttackTarget();
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
            boolean flag = this.isAngry() && this.getAttackTarget() != null && this.getAttackTarget().getDistanceSq(this) < 4.0D;
            this.setNearTarget(flag);
        }
    }

    private boolean isNearTarget() {
        return this.getSheepFlag();
    }

    private void setNearTarget(boolean p_226452_1_) {
        this.setSheepFlag(p_226452_1_);
    }

    private boolean isTooFar(BlockPos pos) {
        return !this.isWithinDistance(pos);
    }

    private void setSheepFlag(boolean p_226404_2_) {
        if (p_226404_2_) {
            this.dataTracker.set(DATA_FLAGS_ID, (byte) (this.dataTracker.get(DATA_FLAGS_ID) | 2));
        } else {
            this.dataTracker.set(DATA_FLAGS_ID, (byte) (this.dataTracker.get(DATA_FLAGS_ID) & ~2));
        }
    }

    private boolean getSheepFlag() {
        return (this.dataTracker.get(DATA_FLAGS_ID) & 2) != 0;
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    public ResourceLocation getLootTable() {
        if (this.getSheared()) {
            return this.getType().getLootTable();
        } else {
            switch (this.getFleeceColor()) {
                case WHITE:
                default:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/white");
                case ORANGE:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/orange");
                case MAGENTA:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/magenta");
                case LIGHT_BLUE:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/light_blue");
                case YELLOW:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/yellow");
                case LIME:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/lime");
                case PINK:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/pink");
                case GRAY:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/gray");
                case LIGHT_GRAY:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/light_gray");
                case CYAN:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/cyan");
                case PURPLE:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/purple");
                case BLUE:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/blue");
                case BROWN:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/brown");
                case GREEN:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/green");
                case RED:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/red");
                case BLACK:
                    return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/black");
            }
        }
    }

    public boolean setSheepAttacker(Entity attacker) {
        this.setAnger(400 + this.rand.nextInt(400));
        if (attacker instanceof LivingEntity) {
            this.setRevengeTarget((LivingEntity) attacker);
        }
        return true;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();
            if (!this.world.isClient && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity) && !this.isAIDisabled()) {
                this.setSheepAttacker(entity);
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    private boolean isWithinDistance(BlockPos pos) {
        return pos.withinDistance(new BlockPos(this), (double) 48);
    }

    static class AngerGoal extends HurtByTargetGoal {

        AngerGoal(HornedSheepEntity sheepIn) {
            super(sheepIn);
        }

        protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
            if (mobIn instanceof HornedSheepEntity && this.goalOwner.canEntityBeSeen(targetIn) && ((HornedSheepEntity) mobIn).setSheepAttacker(targetIn)) {
                mobIn.setAttackTarget(targetIn);
            }
        }

    }

    static class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        AttackPlayerGoal(HornedSheepEntity sheepEntity) {
            super(sheepEntity, PlayerEntity.class, true);
        }

        public boolean shouldExecute() {
            return this.canCharge() && super.shouldExecute();
        }

        public boolean shouldContinueExecuting() {
            boolean flag = this.canCharge();
            if (flag && this.goalOwner.getAttackTarget() != null) {
                return super.shouldContinueExecuting();
            } else {
                this.target = null;
                return false;
            }
        }

        private boolean canCharge() {
            HornedSheepEntity sheepEntity = (HornedSheepEntity) this.goalOwner;
            return sheepEntity.isAngry();
        }
    }

    class ChargeGoal extends MeleeAttackGoal {

        final HornedSheepEntity attacker;

        ChargeGoal(HornedSheepEntity creatureIn, double speedIn, boolean useLongMemory) {
            super(creatureIn, speedIn, useLongMemory);
            this.attacker = creatureIn;
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && this.attacker.isAngry();
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && this.attacker.isAngry();
        }
    }


}
