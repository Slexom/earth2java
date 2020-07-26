package slexom.earthtojava.mobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.init.*;
import slexom.earthtojava.mobs.world.spawner.E2JWanderingTraderManager;

public class Earth2JavaMod implements ModInitializer {

    public static final String MOD_ID = "earthtojavamobs";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "group"),
            () -> new ItemStack(ItemInit.HORN));

    @Override
    public void onInitialize() {
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

    }

    private void registerTraderSpawner() {

        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            ServerWorld world = minecraftServer.getOverworld();
            E2JWanderingTraderManager.tick(world);
        });
    }


}
