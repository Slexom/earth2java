package slexom.earthtojava.mobs;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.mobs.client.renderer.tileentity.RainbowBedItemStackTileEntityRenderer;
import slexom.earthtojava.mobs.config.ConfigHelper;
import slexom.earthtojava.mobs.config.ConfigHolder;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.EntityAttributeInit;
import slexom.earthtojava.mobs.item.E2JBlockItem;
import slexom.earthtojava.mobs.item.ModdedSpawnEggItem;
import slexom.earthtojava.mobs.world.gen.E2JOreGen;

@Mod.EventBusSubscriber(modid = EarthToJavaMobsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(EarthToJavaMobsMod.MOD_ID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        final ItemGroup ITEM_GROUP = EarthToJavaMobsMod.E2JItemGroup.instance;
        BlockInit.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof FlowingFluidBlock))
                .filter(block -> block.get() != BlockInit.POTTED_BUTTERCUP.get())
                .map(RegistryObject::get)
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(ITEM_GROUP);
                    final Item.Properties hiddenBlockProperties = new Item.Properties().group(null);
                    if (block == BlockInit.MELON_GOLEM_HEAD_BLINK.get() || block == BlockInit.MELON_GOLEM_HEAD_SHOOT.get()) {
                        final BlockItem blockItem = new E2JBlockItem(block, hiddenBlockProperties);
                        blockItem.setRegistryName(block.getRegistryName());
                        registry.register(blockItem);
                    } else if (block == BlockInit.RAINBOW_BED.get()) {
                        final Item.Properties bedProperties = new Item.Properties().setISTER(() -> RainbowBedItemStackTileEntityRenderer::new).group(ITEM_GROUP);
                        final BlockItem blockItem = new E2JBlockItem(block, bedProperties);
                        blockItem.setRegistryName(block.getRegistryName());
                        registry.register(blockItem);
                    } else {
                        final BlockItem blockItem = new E2JBlockItem(block, properties);
                        blockItem.setRegistryName(block.getRegistryName());
                        registry.register(blockItem);
                    }
                });
    }

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
            ConfigHelper.bakeClient(config);
            LOGGER.debug("Baked client config");
//        } else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
//            ConfigHelper.bakeServer(config);
//            LOGGER.debug("Baked server config");
        } else if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            ConfigHelper.bakeCommon(config);
            LOGGER.debug("Baked common config");
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModdedSpawnEggItem.initUnaddedEggs();
        EntityAttributeInit.init();
    }

    @SubscribeEvent
    public static void registerOres(FMLLoadCompleteEvent event) {
        E2JOreGen.generateOre();
    }

}