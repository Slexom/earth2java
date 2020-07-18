package slexom.earthtojava.mobs.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.tileentity.RainbowBedTileEntity;

public class TileEntityTypeInit {

    public static BlockEntityType<RainbowBedTileEntity> RAINBOW_BED;

    public static void init() {
        RAINBOW_BED = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Earth2JavaMod.MOD_ID, "rainbow_bed"), BlockEntityType.Builder.create(RainbowBedTileEntity::new, BlockInit.RAINBOW_BED).build(null));
    }

}
