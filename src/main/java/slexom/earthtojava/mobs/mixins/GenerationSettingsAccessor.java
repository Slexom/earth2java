package slexom.earthtojava.mobs.mixins;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author shartte
 * Allows the settings in a Biome's generation settings to be modified.
 */
@Mixin(GenerationSettings.class)
public interface GenerationSettingsAccessor {

    @Accessor
    List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures();

    @Accessor
    void setFeatures(List<List<Supplier<ConfiguredFeature<?, ?>>>> features);

    @Accessor
    List<Supplier<ConfiguredStructureFeature<?, ?>>> getStructureFeatures();

    @Accessor
    void setStructureFeatures(List<Supplier<ConfiguredStructureFeature<?, ?>>> structureFeatures);

}