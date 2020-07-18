package slexom.earthtojava.mobs.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;
import slexom.earthtojava.mobs.client.renderer.entity.*;
import slexom.earthtojava.mobs.client.renderer.tileentity.RainbowBedTileEntityRenderer;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.EntityTypesInit;
import slexom.earthtojava.mobs.init.FluidInit;
import slexom.earthtojava.mobs.init.TileEntityTypeInit;


@EventBusSubscriber(modid = EarthToJavaMobsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(EarthToJavaMobsMod.MOD_ID + " Client Mod Event Subscriber");

    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_STILL.get(), RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_FLOWING.get(), RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(BlockInit.BUTTERCUP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_BUTTERCUP.get(), RenderType.getCutout());
        registerEntityRenderer();
        registerProjectileRenderer();
        registerTileEntityRenderer();
    }

    private static void registerTileEntityRenderer() {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.RAINBOW_BED.get(), RainbowBedTileEntityRenderer::new);
    }

    private static void registerProjectileRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), renderManagerIn -> new SpriteRenderer<>(renderManagerIn, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), renderManagerIn -> new SpriteRenderer<>(renderManagerIn, Minecraft.getInstance().getItemRenderer()));
    }

    private static void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.ASHEN_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), CluckshroomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JOneColorSheepRenderer<>(renderManagerIn, EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT.get(), GlowSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), HornedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JOneColorSheepRenderer<>(renderManagerIn, EntityTypesInit.INKY_SHEEP_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), MoobloomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), MuddyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PALE_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JOneColorSheepRenderer<>(renderManagerIn, EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), SkeletonWolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.SUNSET_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), TropicalSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), WoolyCowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get(), FurnaceGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get(), MelonGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.ALBINO_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new JumboRabbitRenderer(renderManagerIn, EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT.get(), JollyLlamaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), BoneSpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT.get(), E2JWanderingTraderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT.get(), RainbowSheepRenderer::new);
    }

    @SubscribeEvent
    public static void bedAtlas(TextureStitchEvent.Pre event) {
        ResourceLocation rainbowBedTexture = new ResourceLocation(EarthToJavaMobsMod.MOD_ID, "entity/bed/rainbow");
        if (event.getMap().getTextureLocation() == Atlases.BED_ATLAS) {
            event.addSprite(rainbowBedTexture);
        }
    }

}