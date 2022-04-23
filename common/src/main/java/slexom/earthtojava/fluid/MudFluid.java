package slexom.earthtojava.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.FluidInit;
import slexom.earthtojava.init.ItemInit;

public class MudFluid extends FlowableFluid {

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    public Fluid getFlowing() {
        return FluidInit.MUD_FLUID_FLOWING.get();
    }

    @Override
    public Fluid getStill() {
        return FluidInit.MUD_FLUID_STILL.get();
    }

    @Override
    public Item getBucketItem() {
        return ItemInit.MUD_BUCKET.get();
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }


    @Override
    protected int getFlowSpeed(WorldView worldIn) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView worldIn) {
        return 1;
    }


    @Override
    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        Identifier mudTag = new Identifier(Earth2JavaMod.MOD_ID, "mud");
        TagKey<Fluid> mudTagKey = TagKey.of(Registry.FLUID_KEY, mudTag);
        return direction == Direction.DOWN && !fluid.isIn(mudTagKey);
    }

    @Override
    public int getTickRate(WorldView p_205569_1_) {
        return 20;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return BlockInit.MUD_BLOCK.get().getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return state.get(LEVEL);
    }

    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }

    @Override
    protected void flow(WorldAccess worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, FluidState fluidStateIn) {
        Identifier mudTag = new Identifier(Earth2JavaMod.MOD_ID, "mud");
        TagKey<Fluid> mudTagKey = TagKey.of(Registry.FLUID_KEY, mudTag);
        if (this.getFlowing().isIn(mudTagKey)) {
            boolean flag = false;
            for (Direction dir : Direction.values()) {
                if (worldIn.getFluidState(pos.offset(dir)).isIn(FluidTags.LAVA)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 3);
                this.playExtinguishEvent(worldIn, pos);
                return;
            }
        }
        super.flow(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }


    public static class Flowing extends MudFluid {

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        public boolean isStill(FluidState state) {
            return false;
        }

    }

    public static class Source extends MudFluid {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }

}
