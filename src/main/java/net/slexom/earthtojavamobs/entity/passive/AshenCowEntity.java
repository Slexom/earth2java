
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AshenCowEntity extends CowEntity {

    public AshenCowEntity(EntityType<AshenCowEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    public CowEntity createChild(AgeableEntity ageable) {
        return (AshenCowEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
