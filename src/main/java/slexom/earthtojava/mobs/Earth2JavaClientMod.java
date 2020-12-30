package slexom.earthtojava.mobs;

import net.fabricmc.api.ClientModInitializer;
import slexom.earthtojava.mobs.init.EntityModeLayersInit;
import slexom.earthtojava.mobs.init.renderer.RendererInit;

public class Earth2JavaClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        addBedTextureToAtlas();
        EntityModeLayersInit.init();
        RendererInit.init();
    }

    private void addBedTextureToAtlas() {
//        Identifier sprite = new Identifier("earthtojavamobs:entity/bed/rainbow");
//        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BEDS_ATLAS_TEXTURE).register((atlasTexture, registry) -> registry.register(sprite));
    }
}
