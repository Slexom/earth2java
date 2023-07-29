package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.entity.monster.*;
import slexom.earthtojava.entity.passive.*;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.entity.projectile.MelonSeedProjectileEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;
import slexom.earthtojava.utils.Utils;

public final class EntityTypesInit {

	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ALBINO_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> AMBER_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ASHEN_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> BOLD_STRIPED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoneShardEntity>> BONE_SHARD_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoneSpiderEntity>> BONE_SPIDER_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<BoulderingZombieEntity>> BOULDERING_ZOMBIE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> BRONZED_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<CluckshroomEntity>> CLUCKSHROOM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> COOKIE_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> CREAM_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> DAIRY_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<FancyChickenEntity>> FANCY_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FLECKED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> FRECKLED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<FurnaceGolemEntity>> FURNACE_GOLEM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FUZZY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> HARELEQUIN_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<HornedSheepEntity>> HORNED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> INKY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<JollyLlamaEntity>> JOLLY_LLAMA_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<JumboRabbitEntity>> JUMBO_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<LobberZombieEntity>> LOBBER_ZOMBIE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> LONG_NOSED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MelonGolemEntity>> MELON_GOLEM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MelonSeedProjectileEntity>> MELON_SEED_PROJECTILE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> MIDNIGHT_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MoobloomEntity>> MOOBLOOM_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MoolipEntity>> MOOLIP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> MOTTLED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> MUDDY_FOOT_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<MuddyPigEntity>> MUDDY_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PALE_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> PATCHED_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PIEBALD_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PINK_FOOTED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> PINTO_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<RainbowSheepEntity>> RAINBOW_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> ROCKY_SHEEP_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<RottenFleshProjectileEntity>> ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<SkeletonWolfEntity>> SKELETON_WOLF_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> SKEWBALD_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SOOTY_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SPOTTED_PIG_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> STORMY_CHICKEN_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> SUNSET_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<TropicalSlimeEntity>> TROPICAL_SLIME_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<UmbraCowEntity>> UMBRA_COW_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> VESTED_RABBIT_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<VilerWitchEntity>> VILER_WITCH_REGISTRY_OBJECT;
	public static final RegistrySupplier<EntityType<WoolyCowEntity>> WOOLY_COW_REGISTRY_OBJECT;

