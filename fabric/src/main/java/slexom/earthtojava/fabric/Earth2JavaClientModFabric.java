package slexom.earthtojava.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.Earth2JavaClientMod;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.renderer.RendererInit;

public class Earth2JavaClientModFabric implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        addBedTextureToAtlas();
        Earth2JavaClientMod.initializeClient();
    }

    private void addBedTextureToAtlas() {
        Identifier sprite = new Identifier("earthtojavamobs:entity/bed/rainbow");
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.BEDS_ATLAS_TEXTURE).register((atlasTexture, registry) -> registry.register(sprite));
    }
}
