package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseLlamaEntity;

import javax.annotation.Nullable;

public class JollyLlamaEntity extends E2JBaseLlamaEntity {

    public JollyLlamaEntity(EntityType<JollyLlamaEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean wearsArmor() {
        return false;
    }

    @Override
    public boolean isArmor(ItemStack stack) {
        return false;
    }

    @Override
    @Nullable
    public DyeColor getColor() {
        return null;
    }
}
