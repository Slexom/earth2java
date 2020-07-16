package slexom.earthtojava.mobs.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;

import java.util.Random;

public class E2JBaseCowEntity<T extends CowEntity> extends CowEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JBaseCowEntity(EntityType<? extends CowEntity> type, World worldIn) {
        super(type, worldIn);
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createCowAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
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
    public T createChild(PassiveEntity ageable) {
        return (T) getType().create(this.world);
    }


}
