package slexom.earthtojava.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;

public class E2JBaseZombieEntity extends ZombieEntity {

    public BlinkManager blinkManager;

    public E2JBaseZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        blinkManager = new BlinkManager();
    }

    public static DefaultAttributeContainer.Builder createZombieAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D).add(EntityAttributes.GENERIC_ARMOR, 2.0D).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

}
