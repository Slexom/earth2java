package slexom.earthtojava.mobs.init;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.FancyChickenModel;

public class EntityModeLayerInit {
    public static EntityModelLayer FANCY_CHICKEN_ENTITY_MODEL_LAYER;


    public static void init(){
        FANCY_CHICKEN_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, FancyChickenModel.getTexturedModelData() );
    }

}
