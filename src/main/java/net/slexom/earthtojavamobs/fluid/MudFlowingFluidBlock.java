package net.slexom.earthtojavamobs.fluid;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;

import java.util.List;
import java.util.function.Supplier;

public class MudFlowingFluidBlock extends FlowingFluidBlock {
    private FlowingFluid fluid;

    private final java.util.function.Supplier<? extends Fluid> supplier;
    private final List<IFluidState> field_212565_c;

    private boolean fluidStateCacheInitialized = false;

    public MudFlowingFluidBlock(FlowingFluid fluidIn, Block.Properties builder) {
        super(fluidIn, builder);
        this.fluid = fluidIn;
        this.field_212565_c = Lists.newArrayList();
        this.field_212565_c.add(fluidIn.getStillFluidState(false));
        for (int i = 1; i < 8; ++i) {
            this.field_212565_c.add(fluidIn.getFlowingFluidState(8 - i, false));
        }
        this.field_212565_c.add(fluidIn.getFlowingFluidState(8, true));
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, Integer.valueOf(0)));
        fluidStateCacheInitialized = true;
        supplier = fluidIn.delegate;
    }
    public MudFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier, Block.Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
        this.fluid = null;
        this.field_212565_c = Lists.newArrayList();
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, Integer.valueOf(0)));
        this.supplier = supplier;
    }

    @Override
    public float getSpeedFactor() {
        return 0.3F;
    }


    private void triggerMixEffects(IWorld worldIn, BlockPos pos) {
        worldIn.playEvent(1501, pos, 0);
    }


    public boolean reactWithNeighbors(World worldIn, BlockPos pos, BlockState state) {

        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        if(FluidTags.getCollection().get(mudTag) != null) {
            if (this.fluid.isIn(FluidTags.getCollection().get(mudTag))) {
                boolean flagForMud = false;
                for (Direction direction : Direction.values()) {
                    if (worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA)) {
                        flagForMud = true;
                        break;
                    }
                }
                if (flagForMud) {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.DIRT.getDefaultState()));
                    this.triggerMixEffects(worldIn, pos);
                    return false;
                }
            }
        }
        if (this.fluid.isIn(FluidTags.LAVA)) {
            boolean flag = false;
            for (Direction direction : Direction.values()) {
                if (direction != Direction.DOWN && worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                IFluidState ifluidstate = worldIn.getFluidState(pos);
                if (ifluidstate.isSource()) {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.OBSIDIAN.getDefaultState()));
                    this.triggerMixEffects(worldIn, pos);
                    return false;
                }
                if (ifluidstate.getActualHeight(worldIn, pos) >= 0.44444445F) {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.COBBLESTONE.getDefaultState()));
                    this.triggerMixEffects(worldIn, pos);
                    return false;
                }
            }
        }
        return true;

    }

}
