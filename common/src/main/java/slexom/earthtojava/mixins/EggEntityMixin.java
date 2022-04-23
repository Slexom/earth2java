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
        return switch (random) {
            case 2 -> EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get();
            case 4 -> EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get();
            case 6 -> EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get();
            case 8 -> EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get();
            case 10 -> EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get();
            default -> EntityType.CHICKEN;
        };
    }

}
