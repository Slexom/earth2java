package net.slexom.earthtojavamobs.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.client.renderer.entity.*;
import net.slexom.earthtojavamobs.init.EntityTypesInit;
import net.slexom.earthtojavamobs.init.FluidInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(EarthtojavamobsMod.MOD_ID + " Client Mod Event Subscriber");

    /**
     * We need to register our renderers on the client because rendering code does not exist on the server
     * and trying to use it on a dedicated server will crash the game.
     * <p>
     * This method will be called by Forge when it is time for the mod to do its client-side setup
     * This method will always be called after the Registry events.
     * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
     */
    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_STILL.get(), RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(FluidInit.MUD_FLUID_FLOWING.get(), RenderType.getSolid());

        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AmberChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.AmberChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AshenCow.registryObject.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.AshenCow.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.Cluckshroom.registryObject.get(), CluckshroomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FleckedSheep.registryObject.get(), FleckedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.GlowSquid.registryObject.get(), GlowSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HornedSheep.registryObject.get(), HornedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.InkySheep.registryObject.get(), InkySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MidnightChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.MidnightChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.Moobloom.registryObject.get(), MoobloomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MuddyPig.registryObject.get(), MuddyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PalePig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PalePig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PiebaldPig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PiebaldPig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.RockySheep.registryObject.get(), RockySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SkeletonWolf.registryObject.get(), SkeletonWolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SpottedPig.registryObject.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.SpottedPig.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.StormyChicken.registryObject.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.StormyChicken.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SunsetCow.registryObject.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.SunsetCow.registryName));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.TropicalSlime.registryObject.get(), TropicalSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.WoolyCow.registryObject.get(), WoolyCowRenderer::new);
    }


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