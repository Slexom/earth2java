package slexom.earthtojava.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.JumboRabbitModel;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

public class JumboRabbitRenderer extends MobEntityRenderer<JumboRabbitEntity, JumboRabbitModel<JumboRabbitEntity>> {


	public JumboRabbitRenderer(EntityRendererFactory.Context context) {
		super(context, new JumboRabbitModel<>(context.getPart(EntityModelLayersInit.JUMBO_RABBIT_ENTITY_MODEL_LAYER)), 0.6F);
	}

	@Override
	public Identifier getTexture(JumboRabbitEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("rabbit", EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("rabbit", EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}