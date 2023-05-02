package slexom.earthtojava.quilt;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import slexom.earthtojava.Earth2JavaClientMod;

public class Earth2JavaClientModQuilt implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient(ModContainer mod) {
        Earth2JavaClientMod.initializeClient();
    }

}
