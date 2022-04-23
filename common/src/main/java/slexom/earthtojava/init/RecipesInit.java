package slexom.earthtojava.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.Fluid;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.item.crafting.MudBucketRecipe;

public class RecipesInit {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER_DEFERRED_REGISTER = DeferredRegister.create(Earth2JavaMod.MOD_ID, Registry.RECIPE_SERIALIZER_KEY);


    public static RegistrySupplier<RecipeSerializer<MudBucketRecipe>> MUD_BUCKET_RECIPE;

    static {
        MUD_BUCKET_RECIPE = RECIPE_SERIALIZER_DEFERRED_REGISTER.register( "mud_bucket", MudBucketRecipe.Serializer::new);

    }
    public static void init() {
        RECIPE_SERIALIZER_DEFERRED_REGISTER.register();
    }
}
