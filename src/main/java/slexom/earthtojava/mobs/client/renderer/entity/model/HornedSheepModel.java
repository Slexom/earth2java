package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

@Environment(EnvType.CLIENT)
public class HornedSheepModel<T extends HornedSheepEntity> extends SheepEntityModel<T> {
    // private final ModelPart hornRight;
    // private final ModelPart hornLeft;

    public HornedSheepModel(ModelPart root) {
        super(root);
        // this.hornLeft = root.getChild("horn_left");
        // this.hornRight = root.getChild("horn_right");


    }

    public static TexturedModelData getTexturedModelData() {
        float hornX = -7.0F;
        float hornY = -5.0F;
        float hornZ = -4.0F;
        ModelData modelData = QuadrupedEntityModel.getModelData(12, Dilation.NONE);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData
                .addChild(
                        "head",
                        ModelPartBuilder
                                .create()
                                .uv(0, 0)
                                .cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F)
                                .uv(0, 32)
                                .cuboid(hornX, hornY, hornZ, 4.0F, 7.0F, 6.0F)
                                .uv(20, 32)
                                .cuboid(hornX, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F)
                                .uv(0, 32)
                                .cuboid(hornX + 10.0F, hornY, hornZ, 4.0F, 7.0F, 6.0F, true)
                                .uv(20, 32)
                                .cuboid(hornX + 10.0F, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F, true),
                        ModelTransform.pivot(0.0F, 6.0F, -8.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 8).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

}