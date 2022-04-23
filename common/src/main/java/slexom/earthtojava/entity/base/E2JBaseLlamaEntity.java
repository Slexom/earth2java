package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;

public class E2JBaseLlamaEntity extends LlamaEntity {

    public BlinkManager blinkManager;

    public E2JBaseLlamaEntity(EntityType<? extends LlamaEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createJollyLlamaAttributes() {
        return createAbstractDonkeyAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

}
