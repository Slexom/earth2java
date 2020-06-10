package net.slexom.earthtojavamobs.init;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.crafting.recipe.MudBucketRecipe;

public class CraftingInit {

    @ObjectHolder(EarthtojavamobsMod.MOD_ID)
    @Mod.EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Recipes {
        public static final IRecipeSerializer<MudBucketRecipe> MUD_BUCKET_RECIPE_SERIALIZER = null;

        @SubscribeEvent
        public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            event.getRegistry().registerAll(
                    new MudBucketRecipe.Serializer().setRegistryName(new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud_bucket"))
            );
        }
    }
}
