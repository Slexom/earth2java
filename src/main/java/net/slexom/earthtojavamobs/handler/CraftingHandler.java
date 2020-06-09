//package net.slexom.earthtojavamobs.handler;
//
//import net.minecraftforge.event.entity.player.PlayerEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.slexom.earthtojavamobs.EarthtojavamobsMod;
//
//@Mod.EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID)
//public class CraftingHandler {
//
//    @SubscribeEvent
//    public static void onMudBucketCrafted(PlayerEvent.ItemCraftedEvent event) {
//        System.out.println(event.getInventory());
//
//        for (int i = 0; i < 9; i++) {
//            System.out.println(event.getInventory().getStackInSlot(i));
//        }
//
//    }
//
//
//}
