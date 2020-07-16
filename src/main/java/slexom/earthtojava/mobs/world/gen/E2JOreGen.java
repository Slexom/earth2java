package slexom.earthtojava.mobs.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.init.BlockInit;

import java.util.Random;

public class E2JOreGen {

    public static void generateOre() {
        for (Biome biome : ForgeRegistries.BIOMES) {

            biome.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    new OreFeature(OreFeatureConfig::deserialize) {
                        @Override
                        public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, OreFeatureConfig config) {
                            DimensionType dimensionType = world.getDimension().getType();
                            boolean dimensionCriteria = false;
                            if (dimensionType == DimensionType.OVERWORLD)
                                dimensionCriteria = true;
                            if (!dimensionCriteria)
                                return false;
                            return super.place(world, generator, rand, pos, config);
                        }
                    }.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.RUBY_ORE.get().getDefaultState(), 8))
                            .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 0, 0, 16)))
            );
        }
    }

}
