package slexom.earthtojava.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import slexom.earthtojava.init.BlockEntityTypeInit;

public class RainbowBedBlock extends BedBlock {
    public RainbowBedBlock(DyeColor colorIn, Settings properties) {
        super(colorIn, properties);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.RAINBOW_BED.get().instantiate(pos, state);
    }

}