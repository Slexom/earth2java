package slexom.earthtojava.init;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import slexom.earthtojava.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.client.renderer.entity.model.*;

public class EntityModelLayersInit {
    public static EntityModelLayer BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER;
    public static EntityModelLayer FANCY_CHICKEN_ENTITY_MODEL_LAYER;
    public static EntityModelLayer HORNED_SHEEP_ENTITY_MODEL_LAYER;
    public static EntityModelLayer JOLLY_LLAMA_ENTITY_MODEL_LAYER;
    public static EntityModelLayer JUMBO_RABBIT_ENTITY_MODEL_LAYER;
    public static EntityModelLayer LOBBER_ZOMBIE_ENTITY_MODEL_LAYER;
    public static EntityModelLayer MUDDY_PIG_ENTITY_MODEL_LAYER;
    public static EntityModelLayer RAINBOW_BED_FOOT_MODEL_LAYER;
    public static EntityModelLayer RAINBOW_BED_HEAD_MODEL_LAYER;
    public static EntityModelLayer RAINBOW_SHEEP_ENTITY_MODEL_LAYER;
    public static EntityModelLayer RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER;
    public static EntityModelLayer SKELETON_WOLF_ENTITY_MODEL_LAYER;
    public static EntityModelLayer SOOTY_PIG_ENTITY_MODEL_LAYER;
    public static EntityModelLayer VILER_WITCH_ENTITY_MODEL_LAYER;

    public static void init() {
        FANCY_CHICKEN_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, FancyChickenModel.getTexturedModelData());
        HORNED_SHEEP_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME, HornedSheepModel.getTexturedModelData());
        JOLLY_LLAMA_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, JollyLlamaModel.getTexturedModelData(Dilation.NONE));
        JUMBO_RABBIT_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, JumboRabbitModel.getTexturedModelData());
        MUDDY_PIG_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.MUDDY_PIG_REGISTRY_NAME, MuddyPigModel.getTexturedModelData(Dilation.NONE));
        RAINBOW_SHEEP_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, RainbowSheepModel.getTexturedModelData());
        RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME + "_fur", RainbowSheepWoolModel.getTexturedModelData());
        SKELETON_WOLF_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, SkeletonWolfModel.getTexturedModelData());
        BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_NAME, BoulderingZombieModel.getTexturedModelData());
        LOBBER_ZOMBIE_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_NAME, LobberZombieModel.getTexturedModelData());
        VILER_WITCH_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.VILER_WITCH_REGISTRY_NAME, VilerWitchModel.getTexturedModelData());
        SOOTY_PIG_ENTITY_MODEL_LAYER = RegisterHelper.registerEntityModelLayer(EntityTypesInit.SOOTY_PIG_REGISTRY_NAME, SootyPigModel.getTexturedModelData(Dilation.NONE));
        RAINBOW_BED_HEAD_MODEL_LAYER = RegisterHelper.registerEntityModelLayer("rainbow_bed_head", RainbowBedBlockEntityRenderer.getHeadTexturedModelData());
        RAINBOW_BED_FOOT_MODEL_LAYER = RegisterHelper.registerEntityModelLayer("rainbow_bed_foot", RainbowBedBlockEntityRenderer.getFootTexturedModelData());
    }

}
