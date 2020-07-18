package slexom.earthtojava.mobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;

@OnlyIn(Dist.CLIENT)
public class E2JWanderingTraderRenderer extends MobRenderer<E2JWanderingTraderEntity, VillagerModel<E2JWanderingTraderEntity>> {
    private static final ResourceLocation field_217780_a = new ResourceLocation("earthtojavamobs:textures/entity/villager/wandering_trader/wandering_trader.png");

    public E2JWanderingTraderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VillagerModel<>(0.0F), 0.5F);
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    public ResourceLocation getEntityTexture(E2JWanderingTraderEntity entity) {
        return field_217780_a;
    }

    protected void preRenderCallback(E2JWanderingTraderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
    }
}