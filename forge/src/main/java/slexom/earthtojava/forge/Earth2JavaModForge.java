package slexom.earthtojava.forge;

import dev.architectury.platform.forge.EventBuses;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;

@Mod(Earth2JavaMod.MOD_ID)
@Mod.EventBusSubscriber(modid = Earth2JavaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Earth2JavaModForge {

    public Earth2JavaModForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Earth2JavaMod.MOD_ID, eventBus);
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        BlockInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        ItemInit.init();
        BlockEntityTypeInit.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(Earth2JavaModClientForge::clientInit));

        eventBus.addListener(Earth2JavaModForge::setup);
    }

    private static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Earth2JavaMod.onPostInit();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.BUTTERCUP.getId(), BlockInit.POTTED_BUTTERCUP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.PINK_DAISY.getId(), BlockInit.POTTED_PINK_DAISY);
        });
    }

}