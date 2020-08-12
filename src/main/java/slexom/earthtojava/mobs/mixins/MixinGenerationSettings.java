package slexom.earthtojava.mobs.mixins;


import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import slexom.earthtojava.mobs.world.gen.feature.ExtendedGenerationSettings;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(GenerationSettings.class)
public class MixinGenerationSettings implements ExtendedGenerationSettings {
    @Shadow
    @Final
    private Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> carvers;

    @Mutable
    @Shadow
    @Final
    private List<List<Supplier<ConfiguredFeature<?, ?>>>> features;


    @Override
    public void setFeatures(List<List<Supplier<ConfiguredFeature<?, ?>>>> features) {
        this.features = features;
    }
}
