package slexom.earthtojava.mobs.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class RubyOreFeature extends Feature<RubyOreFeatureConfig> {
    public RubyOreFeature(Codec<RubyOreFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, RubyOreFeatureConfig rubyOreFeatureConfig) {
        if (structureWorldAccess.getBlockState(blockPos).isOf(rubyOreFeatureConfig.target.getBlock())) {
            structureWorldAccess.setBlockState(blockPos, rubyOreFeatureConfig.state, 2);
        }

        return true;
    }
}