package slexom.earthtojava.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import slexom.earthtojava.init.ItemInit;

import java.util.function.BiConsumer;

public class Earth2JavaDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(Earth2JavaLootTables::new);

    }

    private static class Earth2JavaLootTables extends SimpleFabricLootTableProvider {
        public Earth2JavaLootTables(FabricDataGenerator dataGenerator) {
            super(dataGenerator, LootContextTypes.BLOCK);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
            biConsumer.accept(ItemInit.RAINBOW_CARPET.getId(), BlockLootTableGenerator.drops(ItemInit.RAINBOW_CARPET.get(), ConstantLootNumberProvider.create(1.0F)));
        }
    }
}