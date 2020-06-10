package net.slexom.earthtojavamobs;

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
import net.minecraftforge.registries.IForgeRegistry;
import net.slexom.earthtojavamobs.init.BlockInit;
import net.slexom.earthtojavamobs.item.ModdedSpawnEggItem;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    /**
     * This method will be called by Forge when it is time for the mod to register its Items.
     * This method will always be called after the Block registry method.
     */
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        // Automatically register BlockItems for all our Blocks
        BlockInit.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof FlowingFluidBlock))
                .map(RegistryObject::get)
                // You can do extra filtering here if you don't want some blocks to have an BlockItem automatically registered for them
                // .filter(block -> needsItemBlock(block))
                // Register the BlockItem for the block
                .forEach(block -> {
                    // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
                    final Item.Properties properties = new Item.Properties().group(ItemGroup.MISC);
                    // Create the new BlockItem with the block and it's properties
                    final BlockItem blockItem = new BlockItem(block, properties);
                    // Set the new BlockItem's registry name to the block's registry name
                    blockItem.setRegistryName(block.getRegistryName());
                    // Register the BlockItem
                    registry.register(blockItem);
                });
    }

    /**
     * This method will be called by Forge when a config changes.
     */
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
//        final ModConfig config = event.getConfig();
//        // Rebake the configs when they change
//        if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
//            ConfigHelper.bakeClient(config);
//            LOGGER.debug("Baked client config");
//        } else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
//            ConfigHelper.bakeServer(config);
//            LOGGER.debug("Baked server config");
//        }
    }

    /**
     * Exists to work around a limitation with Spawn Eggs:
     * Spawn Eggs require an EntityType, but EntityTypes are created AFTER Items.
     * Therefore it is "impossible" for modded spawn eggs to exist.
     * To get around this we have our own custom SpawnEggItem, but it needs
     * some extra setup after Item and EntityType registration completes.
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModdedSpawnEggItem.initUnaddedEggs();
    }

}