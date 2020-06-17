package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


public class PalePigEntity extends PigEntity {

    public PalePigEntity(EntityType<PalePigEntity> type, World world) {
        super(type, world);
        experienceValue = 0;
        setNoAI(false);
    }

    @Override
    public PigEntity createChild(AgeableEntity ageable) {
        return (PalePigEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
 
