package slexom.earthtojava.mobs.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public class RubyOreFeatureConfig implements FeatureConfig {
    public static final Codec<RubyOreFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(BlockState.CODEC.fieldOf("target").forGetter((rubyOreFeatureConfig) -> rubyOreFeatureConfig.target), BlockState.CODEC.fieldOf("state").forGetter((rubyOreFeatureConfig) -> rubyOreFeatureConfig.state)).apply(instance, RubyOreFeatureConfig::new));
    public final BlockState state;
    public final BlockState target;

    public RubyOreFeatureConfig(BlockState target, BlockState state) {
        this.target = target;
        this.state = state;
    }
}
