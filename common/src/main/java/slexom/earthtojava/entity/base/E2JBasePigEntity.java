package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBasePigEntity extends PigEntity {

    public BlinkManager blinkManager;
    private final EntityVariantManager<E2JBasePigEntity> variantManager;

    public E2JBasePigEntity(EntityType<? extends PigEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        variantManager = new EntityVariantManager<>();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createPigAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

    @Override
    public E2JBasePigEntity createChild(ServerWorld world, PassiveEntity other) {
        return variantManager.getChild(this, other).create(world);
    }
}
