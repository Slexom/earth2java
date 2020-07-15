package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.passive.MuddyPigEntity;

@Environment(EnvType.CLIENT)
public class MuddyPigEntityModel<T extends MuddyPigEntity> extends QuadrupedEntityModel<T> {

    public MuddyPigEntityModel() {
        this(0.0F);
    }

    public MuddyPigEntityModel(float scale) {
        super(6, scale, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
        float mudBoxX = -1.0F;
        float mudBoxY = -5.0F;
        float mudBoxZ = -7.0F;
        this.head.setTextureOffset(24, 0).addCuboid(mudBoxX, mudBoxY, mudBoxZ, 4.0F, 1.0F, 4.0F, scale);
        this.head.setTextureOffset(40, 0).addCuboid(mudBoxX, mudBoxY - 6.0F, mudBoxZ + 2.0F, 4.0F, 6.0F, 1.0F, scale);
        this.head.setTextureOffset(16, 16).addCuboid(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, scale);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.head.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.07F);
        this.head.pitch = (float)Math.PI / 8.0F;
        this.torso.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.14F);
    }
}