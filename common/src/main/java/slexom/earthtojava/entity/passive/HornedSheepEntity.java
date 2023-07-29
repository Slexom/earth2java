package slexom.earthtojava.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.ai.goal.HornedSheepActiveTargetGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepMeleeAttackGoal;
import slexom.earthtojava.entity.ai.goal.HornedSheepRevengeGoal;
import slexom.earthtojava.entity.base.E2JBaseSheepEntity;

import java.util.UUID;


public class HornedSheepEntity extends E2JBaseSheepEntity implements Angerable, Shearable {

	private static final TrackedData<Byte> DATA_FLAGS_ID = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.BYTE);
	private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(HornedSheepEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
	private EatGrassGoal eatGrassGoal;
	private UUID lastHurtBy;

	public HornedSheepEntity(EntityType<? extends HornedSheepEntity> type, World world) {
		super(type, world);
	}

	public static DefaultAttributeContainer.Builder createHornedSheepAttributes() {
		return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0D)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		dataTracker.startTracking(DATA_FLAGS_ID, (byte) 0);
		dataTracker.startTracking(ANGER_TIME, 0);
	}

	protected void initGoals() {
		eatGrassGoal = new EatGrassGoal(this);
		goalSelector.add(0, new SwimGoal(this));
		goalSelector.add(1, new AnimalMateGoal(this, 1.0D));
		goalSelector.add(3, new TemptGoal(this, 1.1D, Ingredient.ofItems(Items.WHEAT), false));
		goalSelector.add(4, new FollowParentGoal(this, 1.25D));
		goalSelector.add(4, eatGrassGoal);
		goalSelector.add(5, new HornedSheepMeleeAttackGoal(this, 1.4D, true));
		goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
		goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.add(8, new LookAroundGoal(this));
		targetSelector.add(1, (new HornedSheepRevengeGoal(this)).setGroupRevenge());
		targetSelector.add(2, new HornedSheepActiveTargetGoal(this));
	}

	public void writeCustomDataToNbt(NbtCompound compound) {
		super.writeCustomDataToNbt(compound);
		writeAngerToNbt(compound);
	}

	public void readCustomDataFromNbt(NbtCompound compound) {
		super.readCustomDataFromNbt(compound);
		readAngerFromNbt(getWorld(), compound);
	}

	public boolean tryAttack(Entity entityIn) {
		boolean flag = entityIn.damage(entityIn.getDamageSources().mobAttack(this), (float) ((int) getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));
		if (flag) {
			applyDamageEffects(this, entityIn);
		}
		return flag;
	}

	public void setAttacker(@Nullable LivingEntity livingBase) {
		super.setAttacker(livingBase);
		if (livingBase != null) {
			lastHurtBy = livingBase.getUuid();
		}
	}

	protected void mobTick() {
		if (isAngry()) {
			int i = getAnger();
			setAnger(i - 1);
			LivingEntity livingentity = getTarget();
			if (i == 0 && livingentity != null) {
				setSheepAttacker(livingentity);
			}
		}
	}

	public boolean isAngry() {
		return getAnger() > 0;
	}

	private int getAnger() {
		return dataTracker.get(ANGER_TIME);
	}

	private void setAnger(int angerTime) {
		dataTracker.set(ANGER_TIME, angerTime);
	}

	public void tickMovement() {
		super.tickMovement();
		if (!getWorld().isClient) {
			boolean flag = isAngry() && getTarget() != null && getTarget().squaredDistanceTo(this) < 4.0D;
			setNearTarget(flag);
		}
	}

	private void setNearTarget(boolean p_226452_1_) {
		setSheepFlag(p_226452_1_);
	}

	private void setSheepFlag(boolean p_226404_2_) {
		if (p_226404_2_) {
			dataTracker.set(DATA_FLAGS_ID, (byte) (dataTracker.get(DATA_FLAGS_ID) | 2));
		} else {
			dataTracker.set(DATA_FLAGS_ID, (byte) (dataTracker.get(DATA_FLAGS_ID) & ~2));
		}
	}

	public Identifier getLootTableId() {
		if (isSheared()) {
			return getType().getLootTableId();
		}
		return switch (getColor()) {
			case WHITE -> new Identifier("earthtojavamobs", "entities/horned_sheep/white");
			case ORANGE -> new Identifier("earthtojavamobs", "entities/horned_sheep/orange");
			case MAGENTA -> new Identifier("earthtojavamobs", "entities/horned_sheep/magenta");
			case LIGHT_BLUE -> new Identifier("earthtojavamobs", "entities/horned_sheep/light_blue");
			case YELLOW -> new Identifier("earthtojavamobs", "entities/horned_sheep/yellow");
			case LIME -> new Identifier("earthtojavamobs", "entities/horned_sheep/lime");
			case PINK -> new Identifier("earthtojavamobs", "entities/horned_sheep/pink");
			case GRAY -> new Identifier("earthtojavamobs", "entities/horned_sheep/gray");
			case LIGHT_GRAY -> new Identifier("earthtojavamobs", "entities/horned_sheep/light_gray");
			case CYAN -> new Identifier("earthtojavamobs", "entities/horned_sheep/cyan");
			case PURPLE -> new Identifier("earthtojavamobs", "entities/horned_sheep/purple");
			case BLUE -> new Identifier("earthtojavamobs", "entities/horned_sheep/blue");
			case BROWN -> new Identifier("earthtojavamobs", "entities/horned_sheep/brown");
			case GREEN -> new Identifier("earthtojavamobs", "entities/horned_sheep/green");
			case RED -> new Identifier("earthtojavamobs", "entities/horned_sheep/red");
			case BLACK -> new Identifier("earthtojavamobs", "entities/horned_sheep/black");
		};
	}

	public boolean setSheepAttacker(Entity attacker) {
		setAnger(400 + random.nextInt(400));
		if (attacker instanceof LivingEntity) {
			setAttacker((LivingEntity) attacker);
		}
		return true;
	}

	public boolean damage(DamageSource source, float amount) {
		if (isInvulnerableTo(source)) {
			return false;
		}
		Entity entity = source.getAttacker();
		if (!getWorld().isClient && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && canSee(entity) && !isAiDisabled()) {
			setSheepAttacker(entity);
		}
		return super.damage(source, amount);
	}


	@Override
	public int getAngerTime() {
		return dataTracker.get(ANGER_TIME);
	}

	@Override
	public void setAngerTime(int ticks) {
		dataTracker.set(ANGER_TIME, ticks);
	}

	@Nullable
	@Override
	public UUID getAngryAt() {
		return lastHurtBy;
	}

	@Override
	public void setAngryAt(@Nullable UUID uuid) {
		lastHurtBy = uuid;
	}

	@Override
	public void chooseRandomAngerTime() {
		setAngerTime(ANGER_TIME_RANGE.get(random));
	}


}
