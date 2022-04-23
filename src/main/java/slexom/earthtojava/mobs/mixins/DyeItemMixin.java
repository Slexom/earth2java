package slexom.earthtojava.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Mixin(DyeItem.class)
public class DyeItemMixin {

    @Inject(at = @At("HEAD"), method = "useOnEntity", cancellable = true)
    public void e2jUseOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> returnable) {
        if (entity instanceof E2JBaseMonoColorSheepEntity) {
            returnable.setReturnValue(ActionResult.PASS);
        }
    }

}
