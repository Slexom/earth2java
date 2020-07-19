package slexom.earthtojava.mobs.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;

public class RainbowBedBlock extends BedBlock {
    public RainbowBedBlock(DyeColor colorIn, AbstractBlock.Settings properties) {
        super(colorIn, properties);
    }

    public BlockEntity createBlockEntity(BlockView world) {
        return BlockEntityTypeInit.RAINBOW_BED.instantiate();
    }

}