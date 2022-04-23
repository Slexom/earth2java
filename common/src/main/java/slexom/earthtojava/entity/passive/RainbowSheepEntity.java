package slexom.earthtojava.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.SoundEventsInit;

public class RainbowSheepEntity extends E2JBaseMonoColorSheepEntity {

    public RainbowSheepEntity(EntityType<? extends RainbowSheepEntity> type, World world) {
        super(type, world, new ItemStack(BlockInit.RAINBOW_WOOL.get()));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEventsInit.RAINBOW_SHEEP_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundEventsInit.RAINBOW_SHEEP_DEATH.get();
    }

}
