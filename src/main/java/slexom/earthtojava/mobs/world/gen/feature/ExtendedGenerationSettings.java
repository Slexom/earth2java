package slexom.earthtojava.mobs.world.gen.feature;

import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Supplier;

public interface ExtendedGenerationSettings {
    void setFeatures(List<List<Supplier<ConfiguredFeature<?, ?>>>> features);
}