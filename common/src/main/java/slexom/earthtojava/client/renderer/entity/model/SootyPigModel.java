package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import slexom.earthtojava.entity.base.E2JBasePigEntity;

@Environment(EnvType.CLIENT)
public class SootyPigModel extends QuadrupedEntityModel<E2JBasePigEntity> {
	public SootyPigModel(ModelPart root) {
		super(root, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
	}

	private static ModelPartBuilder legsModelPartBuilder(int textureX, int textureY, Dilation dilation) {
		return ModelPartBuilder.create().uv(textureX, textureY).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, dilation);
	}

	public static TexturedModelData getTexturedModelData(Dilation dilation) {
		ModelData modelData = QuadrupedEntityModel.getModelData(6, dilation);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, dilation).uv(16, 16).cuboid(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, dilation), ModelTransform.pivot(0.0F, 12.0F, -6.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, legsModelPartBuilder(0, 42, dilation), ModelTransform.pivot(-3.0F, 18.0F, 7.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, legsModelPartBuilder(16, 42, dilation), ModelTransform.pivot(3.0F, 18.0F, 7.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, legsModelPartBuilder(0, 32, dilation), ModelTransform.pivot(-3.0F, 18.0F, -5.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, legsModelPartBuilder(16, 32, dilation), ModelTransform.pivot(3.0F, 18.0F, -5.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
}
