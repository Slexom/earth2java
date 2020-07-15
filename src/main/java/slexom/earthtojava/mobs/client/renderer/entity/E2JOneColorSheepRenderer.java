package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.layers.E2JOneColorSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepModel;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JOneColorSheepRenderer<T extends E2JOneColorSheepEntity<T>> extends MobEntityRenderer<T, E2JOneColorSheepModel<T>> {

    private final String registryName;

    public E2JOneColorSheepRenderer(EntityRenderDispatcher renderManagerIn, String registryName) {
        super(renderManagerIn, new E2JOneColorSheepModel<>(), 0.7F);
        this.registryName = registryName;
        String woolTexture = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_fur.png", this.registryName, this.registryName);
        this.addFeature(new E2JOneColorSheepWoolLayer<>(this, woolTexture));
    }

    public Identifier getTexture(T entity) {
        String textureString = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}.png", this.registryName, this.registryName);
        String textureBlinkString = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_blink.png", this.registryName, this.registryName);
        Identifier texture = new Identifier(textureString);
        Identifier textureBlink = new Identifier(textureBlinkString);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}