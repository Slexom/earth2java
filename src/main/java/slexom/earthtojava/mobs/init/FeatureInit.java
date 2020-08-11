package slexom.earthtojava.mobs.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.world.gen.feature.ModifiableGeneration;
import slexom.earthtojava.mobs.world.gen.feature.RubyOreFeature;
import slexom.earthtojava.mobs.world.gen.feature.RubyOreFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FeatureInit {

    public static LakeFeature MUD_LAKE;
     public static ConfiguredFeature<?, ?> RUBY_ORE;

    public static void init() {

        MUD_LAKE = Registry.register(Registry.FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "mud_lake"), new LakeFeature(SingleStateFeatureConfig.CODEC));
        RUBY_ORE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "ruby_ore"), Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, BlockInit.RUBY_ORE.getDefaultState(), 8))  .decorate(Decorator.EMERALD_ORE.configure(DecoratorConfig.DEFAULT)));
    }





}

