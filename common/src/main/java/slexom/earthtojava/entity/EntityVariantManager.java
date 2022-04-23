package slexom.earthtojava.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;

public class EntityVariantManager<T extends PassiveEntity> {

    public EntityType<T> getChild(PassiveEntity parent1, PassiveEntity parent2) {
        if (parent1.getRandom().nextInt(100) > 50) {
            return (EntityType<T>) parent2.getType();
        }
        return (EntityType<T>) parent1.getType();
    }

}
