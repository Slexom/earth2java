package slexom.earthtojava.mobs.client.renderer.tileentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.block.RainbowBedBlock;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;
import slexom.earthtojava.mobs.tileentity.RainbowBedTileEntity;

@Environment(EnvType.CLIENT)
public class RainbowBedTileEntityRenderer extends BlockEntityRenderer<RainbowBedTileEntity> {
    private final ModelPart field_228843_a_;
    private final ModelPart field_228844_c_;
    private final ModelPart[] legs = new ModelPart[4];

    public RainbowBedTileEntityRenderer(BlockEntityRenderDispatcher p_i226004_1_) {
        super(p_i226004_1_);
        this.field_228843_a_ = new ModelPart(64, 64, 0, 0);
        this.field_228843_a_.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.field_228844_c_ = new ModelPart(64, 64, 0, 22);
        this.field_228844_c_.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.legs[0] = new ModelPart(64, 64, 50, 0);
        this.legs[1] = new ModelPart(64, 64, 50, 6);
        this.legs[2] = new ModelPart(64, 64, 50, 12);
        this.legs[3] = new ModelPart(64, 64, 50, 18);
        this.legs[0].addCuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[1].addCuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[2].addCuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[3].addCuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[0].pitch = ((float) Math.PI / 2F);
        this.legs[1].pitch = ((float) Math.PI / 2F);
        this.legs[2].pitch = ((float) Math.PI / 2F);
        this.legs[3].pitch = ((float) Math.PI / 2F);
        this.legs[0].roll = 0.0F;
        this.legs[1].roll = ((float) Math.PI / 2F);
        this.legs[2].roll = ((float) Math.PI * 1.5F);
        this.legs[3].roll = (float) Math.PI;
    }

    public void render(RainbowBedTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        SpriteIdentifier material = new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier("earthtojavamobs:entity/bed/rainbow"));
        World world = tileEntityIn.getWorld();
        if (world != null) {
            BlockState blockstate = tileEntityIn.getCachedState();
            DoubleBlockProperties.PropertySource<RainbowBedTileEntity> icallbackwrapper = DoubleBlockProperties.toPropertySource(BlockEntityTypeInit.RAINBOW_BED, RainbowBedBlock::getBedPart, RainbowBedBlock::getOppositePartDirection, ChestBlock.FACING, blockstate, world, tileEntityIn.getPos(), (worldAccess, blockPos) -> false);
            int i = icallbackwrapper.apply(new LightmapCoordinatesRetriever<>()).get(combinedLightIn);
            this.method_3558(matrixStackIn, bufferIn, blockstate.get(RainbowBedBlock.PART) == BedPart.HEAD, blockstate.get(RainbowBedBlock.FACING), material, i, combinedOverlayIn, false);
        } else {
            this.method_3558(matrixStackIn, bufferIn, true, Direction.SOUTH, material, combinedLightIn, combinedOverlayIn, false);
            this.method_3558(matrixStackIn, bufferIn, false, Direction.SOUTH, material, combinedLightIn, combinedOverlayIn, true);
        }

    }

    private void method_3558(MatrixStack p_228847_1_, VertexConsumerProvider p_228847_2_, boolean p_228847_3_, Direction p_228847_4_, SpriteIdentifier p_228847_5_, int p_228847_6_, int p_228847_7_, boolean p_228847_8_) {
        this.field_228843_a_.visible = p_228847_3_;
        this.field_228844_c_.visible = !p_228847_3_;
        this.legs[0].visible = !p_228847_3_;
        this.legs[1].visible = p_228847_3_;
        this.legs[2].visible = !p_228847_3_;
        this.legs[3].visible = p_228847_3_;
        p_228847_1_.push();
        p_228847_1_.translate(0.0D, 0.5625D, p_228847_8_ ? -1.0D : 0.0D);
        p_228847_1_.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        p_228847_1_.translate(0.5D, 0.5D, 0.5D);
        p_228847_1_.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F + p_228847_4_.asRotation()));
        p_228847_1_.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer ivertexbuilder = p_228847_5_.getVertexConsumer(p_228847_2_, RenderLayer::getEntitySolid);
        this.field_228843_a_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228844_c_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.legs[0].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.legs[1].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.legs[2].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.legs[3].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        p_228847_1_.pop();
    }
}