package slexom.earthtojava.mobs.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import slexom.earthtojava.mobs.Earth2JavaMod;

public class FeatureInit {

    public static LakeFeature MUD_LAKE;

    public static void init() {
        MUD_LAKE = Registry.register(Registry.FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "mud_lake"), new LakeFeature(SingleStateFeatureConfig.CODEC));
    }
}

