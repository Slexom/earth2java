package slexom.earthtojava.mobs.init;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.Dilation;
import slexom.earthtojava.mobs.client.renderer.entity.model.FancyChickenModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.JollyLlamaModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.JumboRabbitModel;

public class EntityModeLayersInit {
    public static EntityModelLayer FANCY_CHICKEN_ENTITY_MODEL_LAYER;
    public static EntityModelLayer HORNED_SHEEP_ENTITY_MODEL_LAYER;
    public static EntityModelLayer JOLLY_LLAMA_ENTITY_MODEL_LAYER;
    public static EntityModelLayer JUMBO_RABBIT_ENTITY_MODEL_LAYER;


    public static void init() {
        FANCY_CHICKEN_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, FancyChickenModel.getTexturedModelData());
        HORNED_SHEEP_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME, HornedSheepModel.getTexturedModelData());
        JOLLY_LLAMA_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, JollyLlamaModel.getTexturedModelData(Dilation.NONE));
        JUMBO_RABBIT_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, JumboRabbitModel.getTexturedModelData());
    }

}
