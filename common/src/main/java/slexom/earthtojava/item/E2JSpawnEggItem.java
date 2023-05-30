package slexom.earthtojava.item;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.utils.Utils;

import java.util.List;

public class E2JSpawnEggItem extends ArchitecturySpawnEggItem {

    public E2JSpawnEggItem(RegistrySupplier<? extends EntityType<? extends MobEntity>> entityType, int primaryColor, int secondaryColor, Settings settings) {
        super(entityType, primaryColor, secondaryColor, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String translationKey = this.getTranslationKey() + ".desc";
        Utils.appendE2JTooltip(translationKey, tooltip);
    }

}