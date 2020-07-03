package net.slexom.earthtojavamobs.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.entity.passive.MuddyPigEntity;

@OnlyIn(Dist.CLIENT)
public class MuddyPigModel<T extends MuddyPigEntity> extends QuadrupedModel<T> {

    public MuddyPigModel() {
        this(0.0F);
    }

    public MuddyPigModel(float scale) {
        super(6, scale, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
        float mudBoxX = -1.0F;
        float mudBoxY = -5.0F;
        float mudBoxZ = -7.0F;
        this.headModel.setTextureOffset(24, 0).addBox(mudBoxX, mudBoxY, mudBoxZ, 4.0F, 1.0F, 4.0F, scale);
        this.headModel.setTextureOffset(40, 0).addBox(mudBoxX, mudBoxY - 6.0F, mudBoxZ + 2.0F, 4.0F, 6.0F, 1.0F, scale);
        this.headModel.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, scale);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.headModel.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.07F);
        this.headModel.rotateAngleX = (float)Math.PI / 8.0F;
        this.body.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.14F);
    }
}