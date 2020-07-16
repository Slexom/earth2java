package slexom.earthtojava.mobs.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.config.E2JModConfig;

import javax.annotation.Nullable;
import java.util.List;

public class E2JItem extends Item {
    public E2JItem(Settings properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (E2JModConfig.showDescription) {
            tooltip.add(new TranslationTextComponent(this.getTranslationKey() + ".desc").applyTextStyle(TextFormatting.GRAY));
        }
    }

}
