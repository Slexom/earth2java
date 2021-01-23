package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseShearableCowEntity;

public class UmbraCowEntity extends E2JBaseShearableCowEntity<UmbraCowEntity> {

    public UmbraCowEntity(EntityType<UmbraCowEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BLACK_WOOL));
    }

}
