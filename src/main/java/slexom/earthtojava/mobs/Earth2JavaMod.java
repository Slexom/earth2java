package slexom.earthtojava.mobs;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.init.*;
import slexom.earthtojava.mobs.world.spawner.E2JWanderingTraderManager;

public class Earth2JavaMod implements ModInitializer {

    public static final String MOD_ID = "earthtojavamobs";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "group"),
            () -> new ItemStack(ItemInit.HORN));

    private static final Logger LOGGER = LogManager.getLogger("Earth2Java");

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        FluidInit.init();
        BlockInit.init();
        BlockEntityTypeInit.init();
        FeatureInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        EntitySpawnInit.init();
        ItemInit.init();
        RecipesInit.init();
        registerTraderSpawner();
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BlockInit.BUTTERCUP.asItem(), 0.65F);
        LOGGER.info("Mod loaded! Enjoy :D");
    }

    private void registerTraderSpawner() {

        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            ServerWorld world = minecraftServer.getOverworld();
            E2JWanderingTraderManager.tick(world);
        });
    }


}