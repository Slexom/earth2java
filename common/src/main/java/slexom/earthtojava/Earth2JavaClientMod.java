package slexom.earthtojava;

import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.renderer.BlockRendererInit;
import slexom.earthtojava.init.renderer.EntityRendererInit;

public class Earth2JavaClientMod {

	public static void initializeClient() {
		EntityModelLayersInit.init();
	}

	public static void onPostInit() {
		EntityRendererInit.init();
		BlockRendererInit.init();
	}

}
