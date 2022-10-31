package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntityType;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;
import slexom.earthtojava.utils.Utils;

public final class BlockEntityTypeInit {

    public static final RegistrySupplier<BlockEntityType<RainbowBedBlockEntity>> RAINBOW_BED;

    static {
        RAINBOW_BED = Earth2JavaMod.BLOCK_ENTITY_TYPE_REGISTRAR.register(Utils.modIdentifierOf("rainbow_bed"), () -> BlockEntityType.Builder.create(RainbowBedBlockEntity::new, BlockInit.RAINBOW_BED.get()).build(null));
    }

    private BlockEntityTypeInit() {   throw new IllegalStateException("Utility class");
    }

    public static void init() {
    }

}
