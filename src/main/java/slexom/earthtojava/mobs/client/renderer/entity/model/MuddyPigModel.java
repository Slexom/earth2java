package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import slexom.earthtojava.entity.passive.MuddyPigEntity;

@Environment(EnvType.CLIENT)
public class MuddyPigModel<T extends MuddyPigEntity> extends QuadrupedEntityModel<T> {

    public MuddyPigModel(ModelPart root) {
        super(root, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        float mudBoxX = -1.0F;
        float mudBoxY = -5.0F;
        float mudBoxZ = -7.0F;
        ModelData modelData = QuadrupedEntityModel.getModelData(6, dilation);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, dilation)
                        .uv(16, 16).cuboid(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, dilation)
                        .uv(24, 0).cuboid(mudBoxX, mudBoxY, mudBoxZ, 4.0F, 1.0F, 4.0F, dilation)
                        .uv(40, 0).cuboid(mudBoxX, mudBoxY - 6.0F, mudBoxZ + 2.0F, 4.0F, 6.0F, 1.0F, dilation),
                ModelTransform.pivot(0.0F, 12.0F, -6.0F));
        return TexturedModelData.of(modelData, 64, 32);

    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.head.roll = entityIn.getShakeAngle(partialTick, -0.07F);
        this.head.pitch = (float) Math.PI / 8.0F;
        this.body.roll = entityIn.getShakeAngle(partialTick, -0.14F);
    }

}
