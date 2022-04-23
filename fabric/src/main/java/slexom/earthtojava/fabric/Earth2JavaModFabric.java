package slexom.earthtojava.fabric;

import net.fabricmc.api.ModInitializer;
import slexom.earthtojava.Earth2JavaMod;

public class Earth2JavaModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Earth2JavaMod.initialize();
    }

}
