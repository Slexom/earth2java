package slexom.earthtojava.mobs;

import net.fabricmc.api.ClientModInitializer;
import slexom.earthtojava.mobs.init.renderer.RendererInit;

public class Earth2JavaClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RendererInit.init();
    }

}
