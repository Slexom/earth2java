package slexom.earthtojava.mobs.entity.base;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class E2JBaseSheepEntity<T extends SheepEntity> extends SheepEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JBaseSheepEntity(EntityType<? extends SheepEntity> type, World worldIn) {
        super(type, worldIn);
        experienceValue = 3;
        setNoAI(false);
    }
    @Override
    public void livingTick() {
        super.livingTick();
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }

    @Override
    public T createChild(AgeableEntity ageable) {
        return (T) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
