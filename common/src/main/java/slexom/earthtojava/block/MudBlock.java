package slexom.earthtojava.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import slexom.earthtojava.Earth2JavaMod;

public class MudBlock extends FluidBlock {

    public MudBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    private void triggerMixEffects(WorldAccess worldIn, BlockPos pos) {
        worldIn.syncWorldEvent(1501, pos, 0);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    public boolean receiveNeighborFluids(World world, BlockPos pos, BlockState state) {
        Identifier mudTag = new Identifier(Earth2JavaMod.MOD_ID, "mud");
        TagKey<Fluid> mudTagKey = TagKey.of(Registry.FLUID_KEY, mudTag);
        if (this.fluid.isIn(mudTagKey)) {
            boolean flag = false;
            for (Direction direction : Direction.values()) {
                if (world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                this.triggerMixEffects(world, pos);
                return false;
            }
        }
        return true;
    }

}
