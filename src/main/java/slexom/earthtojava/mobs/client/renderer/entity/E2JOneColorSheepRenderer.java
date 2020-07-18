package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.E2JOneColorSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepModel;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JOneColorSheepRenderer<T extends E2JOneColorSheepEntity<T>> extends MobRenderer<T, E2JOneColorSheepModel<T>> {

    private final String registryName;

    public E2JOneColorSheepRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new E2JOneColorSheepModel<>(), 0.7F);
        this.registryName = registryName;
        String woolTexture = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_fur.png", this.registryName, this.registryName);
        this.addLayer(new E2JOneColorSheepWoolLayer<>(this, woolTexture));
    }

    public ResourceLocation getEntityTexture(T entity) {
        String textureString = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}.png", this.registryName, this.registryName);
        String textureBlinkString = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_blink.png", this.registryName, this.registryName);
        ResourceLocation texture = new ResourceLocation(textureString);
        ResourceLocation textureBlink = new ResourceLocation(textureBlinkString);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}