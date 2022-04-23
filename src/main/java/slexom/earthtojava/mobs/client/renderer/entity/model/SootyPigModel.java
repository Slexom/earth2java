package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
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
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, dilation).uv(16, 16).cuboid(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, dilation), ModelTransform.pivot(0.0F, 12.0F, -6.0F));
        modelPartData.addChild(
                "right_hind_leg",
                legsModelPartBuilder(0, 42, dilation),
                ModelTransform.pivot(-3.0F, 18.0F, 7.0F));
        modelPartData.addChild(
                "left_hind_leg",
                legsModelPartBuilder(16, 42, dilation),
                ModelTransform.pivot(3.0F, 18.0F, 7.0F));
        modelPartData.addChild(
                "right_front_leg",
                legsModelPartBuilder(0, 32, dilation),
                ModelTransform.pivot(-3.0F, 18.0F, -5.0F));
        modelPartData.addChild(
                "left_front_leg",
                legsModelPartBuilder(16, 32, dilation),
                ModelTransform.pivot(3.0F, 18.0F, -5.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
