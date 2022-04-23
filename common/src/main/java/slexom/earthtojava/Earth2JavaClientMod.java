package slexom.earthtojava;

import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.renderer.RendererInit;

public class Earth2JavaClientMod {

    public static void initializeClient() {
        EntityModelLayersInit.init();
        RendererInit.init();
    }


}
