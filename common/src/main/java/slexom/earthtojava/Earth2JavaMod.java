package slexom.earthtojava;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;

import java.util.function.Supplier;

public class Earth2JavaMod {

    public static final String MOD_ID = "earthtojavamobs";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(RegistryKeys.BLOCK_ENTITY_TYPE);
    public static final Registrar<Block> BLOCK_REGISTRAR = REGISTRIES.get().get(RegistryKeys.BLOCK);
    public static final Registrar<EntityType<?>> ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(RegistryKeys.ENTITY_TYPE);
    public static final Registrar<Item> ITEM_REGISTRAR = REGISTRIES.get().get(RegistryKeys.ITEM);
    public static final Registrar<SoundEvent> SOUND_EVENT_REGISTRAR = REGISTRIES.get().get(RegistryKeys.SOUND_EVENT);

    public static final Identifier ITEM_GROUP_IDENTIFIER = new Identifier(MOD_ID, "group");

    public static final CreativeTabRegistry.TabSupplier CREATIVE_TAB_SUPPLIER = CreativeTabRegistry.create(ITEM_GROUP_IDENTIFIER, () -> new ItemStack(ItemInit.HORN.get()));

    private static final Logger LOGGER = LogManager.getLogger("Earth2Java");

    public static void initialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        BlockInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        ItemInit.init();
        BlockEntityTypeInit.init();
        LOGGER.info("[Earth2Java] Mod loaded! Enjoy :D");
    }

    public static void initializeForge() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        BlockInit.init();

        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        ItemInit.init();
        BlockEntityTypeInit.init();
        //  postRegister();
        LOGGER.info("[Earth2Java] Mod loaded! Enjoy :D");
    }

    public static void onPostInit() {
        BlockInit.onPostInit();
        EntitySpawnInit.initSpawnRestriction();
        EntitySpawnInit.init();
    }

}
