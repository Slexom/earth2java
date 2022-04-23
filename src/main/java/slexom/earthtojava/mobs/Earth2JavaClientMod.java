package slexom.earthtojava;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.renderer.RendererInit;

public class Earth2JavaClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        addBedTextureToAtlas();
        EntityModelLayersInit.init();
        RendererInit.init();
    }

    private void addBedTextureToAtlas() {
        Identifier sprite = new Identifier("earthtojavamobs:entity/bed/rainbow");
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BEDS_ATLAS_TEXTURE).register((atlasTexture, registry) -> registry.register(sprite));
    }
}
