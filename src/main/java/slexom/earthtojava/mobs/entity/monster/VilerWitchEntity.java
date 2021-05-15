package slexom.earthtojava.mobs.entity.monster;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.init.SoundEventsInit;

import java.util.Random;

public class VilerWitchEntity extends WitchEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    private int castingTicks = 0;

    public VilerWitchEntity(EntityType<? extends WitchEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;

        if (this.castingTicks > 0) {
            --this.castingTicks;
        }
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
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
            float g = MathHelper.sqrt(d * d + f * f);
            Potion potion = Potions.HARMING;
            if (target instanceof RaiderEntity) {
                if (target.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.REGENERATION;
                }
                this.setTarget(null);
            } else if (g >= 8.0F && !target.hasStatusEffect(StatusEffects.SLOWNESS)) {
                potion = Potions.SLOWNESS;
            } else if (target.getHealth() >= 8.0F && !target.hasStatusEffect(StatusEffects.POISON)) {
                potion = Potions.POISON;
            } else if (g <= 3.0F && !target.hasStatusEffect(StatusEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.WEAKNESS;
            }
            PotionEntity potionEntity = new PotionEntity(this.world, this);
            if (potion == Potions.HARMING || potion == Potions.POISON) {
                potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), potion));
            } else {
                potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
            }
            potionEntity.pitch -= -20.0F;
            potionEntity.setVelocity(d, e + (double) (g * 0.2F), f, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEventsInit.VILER_WITCH_CASTING, this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }
            this.world.spawnEntity(potionEntity);
        }
    }

}
