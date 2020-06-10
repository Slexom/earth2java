package net.slexom.earthtojavamobs.crafting.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.slexom.earthtojavamobs.init.CraftingInit;

public class MudBucketRecipe extends ShapelessRecipe {

    public MudBucketRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn) {
        super(idIn, groupIn, recipeOutputIn, recipeItemsIn);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(final CraftingInventory inv) {
        final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println(remainingItems);
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println("################################################");
        for (int i = 0; i < remainingItems.size(); ++i) {
            final ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty() && itemstack.getItem() == Items.BUCKET) {
                remainingItems.remove(i);
            }
        }
        return remainingItems;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return CraftingInit.Recipes.MUD_BUCKET_RECIPE_SERIALIZER;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MudBucketRecipe> {
        @Override
        public MudBucketRecipe read(final ResourceLocation recipeID, final JsonObject json) {
            System.out.println(recipeID);
            System.out.println(json);
            final String group = JSONUtils.getString(json, "group", "");
            final NonNullList<Ingredient> ingredients = RecipeUtil.parseShapeless(json);
            final ItemStack result = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), true);
            return new MudBucketRecipe(recipeID, group, result, ingredients);
        }

        @Override
        public MudBucketRecipe read(final ResourceLocation recipeID, final PacketBuffer buffer) {
            final String group = buffer.readString(Short.MAX_VALUE);
            final int numIngredients = buffer.readVarInt();
            final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);
            for (int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.read(buffer));
            }
            final ItemStack result = buffer.readItemStack();
            return new MudBucketRecipe(recipeID, group, result, ingredients);
        }

        @Override
        public void write(final PacketBuffer buffer, final MudBucketRecipe recipe) {
            buffer.writeString(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (final Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput());
        }
    }
}
