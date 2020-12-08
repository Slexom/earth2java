package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.feature.MoobloomLayer;
import slexom.earthtojava.mobs.client.renderer.entity.feature.MoolipLayer;
import slexom.earthtojava.mobs.entity.passive.MoobloomEntity;
import slexom.earthtojava.mobs.entity.passive.MoolipEntity;

@Environment(EnvType.CLIENT)
public class MoolipRenderer extends MobEntityRenderer<MoolipEntity, CowEntityModel<MoolipEntity>> {

    public MoolipRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new CowEntityModel<>(), 0.7F);
        this.addFeature(new MoolipLayer<>(this));
    }

    public Identifier getTexture(MoolipEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/cow/moolip/moolip.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/cow/moolip/moolip_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}