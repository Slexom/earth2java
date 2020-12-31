package slexom.earthtojava.mobs.init.renderer;

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import slexom.earthtojava.mobs.client.renderer.entity.*;
import slexom.earthtojava.mobs.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.mobs.entity.base.*;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;
import slexom.earthtojava.mobs.init.EntityTypesInit;

public class EntityRendererInit {

    public static void init() {
        registerEntitiesRenderer();
        registerProjectileRenderer();
        registerBlockEntityRenderer();
    }

    private static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.INSTANCE.register(BlockEntityTypeInit.RAINBOW_BED, RainbowBedBlockEntityRenderer::new);
    }

    private static void registerProjectileRenderer() {
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
    }

    private static void registerEntitiesRenderer() {
        registerBaseVariantEntitiesRenderer();
        registerSpecialVariantEntitiesRenderer();
    }

    private static void registerBaseVariantEntitiesRenderer() {
        registerChickenEntityRenderer(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME);
        registerChickenEntityRenderer(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.BRONZED_CHICKEN_REGISTRY_NAME);
        registerChickenEntityRenderer(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME);
        registerChickenEntityRenderer(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME);
        registerChickenEntityRenderer(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_NAME);

        registerCowEntityRenderer(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, EntityTypesInit.ALBINO_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, EntityTypesInit.ASHEN_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, EntityTypesInit.COOKIE_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, EntityTypesInit.PINTO_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, EntityTypesInit.SUNSET_COW_REGISTRY_NAME);

        registerPigEntityRenderer(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, EntityTypesInit.PALE_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME);

        registerMonoColorSheepEntityRenderer(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, EntityTypesInit.INKY_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, EntityTypesInit.PATCHED_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME);

        registerRabbitEntityRenderer(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME);
    }

    private static void registerSpecialVariantEntitiesRenderer() {
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, CluckshroomRenderer::new);
//        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, (dispatcher, context) -> new GlowSquidRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, HornedSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, MoobloomRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MOOLIP_REGISTRY_OBJECT, MoolipRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, MuddyPigRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, SkeletonWolfRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, TropicalSlimeRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, WoolyCowRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, FurnaceGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, MelonGolemRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, JumboRabbitRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, JollyLlamaRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BoneSpiderRenderer::new);
//        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT, (dispatcher, context) -> new E2JWanderingTraderRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, RainbowSheepRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, FancyChickenRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, BoulderingZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, LobberZombieRenderer::new);
    }

    private static <E extends E2JBaseChickenEntity<E>> void registerChickenEntityRenderer(EntityType<E> entity, String identifier) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> new E2JChickenRenderer(context, identifier));
    }

    private static <E extends E2JBaseCowEntity<E>> void registerCowEntityRenderer(EntityType<E> entity, String identifier) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> new E2JCowRenderer(context, identifier));
    }

    private static <E extends E2JBaseMonoColorSheepEntity<E>> void registerMonoColorSheepEntityRenderer(EntityType<E> entity, String identifier) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> new E2JMonoColorSheepRenderer(context, identifier));
    }

    private static <E extends E2JBasePigEntity<E>> void registerPigEntityRenderer(EntityType<E> entity, String identifier) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> new E2JPigRenderer(context, identifier));
    }

    private static <E extends E2JBaseRabbitEntity<E>> void registerRabbitEntityRenderer(EntityType<E> entity, String identifier) {
        EntityRendererRegistry.INSTANCE.register(entity, (context) -> new E2JRabbitRenderer(context, identifier));
    }

//    private static void registerZombieEntityRenderer(EntityType<?> entity, String identifier) {
//        EntityRendererRegistry.INSTANCE.register(entity, (dispatcher, context) -> new E2JZombieRenderer(dispatcher, identifier));
//    }

}
