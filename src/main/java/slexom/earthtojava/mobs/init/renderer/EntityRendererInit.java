package slexom.earthtojava.mobs.init.renderer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import slexom.earthtojava.mobs.client.renderer.entity.*;
import slexom.earthtojava.mobs.client.renderer.tileentity.RainbowBedTileEntityRenderer;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.EntityTypesInit;
import slexom.earthtojava.mobs.init.FluidInit;
import slexom.earthtojava.mobs.init.TileEntityTypeInit;

public class EntityRendererInit {

    public static void init() {
        registerEntityRenderer();
        registerProjectileRenderer();
        registerTileEntityRenderer();
    }

    private static void registerTileEntityRenderer() {
        BlockEntityRendererRegistry.INSTANCE.register(TileEntityTypeInit.RAINBOW_BED, RainbowBedTileEntityRenderer::new);
    }

    private static void registerProjectileRenderer() {
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT, (dispatcher, context) -> new FlyingItemEntityRenderer<>(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
    }

    private static void registerEntityRenderer() {
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, (dispatcher, context) -> new E2JChickenRenderer(dispatcher, EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, (dispatcher, context) -> new E2JCowRenderer(dispatcher, EntityTypesInit.ASHEN_COW_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, (dispatcher, context) -> new CluckshroomRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, (dispatcher, context) -> new E2JOneColorSheepRenderer<>(dispatcher, EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, (dispatcher, context) -> new GlowSquidRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, (dispatcher, context) -> new HornedSheepRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, (dispatcher, context) -> new E2JOneColorSheepRenderer<>(dispatcher, EntityTypesInit.INKY_SHEEP_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, (dispatcher, context) -> new E2JChickenRenderer(dispatcher, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, (dispatcher, context) -> new MoobloomRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, (dispatcher, context) -> new MuddyPigRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, (dispatcher, context) -> new E2JPigRenderer(dispatcher, EntityTypesInit.PALE_PIG_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, (dispatcher, context) -> new E2JPigRenderer(dispatcher, EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, (dispatcher, context) -> new E2JOneColorSheepRenderer<>(dispatcher, EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, (dispatcher, context) -> new SkeletonWolfRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, (dispatcher, context) -> new E2JPigRenderer(dispatcher, EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, (dispatcher, context) -> new E2JChickenRenderer(dispatcher, EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, (dispatcher, context) -> new E2JCowRenderer(dispatcher, EntityTypesInit.SUNSET_COW_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, (dispatcher, context) -> new TropicalSlimeRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, (dispatcher, context) -> new WoolyCowRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, (dispatcher, context) -> new E2JRabbitRenderer(dispatcher, EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, (dispatcher, context) -> new E2JRabbitRenderer(dispatcher, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, (dispatcher, context) -> new E2JRabbitRenderer(dispatcher, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, (dispatcher, context) -> new FurnaceGolemRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, (dispatcher, context) -> new MelonGolemRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, (dispatcher, context) -> new E2JCowRenderer(dispatcher, EntityTypesInit.ALBINO_COW_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, (dispatcher, context) -> new JumboRabbitRenderer(dispatcher, EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, (dispatcher, context) -> new JollyLlamaRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, (dispatcher, context) -> new BoneSpiderRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT, (dispatcher, context) -> new E2JWanderingTraderRenderer(dispatcher));
        EntityRendererRegistry.INSTANCE.register(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, (dispatcher, context) -> new RainbowSheepRenderer(dispatcher));
    }


}
