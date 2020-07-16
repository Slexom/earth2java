package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.layers.MelonGolemHeadLayer;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

@Environment(EnvType.CLIENT)
public class MelonGolemRenderer extends MobEntityRenderer<MelonGolemEntity, SnowmanEntityModel<MelonGolemEntity>> {
    private static final Identifier SNOW_MAN_TEXTURES = new Identifier("textures/entity/snow_golem.png");

    public MelonGolemRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new SnowmanEntityModel<>(), 0.5F);
        this.addFeature(new MelonGolemHeadLayer(this));
    }

    public Identifier getTexture(MelonGolemEntity entity) {
//        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem.png");
//        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
//        // private static final Identifier textureShoot = new Identifier("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
        return SNOW_MAN_TEXTURES;
    }
}