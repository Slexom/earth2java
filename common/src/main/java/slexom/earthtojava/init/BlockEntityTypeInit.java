package slexom.earthtojava.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.entity.RainbowBedBlockEntity;

public class BlockEntityTypeInit  {
    private static final DeferredRegister<BlockEntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(Earth2JavaMod.MOD_ID, Registry.BLOCK_ENTITY_TYPE_KEY);

    public static RegistrySupplier<BlockEntityType<RainbowBedBlockEntity>> RAINBOW_BED;

    public static void init() {
        RAINBOW_BED = ENTITY_TYPE_DEFERRED_REGISTER.register("rainbow_bed", () -> BlockEntityType.Builder.create(RainbowBedBlockEntity::new, BlockInit.RAINBOW_BED.get()).build(null));
        ENTITY_TYPE_DEFERRED_REGISTER.register();
    }

}
