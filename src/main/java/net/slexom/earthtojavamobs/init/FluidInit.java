package net.slexom.earthtojavamobs.init;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;

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
    )
            .tickRate(20)
            .block(BlockInit.MUD_BLOCK)
            .bucket(ItemInit.MUD_BUCKET);

}