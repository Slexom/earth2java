package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.JollyLlamaModel;
import slexom.earthtojava.entity.passive.JollyLlamaEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class JollyLlamaRenderer extends MobEntityRenderer<JollyLlamaEntity, JollyLlamaModel> {

	public JollyLlamaRenderer(EntityRendererFactory.Context context) {
		super(context, new JollyLlamaModel(context.getPart(EntityModelLayersInit.JOLLY_LLAMA_ENTITY_MODEL_LAYER)), 0.7F);
	}

	@Override
	public Identifier getTexture(JollyLlamaEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("llama", RegistryNames.JOLLY_LLAMA_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("llama", RegistryNames.JOLLY_LLAMA_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}
