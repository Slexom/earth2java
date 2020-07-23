package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import slexom.earthtojava.mobs.entity.base.E2JBaseSheepEntity;

import javax.annotation.Nullable;
import java.util.UUID;


public class HornedSheepEntity extends E2JBaseSheepEntity<HornedSheepEntity> implements IAngerable {

    private EatGrassGoal eatGrassGoal;
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.createKey(HornedSheepEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(HornedSheepEntity.class, DataSerializers.VARINT);
    private UUID lastHurtBy;

    private static final RangedInteger field_234180_bw_ = TickRangeConverter.convertRange(20, 39);

    public HornedSheepEntity(EntityType<HornedSheepEntity> type, World world) {
        super(type, world);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_FLAGS_ID, (byte) 0);
        this.dataManager.register(ANGER_TIME, 0);
    }

    protected void registerGoals() {
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new HornedSheepEntity.ChargeGoal(this, 1.4D, true));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(4, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new AngerGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new HornedSheepEntity.AttackPlayerGoal(this));
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.func_233682_c_(compound);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.func_241358_a_((ServerWorld) this.world, compound);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.func_233637_b_(Attributes.ATTACK_DAMAGE)));//ATTACK
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }
        return flag;
    }

    @Override
    public int func_230256_F__() {
        return this.dataManager.get(ANGER_TIME);
    }

    @Override
    public void func_230260_a__(int value) {
        this.dataManager.set(ANGER_TIME, value);
    }

    @Nullable
    @Override
    public UUID func_230257_G__() {
        return this.lastHurtBy;
    }

    @Override
    public void func_230259_a_(@Nullable UUID uuid) {
        this.lastHurtBy = uuid;
    }

    @Override
    public void func_230258_H__() {
        this.func_230260_a__(field_234180_bw_.func_233018_a_(this.rand));
    }

    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        super.setRevengeTarget(livingBase);
        if (livingBase != null) {
            this.lastHurtBy = livingBase.getUniqueID();
        }
    }

    protected void updateAITasks() {
        if (!this.world.isRemote) {
            this.func_241359_a_((ServerWorld) this.world, false);
        }
    }

    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            boolean flag = this.func_233678_J__() && this.getAttackTarget() != null && this.getAttackTarget().getDistanceSq(this) < 4.0D;
            this.setNearTarget(flag);
        }
    }

    private void setNearTarget(boolean p_226452_1_) {
        this.setSheepFlag(p_226452_1_);
    }


    private void setSheepFlag(boolean p_226404_2_) {
        if (p_226404_2_) {
            this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) | 2));
        } else {
            this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) & ~2));
        }
    }

//    private boolean getSheepFlag() {
//        return (this.dataManager.get(DATA_FLAGS_ID) & 2) != 0;
//    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.MAX_HEALTH, 8.0D)
                .func_233815_a_(Attributes.FOLLOW_RANGE, 48.0D)
                .func_233815_a_(Attributes.MOVEMENT_SPEED, 0.23D)
                .func_233815_a_(Attributes.ATTACK_DAMAGE, 2.0D);
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
        this.func_230260_a__(400 + this.rand.nextInt(400));
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

    private boolean isWithinDistance(BlockPos pos) {
        return pos.withinDistance(new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), (double) 48);
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
            super(sheepEntity, PlayerEntity.class, 10, true, false, sheepEntity::func_233680_b_);
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
            return sheepEntity.func_233678_J__();
        }
    }

    class ChargeGoal extends MeleeAttackGoal {

        ChargeGoal(HornedSheepEntity creatureIn, double speedIn, boolean useLongMemory) {
            super(creatureIn, speedIn, useLongMemory);
        }

        public boolean shouldExecute() {
            return super.shouldExecute() && HornedSheepEntity.this.func_233678_J__();
        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && HornedSheepEntity.this.func_233678_J__();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
