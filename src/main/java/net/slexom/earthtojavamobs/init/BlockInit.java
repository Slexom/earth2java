package net.slexom.earthtojavamobs.init;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.block.MudBlock;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, EarthtojavamobsMod.MOD_ID);

    public static final RegistryObject<FlowingFluidBlock> MUD_BLOCK = BLOCKS.register("mud_fluid",
            () -> new MudBlock(
                    FluidInit.MUD_FLUID_STILL,
                    Block.Properties.create(Material.WATER, MaterialColor.BROWN)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(100.0F)
                            .speedFactor(0.75F)
                            .noDrops()
            )
    );

}
