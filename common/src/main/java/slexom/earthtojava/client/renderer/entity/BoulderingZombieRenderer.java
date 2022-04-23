package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.BoulderingZombieModel;
import slexom.earthtojava.entity.monster.BoulderingZombieEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class BoulderingZombieRenderer extends ZombieBaseEntityRenderer<BoulderingZombieEntity, BoulderingZombieModel<BoulderingZombieEntity>> {

    public BoulderingZombieRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayersInit.BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public BoulderingZombieRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new BoulderingZombieModel(ctx.getPart(layer)), new BoulderingZombieModel(ctx.getPart(legsArmorLayer)), new BoulderingZombieModel(ctx.getPart(bodyArmorLayer)));
    }

    public Identifier getTexture(BoulderingZombieEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/zombie/bouldering_zombie/bouldering_zombie.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/zombie/bouldering_zombie/bouldering_zombie_blink.png");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}