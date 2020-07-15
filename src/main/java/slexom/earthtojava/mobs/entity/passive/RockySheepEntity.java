
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

public class RockySheepEntity extends E2JOneColorSheepEntity<RockySheepEntity> {

    public RockySheepEntity(EntityType<? extends RockySheepEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.GRAY_WOOL));
    }

}
