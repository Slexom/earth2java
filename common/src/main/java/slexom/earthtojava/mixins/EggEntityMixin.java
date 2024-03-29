package slexom.earthtojava.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slexom.earthtojava.init.EntityTypesInit;


@Mixin(EggEntity.class)
public class EggEntityMixin {
	private final Random random = Random.create();

	@Redirect(
			method = "onCollision",
			at = @At(
					value = "FIELD",
					target = "Lnet/minecraft/entity/EntityType;CHICKEN:Lnet/minecraft/entity/EntityType;")
	)
	public EntityType<?> e2jGetChickenType() {
		int r = random.nextInt(20);
		return switch (r) {
			case 2 -> EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get();
			case 4 -> EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get();
			case 6 -> EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get();
			case 8 -> EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get();
			case 10 -> EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get();
			case 12 -> EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT.get();
			default -> EntityType.CHICKEN;
		};
	}

}
