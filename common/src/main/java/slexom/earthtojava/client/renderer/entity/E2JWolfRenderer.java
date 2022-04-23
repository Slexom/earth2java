package slexom.earthtojava.client.renderer.entity;//package slexom.earthtojava.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.WolfEntityRenderer;
//import net.minecraft.entity.passive.WolfEntity;
//import net.minecraft.util.Identifier;
//
//import java.text.MessageFormat;
//
//@Environment(EnvType.CLIENT)
//public class E2JWolfRenderer extends WolfEntityRenderer {
//    private final String registryName;
//
//    public E2JWolfRenderer(EntityRenderDispatcher renderManagerIn, String registryName) {
//        super(renderManagerIn);
//        this.registryName = registryName;
//    }
//
//    @Override
//    public Identifier getTexture(WolfEntity entity) {
//        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/wolf/{0}/{0}.png", this.registryName);
//        String resourceTextureAngry = MessageFormat.format("earthtojavamobs:textures/mobs/wolf/{0}/{0}_angry.png", this.registryName);
//        Identifier texture = new Identifier(resourceTexture);
//        Identifier textureAngry = new Identifier(resourceTextureAngry);
//        return entity.hasAngerTime() ? textureAngry : texture;
//    }
//
//}
