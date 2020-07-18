package slexom.earthtojava.mobs.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.Earth2JavaMod;

public class RegisterHelper {
    private static Item.Settings commonSettings = new Item.Settings().group(Earth2JavaMod.ITEM_GROUP);

    public static Block registerBlock(String registryName, Block block, boolean registerItem) {
        if (registerItem) {
            registerItem(registryName, new BlockItem(block, commonSettings));
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
}
