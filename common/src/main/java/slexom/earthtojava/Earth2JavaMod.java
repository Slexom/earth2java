package slexom.earthtojava;

import com.google.common.base.Suppliers;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.Registries;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;
import slexom.earthtojava.init.features.ConfiguredFeatureInit;
import slexom.earthtojava.init.features.PlacedFeatureInit;

import java.util.function.Supplier;

public class Earth2JavaMod {

    public static final String MOD_ID = "earthtojavamobs";
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));

    public static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(Registry.BLOCK_ENTITY_TYPE_KEY);
    public static final Registrar<Block> BLOCK_REGISTRAR = REGISTRIES.get().get(Registry.BLOCK_KEY);
    public static final Registrar<EntityType<?>> ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(Registry.ENTITY_TYPE_KEY);
    public static final Registrar<Fluid> FLUID_REGISTRAR = REGISTRIES.get().get(Registry.FLUID_KEY);
    public static final Registrar<Item> ITEM_REGISTRAR = REGISTRIES.get().get(Registry.ITEM_KEY);
    public static final Registrar<RecipeSerializer<?>> RECIPE_SERIALIZER_REGISTRAR = REGISTRIES.get().get(Registry.RECIPE_SERIALIZER_KEY);
    public static final Registrar<SoundEvent> SOUND_EVENT_REGISTRAR = REGISTRIES.get().get(Registry.SOUND_EVENT_KEY);
    public static final Registrar<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_REGISTRAR = REGISTRIES.get().get(Registry.CONFIGURED_FEATURE_KEY);
    public static final Registrar<PlacedFeature> PLACED_FEATURE_REGISTRAR = REGISTRIES.get().get(Registry.PLACED_FEATURE_KEY);

    public static final Identifier ITEM_GROUP_IDENTIFIER = new Identifier(MOD_ID, "group");

    public static final ItemGroup ITEM_GROUP = CreativeTabRegistry.create(ITEM_GROUP_IDENTIFIER, () -> new ItemStack(ItemInit.HORN.get()));

    private static final Logger LOGGER = LogManager.getLogger("Earth2Java");

    public static void initialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        FluidInit.init();
        BlockInit.init();
        ConfiguredFeatureInit.init();
        PlacedFeatureInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        EntitySpawnInit.init();
        ItemInit.init();
        BlockEntityTypeInit.init();
        RecipesInit.init();
        postRegister();
        LOGGER.info("[Earth2Java] Mod loaded! Enjoy :D");
    }

    public static Identifier toIdentifier(String registryName) {
        return new Identifier(MOD_ID, registryName);
    }

    private static void postRegister() {
        LifecycleEvent.SETUP.register(() -> {
            BlockInit.registerCompostable();
            BlockInit.registerFlammable();
            EntitySpawnInit.initSpawnRestriction();
        });
    }
}
