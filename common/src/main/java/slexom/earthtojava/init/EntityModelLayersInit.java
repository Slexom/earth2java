package slexom.earthtojava.init;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.client.renderer.block.entity.RainbowBedBlockEntityRenderer;
import slexom.earthtojava.client.renderer.entity.model.*;

import java.util.HashMap;
import java.util.Map;

public final class EntityModelLayersInit {
    public static final EntityModelLayer BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer FANCY_CHICKEN_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer HORNED_SHEEP_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer JOLLY_LLAMA_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer JUMBO_RABBIT_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer LOBBER_ZOMBIE_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer MUDDY_PIG_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer RAINBOW_BED_FOOT_MODEL_LAYER;
    public static final EntityModelLayer RAINBOW_BED_HEAD_MODEL_LAYER;
    public static final EntityModelLayer RAINBOW_SHEEP_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer SKELETON_WOLF_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer SOOTY_PIG_ENTITY_MODEL_LAYER;
    public static final EntityModelLayer VILER_WITCH_ENTITY_MODEL_LAYER;
    public static Map<EntityModelLayer, TexturedModelData> E2J_MODEL_LAYERS = new HashMap<>();

    static {
        FANCY_CHICKEN_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, FancyChickenModel.getTexturedModelData());
        HORNED_SHEEP_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME, HornedSheepModel.getTexturedModelData());
        JOLLY_LLAMA_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, JollyLlamaModel.getTexturedModelData(Dilation.NONE));
        JUMBO_RABBIT_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, JumboRabbitModel.getTexturedModelData());
        MUDDY_PIG_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.MUDDY_PIG_REGISTRY_NAME, MuddyPigModel.getTexturedModelData(Dilation.NONE));
        RAINBOW_SHEEP_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, RainbowSheepModel.getTexturedModelData());
        RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME + "_fur", RainbowSheepWoolModel.getTexturedModelData());
        SKELETON_WOLF_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, SkeletonWolfModel.getTexturedModelData());
        BOULDERING_ZOMBIE_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_NAME, BoulderingZombieModel.getTexturedModelData());
        LOBBER_ZOMBIE_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_NAME, LobberZombieModel.getTexturedModelData());
        VILER_WITCH_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.VILER_WITCH_REGISTRY_NAME, VilerWitchModel.getTexturedModelData());
        SOOTY_PIG_ENTITY_MODEL_LAYER = registerEntityModelLayer(EntityTypesInit.SOOTY_PIG_REGISTRY_NAME, SootyPigModel.getTexturedModelData(Dilation.NONE));
        RAINBOW_BED_HEAD_MODEL_LAYER = registerEntityModelLayer("rainbow_bed_head", RainbowBedBlockEntityRenderer.getHeadTexturedModelData());
        RAINBOW_BED_FOOT_MODEL_LAYER = registerEntityModelLayer("rainbow_bed_foot", RainbowBedBlockEntityRenderer.getFootTexturedModelData());
    }

    public static void init() {

    }

    public static EntityModelLayer registerEntityModelLayer(String registryName, TexturedModelData modelPart) {
        EntityModelLayer entityModelLayer = new EntityModelLayer(new Identifier(Earth2JavaMod.MOD_ID, registryName), "main");
        // E2J_MODEL_LAYERS.put(entityModelLayer, modelPart);
        EntityModelLayerRegistry.register(entityModelLayer, () -> modelPart);
        return entityModelLayer;
    }
}
