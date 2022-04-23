package slexom.earthtojava.mixins;

import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntityRenderer.class)
public class CatEntityRendererMixin {
    Identifier PEANUT_BUTTER_TEXTURE = new Identifier("earthtojavamobs:textures/mobs/cat/peanut_butter/peanut_butter_jellie.png");

    @Inject(at = @At("HEAD"), method = "getTexture", cancellable = true)
    public void e2jGetTexture(CatEntity catEntity, CallbackInfoReturnable<Identifier> cir) {
        String catName = Formatting.strip(catEntity.getName().getString());
        if ("Peanut Butter".equals(catName)) {
            cir.setReturnValue(PEANUT_BUTTER_TEXTURE);
        }
    }

}
