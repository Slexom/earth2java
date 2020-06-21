
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slexom.earthtojavamobs.entity.base.E2JOneColorSheepEntity;

public class InkySheepEntity extends E2JOneColorSheepEntity<InkySheepEntity> {

    public InkySheepEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world, new ItemStack(Blocks.BLACK_WOOL));
    }

}
