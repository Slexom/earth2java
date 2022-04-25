package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntityType;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;

public final class BlockEntityTypeInit {

    public static final RegistrySupplier<BlockEntityType<RainbowBedBlockEntity>> RAINBOW_BED;

    static {
        RAINBOW_BED = Earth2JavaMod.BLOCK_ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier("rainbow_bed"), () -> BlockEntityType.Builder.create(RainbowBedBlockEntity::new, BlockInit.RAINBOW_BED.get()).build(null));
    }

    public static void init() {
    }

}
