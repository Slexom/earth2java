package slexom.earthtojava.mobs.client.renderer.tileentity;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.render.Atlases;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderType;
import net.minecraft.client.render.Vector3f;
import net.minecraft.client.render.model.Material;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.tileentity.DualBrightnessCallback;
import net.minecraft.client.render.tileentity.TileEntityRenderer;
import net.minecraft.client.render.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.block.RainbowBedBlock;
import slexom.earthtojava.mobs.init.TileEntityTypeInit;
import slexom.earthtojava.mobs.tileentity.RainbowBedTileEntity;

@Environment(EnvType.CLIENT)
public class RainbowBedTileEntityRenderer extends TileEntityRenderer<RainbowBedTileEntity> {
    private final ModelRenderer field_228843_a_;
    private final ModelRenderer field_228844_c_;
    private final ModelRenderer[] field_228845_d_ = new ModelRenderer[4];

    public RainbowBedTileEntityRenderer(TileEntityRendererDispatcher p_i226004_1_) {
        super(p_i226004_1_);
        this.field_228843_a_ = new ModelPart(64, 64, 0, 0);
        this.field_228843_a_.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.field_228844_c_ = new ModelPart(64, 64, 0, 22);
        this.field_228844_c_.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.field_228845_d_[0] = new ModelPart(64, 64, 50, 0);
        this.field_228845_d_[1] = new ModelPart(64, 64, 50, 6);
        this.field_228845_d_[2] = new ModelPart(64, 64, 50, 12);
        this.field_228845_d_[3] = new ModelPart(64, 64, 50, 18);
        this.field_228845_d_[0].addCuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.field_228845_d_[1].addCuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.field_228845_d_[2].addCuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.field_228845_d_[3].addCuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.field_228845_d_[0].pitch = ((float) Math.PI / 2F);
        this.field_228845_d_[1].pitch = ((float) Math.PI / 2F);
        this.field_228845_d_[2].pitch = ((float) Math.PI / 2F);
        this.field_228845_d_[3].pitch = ((float) Math.PI / 2F);
        this.field_228845_d_[0].rotateAngleZ = 0.0F;
        this.field_228845_d_[1].rotateAngleZ = ((float) Math.PI / 2F);
        this.field_228845_d_[2].rotateAngleZ = ((float) Math.PI * 1.5F);
        this.field_228845_d_[3].rotateAngleZ = (float) Math.PI;
    }

    public void render(RainbowBedTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Material material = new Material(Atlases.BED_ATLAS, new Identifier("earthtojavamobs:entity/bed/rainbow"));
        World world = tileEntityIn.getWorld();
        if (world != null) {
            BlockState blockstate = tileEntityIn.getBlockState();
            TileEntityMerger.ICallbackWrapper<RainbowBedTileEntity> icallbackwrapper = TileEntityMerger.func_226924_a_(TileEntityTypeInit.RAINBOW_BED.get(), RainbowBedBlock::func_226863_i_, RainbowBedBlock::func_226862_h_, ChestBlock.FACING, blockstate, world, tileEntityIn.getPos(), (p_228846_0_, p_228846_1_) -> false);
            int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).get(combinedLightIn);
            this.func_228847_a_(matrixStackIn, bufferIn, blockstate.get(RainbowBedBlock.PART) == BedPart.HEAD, blockstate.get(RainbowBedBlock.HORIZONTAL_FACING), material, i, combinedOverlayIn, false);
        } else {
            this.func_228847_a_(matrixStackIn, bufferIn, true, Direction.SOUTH, material, combinedLightIn, combinedOverlayIn, false);
            this.func_228847_a_(matrixStackIn, bufferIn, false, Direction.SOUTH, material, combinedLightIn, combinedOverlayIn, true);
        }

    }

    private void func_228847_a_(MatrixStack p_228847_1_, VertexConsumerProvider p_228847_2_, boolean p_228847_3_, Direction p_228847_4_, Material p_228847_5_, int p_228847_6_, int p_228847_7_, boolean p_228847_8_) {
        this.field_228843_a_.showModel = p_228847_3_;
        this.field_228844_c_.showModel = !p_228847_3_;
        this.field_228845_d_[0].showModel = !p_228847_3_;
        this.field_228845_d_[1].showModel = p_228847_3_;
        this.field_228845_d_[2].showModel = !p_228847_3_;
        this.field_228845_d_[3].showModel = p_228847_3_;
        p_228847_1_.push();
        p_228847_1_.translate(0.0D, 0.5625D, p_228847_8_ ? -1.0D : 0.0D);
        p_228847_1_.rotate(Vector3f.XP.rotationDegrees(90.0F));
        p_228847_1_.translate(0.5D, 0.5D, 0.5D);
        p_228847_1_.rotate(Vector3f.ZP.rotationDegrees(180.0F + p_228847_4_.getHorizontalAngle()));
        p_228847_1_.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer ivertexbuilder = p_228847_5_.getBuffer(p_228847_2_, RenderType::getEntitySolid);
        this.field_228843_a_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228844_c_.render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228845_d_[0].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228845_d_[1].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228845_d_[2].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        this.field_228845_d_[3].render(p_228847_1_, ivertexbuilder, p_228847_6_, p_228847_7_);
        p_228847_1_.pop();
    }
}