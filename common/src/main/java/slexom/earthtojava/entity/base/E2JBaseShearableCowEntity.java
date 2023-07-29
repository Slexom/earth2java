package slexom.earthtojava.entity.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.*;
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

public class E2JBaseShearableCowEntity extends E2JBaseCowEntity implements Shearable {

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
		eatGrassGoal = new EatGrassGoal(this);
		goalSelector.add(5, eatGrassGoal);
	}

	protected void mobTick() {
		eatGrassTimer = eatGrassGoal.getTimer();
		super.mobTick();
	}

	public void tickMovement() {
		if (getWorld().isClient) {
			eatGrassTimer = Math.max(0, eatGrassTimer - 1);
		}
		super.tickMovement();
	}

	@Environment(EnvType.CLIENT)
	public void handleStatus(byte status) {
		if (status == EntityStatuses.SET_SHEEP_EAT_GRASS_TIMER_OR_PRIME_TNT_MINECART) {
			eatGrassTimer = 40;
		} else {
			super.handleStatus(status);
		}
	}

	protected void initDataTracker() {
		super.initDataTracker();
		dataTracker.startTracking(isSheared, (byte) 0);
	}

	public boolean isSheared() {
		return (dataTracker.get(isSheared) & 16) != 0;
	}

	public void setSheared(boolean sheared) {
		byte b0 = dataTracker.get(isSheared);
		if (sheared) {
			dataTracker.set(isSheared, (byte) (b0 | 16));
		} else {
			dataTracker.set(isSheared, (byte) (b0 & -17));
		}
	}

	public void onEatingGrass() {
		setSheared(false);
		if (isBaby()) {
			growUp(30);
		}
	}

	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() instanceof ShearsItem) {
			if (!getWorld().isClient && isShearable()) {
				sheared(SoundCategory.PLAYERS);
				itemStack.damage(1, (LivingEntity) player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
				return ActionResult.SUCCESS;
			}
			return ActionResult.CONSUME;
		}
		return super.interactMob(player, hand);
	}

	public void sheared(SoundCategory shearedSoundCategory) {
		getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
		setSheared(true);
		int i = 1 + random.nextInt(3);
		for (int j = 0; j < i; ++j) {
			ItemEntity itemEntity = dropItem(wool.getItem(), 1);
			if (itemEntity != null) {
				itemEntity.setVelocity(itemEntity.getVelocity().add((random.nextFloat() - random.nextFloat()) * 0.1F, random.nextFloat() * 0.05F, (random.nextFloat() - random.nextFloat()) * 0.1F));
			}
		}
	}

	public boolean isShearable() {
		return isAlive() && !isSheared() && !isBaby();
	}

	public void writeCustomDataToNbt(NbtCompound compound) {
		super.writeCustomDataToNbt(compound);
		compound.putBoolean("Sheared", isSheared());
	}

	public void readCustomDataFromNbt(NbtCompound compound) {
		super.readCustomDataFromNbt(compound);
		setSheared(compound.getBoolean("Sheared"));
	}

	@Environment(EnvType.CLIENT)
	public float getNeckAngle(float delta) {
		if (eatGrassTimer <= 0) {
			return 0.0F;
		}
		if (eatGrassTimer >= 4 && eatGrassTimer <= 36) {
			return 1.0F;
		}
		return eatGrassTimer < 4 ? ((float) eatGrassTimer - delta) / 4.0F : -((float) (eatGrassTimer - 40) - delta) / 4.0F;
	}

	@Environment(EnvType.CLIENT)
	public float getHeadAngle(float delta) {
		if (eatGrassTimer > 4 && eatGrassTimer <= 36) {
			float f = ((float) (eatGrassTimer - 4) - delta) / 32.0F;
			return 0.62831855F + 0.21991149F * MathHelper.sin(f * 28.7F);
		}
		return eatGrassTimer > 0 ? 0.62831855F : getPitch() * 0.017453292F;
	}

	public Identifier getLootTableId() {
		if (isSheared()) {
			return new Identifier("minecraft", "entities/cow");
		}
		return getType().getLootTableId();
	}
}
