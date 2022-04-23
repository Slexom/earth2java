package slexom.earthtojava.client.renderer.block.entity;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;
import slexom.earthtojava.init.BlockEntityTypeInit;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class RainbowBedBlockEntityRenderer implements BlockEntityRenderer<RainbowBedBlockEntity> {
    private final ModelPart head;
    private final ModelPart foot;

    public RainbowBedBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this.head = context.getLayerModelPart(EntityModelLayersInit.RAINBOW_BED_HEAD_MODEL_LAYER);
        this.foot = context.getLayerModelPart(EntityModelLayersInit.RAINBOW_BED_FOOT_MODEL_LAYER);
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), ModelTransform.NONE);
        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(50, 6).cuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 1.5707964F));
        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(50, 18).cuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 3.1415927F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getFootTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 22).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), ModelTransform.NONE);
        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(50, 0).cuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 0.0F));
        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(50, 12).cuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), ModelTransform.rotation(1.5707964F, 0.0F, 4.712389F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void render(RainbowBedBlockEntity bedBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        SpriteIdentifier spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier("earthtojavamobs:entity/bed/rainbow"));
        World world = bedBlockEntity.getWorld();
        if (world != null) {
            BlockState blockState = bedBlockEntity.getCachedState();
            DoubleBlockProperties.PropertySource<RainbowBedBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(BlockEntityTypeInit.RAINBOW_BED, RainbowBedBlock::getBedPart, RainbowBedBlock::getOppositePartDirection, ChestBlock.FACING, blockState, world, bedBlockEntity.getPos(), (worldAccess, blockPos) -> false);
            int k = ((Int2IntFunction) propertySource.apply(new LightmapCoordinatesRetriever())).get(light);
            this.method_3558(matrixStack, vertexConsumerProvider, blockState.get(RainbowBedBlock.PART) == BedPart.HEAD ? this.head : this.foot, blockState.get(RainbowBedBlock.FACING), spriteIdentifier, k, overlay, false);
        } else {
            this.method_3558(matrixStack, vertexConsumerProvider, this.head, Direction.SOUTH, spriteIdentifier, light, overlay, false);
            this.method_3558(matrixStack, vertexConsumerProvider, this.foot, Direction.SOUTH, spriteIdentifier, light, overlay, true);
        }
    }

    private void method_3558(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, ModelPart modelPart, Direction direction, SpriteIdentifier spriteIdentifier, int light, int overlay, boolean bl) {
        matrix.push();
        matrix.translate(0.0D, 0.5625D, bl ? -1.0D : 0.0D);
        matrix.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        matrix.translate(0.5D, 0.5D, 0.5D);
        matrix.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F + direction.asRotation()));
        matrix.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
        modelPart.render(matrix, vertexConsumer, light, overlay);
        matrix.pop();
    }

}