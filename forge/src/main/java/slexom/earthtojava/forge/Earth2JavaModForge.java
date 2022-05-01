package slexom.earthtojava.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slexom.earthtojava.Earth2JavaClientMod;
import slexom.earthtojava.Earth2JavaMod;

@Mod(Earth2JavaMod.MOD_ID)
@Mod.EventBusSubscriber(modid = Earth2JavaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Earth2JavaModForge {
    // private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public Earth2JavaModForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Earth2JavaMod.MOD_ID, eventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> (DistExecutor.SafeRunnable) () -> eventBus.addListener(this::setupClient));
        Earth2JavaMod.initialize();
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    private void setupClient(final FMLClientSetupEvent event) {
        Earth2JavaClientMod.initializeClient();
    }
}