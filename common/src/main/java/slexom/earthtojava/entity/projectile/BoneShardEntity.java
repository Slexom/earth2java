package slexom.earthtojava.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
		super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), owner, world);
	}

	public BoneShardEntity(World world, double x, double y, double z) {
		super(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), x, y, z, world);
	}

	public BoneShardEntity(EntityType<BoneShardEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected Item getDefaultItem() {
		return ItemInit.BONE_SHARD.get();
	}

	@Environment(EnvType.CLIENT)
	private ParticleEffect getParticleParameters() {
		ItemStack itemStack = getItem();
		return itemStack.isEmpty() ? ParticleTypes.SPIT : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void handleStatus(byte status) {
		if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
			ParticleEffect particleEffect = getParticleParameters();
			for (int i = 0; i < 8; ++i) {
				getWorld().addParticle(particleEffect, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		playSound(SoundEventsInit.BONE_SPIDER_BONE_STAB.get(), 1.0F, 1.0F);
		Entity entity = entityHitResult.getEntity();
		entity.damage(entity.getDamageSources().thrown(this, getOwner()), 10.0F);
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!getWorld().isClient) {
			getWorld().sendEntityStatus(this, (byte) 3);
			discard();
		}

	}

}