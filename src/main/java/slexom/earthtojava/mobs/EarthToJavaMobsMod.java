package slexom.earthtojava.mobs;

import net.minecraft.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slexom.earthtojava.mobs.config.ConfigHolder;
import slexom.earthtojava.mobs.entity.EntitySpawn;
import slexom.earthtojava.mobs.init.*;
import slexom.earthtojava.mobs.world.spawner.E2JWanderingTraderSpawner;

/* TODO
 * Opzione attiva/disattiva extra tab
 * Opzione delay spawn trader
 */

@Mod(EarthToJavaMobsMod.MOD_ID)
public class EarthToJavaMobsMod {

    public static final String MOD_ID = "earthtojavamobs";

    public EarthToJavaMobsMod() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FluidInit.FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        TileEntityTypeInit.TILE_ENTITY_TYPES.register(modEventBus);
        EntityTypesInit.ENTITY_TYPES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        RecipesInit.RECIPES.register(modEventBus);
        modEventBus.register(this);
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(new E2JWanderingTraderSpawner());

        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);

    }

    private static void registerToComposter() {
        ComposterBlock.registerCompostable(0.65F, BlockInit.BUTTERCUP.get());
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            registerToComposter();
            EntitySpawn.init();
            FeatureInit.init();
        });
    }


}
