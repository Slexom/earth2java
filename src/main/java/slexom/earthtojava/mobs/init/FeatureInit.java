package slexom.earthtojava.mobs.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountNoiseDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.config.ModConfig;

public class FeatureInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static ConfiguredFeature<?, ?> FLOWER_BUTTERCUP_CONFIGURED_FEATURE;
    public static ConfiguredFeature<?, ?> MUD_LAKE_CONFIGURED_FEATURE;
    public static ConfiguredFeature<?, ?> ORE_RUBY_CONFIGURED_FEATURE;

    public static void init() {
        FLOWER_BUTTERCUP_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "flower_buttercup"), (Feature.FLOWER.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BUTTERCUP.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).build()).decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE).decorate(ConfiguredFeatures.Decorators.HEIGHTMAP).spreadHorizontally()).decorate(Decorator.COUNT_NOISE.configure(new CountNoiseDecoratorConfig(-0.8D, 15, 4))));
        MUD_LAKE_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "lake_mud"), Feature.LAKE.configure(new SingleStateFeatureConfig(BlockInit.MUD_BLOCK.getDefaultState())).decorate(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(config.mudLakeConfig.mudLakeFrequency))));
        ORE_RUBY_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "ruby_ore"), Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, BlockInit.RUBY_ORE.getDefaultState(), 8)).decorate(Decorator.EMERALD_ORE.configure(DecoratorConfig.DEFAULT)));
    }

}

