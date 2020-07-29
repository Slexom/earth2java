package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.passive.WoolyCowEntity;

@Environment(EnvType.CLIENT)
public class WoolyCowRenderer extends MobEntityRenderer<WoolyCowEntity, CowEntityModel<WoolyCowEntity>> {

    public WoolyCowRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new CowEntityModel<>(), 0.7F);
    }

    public Identifier getTexture(WoolyCowEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_blink.png");
        Identifier textureSheared = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
        Identifier textureShearedBlink = new Identifier("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared_blink.png");
        boolean blink = entity.getBlinkRemainingTicks() > 0;
        return entity.isSheared() ? blink ? textureShearedBlink : textureSheared : blink ? textureBlink : texture;
    }
}