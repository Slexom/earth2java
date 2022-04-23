package slexom.earthtojava.init;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.fluid.MudFluid;


public class FluidInit {

    public static FlowableFluid MUD_FLUID_FLOWING;
    public static FlowableFluid MUD_FLUID_STILL;

    public static void init() {
        MUD_FLUID_FLOWING = registerFluid("mud_fluid_flowing", new MudFluid.Flowing());
        MUD_FLUID_STILL = registerFluid("mud_fluid", new MudFluid.Source());
    }

    private static FlowableFluid registerFluid(String registryName, FlowableFluid fluid) {
        return Registry.register(Registry.FLUID, new Identifier(Earth2JavaMod.MOD_ID, registryName), fluid);
    }
}