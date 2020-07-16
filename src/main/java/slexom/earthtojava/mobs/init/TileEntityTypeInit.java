package slexom.earthtojava.mobs.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.tileentity.RainbowBedTileEntity;

public class TileEntityTypeInit {

    public static BlockEntityType<RainbowBedTileEntity> RAINBOW_BED;

    public static void init() {
        RAINBOW_BED = Registry.register(Registry.BLOCK_ENTITY_TYPE, "rainbow_bed", BlockEntityType.Builder.create(RainbowBedTileEntity::new, BlockInit.RAINBOW_BED).build(Util.method_29187(TypeReferences.BLOCK_ENTITY, "rainbow_bed")));
    }
}
