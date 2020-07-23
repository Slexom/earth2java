package slexom.earthtojava.mobs.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;
import slexom.earthtojava.mobs.item.*;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EarthToJavaMobsMod.MOD_ID);
    public static final RegistryObject<Item> BONE_SHARD = ITEMS.register("bone_shard", () -> new BoneShardItem(new Item.Properties().group(null).maxStackSize(16)));
    private static final ItemGroup itemGroup = E2JItemGroup.instance;
    public static final RegistryObject<BucketItem> MUD_BUCKET = ITEMS.register(
            "mud_fluid_bucket",
            () -> new MudFluidBucketItem(
                    FluidInit.MUD_FLUID_STILL,
                    new Item.Properties()
                            .containerItem(Items.BUCKET)
                            .maxStackSize(1)
                            .group(itemGroup)
            )
    );
    public static final RegistryObject<HornItem> HORN = ITEMS.register("horn", () -> new HornItem(new Item.Properties().group(itemGroup).maxStackSize(64)));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new E2JItem(new Item.Properties().group(itemGroup).maxStackSize(64)));


    // SPAWN EGGS
    private static final Item.Properties spawnEggProps = new Item.Properties().group(itemGroup);

    public static final RegistryObject<ModdedSpawnEggItem> ALBINO_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ALBINO_COW_REGISTRY_NAME, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, 0xdecac3, 0xf0a590);
    public static final RegistryObject<ModdedSpawnEggItem> AMBER_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME, EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, 0xd13719, 0xe38a2b);
    public static final RegistryObject<ModdedSpawnEggItem> ASHEN_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ASHEN_COW_REGISTRY_NAME, EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, 0x3c3c49, 0x898491);
    public static final RegistryObject<ModdedSpawnEggItem> BONE_SPIDER_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, 0x200d16, 0xd6e7e5);
    public static final RegistryObject<ModdedSpawnEggItem> CLUCKSHROOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.CLUCKSHROOM_REGISTRY_NAME, EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, 0xef0000, 0xffffee);
    public static final RegistryObject<ModdedSpawnEggItem> FLECKED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME, EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, 0x2c1e17, 0x907666);
    public static final RegistryObject<ModdedSpawnEggItem> FURNACE_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FURNACE_GOLEM_REGISTRY_NAME, EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, 0x56585a, 0xff5501);
    public static final RegistryObject<ModdedSpawnEggItem> GLOW_SQUID_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.GLOW_SQUID_REGISTRY_NAME, EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, 0x095656, 0x80ffc0);
    public static final RegistryObject<ModdedSpawnEggItem> HARELEQUIN_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, 0x1d1b1a, 0xb09984);
    public static final RegistryObject<ModdedSpawnEggItem> HORNED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME, EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, 0xececec, 0x291811);
    public static final RegistryObject<ModdedSpawnEggItem> INKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.INKY_SHEEP_REGISTRY_NAME, EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, 0x181716, 0x8a7564);
    public static final RegistryObject<ModdedSpawnEggItem> JOLLY_LLAMA_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, 0x5f3425, 0x3b7e3e);
    public static final RegistryObject<ModdedSpawnEggItem> JUMBO_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, 0xb57766, 0xf7d1c0);
    public static final RegistryObject<ModdedSpawnEggItem> MELON_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, 0xeeffff, 0x52811c);
    public static final RegistryObject<ModdedSpawnEggItem> MIDNIGHT_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, 0x06050B, 0x17225a);
    public static final RegistryObject<ModdedSpawnEggItem> MOOBLOOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MOOBLOOM_REGISTRY_NAME, EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, 0xfaca00, 0xf7edc1);
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_FOOT_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, 0xe5e0dd, 0x463832);
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_PIG_REGISTRY_NAME, EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, 0xe6918b, 0x573621);
    public static final RegistryObject<ModdedSpawnEggItem> PALE_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PALE_PIG_REGISTRY_NAME, EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, 0xd3a0a0, 0xead3d3);
    public static final RegistryObject<ModdedSpawnEggItem> PIEBALD_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME, EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, 0xd7c0a9, 0x9b4628);
    public static final RegistryObject<ModdedSpawnEggItem> ROCKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME, EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, 0xa69f9b, 0xe9d0bd);
    public static final RegistryObject<ModdedSpawnEggItem> RAINBOW_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, 0x00ffffff, 0x00ffffff);
    public static final RegistryObject<ModdedSpawnEggItem> SKELETON_WOLF_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, 0xededed, 0xbababa);
    public static final RegistryObject<ModdedSpawnEggItem> SPOTTED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME, EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, 0xedd4d1, 0x413938);
    public static final RegistryObject<ModdedSpawnEggItem> STORMY_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME, EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, 0x3e2525, 0xc0c0c0);
    public static final RegistryObject<ModdedSpawnEggItem> SUNSET_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SUNSET_COW_REGISTRY_NAME, EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, 0x993d0d, 0x171514);
    public static final RegistryObject<ModdedSpawnEggItem> TROPICAL_SLIME_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.TROPICAL_SLIME_REGISTRY_NAME, EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, 0x0e496e, 0x8ed3ff);
    public static final RegistryObject<ModdedSpawnEggItem> VESTED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME, EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, 0xdedede, 0x747474);
    public static final RegistryObject<ModdedSpawnEggItem> WOOLY_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.WOOLY_COW_REGISTRY_NAME, EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, 0xcc3300, 0xff9933);
    public static final RegistryObject<ModdedSpawnEggItem> WANDERING_TRADER_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.WANDERING_TRADER_REGISTRY_NAME, EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT, 0xcc0000, 0xffcccc);

    private static RegistryObject<ModdedSpawnEggItem> registerSpawnEgg(String entityRegistryName, RegistryObject<? extends EntityType<?>> entity, int primaryColor, int secondaryColor) {
        return ITEMS.register(entityRegistryName + "_spawn_egg", () -> new ModdedSpawnEggItem(entity, primaryColor, secondaryColor, spawnEggProps));
    }

}
