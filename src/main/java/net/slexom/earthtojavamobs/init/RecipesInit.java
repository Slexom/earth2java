package net.slexom.earthtojavamobs.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.item.crafting.MudBucketRecipe;

public class RecipesInit {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, EarthtojavamobsMod.MOD_ID);

    public static final RegistryObject<MudBucketRecipe.Serializer> MUD_BUCKET_RECIPE = RECIPES.register("mud_bucket", MudBucketRecipe.Serializer::new);

}
