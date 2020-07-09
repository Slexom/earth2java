package slexom.earthtojava.mobs.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.EarthtojavamobsMod;
import slexom.earthtojava.mobs.item.crafting.MudBucketRecipe;

public class RecipesInit {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EarthtojavamobsMod.MOD_ID);

    public static final RegistryObject<MudBucketRecipe.Serializer> MUD_BUCKET_RECIPE = RECIPES.register("mud_bucket", MudBucketRecipe.Serializer::new);

}
