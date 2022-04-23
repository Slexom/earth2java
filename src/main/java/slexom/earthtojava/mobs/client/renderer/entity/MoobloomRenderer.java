package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.MoobloomButtercupFeatureRenderer;
import slexom.earthtojava.entity.passive.MoobloomEntity;

@Environment(EnvType.CLIENT)
public class MoobloomRenderer extends MobEntityRenderer<MoobloomEntity, CowEntityModel<MoobloomEntity>> {

    public MoobloomRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7F);
        this.addFeature(new MoobloomButtercupFeatureRenderer<>(this));
    }

    public Identifier getTexture(MoobloomEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/cow/moobloom/moobloom.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/cow/moobloom/moobloom_blink.png");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}