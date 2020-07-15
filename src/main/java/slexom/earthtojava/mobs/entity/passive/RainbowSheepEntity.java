package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;
import slexom.earthtojava.mobs.init.BlockInit;

public class RainbowSheepEntity extends E2JOneColorSheepEntity<RainbowSheepEntity> {
    public RainbowSheepEntity(EntityType<? extends RainbowSheepEntity> type, World world) {
        super(type, world, new ItemStack( BlockInit.RAINBOW_WOOL.get()));
    }
}
