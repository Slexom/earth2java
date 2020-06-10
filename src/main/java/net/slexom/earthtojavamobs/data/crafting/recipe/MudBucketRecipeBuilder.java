package net.slexom.earthtojavamobs.data.crafting.recipe;

import com.google.common.base.Preconditions;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.init.CraftingInit;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class MudBucketRecipeBuilder extends ShapelessRecipeBuilder {
    private final Item result;

    private MudBucketRecipeBuilder(final IItemProvider result, final int count) {
        super(result, count);
        this.result = result.asItem();
    }

    /**
     * Creates a new builder for a shapeless cutting recipe.
     *
     * @param resultIn The result item
     * @return The builder
     */
    public static MudBucketRecipeBuilder MudBucketRecipe(final IItemProvider resultIn) {
        return new MudBucketRecipeBuilder(resultIn, 1);
    }

    /**
     * Creates a new builder for a shapeless cutting recipe.
     *
     * @param resultIn The result item
     * @param countIn  The result count
     * @return The builder
     */
    public static MudBucketRecipeBuilder MudBucketRecipe(final IItemProvider resultIn, final int countIn) {
        return new MudBucketRecipeBuilder(resultIn, countIn);
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     *
     * @param consumer The recipe consumer
     */
    @Override
    public void build(final Consumer<IFinishedRecipe> consumer) {
        build(consumer, Preconditions.checkNotNull(result.getRegistryName()));
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}. Use {@link #build(Consumer)} if save is the same as the ID for
     * the result.
     *
     * @param consumer The recipe consumer
     * @param save     The ID to use for the recipe
     */
    @Override
    public void build(final Consumer<IFinishedRecipe> consumer, final String save) {
        final ResourceLocation resourcelocation = result.getRegistryName();
        if (new ResourceLocation(save).equals(resourcelocation)) {
            throw new IllegalStateException("Mud Bucket " + save + " should remove its 'save' argument");
        } else {
            build(consumer, new ResourceLocation(save));
        }
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     *
     * @param consumer The recipe consumer
     * @param id       The ID to use for the recipe
     */
    @Override
    public void build(final Consumer<IFinishedRecipe> consumer, final ResourceLocation id) {
        // Use an AtomicReference to capture the IFinishedRecipe created by the super method
        final AtomicReference<IFinishedRecipe> baseResult = new AtomicReference<>();
        super.build(baseResult::set, id);

        consumer.accept(new Result(baseResult.get()));
    }

    public static class Result extends FinishedRecipeDelegate {
        private Result(final IFinishedRecipe baseRecipe) {
            super(baseRecipe);
        }

        @Override
        public IRecipeSerializer<?> getSerializer() {
            return CraftingInit.Recipes.MUD_BUCKET_RECIPE_SERIALIZER;
        }
    }
}