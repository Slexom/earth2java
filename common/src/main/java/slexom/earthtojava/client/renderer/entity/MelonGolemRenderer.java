package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.MelonGolemCarvedMelonFeatureRenderer;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

@Environment(EnvType.CLIENT)
public class MelonGolemRenderer extends MobEntityRenderer<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> {
    private static final Identifier SNOW_MAN_TEXTURES = new Identifier("textures/entity/snow_golem.png");

    public MelonGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new SnowGolemEntityModel<>(context.getPart(EntityModelLayers.SNOW_GOLEM)), 0.5F);
        this.addFeature(new MelonGolemCarvedMelonFeatureRenderer(this));
    }

    public Identifier getTexture(MelonGolemEntity entity) {
        return SNOW_MAN_TEXTURES;
    }

}