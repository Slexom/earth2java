package net.slexom.earthtojavamobs.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.slexom.earthtojavamobs.init.TileEntityTypeInit;

public class RainbowBedBlock extends BedBlock {
    public RainbowBedBlock(DyeColor colorIn, Properties properties) {
        super(colorIn, properties);
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypeInit.RAINBOW_BED.get().create();
    }

}