
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AmberChickenEntity extends ChickenEntity {

    public AmberChickenEntity(EntityType<AmberChickenEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    public ChickenEntity createChild(AgeableEntity ageable) {
        return (AmberChickenEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
