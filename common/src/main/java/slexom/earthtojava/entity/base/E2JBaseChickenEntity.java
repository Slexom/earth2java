package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseChickenEntity extends ChickenEntity {

    public BlinkManager blinkManager;
    private final EntityVariantManager<E2JBaseChickenEntity> variantManager;

    public E2JBaseChickenEntity(EntityType<? extends ChickenEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        variantManager = new EntityVariantManager<>();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createChickenAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

    @Override
    public E2JBaseChickenEntity createChild(ServerWorld world, PassiveEntity other) {
        return variantManager.getChild(this, other).create(world);
    }

}
