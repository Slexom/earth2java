package slexom.earthtojava.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EggEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.Random;

@Mixin(EggEntity.class)
public class EggEntityMixin {

    @Redirect(
            method = "onCollision",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/EntityType;CHICKEN:Lnet/minecraft/entity/EntityType;")
    )
    public EntityType getChickenType() {
        int random = new Random().nextInt(20);
        switch (random) {
            case 2:
                return EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT;
            case 4:
                return EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT;
            case 6:
                return EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT;
            case 8:
                return EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT;
            case 10:
                return EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT;
            default:
                return EntityType.CHICKEN;
        }
    }

}
