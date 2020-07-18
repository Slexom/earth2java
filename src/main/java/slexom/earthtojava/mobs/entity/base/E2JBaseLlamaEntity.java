package slexom.earthtojava.mobs.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.world.World;

import java.util.Random;

public class E2JBaseLlamaEntity extends LlamaEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JBaseLlamaEntity(EntityType<? extends LlamaEntity> type, World worldIn) {
        super(type, worldIn);
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createJollyLlamaAttributes() {
        return createAbstractDonkeyAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D);
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


}
