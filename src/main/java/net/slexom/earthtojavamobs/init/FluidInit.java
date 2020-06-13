package net.slexom.earthtojavamobs.init;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.fluid.MudFlowingFluidBlock;

public class FluidInit {

    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, EarthtojavamobsMod.MOD_ID);

    public static final ResourceLocation MUD_FLUID_STILL_TEXTURE = new ResourceLocation("earthtojavamobs", "/fluids/mud_still");
    public static final ResourceLocation MUD_FLUID_FLOWING_TEXTURE = new ResourceLocation("earthtojavamobs", "/fluids/mud_flow");

    public static final RegistryObject<FlowingFluid> MUD_FLUID_STILL = FLUIDS.register("mud_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.MUD_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MUD_FLUID_FLOWING = FLUIDS.register("mud_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.MUD_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MUD_PROPERTIES = new ForgeFlowingFluid.Properties(
            MUD_FLUID_STILL,
            MUD_FLUID_FLOWING,
            FluidAttributes.builder(MUD_FLUID_STILL_TEXTURE, MUD_FLUID_FLOWING_TEXTURE)
                    .luminosity(0)
                    .density(1800)
            //.overlay(new ResourceLocation("earthtojavamobs", "/fluids/mud_overlay"))
    )
            .slopeFindDistance(4)
            .explosionResistance(100.F)
            .tickRate(20)
            .block(() -> FluidInit.MUD_BLOCK.get())
            .bucket(() -> ItemInit.MUD_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> MUD_BLOCK = BlockInit.BLOCKS.register("mud_fluid",
            () -> new MudFlowingFluidBlock(
                    () -> FluidInit.MUD_FLUID_STILL.get(),
                    Block.Properties.create(Material.WATER, MaterialColor.BROWN)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(100.0F)
                            .speedFactor(0.5F)
                            .noDrops()
            )
    );

}