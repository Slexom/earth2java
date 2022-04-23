package slexom.earthtojava.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

public class WoolyCowEntity extends E2JBaseShearableCowEntity<WoolyCowEntity> {

    public WoolyCowEntity(EntityType<WoolyCowEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BROWN_WOOL));
    }

}
