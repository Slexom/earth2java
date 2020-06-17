package net.slexom.earthtojavamobs;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.slexom.earthtojavamobs.init.EntityTypesInit;
import net.slexom.earthtojavamobs.init.FluidInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@EventBusSubscriber(modid = EarthtojavamobsMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(EarthtojavamobsMod.MOD_ID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onEggThrown(ProjectileImpactEvent.Throwable event) {
        ThrowableEntity throwable = event.getThrowable();
        World world = throwable.world;
        if (throwable instanceof EggEntity) {
            if (!world.isRemote) {
                if (new Random().nextInt(8) == 0) {
                    int i = 1;
                    if (new Random().nextInt(32) == 0) {
                        i = 4;
                    }
                    for (int j = 0; j < i; ++j) {
                        ChickenEntity chickenentity = getChickenEntity(world);
                        chickenentity.setGrowingAge(-24000);
                        chickenentity.setLocationAndAngles(throwable.getPosX(), throwable.getPosY(), throwable.getPosZ(), throwable.rotationYaw, 0.0F);
                        world.addEntity(chickenentity);
                    }
                }
                world.setEntityState(throwable, (byte) 3);
                throwable.remove();
            }
        }
    }

    private static ChickenEntity getChickenEntity(World world) {
        int chickenType = new Random().nextInt(6);
        ChickenEntity chickenentity;
        switch (chickenType) {
            case 0:
                chickenentity = EntityTypesInit.AmberChicken.registryObject.get().create(world);
                break;
            case 2:
                chickenentity = EntityTypesInit.MidnightChicken.registryObject.get().create(world);
                break;
            case 4:
                chickenentity = EntityTypesInit.StormyChicken.registryObject.get().create(world);
                break;
            default:
                chickenentity = EntityType.CHICKEN.create(world);
                break;
        }
        return chickenentity;
    }


//    @SubscribeEvent
//    public static void onMissingBlock(RegistryEvent.MissingMappings<Block> event) {
//        for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getMappings()) {
//            switch (mapping.key.toString()) {
//                case "earthtojavamobs:mud":
//                    mapping.remap(FluidInit.MUD_BLOCK.get());
//                    break;
//                default:
//
//            }
//        }
//    }

}