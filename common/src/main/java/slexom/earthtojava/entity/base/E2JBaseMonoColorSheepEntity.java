package slexom.earthtojava.entity.base;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public class E2JBaseMonoColorSheepEntity extends E2JBaseSheepEntity implements Shearable {

	private static final TrackedData<Byte> isSheared = DataTracker.registerData(E2JBaseMonoColorSheepEntity.class, TrackedDataHandlerRegistry.BYTE);
	private final ItemStack wool;

	public E2JBaseMonoColorSheepEntity(EntityType<? extends E2JBaseSheepEntity> type, World world, ItemStack wool) {
		super(type, world);
		this.wool = wool;
		experiencePoints = 3;
		setAiDisabled(false);
	}

	@Override
	public Identifier getLootTableId() {
		if (isSheared()) {
			return new Identifier("minecraft", "entities/sheep");
		}
		return getType().getLootTableId();
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

	public boolean isShearable() {
		return isAlive() && !isSheared() && !isBaby();
	}

	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() instanceof ShearsItem) {
			if (!getWorld().isClient && isShearable()) {
				sheared(SoundCategory.PLAYERS);
				itemStack.damage(1, player, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));
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

}
