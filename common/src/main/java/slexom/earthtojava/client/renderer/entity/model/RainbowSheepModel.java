package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Environment(EnvType.CLIENT)
public class RainbowSheepModel<T extends E2JBaseMonoColorSheepEntity> extends SheepEntityModel<T> {

    public RainbowSheepModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = QuadrupedEntityModel.getModelData(12, Dilation.NONE);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), ModelTransform.pivot(0.0F, 6.0F, -8.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(36, 0).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

}
