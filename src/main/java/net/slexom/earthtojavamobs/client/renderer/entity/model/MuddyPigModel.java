package net.slexom.earthtojavamobs.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MuddyPigModel<T extends Entity> extends QuadrupedModel<T> {

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
}