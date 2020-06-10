package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


public class SpottedPigEntity extends PigEntity {

    public SpottedPigEntity(EntityType<SpottedPigEntity> type, World world) {
        super(type, world);
        experienceValue = 0;
        setNoAI(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
    }

    @Override
    public PigEntity createChild(AgeableEntity ageable) {
        return (SpottedPigEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
 
