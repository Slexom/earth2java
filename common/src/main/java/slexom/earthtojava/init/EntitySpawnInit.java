package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.mixins.SpawnRestrictionAccessor;
import slexom.earthtojava.utils.EntitySpawnConfigHolder;
import slexom.earthtojava.world.biome.SpawnPredicates;

import java.util.function.Predicate;

public final class EntitySpawnInit {

	private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

	private EntitySpawnInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {
		manageAnimalEntities();
		manageMonsterEntities();
	}

	public static void initSpawnRestriction() {
		registerCreatureSpawnRestriction(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MOOLIP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT);
		registerCreatureSpawnRestriction(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT);

		registerMonsterSpawnRestriction(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT);
		registerMonsterSpawnRestriction(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT);
		registerMonsterSpawnRestriction(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT);
		registerMonsterSpawnRestriction(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT);
		registerMonsterSpawnRestriction(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT);
		registerMonsterSpawnRestriction(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT);
	}

	private static <T extends AnimalEntity> void registerCreatureSpawnRestriction(RegistrySupplier<EntityType<T>> entity) {
		SpawnRestrictionAccessor.callRegister(entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
	}

	private static <T extends HostileEntity> void registerMonsterSpawnRestriction(RegistrySupplier<EntityType<T>> entity) {
		SpawnRestrictionAccessor.callRegister(entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
	}

	private static void manageAnimalEntities() {
		// Chickens
		registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.AMBER_CHICKEN_SPAWN_BIOMES_PREDICATE, config.amberChicken));
		registerAnimalEntitySpawn(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.BRONZED_CHICKEN_SPAWN_BIOMES_PREDICATE, config.bronzedChicken));
		registerAnimalEntitySpawn(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.FANCY_CHICKEN_SPAWN_BIOMES_PREDICATE, config.fancyChicken));
		registerAnimalEntitySpawn(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.GOLD_CRESTED_CHICKEN_SPAWN_BIOMES_PREDICATE, config.goldCrestedChicken));
		registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MIDNIGHT_CHICKEN_SPAWN_BIOMES_PREDICATE, config.midnightChicken));
		registerAnimalEntitySpawn(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.SKEWBALD_CHICKEN_SPAWN_BIOMES_PREDICATE, config.skewbaldChicken));
		registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.STORMY_CHICKEN_SPAWN_BIOMES_PREDICATE, config.stormyChicken));

		// Cows
		registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.ALBINO_COW_SPAWN_BIOMES_PREDICATE, config.albinoCow));
		registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.ASHEN_COW_SPAWN_BIOMES_PREDICATE, config.ashenCow));
		registerAnimalEntitySpawn(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.COOKIE_COW_SPAWN_BIOMES_PREDICATE, config.cookieCow));
		registerAnimalEntitySpawn(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.CREAM_COW_SPAWN_BIOMES_PREDICATE, config.creamCow));
		registerAnimalEntitySpawn(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.DAIRY_COW_SPAWN_BIOMES_PREDICATE, config.dairyCow));
		registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MOOBLOOM_SPAWN_BIOMES_PREDICATE, config.moobloom));
		registerAnimalEntitySpawn(EntityTypesInit.MOOLIP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MOOLIP_SPAWN_BIOMES_PREDICATE, config.moolip));
		registerAnimalEntitySpawn(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.PINTO_COW_SPAWN_BIOMES_PREDICATE, config.pintoCow));
		registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.SUNSET_COW_SPAWN_BIOMES_PREDICATE, config.sunsetCow));
		registerAnimalEntitySpawn(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.UMBRA_COW_SPAWN_BIOMES_PREDICATE, config.umbraCow));
		registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.WOOLY_COW_SPAWN_BIOMES_PREDICATE, config.woolyCow));
		registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.CLUCKSHROOM_SPAWN_BIOMES_PREDICATE, config.cluckshroom));

		// Pigs
		registerAnimalEntitySpawn(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MOTTLED_PIG_SPAWN_BIOMES_PREDICATE, config.mottledPig));
		registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MUDDY_PIG_SPAWN_BIOMES_PREDICATE, config.muddyPig));
		registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.PALE_PIG_SPAWN_BIOMES_PREDICATE, config.palePig));
		registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.PIEBALD_PIG_SPAWN_BIOMES_PREDICATE, config.piebaldPig));
		registerAnimalEntitySpawn(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.PINK_FOOTED_PIG_SPAWN_BIOMES_PREDICATE, config.pinkFootedPig));
		registerAnimalEntitySpawn(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.SOOTY_PIG_SPAWN_BIOMES_PREDICATE, config.sootyPig));
		registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.SPOTTED_PIG_SPAWN_BIOMES_PREDICATE, config.spottedPig));

		// Rabbits
		registerAnimalEntitySpawn(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.BOLD_STRIPED_RABBIT_SPAWN_BIOMES_PREDICATE, config.boldStripedRabbit));
		registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.HARELEQUIN_RABBIT_SPAWN_BIOMES_PREDICATE, config.harelequinRabbit));
		registerAnimalEntitySpawn(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.FRECKLED_RABBIT_SPAWN_BIOMES_PREDICATE, config.freckledRabbit));
		registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.JUMBO_RABBIT_SPAWN_BIOMES_PREDICATE, config.jumboRabbit));
		registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.MUDDY_FOOT_RABBIT_SPAWN_BIOMES_PREDICATE, config.muddyFootRabbit));
		registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.VESTED_RABBIT_SPAWN_BIOMES_PREDICATE, config.vestedRabbit));

		// Sheeps
		registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.FLECKED_SHEEP_SPAWN_BIOMES_PREDICATE, config.fleckedSheep));
		registerAnimalEntitySpawn(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.PATCHED_SHEEP_SPAWN_BIOMES_PREDICATE, config.patchedSheep));
		registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.RAINBOW_SHEEP_SPAWN_BIOMES_PREDICATE, config.rainbowSheep));
		registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.ROCKY_SHEEP_SPAWN_BIOMES_PREDICATE, config.rockySheep));
		registerAnimalEntitySpawn(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.FUZZY_SHEEP_SPAWN_BIOMES_PREDICATE, config.fuzzySheep));
		registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.HORNED_SHEEP_SPAWN_BIOMES_PREDICATE, config.hornedSheep));
		registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.INKY_SHEEP_SPAWN_BIOMES_PREDICATE, config.inkySheep));
		registerAnimalEntitySpawn(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.LONG_NOSED_SHEEP_SPAWN_BIOMES_PREDICATE, config.longNosedSheep));

		// Other
		registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.JOLLY_LLAMA_SPAWN_BIOMES_PREDICATE, config.jollyLlama));
	}

	private static void manageMonsterEntities() {
		registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.BONE_SPIDER_SPAWN_BIOMES_PREDICATE, config.boneSpider));
		registerMonsterEntitySpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.BOULDERING_ZOMBIE_SPAWN_BIOMES_PREDICATE, config.boulderingZombie));
		registerMonsterEntitySpawn(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.LOBBER_ZOMBIE_SPAWN_BIOMES_PREDICATE, config.lobberZombie));
		registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.SKELETON_WOLF_SPAWN_BIOMES_PREDICATE, config.skeletonWolf));
		registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.TROPICAL_SLIME_SPAWN_BIOMES_PREDICATE, config.tropicalSlime));
		registerMonsterEntitySpawn(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, new EntitySpawnConfigHolder(SpawnPredicates.VILER_WITCH_SPAWN_BIOMES_PREDICATE, config.vilerWitch));
	}

	private static <T extends AnimalEntity> void registerAnimalEntitySpawn(RegistrySupplier<EntityType<T>> entity, EntitySpawnConfigHolder configHolder) {
		if (configHolder.canSpawn()) {
			setCreatureSpawnBiomes(entity, configHolder.predicate(), configHolder.weight(), configHolder.groupMin(), configHolder.groupMax());
		}
	}

	private static <T extends HostileEntity> void registerMonsterEntitySpawn(RegistrySupplier<EntityType<T>> entity, EntitySpawnConfigHolder configHolder) {
		if (configHolder.canSpawn()) {
			setMonsterSpawnBiomes(entity, configHolder.predicate(), configHolder.weight(), configHolder.groupMin(), configHolder.groupMax());
		}
	}


	private static <T extends Entity> void addEntityToBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupSize, int maxGroupSize, SpawnGroup classification) {
		BiomeModifications.addProperties(predicate, (biomeContext, mutable) -> {
			SpawnSettings.SpawnEntry spawnEntry = new SpawnSettings.SpawnEntry(entity.get(), weight, minGroupSize, maxGroupSize);
			mutable.getSpawnProperties().addSpawn(classification, spawnEntry);
		});
	}

	private static <T extends AnimalEntity> void setCreatureSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
		addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.CREATURE);
	}

	private static <T extends HostileEntity> void setMonsterSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
		addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MONSTER);
	}


}
