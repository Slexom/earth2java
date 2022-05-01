package slexom.earthtojava.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.utils.Utils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterHelper {
    private static final Item.Settings commonSettings = new Item.Settings().group(Earth2JavaMod.ITEM_GROUP);
    public static Map<EntityModelLayer, TexturedModelData> E2J_MODEL_LAYERS = new HashMap<>();

    public static Block registerBlock(String registryName, Block block, boolean registerItem) {
        if (registerItem) {
            registerItem(registryName, new BlockItem(block, commonSettings) {
                @Environment(EnvType.CLIENT)
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    String translationKey = this.getTranslationKey() + ".desc";
                    if (I18n.hasTranslation(translationKey)) {
                        TranslatableText description = new TranslatableText(translationKey);
                        List<String> strings = Utils.breakLine(description.getString(), 40);
                        strings.forEach(string -> tooltip.add(new LiteralText(string).formatted(Formatting.GRAY)));
                    }
                }
            });
        }
        return Registry.register(Registry.BLOCK, new Identifier(Earth2JavaMod.MOD_ID, registryName), block);
    }

    public static Item registerItem(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Earth2JavaMod.MOD_ID, registryName), item);
    }

    public static RecipeSerializer<?> registerRecipe(String registryName, RecipeSerializer<?> recipe) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Earth2JavaMod.MOD_ID, registryName), recipe);
    }

    public static <T extends Entity> EntityType<T> registerEntity(String registryName, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Earth2JavaMod.MOD_ID, registryName), entityType);
    }

    public static void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, DefaultAttributeContainer.Builder attributes) {
        FabricDefaultAttributeRegistry.register(entityType, attributes);
    }

    public static EntityModelLayer registerEntityModelLayer(String registryName, TexturedModelData modelPart) {
        EntityModelLayer entityModelLayer = new EntityModelLayer(new Identifier(Earth2JavaMod.MOD_ID, registryName), "main");
        E2J_MODEL_LAYERS.put(entityModelLayer, modelPart);
        return entityModelLayer;
    }

    public static SoundEvent registerSoundEvent(String registryName) {
        final Identifier identifier = new Identifier(Earth2JavaMod.MOD_ID, registryName);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }


    public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerConfiguredFeature(String registryName, F feature, FC config) {
        final Identifier identifier = new Identifier(Earth2JavaMod.MOD_ID, registryName);
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, new ConfiguredFeature<>(feature, config));
    }


    public static PlacedFeature registerPlacedFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature, PlacementModifier... placementModifiers) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, registryName), new PlacedFeature(RegistryEntry.of(configuredFeature), List.of(placementModifiers)));
    }

}
