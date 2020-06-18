package net.slexom.earthtojavamobs;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.config.ConfigHolder;
import net.slexom.earthtojavamobs.config.E2JModConfig;
import net.slexom.earthtojavamobs.entity.passive.GlowSquidEntity;
import net.slexom.earthtojavamobs.init.*;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

import java.util.Random;

@Mod(EarthtojavamobsMod.MOD_ID)
public class EarthtojavamobsMod {

    public static final String MOD_ID = "earthtojavamobs";

    public EarthtojavamobsMod() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        EntityTypesInit.ENTITY_TYPES.register(modEventBus);
        RecipesInit.RECIPES.register(modEventBus);
        modEventBus.register(this);
        modEventBus.addListener(this::setup);

        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);

    }

    private void setup(final FMLCommonSetupEvent event) {
        setMudLakeSpawn();
        registerEntitiesSpawn();
    }

    private static void setMudLakeSpawn() {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, new LakesFeature(BlockStateFeatureConfig::deserialize) {
                        @Override
                        public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
                            DimensionType dimensionType = world.getDimension().getType();
                            boolean dimensionCriteria = false;
                            if (dimensionType == DimensionType.OVERWORLD)
                                dimensionCriteria = true;
                            if (!dimensionCriteria)
                                return false;
                            return super.place(world, generator, rand, pos, config);
                        }
                    }.withConfiguration(new BlockStateFeatureConfig(BlockInit.MUD_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(E2JModConfig.mudLakeFrequency))));
                }
            }
        });
    }

    private static void registerEntitiesSpawn() {
        registerAnimalEntitySpawn(EntityTypesInit.AmberChicken.registryObject.get(), E2JModConfig.amberChickenSpawnBiomes.toArray(new String[0]), E2JModConfig.amberChickenWeight, E2JModConfig.amberChickenGroupMin, E2JModConfig.amberChickenGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.AshenCow.registryObject.get(), E2JModConfig.ashenCowSpawnBiomes.toArray(new String[0]), E2JModConfig.ashenCowWeight, E2JModConfig.ashenCowGroupMin, E2JModConfig.ashenCowGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.Cluckshroom.registryObject.get(), E2JModConfig.cluckshroomSpawnBiomes.toArray(new String[0]), E2JModConfig.cluckshroomWeight, E2JModConfig.cluckshroomGroupMin, E2JModConfig.cluckshroomGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.FleckedSheep.registryObject.get(), E2JModConfig.fleckedSheepSpawnBiomes.toArray(new String[0]), E2JModConfig.fleckedSheepWeight, E2JModConfig.fleckedSheepGroupMin, E2JModConfig.fleckedSheepGroupMax);
        registerGlowingSquidSpawn();
        registerAnimalEntitySpawn(EntityTypesInit.HarelequinRabbit.registryObject.get(), E2JModConfig.harelequinRabbitSpawnBiomes.toArray(new String[0]), E2JModConfig.harelequinRabbitWeight, E2JModConfig.harelequinRabbitGroupMin, E2JModConfig.harelequinRabbitGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.HornedSheep.registryObject.get(), E2JModConfig.hornedSheepSpawnBiomes.toArray(new String[0]), E2JModConfig.hornedSheepWeight, E2JModConfig.hornedSheepGroupMin, E2JModConfig.hornedSheepGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.InkySheep.registryObject.get(), E2JModConfig.inkySheepSpawnBiomes.toArray(new String[0]), E2JModConfig.inkySheepWeight, E2JModConfig.inkySheepGroupMin, E2JModConfig.inkySheepGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MidnightChicken.registryObject.get(), E2JModConfig.midnightChickenSpawnBiomes.toArray(new String[0]), E2JModConfig.midnightChickenWeight, E2JModConfig.midnightChickenGroupMin, E2JModConfig.midnightChickenGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.Moobloom.registryObject.get(), E2JModConfig.moobloomSpawnBiomes.toArray(new String[0]), E2JModConfig.moobloomWeight, E2JModConfig.moobloomGroupMin, E2JModConfig.moobloomGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MuddyFootRabbit.registryObject.get(), E2JModConfig.muddyFootRabbitSpawnBiomes.toArray(new String[0]), E2JModConfig.muddyFootRabbitWeight, E2JModConfig.muddyFootRabbitGroupMin, E2JModConfig.muddyFootRabbitGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MuddyPig.registryObject.get(), E2JModConfig.muddyPigSpawnBiomes.toArray(new String[0]), E2JModConfig.muddyPigWeight, E2JModConfig.muddyPigGroupMin, E2JModConfig.muddyPigGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.PalePig.registryObject.get(), E2JModConfig.palePigSpawnBiomes.toArray(new String[0]), E2JModConfig.palePigWeight, E2JModConfig.palePigGroupMin, E2JModConfig.palePigGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.PiebaldPig.registryObject.get(), E2JModConfig.piebaldPigSpawnBiomes.toArray(new String[0]), E2JModConfig.piebaldPigWeight, E2JModConfig.piebaldPigGroupMin, E2JModConfig.piebaldPigGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.RockySheep.registryObject.get(), E2JModConfig.rockySheepSpawnBiomes.toArray(new String[0]), E2JModConfig.rockySheepWeight, E2JModConfig.rockySheepGroupMin, E2JModConfig.rockySheepGroupMax);
        registerMonsterEntitySpawn(EntityTypesInit.SkeletonWolf.registryObject.get(), E2JModConfig.skeletonWolfSpawnBiomes.toArray(new String[0]), E2JModConfig.skeletonWolfWeight, E2JModConfig.skeletonWolfGroupMin, E2JModConfig.skeletonWolfGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.SpottedPig.registryObject.get(), E2JModConfig.spottedPigSpawnBiomes.toArray(new String[0]), E2JModConfig.spottedPigWeight, E2JModConfig.spottedPigGroupMin, E2JModConfig.spottedPigGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.StormyChicken.registryObject.get(), E2JModConfig.stormyChickenSpawnBiomes.toArray(new String[0]), E2JModConfig.stormyChickenWeight, E2JModConfig.stormyChickenGroupMin, E2JModConfig.stormyChickenGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.SunsetCow.registryObject.get(), E2JModConfig.sunsetCowSpawnBiomes.toArray(new String[0]), E2JModConfig.sunsetCowWeight, E2JModConfig.sunsetCowGroupMin, E2JModConfig.sunsetCowGroupMax);
        registerMonsterEntitySpawn(EntityTypesInit.TropicalSlime.registryObject.get(), E2JModConfig.tropicalSlimeSpawnBiomes.toArray(new String[0]), E2JModConfig.tropicalSlimeWeight, E2JModConfig.tropicalSlimeGroupMin, E2JModConfig.tropicalSlimeGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.VestedRabbit.registryObject.get(), E2JModConfig.vestedRabbitSpawnBiomes.toArray(new String[0]), E2JModConfig.vestedRabbitWeight, E2JModConfig.vestedRabbitGroupMin, E2JModConfig.vestedRabbitGroupMax);
        registerAnimalEntitySpawn(EntityTypesInit.WoolyCow.registryObject.get(), E2JModConfig.woolyCowSpawnBiomes.toArray(new String[0]), E2JModConfig.woolyCowWeight, E2JModConfig.woolyCowGroupMin, E2JModConfig.woolyCowGroupMax);
        registerMobEntitySpawn(EntityTypesInit.FurnaceGolem.registryObject.get(), E2JModConfig.furnaceGolemSpawnBiomes.toArray(new String[0]), E2JModConfig.furnaceGolemWeight, E2JModConfig.furnaceGolemGroupMin, E2JModConfig.furnaceGolemGroupMax);
        registerMobEntitySpawn(EntityTypesInit.MelonGolem.registryObject.get(), E2JModConfig.furnaceGolemSpawnBiomes.toArray(new String[0]), E2JModConfig.furnaceGolemWeight, E2JModConfig.furnaceGolemGroupMin, E2JModConfig.furnaceGolemGroupMax);
    }

    private static void registerAnimalEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        });
    }

    private static void registerMonsterEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        });
    }

    private static void registerMobEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
        });
    }

    private static void registerGlowingSquidSpawn() {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GlowSquid.registryObject.get(), E2JModConfig.glowSquidSpawnBiomes.toArray(new String[0]), E2JModConfig.glowSquidWeight, E2JModConfig.glowSquidGroupMin, E2JModConfig.glowSquidGroupMax);
            EntitySpawnPlacementRegistry.register(EntityTypesInit.GlowSquid.registryObject.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);
        });
    }

}
