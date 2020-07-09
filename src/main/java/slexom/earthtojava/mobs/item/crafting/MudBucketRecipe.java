package slexom.earthtojava.mobs.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import slexom.earthtojava.mobs.init.RecipesInit;

public class MudBucketRecipe extends ShapelessRecipe {

    public MudBucketRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn) {
        super(idIn, groupIn, recipeOutputIn, recipeItemsIn);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(final CraftingInventory inv) {
        System.out.println("HERE");
        final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < remainingItems.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack.hasContainerItem() && !(itemstack.getItem() instanceof BucketItem)) {
                remainingItems.set(i, itemstack.getContainerItem());
            }
        }
        return remainingItems;
    }
    public IRecipeSerializer<?> getSerializer() {
        return RecipesInit.MUD_BUCKET_RECIPE.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MudBucketRecipe> {
        @Override
        public MudBucketRecipe read(final ResourceLocation recipeID, final JsonObject json) {
            final String group = JSONUtils.getString(json, "group", "");
            final NonNullList<Ingredient> ingredients = NonNullList.create();
            for (final JsonElement element : JSONUtils.getJsonArray(json, "ingredients")) {
                ingredients.add(CraftingHelper.getIngredient(element));
            }
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            }
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
