package slexom.earthtojava.mobs.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slexom.earthtojava.mobs.block.RainbowBedBlock;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;

@Mixin(BuiltinModelItemRenderer.class)
public class BERItemRenderer {

    @Inject(at = @At("HEAD"), method = "render")
    private void rainbowBed(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof RainbowBedBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(BlockEntityTypeInit.RAINBOW_BED.instantiate(), matrixStack, vertexConsumerProvider, i, j);
            }
        }
    }

}

//render(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation/Mode;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I;I)V
