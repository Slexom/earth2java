package slexom.earthtojava.mobs.init;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;
import slexom.earthtojava.mobs.fluid.MudFluid;

public class FluidInit {

//    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<Fluid>(ForgeRegistries.FLUIDS, EarthtojavamobsMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, EarthToJavaMobsMod.MOD_ID);


    public static final RegistryObject<FlowingFluid> MUD_FLUID_STILL = FLUIDS.register("mud_fluid", MudFluid.Source::new);
    public static final RegistryObject<FlowingFluid> MUD_FLUID_FLOWING = FLUIDS.register("mud_fluid_flowing", MudFluid.Flowing::new);

}