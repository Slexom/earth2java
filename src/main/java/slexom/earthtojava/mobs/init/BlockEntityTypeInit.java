package slexom.earthtojava.init;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;

public class BlockEntityTypeInit {

    public static BlockEntityType<RainbowBedBlockEntity> RAINBOW_BED;

    public static void init() {
        RAINBOW_BED = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Earth2JavaMod.MOD_ID, "rainbow_bed"), BlockEntityType.Builder.create(RainbowBedBlockEntity::new, BlockInit.RAINBOW_BED).build(null));
    }

}
