package slexom.earthtojava.mobs.init;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.fluid.MudFluid;


public class FluidInit {

    public static Fluid MUD_FLUID_FLOWING;
    public static Fluid MUD_FLUID_STILL;

    public static void init() {
        MUD_FLUID_FLOWING = registerFluid("mud_fluid_flowing", new MudFluid.Flowing());
        MUD_FLUID_STILL = registerFluid("mud_fluid", new MudFluid.Source());
    }

    private static Fluid registerFluid(String registryName, Fluid fluid) {
        return Registry.register(Registry.FLUID, new Identifier(Earth2JavaMod.MOD_ID, registryName), fluid);
    }
}