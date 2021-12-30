package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseMonoColorSheepEntity;
import slexom.earthtojava.mobs.init.BlockInit;
import slexom.earthtojava.mobs.init.SoundEventsInit;

public class RainbowSheepEntity extends E2JBaseMonoColorSheepEntity {

    public RainbowSheepEntity(EntityType<? extends RainbowSheepEntity> type, World world) {
        super(type, world, new ItemStack(BlockInit.RAINBOW_WOOL));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEventsInit.RAINBOW_SHEEP_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEventsInit.RAINBOW_SHEEP_DEATH;
    }

}
