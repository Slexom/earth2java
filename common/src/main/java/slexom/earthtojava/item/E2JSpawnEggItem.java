package slexom.earthtojava.item;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import slexom.earthtojava.utils.Utils;

import javax.annotation.Nullable;
import java.util.List;

public class E2JSpawnEggItem extends ArchitecturySpawnEggItem {

    public E2JSpawnEggItem(RegistrySupplier<? extends EntityType<? extends MobEntity>> entityType, int primaryColor, int secondaryColor, Settings settings) {
        super(entityType, primaryColor, secondaryColor, settings);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String translationKey = this.getTranslationKey() + ".desc";
        if (I18n.hasTranslation(translationKey)) {
            MutableText description = Text.translatable(translationKey);
            List<String> strings = Utils.breakLine(description.getString(), 40);
            strings.forEach(string -> tooltip.add(Text.translatable(string).formatted(Formatting.GRAY)));
        }
    }

}