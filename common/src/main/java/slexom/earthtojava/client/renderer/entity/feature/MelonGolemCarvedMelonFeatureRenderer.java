package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MelonGolemCarvedMelonFeatureRenderer extends FeatureRenderer<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> {
    private final BlockRenderManager blockRenderManager;
    private final ItemRenderer itemRenderer;

    public MelonGolemCarvedMelonFeatureRenderer(FeatureRendererContext<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> featureRendererContext, BlockRenderManager blockRenderManager, ItemRenderer itemRenderer) {
        super(featureRendererContext);
        this.blockRenderManager = blockRenderManager;
        this.itemRenderer = itemRenderer;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, MelonGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isMelonEquipped()) {
            return;
        }
        if (entity.isInvisible()) {
            return;
        }

        boolean hasOutline = MinecraftClient.getInstance().hasOutline(entity);

        matrixStack.push();
        this.getContextModel().getHead().rotate(matrixStack);
        matrixStack.translate(0.0D, -0.34375D, 0.0D);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        matrixStack.scale(0.625F, -0.625F, -0.625F);
        ItemStack head = new ItemStack(BlockInit.CARVED_MELON.get());
        ItemStack headBlink = new ItemStack(BlockInit.MELON_GOLEM_HEAD_BLINK.get());
        ItemStack headShoot = new ItemStack(BlockInit.MELON_GOLEM_HEAD_SHOOT.get());
        ItemStack itemStack;
        if (entity.isShooting()) {
            itemStack = headShoot;
        } else {
            if (entity.blinkManager.getBlinkRemainingTicks() > 0) itemStack = headBlink;
            else itemStack = head;
        }

        if (hasOutline) {
            BlockState blockState = Blocks.CARVED_PUMPKIN.getDefaultState();
            BakedModel bakedModel = this.blockRenderManager.getModel(blockState);
            int n = LivingEntityRenderer.getOverlay(entity, 0.0f);
            matrixStack.translate(-0.5f, -0.5f, -0.5f);
            this.blockRenderManager.getModelRenderer().render(matrixStack.peek(), vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), blockState, bakedModel, 0.0f, 0.0f, 0.0f, light, n);
        } else {
            this.itemRenderer.renderItem(entity, itemStack, ModelTransformationMode.HEAD, false, matrixStack, vertexConsumerProvider, entity.world, light, LivingEntityRenderer.getOverlay(entity, 0.0f), entity.getId());
        }

        matrixStack.pop();
    }
}