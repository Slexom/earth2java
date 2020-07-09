package slexom.earthtojava.mobs.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.EarthtojavamobsMod;

import java.util.function.Supplier;

public class MudBlock extends FlowingFluidBlock {

    public MudBlock(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    private void triggerMixEffects(IWorld worldIn, BlockPos pos) {
        worldIn.playEvent(1501, pos, 0);
    }

    public boolean reactWithNeighbors(World worldIn, BlockPos pos, BlockState state) {
        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        if (this.getFluid().isIn(FluidTags.getCollection().getOrCreate(mudTag))) {
            boolean flag = false;
            for (Direction direction : Direction.values()) {
                if (worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, Blocks.DIRT.getDefaultState()));
                this.triggerMixEffects(worldIn, pos);
                return false;
            }
        }
        return true;
    }

}
