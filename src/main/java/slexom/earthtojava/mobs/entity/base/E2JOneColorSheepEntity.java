package slexom.earthtojava.mobs.entity.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class E2JOneColorSheepEntity<T extends AnimalEntity> extends AnimalEntity {

    private static final TrackedData<Byte> isSheared = DataTracker.registerData(E2JOneColorSheepEntity.class, TrackedDataHandlerRegistry.BYTE);

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
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createSheepAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D);
    }

    protected void initGoals() {
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.1D, Ingredient.ofItems(Items.WHEAT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, this.eatGrassGoal);
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    protected void mobTick() {
        this.sheepTimer = this.eatGrassGoal.getTimer();
        super.mobTick();
    }

    public void tickMovement() {
        if (this.world.isClient) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        super.tickMovement();
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

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 10) {
            this.sheepTimer = 40;
        } else {
            super.handleStatus(id);
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(isSheared, (byte) 0);
    }

    @Override
    public T createChild(PassiveEntity ageable) {
        return (T) getType().create(this.world);
    }

    public boolean isSheared() {
        return (this.dataTracker.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = this.dataTracker.get(isSheared);
        if (sheared) {
            this.dataTracker.set(isSheared, (byte) (b0 | 16));
        } else {
            this.dataTracker.set(isSheared, (byte) (b0 & -17));
        }
    }

    public void onEatingGrass() {
        this.setSheared(false);
        if (this.isBaby()) {
            this.growUp(30);
        }
    }

    public boolean isShearable() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.SHEARS) {
            if (!this.world.isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                itemStack.damage(1, player, (playerEntity) -> {
                    playerEntity.sendToolBreakStatus(hand);
                });
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity((PlayerEntity) null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(this.wool.getItem(), 1);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double) (this.random.nextFloat() * 0.05F), (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }

    }


    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("Sheared", this.isSheared());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    @Environment(EnvType.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_) {
        if (this.sheepTimer <= 0) {
            return 0.0F;
        } else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
            return 1.0F;
        } else {
            return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F : -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_) {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
            float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.pitch * ((float) Math.PI / 180F);
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


}
