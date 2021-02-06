package slexom.earthtojava.mobs.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.utils.Utils;

import javax.annotation.Nullable;
import java.util.List;

public class RegisterHelper {
    private static final Item.Settings commonSettings = new Item.Settings().group(Earth2JavaMod.ITEM_GROUP);

    public static Block registerBlock(String registryName, Block block, boolean registerItem) {
        if (registerItem) {
            registerItem(registryName, new BlockItem(block, commonSettings) {
                @Environment(EnvType.CLIENT)
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    String translationKey = this.getTranslationKey() + ".desc";
                    if (I18n.hasTranslation(translationKey)) {
                        TranslatableText description = new TranslatableText(translationKey);
                        List<String> strings = Utils.breakLine(description.getString(), 40);
                        strings.forEach(string -> tooltip.add(new LiteralText(string).formatted(Formatting.GRAY)));
                    }
                }
            });
        }
        return Registry.register(Registry.BLOCK, new Identifier(Earth2JavaMod.MOD_ID, registryName), block);
    }

    public static Item registerItem(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Earth2JavaMod.MOD_ID, registryName), item);
    }

    public static RecipeSerializer<?> registerRecipe(String registryName, RecipeSerializer<?> recipe) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Earth2JavaMod.MOD_ID, registryName), recipe);
    }

    public static <T extends Entity> EntityType<T> registerEntity(String registryName, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Earth2JavaMod.MOD_ID, registryName), entityType);
    }

    public static void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, DefaultAttributeContainer.Builder attributes) {
        FabricDefaultAttributeRegistry.register(entityType, attributes);
    }

    public static SoundEvent registerSoundEvent(String registryName) {
        final Identifier identifier = new Identifier(Earth2JavaMod.MOD_ID, registryName);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

}
