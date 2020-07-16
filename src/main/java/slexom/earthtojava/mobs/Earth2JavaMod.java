package slexom.earthtojava.mobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.EntityTypesInit;
import slexom.earthtojava.mobs.init.ItemInit;

public class Earth2JavaMod implements ModInitializer {

	public static final String MOD_ID = "earthtojavamobs";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, MOD_ID),
			() -> new ItemStack(Blocks.COBBLESTONE));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		BlockInit.init();
		ItemInit.init();
		EntityTypesInit.init();
		System.out.println("Hello Fabric world!");
	}
}
