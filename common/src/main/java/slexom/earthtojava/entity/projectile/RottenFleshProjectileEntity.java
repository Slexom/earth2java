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
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import slexom.earthtojava.init.EntityTypesInit;
import slexom.earthtojava.init.SoundEventsInit;

public class RottenFleshProjectileEntity extends ThrownItemEntity {

    public RottenFleshProjectileEntity(World world, LivingEntity owner) {
        super(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get(), owner, world);
    }

    public RottenFleshProjectileEntity(World world, double x, double y, double z) {
        super(EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get(), x, y, z, world);
    }

    public RottenFleshProjectileEntity(EntityType<RottenFleshProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.ROTTEN_FLESH;
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
        this.playSound(SoundEventsInit.LOBBER_ZOMBIE_PROJECTILE_SOUND.get(), 1.0F, 1.0F);
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