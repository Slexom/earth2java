package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.passive.JollyLlamaEntity;

@Environment(EnvType.CLIENT)
public class JollyLlamaModel extends EntityModel<JollyLlamaEntity> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart frontLeftLeg;
    private float headPitchModifier;

    public JollyLlamaModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.backRightLeg = root.getChild("right_back_leg");
        this.backLeftLeg = root.getChild("left_back_leg");
        this.frontRightLeg = root.getChild("right_front_leg");
        this.frontLeftLeg = root.getChild("left_front_leg");
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, dilation).uv(0, 14).cuboid("neck", -4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, dilation).uv(17, 0).cuboid("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, dilation).uv(17, 0).cuboid("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, dilation)
                        .uv(96, 16).cuboid("bells", -4.5F, -16.0F, -6.5F, 9.0F, 18.0F, 7.0F, dilation)
                        .uv(96, 0).cuboid("berries", -2.5F, -14.05F, -10.5F, 5.0F, 5.0F, 10.0F, dilation)
                        .uv(0, 47).cuboid("horn_left", 4.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, dilation)
                        .uv(0, 47).mirrored().cuboid("horn_right", -12.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, dilation),
                ModelTransform.pivot(0.0F, 7.0F, -6.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(29, 0).cuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, dilation), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(29, 29).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, dilation);
        modelPartData.addChild("right_back_leg", modelPartBuilder, ModelTransform.pivot(-3.5F, 10.0F, 6.0F));
        modelPartData.addChild("left_back_leg", modelPartBuilder, ModelTransform.pivot(3.5F, 10.0F, 6.0F));
        modelPartData.addChild("right_front_leg", modelPartBuilder, ModelTransform.pivot(-3.5F, 10.0F, -5.0F));
        modelPartData.addChild("left_front_leg", modelPartBuilder, ModelTransform.pivot(3.5F, 10.0F, -5.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    public void setAngles(JollyLlamaEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * ((float) Math.PI / 180F);
        this.head.yaw = headYaw * ((float) Math.PI / 180F);
        this.backRightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.backLeftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.frontRightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.frontLeftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        if (this.child) {
            matrices.push();
            matrices.scale(0.71428573F, 0.64935064F, 0.7936508F);
            matrices.translate(0.0D, 1.3125D, (double) 0.22F);
            this.head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.625F, 0.45454544F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            this.body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();
            matrices.push();
            matrices.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrices.translate(0.0D, 2.0625D, 0.0D);
            ImmutableList.of(this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg).forEach((p_228280_8_) -> {
                p_228280_8_.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            });
            matrices.pop();
        } else {
            ImmutableList.of(this.head, this.body, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg).forEach((p_228279_8_) -> {
                p_228279_8_.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            });
        }
    }

    public void animateModel(JollyLlamaEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.head.pivotY = 6.0F + entity.getNeckAngle(tickDelta) * 9.0F;
        this.headPitchModifier = entity.getHeadAngle(tickDelta);
    }
}