	static {
		ASHEN_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.ASHEN_COW_REGISTRY_NAME);
		ALBINO_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.ALBINO_COW_REGISTRY_NAME);
		COOKIE_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.COOKIE_COW_REGISTRY_NAME);
		CREAM_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.CREAM_COW_REGISTRY_NAME);
		DAIRY_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.DAIRY_COW_REGISTRY_NAME);
		PINTO_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.PINTO_COW_REGISTRY_NAME);
		SUNSET_COW_REGISTRY_OBJECT = registerBaseCowEntity(RegistryNames.SUNSET_COW_REGISTRY_NAME);

		AMBER_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.AMBER_CHICKEN_REGISTRY_NAME);
		BRONZED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.BRONZED_CHICKEN_REGISTRY_NAME);
		GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.GOLD_CRESTED_CHICKEN_REGISTRY_NAME);
		MIDNIGHT_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.MIDNIGHT_CHICKEN_REGISTRY_NAME);
		SKEWBALD_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.SKEWBALD_CHICKEN_REGISTRY_NAME);
		STORMY_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(RegistryNames.STORMY_CHICKEN_REGISTRY_NAME);

		MOTTLED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.MOTTLED_PIG_REGISTRY_NAME);
		PALE_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PALE_PIG_REGISTRY_NAME);
		PIEBALD_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PIEBALD_PIG_REGISTRY_NAME);
		PINK_FOOTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.PINK_FOOTED_PIG_REGISTRY_NAME);
		SOOTY_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.SOOTY_PIG_REGISTRY_NAME);
		SPOTTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(RegistryNames.SPOTTED_PIG_REGISTRY_NAME);

		BOLD_STRIPED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.BOLD_STRIPED_RABBIT_REGISTRY_NAME);
		FRECKLED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.FRECKLED_RABBIT_REGISTRY_NAME);
		HARELEQUIN_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.HARELEQUIN_RABBIT_REGISTRY_NAME);
		MUDDY_FOOT_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.MUDDY_FOOT_RABBIT_REGISTRY_NAME);
		VESTED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(RegistryNames.VESTED_RABBIT_REGISTRY_NAME);

		FLECKED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.FLECKED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
		FUZZY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.FUZZY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
		INKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.INKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BLACK_WOOL));
		LONG_NOSED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.LONG_NOSED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
		PATCHED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.PATCHED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
		ROCKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(RegistryNames.ROCKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.GRAY_WOOL));

		BONE_SHARD_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.BONE_SHARD_REGISTRY_NAME), () -> EntityType.Builder.<BoneShardEntity>create(BoneShardEntity::new, SpawnGroup.MISC)
				.setDimensions(0.25F, 0.25F)
				.maxTrackingRange(4)
				.trackingTickInterval(10)
				.build(RegistryNames.BONE_SHARD_REGISTRY_NAME));
		BONE_SPIDER_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.BONE_SPIDER_REGISTRY_NAME), () -> EntityType.Builder.create(BoneSpiderEntity::new, SpawnGroup.MONSTER)
				.setDimensions(0.6F, 0.7F)
				.maxTrackingRange(8)
				.build(RegistryNames.BONE_SPIDER_REGISTRY_NAME));
		BOULDERING_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.create(BoulderingZombieEntity::new, SpawnGroup.MONSTER)
				.setDimensions(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
				.maxTrackingRange(8)
				.build(RegistryNames.BOULDERING_ZOMBIE_REGISTRY_NAME));
		CLUCKSHROOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.CLUCKSHROOM_REGISTRY_NAME), () -> EntityType.Builder.create(CluckshroomEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.CLUCKSHROOM_REGISTRY_NAME));
		FANCY_CHICKEN_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME), () -> EntityType.Builder.create(FancyChickenEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.FANCY_CHICKEN_REGISTRY_NAME));
		FURNACE_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.FURNACE_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.create(FurnaceGolemEntity::new, SpawnGroup.MISC)
				.setDimensions(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
				.makeFireImmune()
				.maxTrackingRange(10)
				.build(RegistryNames.FURNACE_GOLEM_REGISTRY_NAME));
		HORNED_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.HORNED_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.create(HornedSheepEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.HORNED_SHEEP_REGISTRY_NAME));
		JOLLY_LLAMA_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME), () -> EntityType.Builder.create(JollyLlamaEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.LLAMA.getWidth(), EntityType.LLAMA.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.JOLLY_LLAMA_REGISTRY_NAME));
		JUMBO_RABBIT_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME), () -> EntityType.Builder.create(JumboRabbitEntity::new, SpawnGroup.CREATURE)
				.setDimensions(0.8F, 1.0F)
				.maxTrackingRange(8)
				.build(RegistryNames.JUMBO_RABBIT_REGISTRY_NAME));
		LOBBER_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.create(LobberZombieEntity::new, SpawnGroup.MONSTER)
				.setDimensions(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
				.maxTrackingRange(8)
				.build(RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME));
		MELON_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.MELON_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.create(MelonGolemEntity::new, SpawnGroup.MISC)
				.setDimensions(EntityType.SNOW_GOLEM.getWidth(), EntityType.SNOW_GOLEM.getHeight())
				.allowSpawningInside(Blocks.POWDER_SNOW)
				.maxTrackingRange(8)
				.build(RegistryNames.MELON_GOLEM_REGISTRY_NAME));
		MELON_SEED_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.MELON_SEED_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<MelonSeedProjectileEntity>create(MelonSeedProjectileEntity::new, SpawnGroup.MISC)
				.setDimensions(0.25F, 0.25F)
				.maxTrackingRange(4)
				.trackingTickInterval(10)
				.build(RegistryNames.MELON_SEED_PROJECTILE_REGISTRY_NAME));
		MOOBLOOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.MOOBLOOM_REGISTRY_NAME), () -> EntityType.Builder.create(MoobloomEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.MOOBLOOM_REGISTRY_NAME));
		MOOLIP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.MOOLIP_REGISTRY_NAME), () -> EntityType.Builder.create(MoolipEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.MOOLIP_REGISTRY_NAME));
		MUDDY_PIG_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.MUDDY_PIG_REGISTRY_NAME), () -> EntityType.Builder.create(MuddyPigEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.MUDDY_PIG_REGISTRY_NAME));
		RAINBOW_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.create(RainbowSheepEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME));
		ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<RottenFleshProjectileEntity>create(RottenFleshProjectileEntity::new, SpawnGroup.MISC)
				.setDimensions(0.25F, 0.25F)
				.maxTrackingRange(4)
				.trackingTickInterval(10)
				.build(RegistryNames.ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME));
		SKELETON_WOLF_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.SKELETON_WOLF_REGISTRY_NAME), () -> EntityType.Builder.create(SkeletonWolfEntity::new, SpawnGroup.MONSTER)
				.setDimensions(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.SKELETON_WOLF_REGISTRY_NAME));
		TROPICAL_SLIME_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.TROPICAL_SLIME_REGISTRY_NAME), () -> EntityType.Builder.create(TropicalSlimeEntity::new, SpawnGroup.CREATURE)
				.setDimensions(2.04F, 2.04F)
				.makeFireImmune()
				.maxTrackingRange(10)
				.build(RegistryNames.TROPICAL_SLIME_REGISTRY_NAME));
		UMBRA_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.UMBRA_COW_REGISTRY_NAME), () -> EntityType.Builder.create(UmbraCowEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.UMBRA_COW_REGISTRY_NAME));
		VILER_WITCH_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.VILER_WITCH_REGISTRY_NAME), () -> EntityType.Builder.create(VilerWitchEntity::new, SpawnGroup.MONSTER)
				.setDimensions(EntityType.WITCH.getWidth(), EntityType.WITCH.getHeight())
				.maxTrackingRange(8)
				.build(RegistryNames.VILER_WITCH_REGISTRY_NAME));
		WOOLY_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(RegistryNames.WOOLY_COW_REGISTRY_NAME), () -> EntityType.Builder.create(WoolyCowEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.maxTrackingRange(10)
				.build(RegistryNames.WOOLY_COW_REGISTRY_NAME));
	}

	private EntityTypesInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {
	}

	private static RegistrySupplier<EntityType<E2JBaseChickenEntity>> registerBaseChickenEntity(String registryName) {
		EntityType.Builder<E2JBaseChickenEntity> entityType = EntityType.Builder.create(E2JBaseChickenEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
				.maxTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseCowEntity>> registerBaseCowEntity(String registryName) {
		EntityType.Builder<E2JBaseCowEntity> entityType = EntityType.Builder.create(E2JBaseCowEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
				.maxTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBasePigEntity>> registerBasePigEntity(String registryName) {
		EntityType.Builder<E2JBasePigEntity> entityType = EntityType.Builder.create(E2JBasePigEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
				.maxTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseRabbitEntity>> registerBaseRabbitEntity(String registryName) {
		EntityType.Builder<E2JBaseRabbitEntity> entityType = EntityType.Builder.create(E2JBaseRabbitEntity::new, SpawnGroup.CREATURE)
				.setDimensions(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
				.maxTrackingRange(8);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> entityType.build(registryName));
	}

	private static RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> registerBaseMonoColorSheepEntity(String registryName, ItemStack wool) {
		EntityType.Builder<E2JBaseMonoColorSheepEntity> entityType = EntityType.Builder.<E2JBaseMonoColorSheepEntity>create((type, world) -> new E2JBaseMonoColorSheepEntity(type, world, wool), SpawnGroup.CREATURE)
				.setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
				.maxTrackingRange(10);
		return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> entityType.build(registryName));
	}


}


