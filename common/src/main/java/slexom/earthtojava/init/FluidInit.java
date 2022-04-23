package slexom.earthtojava.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.fluid.MudFluid;


public class FluidInit {
    private static final DeferredRegister<Fluid> FLUID_DEFERRED_REGISTER = DeferredRegister.create(Earth2JavaMod.MOD_ID, Registry.FLUID_KEY);

    public static RegistrySupplier<FlowableFluid> MUD_FLUID_FLOWING;
    public static RegistrySupplier<FlowableFluid> MUD_FLUID_STILL;

    static {
        MUD_FLUID_FLOWING = FLUID_DEFERRED_REGISTER.register("mud_fluid_flowing", MudFluid.Flowing::new);
        MUD_FLUID_STILL = FLUID_DEFERRED_REGISTER.register("mud_fluid", MudFluid.Source::new);
    }

    public static void init() {
        FLUID_DEFERRED_REGISTER.register();
    }

}