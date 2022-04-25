package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.FlowableFluid;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.fluid.MudFluid;


public final class FluidInit {

    public static final RegistrySupplier<FlowableFluid> MUD_FLUID_FLOWING;
    public static final RegistrySupplier<FlowableFluid> MUD_FLUID_STILL;

    static {
        MUD_FLUID_FLOWING = Earth2JavaMod.FLUID_REGISTRAR.register(Earth2JavaMod.toIdentifier("mud_fluid_flowing"), MudFluid.Flowing::new);
        MUD_FLUID_STILL = Earth2JavaMod.FLUID_REGISTRAR.register(Earth2JavaMod.toIdentifier("mud_fluid"), MudFluid.Source::new);
    }

    public static void init() {

    }

}