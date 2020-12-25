package slexom.earthtojava.mobs.tileentity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;

public class RainbowBedBlockEntity extends BlockEntity {
    private DyeColor color;

    public RainbowBedBlockEntity() {
        super(BlockEntityTypeInit.RAINBOW_BED);
    }

    public RainbowBedBlockEntity(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.pos, 11, this.toInitialChunkDataTag());
    }

    @Environment(EnvType.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((BedBlock) this.getCachedState().getBlock()).getColor();
        }

        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}