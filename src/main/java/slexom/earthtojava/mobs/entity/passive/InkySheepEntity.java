
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseMonoColorSheepEntity;

public class InkySheepEntity extends E2JBaseMonoColorSheepEntity<InkySheepEntity> {

    public InkySheepEntity(EntityType<InkySheepEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BLACK_WOOL));
    }

}
