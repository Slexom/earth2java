package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.model.BoulderingZombieModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.LobberZombieModel;
import slexom.earthtojava.mobs.entity.monster.BoulderingZombieEntity;
import slexom.earthtojava.mobs.entity.monster.LobberZombieEntity;

@Environment(EnvType.CLIENT)
public class LobberZombieRenderer extends ZombieBaseEntityRenderer<LobberZombieEntity, LobberZombieModel<LobberZombieEntity>> {

    public LobberZombieRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new LobberZombieModel<>(0.0F, 0.0F, 64, 64), new LobberZombieModel<>(0.5F, true), new LobberZombieModel<>(1.0F, true));
    }

    public Identifier getTexture(LobberZombieEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/zombie/lobber_zombie/lobber_zombie.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/zombie/lobber_zombie/lobber_zombie_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}