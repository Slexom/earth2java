package slexom.earthtojava.forge;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slexom.earthtojava.Earth2JavaClientMod;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.client.renderer.entity.*;
import slexom.earthtojava.init.BlockEntityTypeInit;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Earth2JavaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Earth2JavaModClientForge {
	@SubscribeEvent
	public static void clientInit(final FMLClientSetupEvent event) {

		EntityModelLayersInit.init();
		event.enqueueWork(() -> {
			Earth2JavaClientMod.onPostInit();

			// if (ModList.get().isLoaded("cloth_config")) {
			//     ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
			//             new ConfigScreenHandler.ConfigScreenFactory(
			//                     (mc, screen) -> ConfigScreenBuilder.createConfigScreen(Earth2JavaMod.getConfig(), screen)
			//             )
			//     );
			// }
		});
	}

	@SubscribeEvent
	public static void bedAtlas(TextureStitchEvent.Pre event) {
		Identifier rainbowBedTexture = new Identifier(Earth2JavaMod.MOD_ID, "entity/bed/rainbow");
		if (event.getAtlas().getId() == TexturedRenderLayers.BEDS_ATLAS_TEXTURE) {
			event.addSprite(rainbowBedTexture);
		}
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (Map.Entry<EntityModelLayer, Supplier<TexturedModelData>> entry : EntityModelLayersInit.E2J_MODEL_LAYERS.entrySet()) {
			event.registerLayerDefinition(entry.getKey(), entry.getValue());
		}
	}

	@SubscribeEvent
	public static void event(EntityRenderersEvent.RegisterRenderers event) {

		event.registerEntityRenderer(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), FlyingItemEntityRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), FlyingItemEntityRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get(), FlyingItemEntityRenderer::new);

		event.registerEntityRenderer(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), CluckshroomRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), HornedSheepRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), MoobloomRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.MOOLIP_REGISTRY_OBJECT.get(), MoolipRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), MuddyPigRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), SkeletonWolfRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), TropicalSlimeRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), WoolyCowRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get(), FurnaceGolemRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get(), MelonGolemRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), JumboRabbitRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT.get(), JollyLlamaRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), BoneSpiderRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT.get(), RainbowSheepRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT.get(), FancyChickenRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT.get(), BoulderingZombieRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT.get(), LobberZombieRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT.get(), VilerWitchRenderer::new);
		event.registerEntityRenderer(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT.get(), SootyPigRenderer::new);

		event.registerEntityRenderer(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.chickenRendererFactory(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_NAME));

		event.registerEntityRenderer(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.ALBINO_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.ASHEN_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.COOKIE_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.CREAM_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.DAIRY_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.PINTO_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.cowRendererFactory(EntityTypesInit.SUNSET_COW_REGISTRY_NAME));

		event.registerEntityRenderer(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.shearableCowRendererFactory(EntityTypesInit.WOOLY_COW_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.shearableCowRendererFactory(EntityTypesInit.UMBRA_COW_REGISTRY_NAME));

		event.registerEntityRenderer(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.pigRendererFactory(EntityTypesInit.MOTTLED_PIG_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.pigRendererFactory(EntityTypesInit.PALE_PIG_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.pigRendererFactory(EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.pigRendererFactory(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.pigRendererFactory(EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME));

		event.registerEntityRenderer(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.FUZZY_SHEEP_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.INKY_SHEEP_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.PATCHED_SHEEP_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.monoColorSheepRendererFactory(EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME));

		event.registerEntityRenderer(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME));
		event.registerEntityRenderer(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT.get(), E2JEntityRendererFactory.rabbitRendererFactory(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME));

		event.registerBlockEntityRenderer(BlockEntityTypeInit.RAINBOW_BED.get(), RainbowBedBlockEntityRenderer::new);
	}
}