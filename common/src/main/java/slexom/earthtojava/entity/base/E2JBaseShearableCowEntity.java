package slexom.earthtojava.entity.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class E2JBaseShearableCowEntity<T extends E2JBaseCowEntity> extends E2JBaseCowEntity implements Shearable {

    private static final TrackedData<Byte> isSheared = DataTracker.registerData(E2JBaseShearableCowEntity.class, TrackedDataHandlerRegistry.BYTE);
    private final ItemStack wool;
    private int eatGrassTimer;
    private EatGrassGoal eatGrassGoal;

    public E2JBaseShearableCowEntity(EntityType<? extends E2JBaseShearableCowEntity> type, World world, ItemStack wool) {
        super(type, world);
        this.wool = wool;
        experiencePoints = 3;
        setAiDisabled(false);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(5, this.eatGrassGoal);
    }

    protected void mobTick() {
        this.eatGrassTimer = this.eatGrassGoal.getTimer();
        super.mobTick();
    }

    public void tickMovement() {
        if (this.world.isClient) {
            this.eatGrassTimer = Math.max(0, this.eatGrassTimer - 1);
        }
        super.tickMovement();
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 10) {
            this.eatGrassTimer = 40;
        } else {
            super.handleStatus(id);
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(isSheared, (byte) 0);
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

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof ShearsItem) {
            if (!this.world.isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                itemStack.damage(1, (LivingEntity) player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(this.wool.getItem(), 1);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
            }
        }
    }

    public boolean isShearable() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putBoolean("Sheared", this.isSheared());
    }

    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    @Environment(EnvType.CLIENT)
    public float getNeckAngle(float delta) {
        if (this.eatGrassTimer <= 0) {
            return 0.0F;
        } else if (this.eatGrassTimer >= 4 && this.eatGrassTimer <= 36) {
            return 1.0F;
        } else {
            return this.eatGrassTimer < 4 ? ((float) this.eatGrassTimer - delta) / 4.0F : -((float) (this.eatGrassTimer - 40) - delta) / 4.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public float getHeadAngle(float delta) {
        if (this.eatGrassTimer > 4 && this.eatGrassTimer <= 36) {
            float f = ((float) (this.eatGrassTimer - 4) - delta) / 32.0F;
            return 0.62831855F + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.eatGrassTimer > 0 ? 0.62831855F : this.getPitch() * 0.017453292F;
        }
    }

    public Identifier getLootTableId() {
        if (this.isSheared()) {
            return new Identifier("minecraft", "entities/cow");
        } else {
            return this.getType().getLootTableId();
        }
    }
}
