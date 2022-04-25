package slexom.earthtojava.forge;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slexom.earthtojava.Earth2JavaClientMod;

public class Earth2JavaModClientForge {
    public static void init() {

    }

    public static void clientInit(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (Platform.getEnvironment() != Env.CLIENT) {
                return;
            }

            Earth2JavaClientMod.initializeClient();
        });
    }
}