package slexom.earthtojava.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import slexom.earthtojava.utils.Utils;

import javax.annotation.Nullable;
import java.util.List;

public class MudBucketItem extends BucketItem {
    public MudBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String translationKey = this.getTranslationKey() + ".desc";
        if (I18n.hasTranslation(translationKey)) {
            TranslatableText description = new TranslatableText(translationKey);
            List<String> strings = Utils.breakLine(description.getString(), 40);
            strings.forEach(string -> tooltip.add(new LiteralText(string).formatted(Formatting.GRAY)));
        }
    }
}
