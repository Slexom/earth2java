package slexom.earthtojava.mobs.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.Earth2JavaMod;

public class RegisterHelper {
    private static Item.Settings commonSettings = new Item.Settings().group(Earth2JavaMod.ITEM_GROUP);

    public static Block registerBlock(String registryName, Block block, boolean registerItem) {
        if (registerItem) {
            registerItem(registryName, new BlockItem(block, commonSettings));
        }
        return Registry.register(Registry.BLOCK, new Identifier(Earth2JavaMod.MOD_ID, registryName), block);
    }

    public static Item registerItem(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Earth2JavaMod.MOD_ID, registryName), item);
    }

}
