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

import java.util.Random;

public class VilerWitchEntity extends WitchEntity {


    public BlinkManager blinkManager;

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
        if (!this.isDrinking()) {
            this.castingTicks = new Random().nextInt(20) + 40;
            Vec3d vec3d = target.getVelocity();
            double d = target.getX() + vec3d.x - this.getX();
            double e = target.getEyeY() - 1.100000023841858D - this.getY();
            double f = target.getZ() + vec3d.z - this.getZ();
            double g = Math.sqrt(d * d + f * f);
            Potion potion = Potions.HARMING;
            if (target instanceof RaiderEntity) {
                if (target.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.REGENERATION;
                }
                this.setTarget(null);
            } else if (g >= 8.0D && !target.hasStatusEffect(StatusEffects.SLOWNESS)) {
                potion = Potions.SLOWNESS;
            } else if (target.getHealth() >= 8.0F && !target.hasStatusEffect(StatusEffects.POISON)) {
                potion = Potions.POISON;
            } else if (g <= 3.0D && !target.hasStatusEffect(StatusEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.WEAKNESS;
            }
            PotionEntity potionEntity = new PotionEntity(this.world, this);
            if (potion == Potions.HARMING || potion == Potions.POISON) {
                potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
            } else {
                potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
            }
            potionEntity.setPitch(potionEntity.getPitch() - -20.0F);
            potionEntity.setVelocity(d, e + (g * 0.2D), f, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEventsInit.VILER_WITCH_CASTING, this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }
            this.world.spawnEntity(potionEntity);
        }
    }

}
