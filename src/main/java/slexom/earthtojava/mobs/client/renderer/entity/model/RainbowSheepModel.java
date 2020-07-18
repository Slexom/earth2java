package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

public class RainbowSheepModel<T extends E2JOneColorSheepEntity<T>> extends E2JOneColorSheepModel<T> {

    public RainbowSheepModel() {
        super();

        this.body = new ModelRenderer(this, 36, 0);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.legBackRight = new ModelRenderer(this, 0, 16);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legBackRight.setRotationPoint(-3.0F, (float) (24 - 12), 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 16);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legBackLeft.setRotationPoint(3.0F, (float) (24 - 12), 7.0F);
        this.legFrontRight = new ModelRenderer(this, 16, 16);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legFrontRight.setRotationPoint(-3.0F, (float) (24 - 12), -5.0F);
        this.legFrontLeft = new ModelRenderer(this, 16, 16);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legFrontLeft.setRotationPoint(3.0F, (float) (24 - 12), -5.0F);
    }
}
