package slexom.earthtojava.mixins;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slexom.earthtojava.init.RegisterHelper;

@Mixin(EntityModelLoader.class)
public class EntityModelLoaderMixin {

    @Inject(method = "getModelPart", at = @At("HEAD"), cancellable = true)
    public void E2JGetModelPart(EntityModelLayer layer, CallbackInfoReturnable<ModelPart> returnable) {
        if (RegisterHelper.E2J_MODEL_LAYERS.containsKey(layer)) {
            returnable.setReturnValue(RegisterHelper.E2J_MODEL_LAYERS.get(layer).createModel());
        }
    }

}