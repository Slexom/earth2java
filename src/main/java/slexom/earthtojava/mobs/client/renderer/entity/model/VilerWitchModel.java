package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class VilerWitchModel<T extends Entity> extends VillagerResemblingModel<T> {

    private boolean liftingNose;

    public VilerWitchModel(ModelPart root) {
        super(root);
    }

//    public VilerWitchModel(float scale) {
//        super(scale, 96, 128);
//        this.wart.setPivot(0.0F, -2.0F, 0.0F);
//        this.wart.setTextureOffset(0, 0).addCuboid(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, -0.25F);
//        this.nose.addChild(this.wart);
//
//        this.head = (new ModelPart(this)).setTextureSize(96, 128);
//        this.head.setPivot(0.0F, 0.0F, 0.0F);
//        this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, scale);
//
//        this.field_17141 = (new ModelPart(this)).setTextureSize(96, 128);
//        this.field_17141.setPivot(-10.0F, -11.03125F, -10.0F);
//        this.field_17141.setTextureOffset(0, 64).addCuboid(0.0F, 0.0F, 0.0F, 20.0F, 3.0F, 20.0F);
//        this.head.addChild(this.field_17141);
//        this.head.addChild(this.nose);
//
//        ModelPart hatBox1 = (new ModelPart(this)).setTextureSize(96, 128);
//        hatBox1.setPivot(6.75F, -4.0F, 7.0F);
//        hatBox1.setTextureOffset(0, 87).addCuboid(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F);
//        hatBox1.pitch = -0.05235988F;
//        hatBox1.roll = 0.02617994F;
//        this.field_17141.addChild(hatBox1);
//        ModelPart hatBox2 = (new ModelPart(this)).setTextureSize(96, 128);
//        hatBox2.setPivot(1.75F, -4.0F, 2.0F);
//        hatBox2.setTextureOffset(0, 98).addCuboid(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F);
//        hatBox2.pitch = -0.10471976F;
//        hatBox2.roll = 0.05235988F;
//        hatBox1.addChild(hatBox2);
//        ModelPart hatPoint = (new ModelPart(this)).setTextureSize(96, 128);
//        hatPoint.setPivot(1.75F, -2.0F, 2.0F);
//        hatPoint.setTextureOffset(16, 103).addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.25F);
//        hatPoint.pitch = -0.20943952F;
//        hatPoint.roll = 0.10471976F;
//        hatBox2.addChild(hatPoint);
//        this.torso = (new ModelPart(this)).setTextureSize(96, 128);
//        this.torso.setPivot(0.0F, 0.0F, 0.0F);
//        this.torso.setTextureOffset(16, 20).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, scale);
//        this.robe = (new ModelPart(this)).setTextureSize(96, 128);
//        this.robe.setPivot(0.0F, 0.0F, 0.0F);
//        this.robe.setTextureOffset(0, 38).addCuboid(-5.0F, 0.0F, -3.0F, 10.0F, 19.0F, 7.0F, scale + 0.5F);
//        this.torso.addChild(this.robe);
//        this.arms.setTextureSize(96, 128).setTextureOffset(68, 0).addCuboid(-8.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, scale);
//        this.arms.setTextureSize(96, 128).setTextureOffset(68, 0).addCuboid(4.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, scale, true);
//        this.hood.setPivot(-4.0F, -10.9F, -4.0F);
//        this.hood.setTextureOffset(32, 0).addCuboid(-0.5F, 0.0F, -0.5F, 9.0F, 11.0F, 9.0F);
//        this.head.addChild(this.hood);
//    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = VillagerResemblingModel.getModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), ModelTransform.NONE);
        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(0, 64).cuboid(0.0F, 0.0F, 0.0F, 20.0F, 3.0F, 20.0F), ModelTransform.pivot(-10.0F, -11.03125F, -10.0F));
        ModelPartData hat2 = hat.addChild("hat2", ModelPartBuilder.create().uv(0, 87).cuboid(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F), ModelTransform.of(6.75F, -4.0F, 7.0F, -0.05235988F, 0.0F, 0.02617994F));
        ModelPartData hat3 = hat2.addChild("hat3", ModelPartBuilder.create().uv(0, 98).cuboid(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F), ModelTransform.of(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
        hat3.addChild("hat4", ModelPartBuilder.create().uv(16, 103).cuboid(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));
        hat.addChild("hat_rim", ModelPartBuilder.create().uv(20, 103).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), ModelTransform.NONE);
        ModelPartData nose = head.getChild("nose");
        nose.addChild("mole", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new Dilation(-0.25F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), ModelTransform.NONE);
        body.addChild("jacket", ModelPartBuilder.create().uv(0, 38).cuboid(-5.0F, 0.0F, -3.0F, 10.0F, 19.0F, 7.0F, new Dilation(0.5F)), ModelTransform.NONE);
        modelPartData.addChild("arms", ModelPartBuilder.create()
                        .uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F)
                        .uv(44, 22).cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true)
                        .uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F)
                        .uv(68, 0).cuboid(-8.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F)
                        .uv(68, 0).cuboid(4.5F, -2.5F, -3.0F, 5.0F, 5.0F, 6.0F, true)
                , ModelTransform.of(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
        head.addChild("hood", ModelPartBuilder.create().uv(32, 0).cuboid(-0.5F, 0.0F, -0.5F, 9.0F, 11.0F, 9.0F), ModelTransform.pivot(-4.0F, -10.9F, -4.0F));
        return TexturedModelData.of(modelData, 96, 128);
    }


    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.nose.setPivot(0.0F, -2.0F, 0.0F);
        float f = 0.01F * (float) (entity.getId() % 10);
        this.nose.pitch = MathHelper.sin((float) entity.age * f) * 4.5F * 0.017453292F;
        this.nose.yaw = 0.0F;
        this.nose.roll = MathHelper.cos((float) entity.age * f) * 2.5F * 0.017453292F;
        if (this.liftingNose) {
            this.nose.setPivot(0.0F, 1.0F, -1.5F);
            this.nose.pitch = -0.9F;
        }

    }

    public ModelPart getNose() {
        return this.nose;
    }

    public void setLiftingNose(boolean liftingNose) {
        this.liftingNose = liftingNose;
    }
}
