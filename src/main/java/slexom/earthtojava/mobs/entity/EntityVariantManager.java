package slexom.earthtojava.mobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;

public class EntityVariantManager<T extends PassiveEntity> {

    public EntityType<T> getChild(PassiveEntity parent1, PassiveEntity parent2) {
        if (parent1.getRandom().nextInt(100) > 50) {
            return (EntityType<T>) parent1.getType();
        } else {
            return (EntityType<T>) parent2.getType();
        }
    }

}
