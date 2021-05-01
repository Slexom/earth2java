package slexom.earthtojava.mobs.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import slexom.earthtojava.mobs.init.RecipesInit;

public class MudBucketRecipe extends ShapelessRecipe {
    private final Identifier id;
    private final String group;
    private final ItemStack output;
    private final DefaultedList<Ingredient> input;

    public MudBucketRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        super(id, group, output, input);
        this.id = id;
        this.group = group;
        this.output = output;
        this.input = input;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inv) {
        final DefaultedList<ItemStack> remainingItems = DefaultedList.ofSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < remainingItems.size(); ++i) {
            ItemStack itemstack = inv.getStack(i);
            if (itemstack.getItem().hasRecipeRemainder() && !(itemstack.getItem() instanceof BucketItem)) {
                remainingItems.set(i, itemstack);
            }
        }
        return remainingItems;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipesInit.MUD_BUCKET_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<MudBucketRecipe> {

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> defaultedList = DefaultedList.of();

            for (int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty()) {
                    defaultedList.add(ingredient);
                }
            }

            return defaultedList;
        }

        public MudBucketRecipe read(Identifier recipeID, JsonObject json) {
            String string = JsonHelper.getString(json, "group", "");
            DefaultedList<Ingredient> defaultedList = getIngredients(JsonHelper.getArray(json, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (defaultedList.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(json, "result"));
                return new MudBucketRecipe(recipeID, string, itemStack, defaultedList);
            }
        }

        @Override
        public MudBucketRecipe read(Identifier recipeID, PacketByteBuf buffer) {
            final String group = buffer.readString(Short.MAX_VALUE);
            final int numIngredients = buffer.readVarInt();
            final DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(numIngredients, Ingredient.EMPTY);
            for (int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.fromPacket(buffer));
            }
            final ItemStack result = buffer.readItemStack();
            return new MudBucketRecipe(recipeID, group, result, ingredients);
        }

        @Override
        public void write(PacketByteBuf buffer, MudBucketRecipe recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.getPreviewInputs().size());
            for (final Ingredient ingredient : recipe.getPreviewInputs()) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.getOutput());
        }
    }
}
