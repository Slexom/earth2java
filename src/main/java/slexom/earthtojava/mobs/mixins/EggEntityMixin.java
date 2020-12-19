package slexom.earthtojava.mobs.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import slexom.earthtojava.mobs.init.EntityTypesInit;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin extends ThrownItemEntity {


    public EggEntityMixin(World world, LivingEntity owner) {
        super(EntityType.EGG, owner, world);
    }

    /**
     * Overwrite original method and add mod chickens to possible spawn
     * @param hitResult
     */
    public void onCollision(HitResult hitResult) {
        if (!this.world.isClient) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }
                int random = this.random.nextInt(20);
                for (int j = 0; j < i; ++j) {
                    ChickenEntity chickenEntity;
                    switch (random) {
                        case 2: {
                            chickenEntity = EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.create(this.world);
                            break;
                        }
                        case 4: {
                            chickenEntity = EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.create(this.world);
                            break;
                        }
                        case 6: {
                            chickenEntity = EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.create(this.world);
                            break;
                        }
                        case 8: {
                            chickenEntity = EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.create(this.world);
                            break;
                        }
                        case 10: {
                            chickenEntity = EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.create(this.world);
                            break;
                        }
                        default: {
                            chickenEntity = EntityType.CHICKEN.create(this.world);
                        }
                    }
                    chickenEntity.setBreedingAge(-24000);
                    chickenEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.yaw, 0.0F);
                    this.world.spawnEntity(chickenEntity);
                }
            }
            this.world.sendEntityStatus(this, (byte) 3);
            this.remove();
        }
    }

}
