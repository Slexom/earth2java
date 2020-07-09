package slexom.earthtojava.mobs.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class E2JOneColorSheepEntity<T extends AnimalEntity> extends AnimalEntity implements net.minecraftforge.common.IForgeShearable {

    private static final DataParameter<Byte> isSheared = EntityDataManager.createKey(E2JOneColorSheepEntity.class, DataSerializers.BYTE);

    private int sheepTimer;
    private EatGrassGoal eatGrassGoal;
    private ItemStack wool;
    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JOneColorSheepEntity(EntityType<? extends AnimalEntity> type, World world, ItemStack wool) {
        super(type, world);
        this.wool = wool;
        experienceValue = 3;
        setNoAI(false);
    }

    protected void registerGoals() {
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }


    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233818_a_, 8.0D).func_233815_a_(Attributes.field_233821_d_, 0.23F);
    }

    protected void updateAITasks() {
        this.sheepTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }

    public void livingTick() {
        if (this.world.isRemote) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        super.livingTick();
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

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.sheepTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(isSheared, (byte) 0);
    }

    @Override
    public T createChild(AgeableEntity ageable) {
        return (T) getType().create(this.world);
    }

    public boolean getSheared() {
        return (this.dataManager.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = this.dataManager.get(isSheared);
        if (sheared) {
            this.dataManager.set(isSheared, (byte) (b0 | 16));
        } else {
            this.dataManager.set(isSheared, (byte) (b0 & -17));
        }
    }

    public void eatGrassBonus() {
        this.setSheared(false);
        if (this.isChild()) {
            this.addGrowth(30);
        }
    }

    public boolean isShearable(@javax.annotation.Nonnull ItemStack item, World world, BlockPos pos) {
        return this.isAlive() && !this.getSheared() && !this.isChild();
    }

    public java.util.List<ItemStack> onSheared(@Nullable PlayerEntity player, @javax.annotation.Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!this.world.isRemote) {
            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);
            for (int j = 0; j < i; ++j) {
                items.add(this.wool);
            }
            return items;
        }
        return java.util.Collections.emptyList();
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Sheared", this.getSheared());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_) {
        if (this.sheepTimer <= 0) {
            return 0.0F;
        } else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
            return 1.0F;
        } else {
            return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F : -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_) {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
            float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * ((float) Math.PI / 180F);
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.35F, 1.0F);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
