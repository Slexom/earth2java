
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

public class FleckedSheepEntity extends E2JOneColorSheepEntity<FleckedSheepEntity> {

    public FleckedSheepEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BROWN_WOOL));
    }

}
