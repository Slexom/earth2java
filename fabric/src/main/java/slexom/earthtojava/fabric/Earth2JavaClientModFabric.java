package slexom.earthtojava.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.Earth2JavaClientMod;

public class Earth2JavaClientModFabric implements ClientModInitializer {

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		Earth2JavaClientMod.initializeClient();
		Earth2JavaClientMod.onPostInit();
	}

}
