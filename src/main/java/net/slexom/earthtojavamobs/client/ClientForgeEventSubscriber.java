package net.slexom.earthtojavamobs.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(EarthtojavamobsMod.MOD_ID + " Client Mod Event Subscriber");

    @SubscribeEvent
    public static void onFogColor(EntityViewRenderEvent.FogColors event) {
        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        Fluid blockStateAtEyes = getMudFluid(event);
        if (blockStateAtEyes.isIn(FluidTags.getCollection().getOrCreate(mudTag))) {
            event.setRed(87.0F / 255.0F);
            event.setGreen(54.0F / 255.0F);
            event.setBlue(35.0F / 255.0F);
        }
    }

    @SubscribeEvent
    public static void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        Fluid blockStateAtEyes = getMudFluid(event);
        if (blockStateAtEyes.isIn(FluidTags.getCollection().getOrCreate(mudTag))) {
            event.setDensity(0.85F);
            event.setCanceled(true);
        }
    }

    private static Fluid getMudFluid(EntityViewRenderEvent event) {
        PlayerEntity player = (PlayerEntity) event.getInfo().getRenderViewEntity();
        World world = player.world;
        int x = MathHelper.floor(player.getPosX());
        int y = MathHelper.floor(player.getPosYEye());
        int z = MathHelper.floor(player.getPosZ());
        return world.getFluidState(new BlockPos(x, y, z)).getFluid();
    }


}