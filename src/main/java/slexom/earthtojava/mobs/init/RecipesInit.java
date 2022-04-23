package slexom.earthtojava.init;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.item.crafting.MudBucketRecipe;

public class RecipesInit {
    public static RecipeSerializer<MudBucketRecipe> MUD_BUCKET_RECIPE;

    public static void init() {
        MUD_BUCKET_RECIPE = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Earth2JavaMod.MOD_ID, "mud_bucket"), new MudBucketRecipe.Serializer());
    }
}
