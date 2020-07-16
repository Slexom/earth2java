package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.passive.WoolyCowEntity;

@Environment(EnvType.CLIENT)
public class WoolyCowRenderer extends E2JCowRenderer {

    public WoolyCowRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, "wooly_cow");
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