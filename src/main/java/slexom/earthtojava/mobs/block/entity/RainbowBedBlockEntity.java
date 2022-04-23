package slexom.earthtojava.block.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import slexom.earthtojava.init.BlockEntityTypeInit;

public class RainbowBedBlockEntity extends BlockEntity {
    private DyeColor color;

    public RainbowBedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeInit.RAINBOW_BED, blockPos, blockState);
    }

    public RainbowBedBlockEntity(BlockPos blockPos, BlockState blockState, DyeColor dyeColor) {
        this(blockPos, blockState);
        this.setColor(dyeColor);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Environment(EnvType.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((BedBlock) this.getCachedState().getBlock()).getColor();
        }
        return this.color;
    }

    @Environment(EnvType.CLIENT)
    public void setColor(DyeColor color) {
        this.color = color;
    }
}