package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import slexom.earthtojava.entity.passive.FancyChickenEntity;

@Environment(EnvType.CLIENT)
public class FancyChickenModel<T extends FancyChickenEntity> extends ChickenEntityModel<T> {

    private final ModelPart tail;
    private final ModelPart crest;

    public FancyChickenModel(ModelPart root) {
        super(root);
        this.tail = root.getChild("tail");
        this.crest = root.getChild("crest");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild("beak", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -6.0F, -4.0F, 4.0F, 2.0F, 2.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild("red_thing", ModelPartBuilder.create().uv(14, 4).cuboid(-0.5F, -4.0F, -3.0F, 1.0F, 2.0F, 2.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -4.0F, -1.0F, 6.0F, 8.0F, 6.0F), ModelTransform.of(0.0F, 16.0F, 0.0F, 1.5707964F, 0.0F, 0.0F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -2.0F, -3.0F, 3.0F, 7.0F, 3.0F);
        modelPartData.addChild("right_leg", modelPartBuilder, ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
        modelPartData.addChild("left_leg", modelPartBuilder, ModelTransform.pivot(1.0F, 19.0F, 1.0F));


        modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(24, 13).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(-4.0F, 11.0F, 0.0F));
        modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(24, 13).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(4.0F, 11.0F, 0.0F));
        modelPartData.addChild("tail", ModelPartBuilder.create().uv(48, 15).cuboid(-1.0F, -12.0F, 8.0F, 1.0F, 10.0F, 7.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        modelPartData.addChild("crest", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -12.0F, -3.0F, 1.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
        return TexturedModelData.of(modelData, 64, 32);

    }

    protected Iterable<ModelPart> getHeadParts() {
        return new ImmutableList.Builder<ModelPart>()
                .addAll(super.getHeadParts())
                .add(this.crest)
                .build();
    }

    protected Iterable<ModelPart> getBodyParts() {
        return new ImmutableList.Builder<ModelPart>()
                .addAll(super.getBodyParts())
                .add(this.tail)
                .build();
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.crest.pitch = this.head.pitch;
        this.crest.yaw = this.head.yaw;
    }
}
