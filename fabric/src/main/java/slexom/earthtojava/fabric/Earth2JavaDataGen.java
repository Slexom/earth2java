package slexom.earthtojava.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import slexom.earthtojava.init.ItemInit;

public class Earth2JavaDataGen implements DataGeneratorEntrypoint {


	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(Earth2JavaLootTables::new);
	}

	private static class Earth2JavaLootTables extends FabricBlockLootTableProvider {


		protected Earth2JavaLootTables(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void generate() {
			drops(ItemInit.RAINBOW_CARPET.get(), ConstantLootNumberProvider.create(1.0F));
		}
	}
}