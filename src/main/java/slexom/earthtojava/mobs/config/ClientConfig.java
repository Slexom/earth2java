package slexom.earthtojava.mobs.config;

import net.minecraftforge.common.ForgeConfigSpec;


final class ClientConfig {

    final ForgeConfigSpec.BooleanValue showDescription;

    ClientConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");
        showDescription = builder
                .comment("Show(true) or hide(false) Minecraft Earth description for items")
                .define("showDescription", true);
    }

}