package slexom.earthtojava.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slexom.earthtojava.Earth2JavaMod;

@Mixin(BackgroundRenderer.class)
public class MudFogMixin {

    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private static void mudFogColor(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci) {
        Identifier mudTag = new Identifier(Earth2JavaMod.MOD_ID, "mud");
        TagKey<Fluid> mudTagKey = TagKey.of(Registry.FLUID_KEY, mudTag);
        BlockPos blockPos = camera.getBlockPos();
        FluidState fluidState = world.getFluidState(blockPos);
        if (fluidState.isIn(mudTagKey)) {
            red = 87.0F / 255.0F;
            green = 54.0F / 255.0F;
            blue = 35.0F / 255.0F;
            RenderSystem.clearColor(red, green, blue, 0.0F);
            ci.cancel();
        }
    }


    @Inject(at = @At("HEAD"), method = "applyFog", cancellable = true)
    private static void mudFogDensity(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        Identifier mudTag = new Identifier(Earth2JavaMod.MOD_ID, "mud");
        TagKey<Fluid> mudTagKey = TagKey.of(Registry.FLUID_KEY, mudTag);
        Entity entity = camera.getFocusedEntity();
        World world = entity.getEntityWorld();
        BlockPos blockPos = camera.getBlockPos();
        FluidState fluidState = world.getFluidState(blockPos);
        if (fluidState.isIn(mudTagKey)) {
            RenderSystem.setShaderFogStart(0.0F);
            RenderSystem.setShaderFogEnd(1.0F);
            // RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
            // RenderSystem.setupNvFogDistance();
            ci.cancel();
        }
    }

}
