package slexom.earthtojava.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.MoolipPlaceBlockGoal;
import slexom.earthtojava.entity.base.E2JBaseCowEntity;
import slexom.earthtojava.init.BlockInit;

public class MoolipEntity extends E2JBaseCowEntity implements Shearable {

	public MoolipEntity(EntityType<MoolipEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		goalSelector.add(8, new MoolipPlaceBlockGoal(this));
	}

	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() instanceof ShearsItem && isShearable()) {
			sheared(SoundCategory.PLAYERS);
			if (!getWorld().isClient) {
				itemStack.damage(1, player, ((playerEntity) -> playerEntity.sendToolBreakStatus(hand)));
			}
			return ActionResult.success(getWorld().isClient);
		}
		return super.interactMob(player, hand);
	}

	public void sheared(SoundCategory shearedSoundCategory) {
		getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
		if (getWorld().isClient()) return;

		CowEntity cowEntity = EntityType.COW.create(getWorld());
		if (cowEntity == null) return;

		((ServerWorld) getWorld()).spawnParticles(ParticleTypes.EXPLOSION, getX(), getBodyY(0.5D), getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
		discard();
		cowEntity.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), getPitch());
		cowEntity.setHealth(getHealth());
		cowEntity.bodyYaw = bodyYaw;
		if (hasCustomName()) {
			cowEntity.setCustomName(getCustomName());
			cowEntity.setCustomNameVisible(isCustomNameVisible());
		}
		if (isPersistent()) {
			cowEntity.setPersistent();
		}
		cowEntity.setInvulnerable(isInvulnerable());
		getWorld().spawnEntity(cowEntity);
		for (int i = 0; i < 5; ++i) {
			getWorld().spawnEntity(new ItemEntity(getWorld(), getX(), getBodyY(1.0D), getZ(), new ItemStack(BlockInit.PINK_DAISY.get())));
		}
	}

	public boolean isShearable() {
		return isAlive() && !isBaby();
	}

}