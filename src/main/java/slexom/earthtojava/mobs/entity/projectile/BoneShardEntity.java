package slexom.earthtojava.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.ItemInit;
import slexom.earthtojava.init.SoundEventsInit;

public class BoneShardEntity extends ThrownItemEntity {


    public BoneShardEntity(World world, LivingEntity owner) {
        super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT, owner, world);
    }

    public BoneShardEntity(World world, double x, double y, double z) {
        super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT, x, y, z, world);
    }

    public BoneShardEntity(EntityType<BoneShardEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.BONE_SHARD;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.SPIT : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        this.playSound(SoundEventsInit.BONE_SPIDER_BONE_STAB, 1.0F, 1.0F);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 10.0F);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            this.discard();
        }

    }

}