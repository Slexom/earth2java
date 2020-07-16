package slexom.earthtojava.mobs.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;
import slexom.earthtojava.mobs.init.TileEntityTypeInit;

public class RainbowBedBlock extends BedBlock {
    public RainbowBedBlock(DyeColor colorIn, AbstractBlock.Settings properties) {
        super(colorIn, properties);
    }

    public BlockEntity createBlockEntity(BlockView world) {
        return TileEntityTypeInit.RAINBOW_BED.instantiate();
    }

}