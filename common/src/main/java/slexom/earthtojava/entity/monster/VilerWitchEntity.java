package slexom.earthtojava.entity.monster;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.init.SoundEventsInit;

public class VilerWitchEntity extends WitchEntity {


    public final BlinkManager blinkManager;

    private int castingTicks = 0;

    public VilerWitchEntity(EntityType<? extends WitchEntity> entityType, World world) {
        super(entityType, world);
        blinkManager = new BlinkManager();
    }


    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
        if (this.castingTicks > 0) {
            --this.castingTicks;
        }
    }

    @Environment(EnvType.CLIENT)
    public int getCastingTicks() {
        return this.castingTicks;
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {
        if (this.isDrinking()) return;


        this.castingTicks = this.random.nextInt(20) + 40;
        Vec3d vec3d = target.getVelocity();
        double dX = target.getX() + vec3d.x - this.getX();
        double dY = target.getEyeY() - 1.1d - this.getY();
        double dZ = target.getZ() + vec3d.z - this.getZ();
        double distance = Math.sqrt(dX * dX + dZ * dZ);
        Potion potion = Potions.HARMING;
        if (target instanceof RaiderEntity) {
            if (target.getHealth() <= 4.0F) {
                potion = Potions.HEALING;
            } else {
                potion = Potions.REGENERATION;
            }
            this.setTarget(null);
        } else if (distance >= 8.0D && !target.hasStatusEffect(StatusEffects.SLOWNESS)) {
            potion = Potions.SLOWNESS;
        } else if (target.getHealth() >= 8.0F && !target.hasStatusEffect(StatusEffects.POISON)) {
            potion = Potions.POISON;
        } else if (distance <= 3.0D && !target.hasStatusEffect(StatusEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
            potion = Potions.WEAKNESS;
        }
        PotionEntity potionEntity = new PotionEntity(this.getWorld(), this);
        if (potion == Potions.HARMING || potion == Potions.POISON) {
            potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
        } else {
            potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        }
        potionEntity.setPitch(potionEntity.getPitch() - -20.0F);
        potionEntity.setVelocity(dX, dY + (distance * 0.2D), dZ, 0.75F, 8.0F);
        if (!this.isSilent()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEventsInit.VILER_WITCH_CASTING.get(), this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
        }
        this.getWorld().spawnEntity(potionEntity);

    }

}
