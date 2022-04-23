package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;

public class E2JBaseSpiderEntity<T extends SpiderEntity> extends SpiderEntity {

    public BlinkManager blinkManager;

    public E2JBaseSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createSpiderAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

}
