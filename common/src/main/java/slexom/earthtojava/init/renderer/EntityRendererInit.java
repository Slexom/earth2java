package slexom.earthtojava.init.renderer;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import slexom.earthtojava.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.client.renderer.entity.*;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.init.BlockEntityTypeInit;
import slexom.earthtojava.init.EntityTypesInit;

public class EntityRendererInit {

    public static void init() {
        registerEntitiesRenderer();
        registerProjectileRenderer();
        registerBlockEntityRenderer();
    }

    private static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.register(BlockEntityTypeInit.RAINBOW_BED.get(), RainbowBedBlockEntityRenderer::new);
    }

    private static void registerProjectileRenderer() {
        EntityRendererRegistry.register(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT, FlyingItemEntityRenderer::new);
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
        registerChickenEntityRenderer(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT, EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_NAME);

        registerCowEntityRenderer(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, EntityTypesInit.ALBINO_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, EntityTypesInit.ASHEN_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, EntityTypesInit.COOKIE_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT, EntityTypesInit.CREAM_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT, EntityTypesInit.DAIRY_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, EntityTypesInit.PINTO_COW_REGISTRY_NAME);
        registerCowEntityRenderer(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, EntityTypesInit.SUNSET_COW_REGISTRY_NAME);

        registerShearableCowEntityRenderer(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, EntityTypesInit.WOOLY_COW_REGISTRY_NAME);
        registerShearableCowEntityRenderer(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, EntityTypesInit.UMBRA_COW_REGISTRY_NAME);

        registerPigEntityRenderer(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT, EntityTypesInit.MOTTLED_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, EntityTypesInit.PALE_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_NAME);
        registerPigEntityRenderer(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME);

        registerMonoColorSheepEntityRenderer(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT, EntityTypesInit.FUZZY_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, EntityTypesInit.INKY_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT, EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, EntityTypesInit.PATCHED_SHEEP_REGISTRY_NAME);
        registerMonoColorSheepEntityRenderer(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME);

        registerRabbitEntityRenderer(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.FRECKLED_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME);
        registerRabbitEntityRenderer(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME);
    }

    private static void registerSpecialVariantEntitiesRenderer() {
        EntityRendererRegistry.register(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, CluckshroomRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, HornedSheepRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, MoobloomRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.MOOLIP_REGISTRY_OBJECT, MoolipRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, MuddyPigRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, SkeletonWolfRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, TropicalSlimeRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, WoolyCowRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, FurnaceGolemRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, MelonGolemRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, JumboRabbitRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, JollyLlamaRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BoneSpiderRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, RainbowSheepRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, FancyChickenRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, BoulderingZombieRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, LobberZombieRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, VilerWitchRenderer::new);
        EntityRendererRegistry.register(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT, SootyPigRenderer::new);
    }

    private static <E extends E2JBaseChickenEntity> void registerChickenEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JChickenRenderer(context, identifier));
    }

    private static <E extends E2JBaseCowEntity> void registerCowEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JCowRenderer(context, identifier));
    }

    private static <E extends E2JBaseShearableCowEntity<E>> void registerShearableCowEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JShearableCowRenderer(context, identifier));
    }

    private static <E extends E2JBaseMonoColorSheepEntity> void registerMonoColorSheepEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JMonoColorSheepRenderer(context, identifier));
    }

    private static <E extends E2JBasePigEntity> void registerPigEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JPigRenderer(context, identifier));
    }

    private static <E extends E2JBaseRabbitEntity> void registerRabbitEntityRenderer(RegistrySupplier<EntityType<E>> entity, String identifier) {
        EntityRendererRegistry.register(entity, (context) -> new E2JRabbitRenderer(context, identifier));
    }

}
