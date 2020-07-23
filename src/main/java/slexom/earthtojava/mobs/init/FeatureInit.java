package slexom.earthtojava.mobs.init;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.config.E2JModConfig;

import java.util.Random;

public class FeatureInit {

    public static void init() {
        ForgeRegistries.BIOMES.getValues().forEach(FeatureInit::generate);
    }

    public static void generate(Biome biome) {
        addButtercup(biome);
        addMudLake(biome);
        addRubyOre(biome);
    }

    private static boolean isOverWorld(RegistryKey<DimensionType> dimension) {
        return dimension == DimensionType.field_235999_c_;
    }

    private static void addMudLake(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.LAKES, new LakesFeature(BlockStateFeatureConfig.field_236455_a_) {
            @Override
            public boolean func_230362_a_(ISeedReader seedReader, StructureManager structureManager, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
                RegistryKey<DimensionType> dimensionType = seedReader.getWorld().func_234922_V_();
//                boolean dimensionCriteria = false;
//                if (dimensionType == DimensionType.field_235999_c_)
//                    dimensionCriteria = true;
//                if (!dimensionCriteria)
//                    return false;
//                return super.func_230362_a_(seedReader, structureManager, generator, rand, pos, config);
                return isOverWorld(dimensionType) && super.func_230362_a_(seedReader, structureManager, generator, rand, pos, config);
            }
        }.withConfiguration(new BlockStateFeatureConfig(BlockInit.MUD_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(E2JModConfig.mudLakeFrequency))));
    }

    private static void addButtercup(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                new DefaultFlowersFeature(BlockClusterFeatureConfig.field_236587_a_) {
                    @Override
                    public boolean func_225559_a_(IWorld world, BlockPos pos, BlockClusterFeatureConfig config) {
                        RegistryKey<DimensionType> dimensionType = world.getWorld().func_234922_V_();
//                        boolean dimensionCriteria = false;
//                        if (dimensionType == DimensionType.field_235999_c_)
//                            dimensionCriteria = true;
//                        if (!dimensionCriteria)
//                            return false;
//                        return super.func_225559_a_(world, pos, config);
                        return isOverWorld(dimensionType) && super.func_225559_a_(world, pos, config);
                    }
                }
                        .withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BUTTERCUP.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).build())
                        .withPlacement(Placement.COUNT_HEIGHTMAP_32.configure(new FrequencyConfig(5)))
        );
    }

    private static void addRubyOre(Biome biome) {
        biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                new OreFeature(OreFeatureConfig.field_236566_a_) {
                    @Override
                    public boolean func_230362_a_(ISeedReader seedReader, StructureManager structureManager, ChunkGenerator generator, Random random, BlockPos pos, OreFeatureConfig config) {
                        if (!E2JModConfig.canRubyOreGenerate) {
                            return false;
                        }
                        RegistryKey<DimensionType> dimensionType = seedReader.getWorld().func_234922_V_();
//                        boolean dimensionCriteria = false;
//                        if (dimensionType == DimensionType.field_235999_c_)
//                            dimensionCriteria = true;
//                        if (!dimensionCriteria)
//                            return false;
//                        return super.func_230362_a_(seedReader, structureManager, generator, random, pos, config);
                        return isOverWorld(dimensionType) && super.func_230362_a_(seedReader, structureManager, generator, random, pos, config);
                    }
                }.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.RUBY_ORE.get().getDefaultState(), 8))
                        .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(E2JModConfig.rubyOreCount, E2JModConfig.rubyOreBottomOffset, E2JModConfig.rubyOreTopOffset, E2JModConfig.rubyOreMaximum)))
        );

    }
}
