package slexom.earthtojava.mobs.init;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.fluid.MudFluid;

public class FluidInit {

    public static FlowableFluid MUD_FLUID_FLOWING;
    public static FlowableFluid MUD_FLUID_STILL;

    public static void init() {
        MUD_FLUID_FLOWING = registerFluid("mud_fluid_flowing", MudFluid.Flowing::new);
        MUD_FLUID_STILL = registerFluid("mud_fluid", MudFluid.Source::new);
    }

    private Fluid registerFluid(String registryName, Fluid fluid) {
        return Registry.register(Registry.FLUID, registryName, fluid);
    }
}