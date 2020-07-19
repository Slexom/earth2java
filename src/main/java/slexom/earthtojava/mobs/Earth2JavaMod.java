package slexom.earthtojava.mobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.init.*;
import slexom.earthtojava.mobs.world.spawner.E2JWanderingTraderManager;

public class Earth2JavaMod implements ModInitializer {

    public static final String MOD_ID = "earthtojavamobs";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, MOD_ID),
            () -> new ItemStack(Blocks.COBBLESTONE));

    @Override
    public void onInitialize() {
        FluidInit.init();
        BlockInit.init();
        BlockEntityTypeInit.init();
        FeatureInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        ItemInit.init();
        RecipesInit.init();
        EntitySpawnInit.init();
        registerTraderSpawner();
    }

    private void registerTraderSpawner() {
        E2JWanderingTraderManager spawner = new E2JWanderingTraderManager();

        ServerTickEvents.START_SERVER_TICK.register(minecraftServer -> {
            ServerWorld world = minecraftServer.getOverworld();
            spawner.spawn(world, true, true);
            //E2JWanderingTraderManageraaa.tick(world);
        });
    }


}
