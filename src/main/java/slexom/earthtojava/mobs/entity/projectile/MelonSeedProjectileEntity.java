package slexom.earthtojava.mobs.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import slexom.earthtojava.mobs.init.EntityTypesInit;

public class MelonSeedProjectileEntity extends ProjectileItemEntity {


    public MelonSeedProjectileEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), throwerIn, worldIn);
    }

    public MelonSeedProjectileEntity(World worldIn, double x, double y, double z) {
        super(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), x, y, z, worldIn);
    }

    public MelonSeedProjectileEntity(EntityType<MelonSeedProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.MELON_SEEDS;
    }

    @Environment(EnvType.CLIENT)
    private IParticleData makeParticle() {
        ItemStack itemstack = this.func_213882_k();
        return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10.0F);
        }
        if (!this.world.isClient) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }



}