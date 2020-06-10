package net.slexom.earthtojavamobs.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.client.renderer.entity.*;
import net.slexom.earthtojavamobs.entity.GlowSquidEntity;
import net.slexom.earthtojavamobs.init.EntityTypesInit;
import net.slexom.earthtojavamobs.init.FluidInit;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(EarthtojavamobsMod.MOD_ID + " Client Mod Event Subscriber");

    /**
     * We need to register our renderers on the client because rendering code does not exist on the server
     * and trying to use it on a dedicated server will crash the game.
     * <p>
     * This method will be called by Forge when it is time for the mod to do its client-side setup
     * This method will always be called after the Registry events.
     * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
     */
    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_STILL.get(), RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_FLOWING.get(), RenderType.getSolid());

        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AmberChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.AmberChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AshenCow.registryObject.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.AshenCow.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.Cluckshroom.registryObject.get(), CluckshroomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FleckedSheep.registryObject.get(), FleckedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.GlowSquid.registryObject.get(), GlowSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HornedSheep.registryObject.get(), HornedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.InkySheep.registryObject.get(), InkySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MidnightChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.MidnightChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.Moobloom.registryObject.get(), MoobloomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MuddyPig.registryObject.get(), MuddyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PalePig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PalePig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PiebaldPig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PiebaldPig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.RockySheep.registryObject.get(), RockySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SkeletonWolf.registryObject.get(), SkeletonWolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SpottedPig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.SpottedPig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.StormyChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.StormyChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SunsetCow.registryObject.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.SunsetCow.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.TropicalSlime.registryObject.get(), TropicalSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.WoolyCow.registryObject.get(), WoolyCowRenderer::new);

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
                    }.withConfiguration(new BlockStateFeatureConfig(FluidInit.MUD_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(40))));
                }
            }
        });
    }

    private static void registerEntitiesSpawn() {
        registerAnimalEntitySpawn(EntityTypesInit.AmberChicken.registryObject.get(), EntityTypesInit.AmberChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.AshenCow.registryObject.get(), EntityTypesInit.AshenCow.spawnBiomes, 8, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.Cluckshroom.registryObject.get(), EntityTypesInit.Cluckshroom.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.FleckedSheep.registryObject.get(), EntityTypesInit.FleckedSheep.spawnBiomes, 12, 2, 4);
        registerGlowingSquidSpawn();
        registerAnimalEntitySpawn(EntityTypesInit.HornedSheep.registryObject.get(), EntityTypesInit.HornedSheep.spawnBiomes, 12, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.InkySheep.registryObject.get(), EntityTypesInit.InkySheep.spawnBiomes, 12, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MidnightChicken.registryObject.get(), EntityTypesInit.MidnightChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.Moobloom.registryObject.get(), EntityTypesInit.Moobloom.spawnBiomes, 8, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MuddyPig.registryObject.get(), EntityTypesInit.MuddyPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PalePig.registryObject.get(), EntityTypesInit.PalePig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PiebaldPig.registryObject.get(), EntityTypesInit.PiebaldPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.RockySheep.registryObject.get(), EntityTypesInit.RockySheep.spawnBiomes, 12, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.SkeletonWolf.registryObject.get(), EntityTypesInit.SkeletonWolf.spawnBiomes, 7, 2, 6);
        registerAnimalEntitySpawn(EntityTypesInit.SpottedPig.registryObject.get(), EntityTypesInit.SpottedPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.StormyChicken.registryObject.get(), EntityTypesInit.StormyChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.SunsetCow.registryObject.get(), EntityTypesInit.SunsetCow.spawnBiomes, 8, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.TropicalSlime.registryObject.get(), EntityTypesInit.TropicalSlime.spawnBiomes, 7, 1, 3);
        registerAnimalEntitySpawn(EntityTypesInit.WoolyCow.registryObject.get(), EntityTypesInit.WoolyCow.spawnBiomes, 8, 2, 4);
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

    private static void registerGlowingSquidSpawn() {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GlowSquid.registryObject.get(), EntityTypesInit.GlowSquid.spawnBiomes, 6, 1, 4);
            EntitySpawnPlacementRegistry.register(EntityTypesInit.GlowSquid.registryObject.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);
        });
    }

}