package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.recipe.RecipeSerializer;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.item.crafting.MudBucketRecipe;

public final class RecipesInit {


    public static final RegistrySupplier<RecipeSerializer<MudBucketRecipe>> MUD_BUCKET_RECIPE;

    static {
        MUD_BUCKET_RECIPE = Earth2JavaMod.RECIPE_SERIALIZER_REGISTRAR.register(Earth2JavaMod.toIdentifier("mud_bucket"), MudBucketRecipe.Serializer::new);
    }

    public static void init() {

    }
}
