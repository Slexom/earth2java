
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


public class MidnightChickenEntity extends ChickenEntity {

    public MidnightChickenEntity(EntityType<MidnightChickenEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    public ChickenEntity createChild(AgeableEntity ageable) {
        return (MidnightChickenEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}