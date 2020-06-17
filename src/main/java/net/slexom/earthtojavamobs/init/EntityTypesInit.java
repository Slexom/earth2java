package net.slexom.earthtojavamobs.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.entity.*;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

public class EntityTypesInit {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, EarthtojavamobsMod.MOD_ID);


    public static final class AmberChicken {
        public static final String registryName = "amber_chicken";
        public static final RegistryObject<EntityType<AmberChickenEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<AmberChickenEntity>create(AmberChickenEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );

        public static final int eggPrimaryColor = -3066087;
        public static final int eggSecondaryColor = -1865173;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.DESERT, BiomeSpawnHelper.SAVANNA);

    }

    public static final class AshenCow {
        public static final String registryName = "ashen_cow";
        public static final RegistryObject<EntityType<AshenCowEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<AshenCowEntity>create(AshenCowEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x3c3c49;
        public static final int eggSecondaryColor = 0x898491;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.GRAVELLY_MOUNTAINS);
    }

    public static final class Cluckshroom {
        public static final String registryName = "cluckshroom";
        public static final RegistryObject<EntityType<CluckshroomEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<CluckshroomEntity>create(CluckshroomEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xef0000;
        public static final int eggSecondaryColor = 0xffffee;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.MUSHROOM_FIELDS);
    }

    public static final class FleckedSheep {
        public static final String registryName = "flecked_sheep";
        public static final RegistryObject<EntityType<FleckedSheepEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<FleckedSheepEntity>create(FleckedSheepEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x2c1e17;
        public static final int eggSecondaryColor = 0x907666;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.TAIGA, BiomeSpawnHelper.GRAVELLY_MOUNTAINS, BiomeSpawnHelper.FOREST);
    }

    public static final class GlowSquid {
        public static final String registryName = "glow_squid";
        public static final RegistryObject<EntityType<GlowSquidEntity>> registryObject = EntityTypesInit.ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<GlowSquidEntity>create(GlowSquidEntity::new, EntityClassification.WATER_CREATURE)
                        .size(EntityType.SQUID.getWidth(), EntityType.SQUID.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x095656;
        public static final int eggSecondaryColor = 0x80ffc0;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.OCEAN, BiomeSpawnHelper.RIVER, BiomeSpawnHelper.SWAMP);

    }

    public static final class HornedSheep {
        public static final String registryName = "horned_sheep";
        public static final RegistryObject<EntityType<HornedSheepEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<HornedSheepEntity>create(HornedSheepEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xececec;
        public static final int eggSecondaryColor = 0x291811;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.GRAVELLY_MOUNTAINS);
    }

    public static final class InkySheep {
        public static final String registryName = "inky_sheep";
        public static final RegistryObject<EntityType<InkySheepEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<InkySheepEntity>create(InkySheepEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x181716;
        public static final int eggSecondaryColor = 0x8a7564;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.GRAVELLY_MOUNTAINS);
    }

    public static final class MidnightChicken {
        public static final String registryName = "midnight_chicken";
        public static final RegistryObject<EntityType<MidnightChickenEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<MidnightChickenEntity>create(MidnightChickenEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = -16382709;
        public static final int eggSecondaryColor = -15261094;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.FOREST, BiomeSpawnHelper.DARK_FOREST, BiomeSpawnHelper.JUNGLE, BiomeSpawnHelper.BIRCH_FOREST);
    }

    public static final class Moobloom {
        public static final String registryName = "moobloom";
        public static final RegistryObject<EntityType<MoobloomEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<MoobloomEntity>create(MoobloomEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xfaca00;
        public static final int eggSecondaryColor = 0xf7edc1;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    }

    public static final class MuddyPig {
        public static final String registryName = "muddy_pig";
        public static final RegistryObject<EntityType<MuddyPigEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<MuddyPigEntity>create(MuddyPigEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xe6918b;
        public static final int eggSecondaryColor = 0x573621;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.RIVER);
    }

    public static final class PalePig {
        public static final String registryName = "pale_pig";
        public static final RegistryObject<EntityType<PalePigEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<PalePigEntity>create(PalePigEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xd3a0a0;
        public static final int eggSecondaryColor = 0xead3d3;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.SNOWY_TUNDRA, BiomeSpawnHelper.SNOWY_TAIGA);
    }

    public static final class PiebaldPig {
        public static final String registryName = "piebald_pig";
        public static final RegistryObject<EntityType<PiebaldPigEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<PiebaldPigEntity>create(PiebaldPigEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xd7c0a9;
        public static final int eggSecondaryColor = 0x9b4628;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.FOREST, BiomeSpawnHelper.BIRCH_FOREST, BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.TAIGA, BiomeSpawnHelper.SAVANNA);
    }

    public static final class RockySheep {
        public static final String registryName = "rocky_sheep";
        public static final RegistryObject<EntityType<RockySheepEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<RockySheepEntity>create(RockySheepEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xa69f9b;
        public static final int eggSecondaryColor = 0xe9d0bd;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.GRAVELLY_MOUNTAINS);
    }


    public static final class SkeletonWolf {
        public static final String registryName = "skeleton_wolf";
        public static final RegistryObject<EntityType<SkeletonWolfEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<SkeletonWolfEntity>create(SkeletonWolfEntity::new, EntityClassification.MONSTER)
                        .size(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xededed;
        public static final int eggSecondaryColor = 0xbababa;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.FOREST, BiomeSpawnHelper.TAIGA, BiomeSpawnHelper.SNOWY_TAIGA, BiomeSpawnHelper.GIANT_TAIGA);
    }

    public static final class SpottedPig {
        public static final String registryName = "spotted_pig";
        public static final RegistryObject<EntityType<SpottedPigEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<SpottedPigEntity>create(SpottedPigEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xedd4d1;
        public static final int eggSecondaryColor = 0x413938;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.SWAMP);
    }

    public static final class StormyChicken {
        public static final String registryName = "stormy_chicken";
        public static final RegistryObject<EntityType<StormyChickenEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<StormyChickenEntity>create(StormyChickenEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x3e2525;
        public static final int eggSecondaryColor = 0xc0c0c0;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.GRAVELLY_MOUNTAINS, BiomeSpawnHelper.TAIGA);
    }

    public static final class SunsetCow {
        public static final String registryName = "sunset_cow";
        public static final RegistryObject<EntityType<SunsetCowEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<SunsetCowEntity>create(SunsetCowEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x993d0d;
        public static final int eggSecondaryColor = 0x171514;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.SAVANNA);
    }

    public static final class TropicalSlime {
        public static final String registryName = "tropical_slime";
        public static final RegistryObject<EntityType<TropicalSlimeEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<TropicalSlimeEntity>create(TropicalSlimeEntity::new, EntityClassification.CREATURE)
                        .size(0.51F, 0.51F)
                        .immuneToFire()
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x0e496e;
        public static final int eggSecondaryColor = 0x8ed3ff;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.BEACH);
    }

    public static final class WoolyCow {
        public static final String registryName = "wooly_cow";
        public static final RegistryObject<EntityType<WoolyCowEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<WoolyCowEntity>create(WoolyCowEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xcc3300;
        public static final int eggSecondaryColor = 0xff9933;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.TAIGA, BiomeSpawnHelper.SNOWY_TAIGA, BiomeSpawnHelper.GIANT_TAIGA);
    }


    public static final class VestedRabbit{
        public static final String registryName = "vested_rabbit";
        public static final RegistryObject<EntityType<VestedRabbitEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<VestedRabbitEntity>create(VestedRabbitEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xdedede;
        public static final int eggSecondaryColor = 0x747474;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS);
    }


    public static final class HarelequinRabbit{
        public static final String registryName = "harelequin_rabbit";
        public static final RegistryObject<EntityType<HarelequinRabbitEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<HarelequinRabbitEntity>create(HarelequinRabbitEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x1d1b1a;
        public static final int eggSecondaryColor = 0xb09984;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS);
    }


    public static final class MuddyFootRabbit{
        public static final String registryName = "muddy_foot_rabbit";
        public static final RegistryObject<EntityType<MuddyFootRabbitEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<MuddyFootRabbitEntity>create(MuddyFootRabbitEntity::new, EntityClassification.CREATURE)
                        .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0xe5e0dd;
        public static final int eggSecondaryColor = 0x463832;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS);
    }

    public static final class FurnaceGolem{
        public static final String registryName = "furnace_golem";
        public static final RegistryObject<EntityType<FurnaceGolemEntity>> registryObject = ENTITY_TYPES.register(
                registryName,
                () -> EntityType.Builder.<FurnaceGolemEntity>create(FurnaceGolemEntity::new, EntityClassification.MISC)
                        .size(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
                        .immuneToFire()
                        .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, registryName).toString())
        );
        public static final int eggPrimaryColor = 0x56585a;
        public static final int eggSecondaryColor = 0xff5501;
        public static final String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS);
    }

}


