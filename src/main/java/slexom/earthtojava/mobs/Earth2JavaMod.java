package slexom.earthtojava.mobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.EntityTypesInit;
import slexom.earthtojava.mobs.init.ItemInit;
import slexom.earthtojava.mobs.init.RecipesInit;
import slexom.earthtojava.mobs.mixins.SpawnRestrictionAccessor;
import slexom.earthtojava.mobs.utils.BiomeSpawnHelper;
import slexom.earthtojava.mobs.world.spawner.E2JWanderingTraderManager;

public class Earth2JavaMod implements ModInitializer {

    public static final String MOD_ID = "earthtojavamobs";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, MOD_ID),
            () -> new ItemStack(Blocks.COBBLESTONE));

    private final static int standardSheepWeight = 12;
    private final static int standardCowWeight = 8;
    private final static int standardChickenWeight = 10;
    private final static int standardPigWeight = 10;
    private final static int standardRabbitWeight = 4;
    private final static int standardWolfWeight = 8;
    private final static int standardFoxWeight = 8;

    private static void registerEntitiesSpawn() {
        registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, BiomeSpawnHelper.AMBER_CHICKEN_SPAWN_BIOMES, standardChickenWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, BiomeSpawnHelper.ASHEN_COW_SPAWN_BIOMES, standardCowWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, BiomeSpawnHelper.CLUCKSHROOM_SPAWN_BIOMES, standardChickenWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, BiomeSpawnHelper.FLECKED_SHEEP_SPAWN_BIOMES, standardSheepWeight, 2, 4);
        registerGlowingSquidSpawn();
        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, BiomeSpawnHelper.HARELEQUIN_RABBIT_SPAWN_BIOMES, standardRabbitWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, BiomeSpawnHelper.HORNED_SHEEP_SPAWN_BIOMES, standardSheepWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, BiomeSpawnHelper.INKY_SHEEP_SPAWN_BIOMES, standardSheepWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, BiomeSpawnHelper.MIDNIGHT_CHICKEN_SPAWN_BIOMES, standardChickenWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, BiomeSpawnHelper.MOOBLOOM_SPAWN_BIOMES, standardCowWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, BiomeSpawnHelper.MUDDY_FOOT_RABBIT_SPAWN_BIOMES, standardRabbitWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, BiomeSpawnHelper.MUDDY_PIG_SPAWN_BIOMES, standardPigWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, BiomeSpawnHelper.PALE_PIG_SPAWN_BIOMES, standardPigWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, BiomeSpawnHelper.PIEBALD_PIG_SPAWN_BIOMES, standardPigWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, BiomeSpawnHelper.ROCKY_SHEEP_SPAWN_BIOMES, standardSheepWeight, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, BiomeSpawnHelper.SKELETON_WOLF_SPAWN_BIOMES, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, BiomeSpawnHelper.SPOTTED_PIG_SPAWN_BIOMES, standardPigWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, BiomeSpawnHelper.STORMY_CHICKEN_SPAWN_BIOMES, standardChickenWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, BiomeSpawnHelper.SUNSET_COW_SPAWN_BIOMES, standardCowWeight, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, BiomeSpawnHelper.TROPICAL_SLIME_SPAWN_BIOMES, 8, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, BiomeSpawnHelper.VESTED_RABBIT_SPAWN_BIOMES, standardRabbitWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, BiomeSpawnHelper.WOOLY_COW_SPAWN_BIOMES, standardCowWeight, 2, 4);
        registerMobEntitySpawn(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, BiomeSpawnHelper.FURNACE_GOLEM_SPAWN_BIOMES, 10, 2, 4);
        registerMobEntitySpawn(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, BiomeSpawnHelper.MELON_GOLEM_SPAWN_BIOMES, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, BiomeSpawnHelper.ALBINO_COW_SPAWN_BIOMES, standardCowWeight, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BiomeSpawnHelper.BONE_SPIDER_SPAWN_BIOMES, 20, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, BiomeSpawnHelper.JUMBO_RABBIT_SPAWN_BIOMES, standardRabbitWeight, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, BiomeSpawnHelper.JOLLY_LLAMA_SPAWN_BIOMES, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, BiomeSpawnHelper.RAINBOW_SHEEP_SPAWN_BIOMES, standardSheepWeight, 2, 4);
