package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class JumboRabbitEntity extends RabbitEntity {
    public JumboRabbitEntity(EntityType<? extends RabbitEntity> p_i50247_1_, World p_i50247_2_) {
        super(p_i50247_1_, p_i50247_2_);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    public RabbitEntity createChild(AgeableEntity ageable) {
        return (JumboRabbitEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
