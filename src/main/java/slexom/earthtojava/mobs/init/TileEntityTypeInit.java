package slexom.earthtojava.mobs.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;
import slexom.earthtojava.mobs.tileentity.RainbowBedTileEntity;

public class TileEntityTypeInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EarthToJavaMobsMod.MOD_ID);

    public static final RegistryObject<TileEntityType<RainbowBedTileEntity>> RAINBOW_BED = TILE_ENTITY_TYPES.register(
            "rainbow_bed",
            () -> TileEntityType.Builder.create(RainbowBedTileEntity::new, BlockInit.RAINBOW_BED.get()).build(null));
}
