package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.MelonGolemHeadLayer;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

@OnlyIn(Dist.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolemEntity, SnowManModel<MelonGolemEntity>> {
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

    public MelonGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SnowManModel<>(), 0.5F);
        this.addLayer(new MelonGolemHeadLayer(this));
    }

    public ResourceLocation getEntityTexture(MelonGolemEntity entity) {
//        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem.png");
//        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
//        // private static final ResourceLocation textureShoot = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
        return SNOW_MAN_TEXTURES;
    }
}