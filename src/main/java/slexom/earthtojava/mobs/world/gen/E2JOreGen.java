package slexom.earthtojava.mobs.world.gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.config.E2JModConfig;
import slexom.earthtojava.mobs.init.BlockInit;

import java.util.Random;

public class E2JOreGen {

    public static void generateOre() {
        for (Biome biome : ForgeRegistries.BIOMES) {

            biome.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    new OreFeature(OreFeatureConfig.field_236566_a_) {
                        @Override
                        public boolean func_230362_a_(ISeedReader seedReader, StructureManager structureManager, ChunkGenerator generator, Random random, BlockPos pos, OreFeatureConfig config) {
                            if (!E2JModConfig.canRubyOreGenerate) {
                                return false;
                            }
                            RegistryKey<DimensionType> dimensionType = seedReader.getWorld().func_234922_V_();
                            boolean dimensionCriteria = false;
                            if (dimensionType == DimensionType.field_235999_c_)
                                dimensionCriteria = true;
                            if (!dimensionCriteria)
                                return false;
                            return super.func_230362_a_(seedReader, structureManager, generator, random, pos, config);
                        }
                    }.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.RUBY_ORE.get().getDefaultState(), 8))
                            .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(E2JModConfig.rubyOreCount, E2JModConfig.rubyOreBottomOffset, E2JModConfig.rubyOreTopOffset, E2JModConfig.rubyOreMaximum)))
            );
        }
    }

}
