package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.UUID;


public class HornedSheepEntity extends SheepEntity {

    private EatGrassGoal eatGrassGoal;
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.createKey(HornedSheepEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(HornedSheepEntity.class, DataSerializers.VARINT);
    private UUID lastHurtBy;


    public HornedSheepEntity(EntityType<? extends HornedSheepEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        ;
        setNoAI(false);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_FLAGS_ID, (byte) 0);
        this.dataManager.register(ANGER_TIME, 0);
    }

    protected void registerGoals() {
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new HornedSheepEntity.ChargeGoal(this, (double) 1.4F, true));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new AngerGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new HornedSheepEntity.AttackPlayerGoal(this));
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Anger", this.getAnger());
        if (this.lastHurtBy != null) {
            compound.putString("HurtBy", this.lastHurtBy.toString());
        } else {
            compound.putString("HurtBy", "");
        }
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
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
        boolean flag = entityIn.attackEntityFrom(DamageSource.func_226252_a_(this), (float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
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

    protected void updateAITasks() {
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
        return this.dataManager.get(ANGER_TIME);
    }

    private void setAnger(int angerTime) {
        this.dataManager.set(ANGER_TIME, angerTime);
    }

    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
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
            this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) | 2));
        } else {
            this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) & ~2));
        }
    }

    private boolean getSheepFlag() {
        return (this.dataManager.get(DATA_FLAGS_ID) & 2) != 0;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
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

    @Override
    public HornedSheepEntity createChild(AgeableEntity ageable) {
        return (HornedSheepEntity) getType().create(this.world);
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
            if (!this.world.isRemote && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity) && !this.isAIDisabled()) {
                this.setSheepAttacker(entity);
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
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
        AttackPlayerGoal(HornedSheepEntity beeIn) {
            super(beeIn, PlayerEntity.class, true);
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
        ChargeGoal(CreatureEntity creatureIn, double speedIn, boolean useLongMemory) {
            super(creatureIn, speedIn, useLongMemory);
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && HornedSheepEntity.this.isAngry();
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && HornedSheepEntity.this.isAngry();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