//        registerAnimalEntitySpawn(EntityTypesInit.R registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, E2JModConfig.amberChickenSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.amberChickenWeight, E2JModConfig.amberChickenGroupMin, E2JModConfig.amberChickenGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, E2JModConfig.ashenCowSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.ashenCowWeight, E2JModConfig.ashenCowGroupMin, E2JModConfig.ashenCowGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, E2JModConfig.cluckshroomSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.cluckshroomWeight, E2JModConfig.cluckshroomGroupMin, E2JModConfig.cluckshroomGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, E2JModConfig.fleckedSheepSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.fleckedSheepWeight, E2JModConfig.fleckedSheepGroupMin, E2JModConfig.fleckedSheepGroupMax);
//        registerGlowingSquidSpawn();
//        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, E2JModConfig.harelequinRabbitSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.harelequinRabbitWeight, E2JModConfig.harelequinRabbitGroupMin, E2JModConfig.harelequinRabbitGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, E2JModConfig.hornedSheepSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.hornedSheepWeight, E2JModConfig.hornedSheepGroupMin, E2JModConfig.hornedSheepGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, E2JModConfig.inkySheepSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.inkySheepWeight, E2JModConfig.inkySheepGroupMin, E2JModConfig.inkySheepGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, E2JModConfig.midnightChickenSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.midnightChickenWeight, E2JModConfig.midnightChickenGroupMin, E2JModConfig.midnightChickenGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, E2JModConfig.moobloomSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.moobloomWeight, E2JModConfig.moobloomGroupMin, E2JModConfig.moobloomGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, E2JModConfig.muddyFootRabbitSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.muddyFootRabbitWeight, E2JModConfig.muddyFootRabbitGroupMin, E2JModConfig.muddyFootRabbitGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, E2JModConfig.muddyPigSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.muddyPigWeight, E2JModConfig.muddyPigGroupMin, E2JModConfig.muddyPigGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, E2JModConfig.palePigSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.palePigWeight, E2JModConfig.palePigGroupMin, E2JModConfig.palePigGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, E2JModConfig.piebaldPigSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.piebaldPigWeight, E2JModConfig.piebaldPigGroupMin, E2JModConfig.piebaldPigGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, E2JModConfig.rockySheepSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.rockySheepWeight, E2JModConfig.rockySheepGroupMin, E2JModConfig.rockySheepGroupMax);
//        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, E2JModConfig.skeletonWolfSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.skeletonWolfWeight, E2JModConfig.skeletonWolfGroupMin, E2JModConfig.skeletonWolfGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, E2JModConfig.spottedPigSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.spottedPigWeight, E2JModConfig.spottedPigGroupMin, E2JModConfig.spottedPigGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, E2JModConfig.stormyChickenSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.stormyChickenWeight, E2JModConfig.stormyChickenGroupMin, E2JModConfig.stormyChickenGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, E2JModConfig.sunsetCowSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.sunsetCowWeight, E2JModConfig.sunsetCowGroupMin, E2JModConfig.sunsetCowGroupMax);
//        registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, E2JModConfig.tropicalSlimeSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.tropicalSlimeWeight, E2JModConfig.tropicalSlimeGroupMin, E2JModConfig.tropicalSlimeGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, E2JModConfig.vestedRabbitSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.vestedRabbitWeight, E2JModConfig.vestedRabbitGroupMin, E2JModConfig.vestedRabbitGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, E2JModConfig.woolyCowSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.woolyCowWeight, E2JModConfig.woolyCowGroupMin, E2JModConfig.woolyCowGroupMax);
//        registerMobEntitySpawn(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, E2JModConfig.furnaceGolemSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.furnaceGolemWeight, E2JModConfig.furnaceGolemGroupMin, E2JModConfig.furnaceGolemGroupMax);
//        registerMobEntitySpawn(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, E2JModConfig.melonGolemSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.melonGolemWeight, E2JModConfig.melonGolemGroupMin, E2JModConfig.melonGolemGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, E2JModConfig.albinoCowSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.albinoCowWeight, E2JModConfig.albinoCowGroupMin, E2JModConfig.albinoCowGroupMax);
//        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, E2JModConfig.boneSpiderSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.boneSpiderWeight, E2JModConfig.boneSpiderGroupMin, E2JModConfig.boneSpiderGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, E2JModConfig.jumboRabbitSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.jumboRabbitWeight, E2JModConfig.jumboRabbitGroupMin, E2JModConfig.jumboRabbitGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, E2JModConfig.jollyLlamaSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.jollyLlamaWeight, E2JModConfig.jollyLlamaGroupMin, E2JModConfig.jollyLlamaGroupMax);
//        registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, E2JModConfig.rainbowSheepSpawnBiomes.toArray(new String[0])                                             , E2JModConfig.rainbowSheepWeight, E2JModConfig.rainbowSheepGroupMin, E2JModConfig.rainbowSheepGroupMax);
    }

    private static void registerAnimalEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }

    private static void registerMonsterEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }

    private static void registerMobEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
    }

    private static void registerGlowingSquidSpawn() {
        BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, BiomeSpawnHelper.GLOW_SQUID_SPAWN_BIOMES, 6, 2, 4);
        SpawnRestrictionAccessor.callRegister(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);
    }

    @Override
    public void onInitialize() {
        BlockInit.init();
        ItemInit.init();
        EntityTypesInit.init();
        RecipesInit.init();
        registerEntitiesSpawn();
        registerTraderSpawner();
    }

    private void registerTraderSpawner() {
        E2JWanderingTraderManager spawner = new E2JWanderingTraderManager();

        ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> {
            ServerWorld world = minecraftServer.getOverworld();
            spawner.spawn(world, true, true);
            //E2JWanderingTraderManageraaa.tick(world);
        });
    }


}
