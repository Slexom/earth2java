package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.entity.EntityVariantManager;

public class E2JBaseSheepEntity extends SheepEntity {

    public BlinkManager blinkManager;
    private final EntityVariantManager<E2JBaseSheepEntity> variantManager;

    public E2JBaseSheepEntity(EntityType<? extends SheepEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        variantManager = new EntityVariantManager<>();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createSheepAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

    @Override
    public E2JBaseSheepEntity createChild(ServerWorld world, PassiveEntity other) {
        return variantManager.getChild(this, other).create(world);
    }

}
