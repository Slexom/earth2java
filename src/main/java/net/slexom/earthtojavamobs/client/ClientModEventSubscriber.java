package net.slexom.earthtojavamobs.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
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
import net.slexom.earthtojavamobs.entity.projectile.BoneShardEntity;
import net.slexom.earthtojavamobs.entity.projectile.MelonSeedProjectileEntity;
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

        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.ASHEN_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), CluckshroomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), FleckedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT.get(), GlowSquidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), HornedSheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), InkySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), MoobloomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), MuddyPigRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PALE_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), RockySheepRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), SkeletonWolfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JPigRenderer(renderManagerIn, EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JChickenRenderer(renderManagerIn, EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.SUNSET_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), TropicalSlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), WoolyCowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JRabbitRenderer(renderManagerIn, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get(), FurnaceGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get(), MelonGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get(), renderManagerIn -> new SpriteRenderer<MelonSeedProjectileEntity>(renderManagerIn, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JCowRenderer(renderManagerIn, EntityTypesInit.ALBINO_COW_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), renderManagerIn -> new E2JSpiderRenderer<>(renderManagerIn, EntityTypesInit.BONE_SPIDER_REGISTRY_NAME));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get(), renderManagerIn -> new SpriteRenderer<BoneShardEntity>(renderManagerIn, Minecraft.getInstance().getItemRenderer()));
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