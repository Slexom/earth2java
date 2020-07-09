package slexom.earthtojava.mobs.tileentity;


import net.minecraft.block.BedBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.init.TileEntityTypeInit;

public class RainbowBedTileEntity extends TileEntity {
    private DyeColor color;

    public RainbowBedTileEntity() {
        super(TileEntityTypeInit.RAINBOW_BED.get());
    }

    public RainbowBedTileEntity(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((BedBlock) this.getBlockState().getBlock()).getColor();
        }
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}