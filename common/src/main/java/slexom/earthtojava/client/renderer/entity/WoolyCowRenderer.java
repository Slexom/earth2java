package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.passive.WoolyCowEntity;

@Environment(EnvType.CLIENT)
public class WoolyCowRenderer extends MobEntityRenderer<WoolyCowEntity, CowEntityModel<WoolyCowEntity>> {

    public WoolyCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
        // todo: bottom fur
    }

    public Identifier getTexture(WoolyCowEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_blink.png");
        Identifier textureSheared = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
        Identifier textureShearedBlink = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared_blink.png");
        boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
        return entity.isSheared() ? blink ? textureShearedBlink : textureSheared : blink ? textureBlink : texture;
    }

}