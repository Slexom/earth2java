package net.slexom.earthtojavamobs.fluid;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;

@Mod.EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID)
public class MudFogHandler {

    @SubscribeEvent
    public static void onFogColor(EntityViewRenderEvent.FogColors event) {
        if (event.getInfo().getRenderViewEntity() instanceof PlayerEntity) {
            ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
            PlayerEntity player = (PlayerEntity) event.getInfo().getRenderViewEntity();
            World world = player.world;
            int x = MathHelper.floor(player.getPosX());
            int y = MathHelper.floor(player.getPosYEye());
            int z = MathHelper.floor(player.getPosZ());
            IFluidState blockStateAtEyes = world.getFluidState(new BlockPos(x, y, z));
            if (blockStateAtEyes.getFluid().isIn(FluidTags.getCollection().get(mudTag))) {
                event.setRed(87.0F / 255.0F);
                event.setGreen(54.0F / 255.0F);
                event.setBlue(35.0F / 255.0F);
            }
        }
    }

}
