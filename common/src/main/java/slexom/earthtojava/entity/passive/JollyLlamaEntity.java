package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.ai.goal.JollyLlamaEatFernGoal;
import slexom.earthtojava.entity.base.E2JBaseLlamaEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class JollyLlamaEntity extends E2JBaseLlamaEntity {

	private int eatFernTimer;
	private JollyLlamaEatFernGoal eatFernGoal;

	public JollyLlamaEntity(EntityType<JollyLlamaEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void initGoals() {
		eatFernGoal = new JollyLlamaEatFernGoal(this);
		goalSelector.add(0, new SwimGoal(this));
		goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
		goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
		goalSelector.add(2, new FormCaravanGoal(this, 2.1D));
		goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
		goalSelector.add(2, eatFernGoal);
		goalSelector.add(5, new FollowParentGoal(this, 1.0D));
		goalSelector.add(6, new WanderAroundFarGoal(this, 0.7D));
		goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.add(8, new LookAroundGoal(this));
	}

	protected void mobTick() {
		eatFernTimer = eatFernGoal.getTimer();
		super.mobTick();
	}

	public void tickMovement() {
		if (getWorld().isClient) {
			eatFernTimer = Math.max(0, eatFernTimer - 1);
		}
		super.tickMovement();
	}

	@Environment(EnvType.CLIENT)
	public void handleStatus(byte status) {
		if (status == 10) {
			eatFernTimer = 40;
		} else {
			super.handleStatus(status);
		}

	}

	@Environment(EnvType.CLIENT)
	public float getNeckAngle(float delta) {
		if (eatFernTimer <= 0) {
			return 0.0F;
		}
		if (eatFernTimer >= 4 && eatFernTimer <= 36) {
			return 1.0F;
		}
		return eatFernTimer < 4 ? ((float) eatFernTimer - delta) / 4.0F : -((float) (eatFernTimer - 40) - delta) / 4.0F;
	}

	@Environment(EnvType.CLIENT)
	public float getHeadAngle(float delta) {
		if (eatFernTimer > 4 && eatFernTimer <= 36) {
			float f = ((float) (eatFernTimer - 4) - delta) / 32.0F;
			return 0.62831855F + 0.21991149F * MathHelper.sin(f * 28.7F);
		}
		return eatFernTimer > 0 ? 0.62831855F : getPitch() * 0.017453292F;
	}

	public void onEatingGrass() {
		playSound(SoundEventsInit.JOLLY_LLAMA_EAT.get(), 0.5f, 1.0f);
	}


	@Override
	public void playAmbientSound() {
		super.playAmbientSound();
		playSound(SoundEventsInit.JOLLY_LLAMA_BELL.get(), 0.3f, 1.0f);
	}

	@Override
	public boolean canBeSaddled() {
		return false;
	}

	@Override
	public boolean canEquip(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canBreedWith(AnimalEntity other) {
		return false;
	}

	@Override
	@Nullable
	public DyeColor getCarpetColor() {
		return null;
	}
}
