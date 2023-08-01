package slexom.earthtojava.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import slexom.earthtojava.Earth2JavaClientMod;

public class Earth2JavaClientModQuilt implements ClientModInitializer {

	@Override
	@ClientOnly
	public void onInitializeClient(ModContainer mod) {
		Earth2JavaClientMod.initializeClient();
		Earth2JavaClientMod.onPostInit();
	}

}
