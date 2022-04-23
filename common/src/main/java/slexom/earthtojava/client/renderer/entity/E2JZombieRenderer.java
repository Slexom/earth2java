package slexom.earthtojava.client.renderer.entity;//package slexom.earthtojava.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
//import net.minecraft.client.render.entity.model.ZombieEntityModel;
//import net.minecraft.util.Identifier;
//import slexom.earthtojava.entity.base.E2JBaseZombieEntity;
//
//import java.text.MessageFormat;
//
//@Environment(EnvType.CLIENT)
//public class E2JZombieRenderer extends ZombieBaseEntityRenderer<E2JBaseZombieEntity, ZombieEntityModel<E2JBaseZombieEntity>> {
//
//    private final String registryName;
//
//    public E2JZombieRenderer(EntityRenderDispatcher entityRenderDispatcher, String registryName) {
//        super(entityRenderDispatcher, new ZombieEntityModel(0.0F, false), new ZombieEntityModel(0.5F, true), new ZombieEntityModel(1.0F, true));
//        this.registryName = registryName;
//    }
//
//    @Override
//    public Identifier getTexture(E2JBaseZombieEntity entity) {
//        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/zombie/{0}/{0}.png", this.registryName);
//        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/zombie/{0}/{0}_blink.png", this.registryName);
//        Identifier texture = new Identifier(resourceTexture);
//        Identifier textureBlink = new Identifier(resourceTextureBlink);
//        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
//    }
//}
