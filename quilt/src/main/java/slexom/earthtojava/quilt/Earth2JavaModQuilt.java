package slexom.earthtojava.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import slexom.earthtojava.Earth2JavaMod;

public class Earth2JavaModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Earth2JavaMod.initialize();
    }

}
