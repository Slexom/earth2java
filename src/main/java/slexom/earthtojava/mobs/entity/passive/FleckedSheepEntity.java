
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseMonoColorSheepEntity;

public class FleckedSheepEntity extends E2JBaseMonoColorSheepEntity<FleckedSheepEntity> {

    public FleckedSheepEntity(EntityType<FleckedSheepEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BROWN_WOOL));
    }

}
