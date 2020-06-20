package net.slexom.earthtojavamobs.init;

import net.minecraft.entity.EntityType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.item.BoneShardItem;
import net.slexom.earthtojavamobs.item.ModdedSpawnEggItem;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, EarthtojavamobsMod.MOD_ID);
    private static final ItemGroup itemGroup = EarthtojavamobsMod.E2JItemGroup.instance;

    public static final RegistryObject<BucketItem> MUD_BUCKET = ITEMS.register(
            "mud_fluid_bucket",
            () -> new BucketItem(
                    FluidInit.MUD_FLUID_STILL,
                    new Item.Properties()
                            .containerItem(Items.BUCKET)
                            .maxStackSize(1)
                            .group(itemGroup)
            )
    );

    public static final RegistryObject<Item> HORN = ITEMS.register("horn", () -> new Item(new Item.Properties().group(itemGroup).maxStackSize(64)));
    public static final RegistryObject<Item> BONE_SHARD = ITEMS.register("bone_shard", () -> new BoneShardItem(new Item.Properties().group(itemGroup).maxStackSize(16)));

    // SPAWN EGGS
    private static final Item.Properties spawnEggProps = new Item.Properties().group(itemGroup);

    private static RegistryObject<ModdedSpawnEggItem> registerSpawnEgg(String entityRegistryName, RegistryObject<? extends EntityType<?>> entity, int primaryColor, int secondaryColor) {
        return ITEMS.register(entityRegistryName + "_spawn_egg", () -> new ModdedSpawnEggItem(entity, primaryColor, secondaryColor, spawnEggProps));
    }

    public static final int AMBER_CHICKEN_EGG_SECONDARY_COLOR = 0xe38a2b;
    public static final int AMBER_CHICKEN_EGG_PRIMARY_COLOR = 0xd13719;
    public static final RegistryObject<ModdedSpawnEggItem> AMBER_CHICKEN_SPAWN_EGG = ITEMS.register(EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, AMBER_CHICKEN_EGG_PRIMARY_COLOR, AMBER_CHICKEN_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int ASHEN_COW_EGG_SECONDARY_COLOR = 0x898491;
    public static final int ASHEN_COW_EGG_PRIMARY_COLOR = 0x3c3c49;
    public static final RegistryObject<ModdedSpawnEggItem> ASHEN_COW_SPAWN_EGG = ITEMS.register(EntityTypesInit.ASHEN_COW_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, ASHEN_COW_EGG_PRIMARY_COLOR, ASHEN_COW_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int CLUCKSHROOM_EGG_SECONDARY_COLOR = 0xffffee;
    public static final int CLUCKSHROOM_EGG_PRIMARY_COLOR = 0xef0000;
    public static final RegistryObject<ModdedSpawnEggItem> CLUCKSHROOM_SPAWN_EGG = ITEMS.register(EntityTypesInit.CLUCKSHROOM_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, CLUCKSHROOM_EGG_PRIMARY_COLOR, CLUCKSHROOM_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int FLECKED_SHEEP_EGG_SECONDARY_COLOR = 0x907666;
    public static final int FLECKED_SHEEP_EGG_PRIMARY_COLOR = 0x2c1e17;
    public static final RegistryObject<ModdedSpawnEggItem> FLECKED_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, FLECKED_SHEEP_EGG_PRIMARY_COLOR, FLECKED_SHEEP_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int GLOW_SQUID_EGG_SECONDARY_COLOR = 0x80ffc0;
    public static final int GLOW_SQUID_EGG_PRIMARY_COLOR = 0x095656;
    public static final RegistryObject<ModdedSpawnEggItem> GLOW_SQUID_SPAWN_EGG = ITEMS.register(EntityTypesInit.GLOW_SQUID_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, GLOW_SQUID_EGG_PRIMARY_COLOR, GLOW_SQUID_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int HORNED_SHEEP_EGG_SECONDARY_COLOR = 0x291811;
    public static final int HORNED_SHEEP_EGG_PRIMARY_COLOR = 0xececec;
    public static final RegistryObject<ModdedSpawnEggItem> HORNED_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, HORNED_SHEEP_EGG_PRIMARY_COLOR, HORNED_SHEEP_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int INKY_SHEEP_EGG_SECONDARY_COLOR = 0x8a7564;
    public static final int INKY_SHEEP_EGG_PRIMARY_COLOR = 0x181716;
    public static final RegistryObject<ModdedSpawnEggItem> INKY_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.INKY_SHEEP_REGISTRY_NAME + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, INKY_SHEEP_EGG_PRIMARY_COLOR, INKY_SHEEP_EGG_SECONDARY_COLOR, spawnEggProps));
    public static final int MIDNIGHT_CHICKEN_EGG_SECONDARY_COLOR = 0x17225a;
    public static final int MIDNIGHT_CHICKEN_EGG_PRIMARY_COLOR = 0x06050B;
    public static final RegistryObject<ModdedSpawnEggItem> MIDNIGHT_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, MIDNIGHT_CHICKEN_EGG_PRIMARY_COLOR, MIDNIGHT_CHICKEN_EGG_SECONDARY_COLOR);
    public static final int MOOBLOOM_EGG_SECONDARY_COLOR = 0xf7edc1;
    public static final int MOOBLOOM_EGG_PRIMARY_COLOR = 0xfaca00;
    public static final RegistryObject<ModdedSpawnEggItem> MOOBLOOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MOOBLOOM_REGISTRY_NAME, EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, MOOBLOOM_EGG_PRIMARY_COLOR, MOOBLOOM_EGG_SECONDARY_COLOR);
    public static final int MUDDY_PIG_EGG_SECONDARY_COLOR = 0x573621;
    public static final int MUDDY_PIG_EGG_PRIMARY_COLOR = 0xe6918b;
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_PIG_REGISTRY_NAME, EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, MUDDY_PIG_EGG_PRIMARY_COLOR, MUDDY_PIG_EGG_SECONDARY_COLOR);
    public static final int PALE_PIG_EGG_SECONDARY_COLOR = 0xead3d3;
    public static final int PALE_PIG_EGG_PRIMARY_COLOR = 0xd3a0a0;
    public static final RegistryObject<ModdedSpawnEggItem> PALE_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PALE_PIG_REGISTRY_NAME, EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, PALE_PIG_EGG_PRIMARY_COLOR, PALE_PIG_EGG_SECONDARY_COLOR);
    public static final int PIEBALD_PIG_EGG_SECONDARY_COLOR = 0x9b4628;
    public static final int PIEBALD_PIG_EGG_PRIMARY_COLOR = 0xd7c0a9;
    public static final RegistryObject<ModdedSpawnEggItem> PIEBALD_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME, EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, PIEBALD_PIG_EGG_PRIMARY_COLOR, PIEBALD_PIG_EGG_SECONDARY_COLOR);
    public static final int ROCKY_SHEEP_EGG_SECONDARY_COLOR = 0xe9d0bd;
    public static final int ROCKY_SHEEP_EGG_PRIMARY_COLOR = 0xa69f9b;
    public static final RegistryObject<ModdedSpawnEggItem> ROCKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME, EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, ROCKY_SHEEP_EGG_PRIMARY_COLOR, ROCKY_SHEEP_EGG_SECONDARY_COLOR);
    public static final int SKELETON_WOLF_EGG_SECONDARY_COLOR = 0xbababa;
    public static final int SKELETON_WOLF_EGG_PRIMARY_COLOR = 0xededed;
    public static final RegistryObject<ModdedSpawnEggItem> SKELETON_WOLF_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, SKELETON_WOLF_EGG_PRIMARY_COLOR, SKELETON_WOLF_EGG_SECONDARY_COLOR);
    public static final int SPOTTED_PIG_EGG_SECONDARY_COLOR = 0x413938;
    public static final int SPOTTED_PIG_EGG_PRIMARY_COLOR = 0xedd4d1;
    public static final RegistryObject<ModdedSpawnEggItem> SPOTTED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME, EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, SPOTTED_PIG_EGG_PRIMARY_COLOR, SPOTTED_PIG_EGG_SECONDARY_COLOR);
    public static final int STORMY_CHICKEN_EGG_SECONDARY_COLOR = 0xc0c0c0;
    public static final int STORMY_CHICKEN_EGG_PRIMARY_COLOR = 0x3e2525;
    public static final RegistryObject<ModdedSpawnEggItem> STORMY_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME, EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, STORMY_CHICKEN_EGG_PRIMARY_COLOR, STORMY_CHICKEN_EGG_SECONDARY_COLOR);
    public static final int SUNSET_COW_EGG_SECONDARY_COLOR = 0x171514;
    public static final int SUNSET_COW_EGG_PRIMARY_COLOR = 0x993d0d;
    public static final RegistryObject<ModdedSpawnEggItem> SUNSET_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SUNSET_COW_REGISTRY_NAME, EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, SUNSET_COW_EGG_PRIMARY_COLOR, SUNSET_COW_EGG_SECONDARY_COLOR);
    public static final int TROPICAL_SLIME_EGG_SECONDARY_COLOR = 0x8ed3ff;
    public static final int TROPICAL_SLIME_EGG_PRIMARY_COLOR = 0x0e496e;
    public static final RegistryObject<ModdedSpawnEggItem> TROPICAL_SLIME_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.TROPICAL_SLIME_REGISTRY_NAME, EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, TROPICAL_SLIME_EGG_PRIMARY_COLOR, TROPICAL_SLIME_EGG_SECONDARY_COLOR);
    public static final int WOOLY_COW_EGG_SECONDARY_COLOR = 0xff9933;
    public static final int WOOLY_COW_EGG_PRIMARY_COLOR = 0xcc3300;
    public static final RegistryObject<ModdedSpawnEggItem> WOOLY_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.WOOLY_COW_REGISTRY_NAME, EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, WOOLY_COW_EGG_PRIMARY_COLOR, WOOLY_COW_EGG_SECONDARY_COLOR);
    public static final int VESTED_RABBIT_EGG_SECONDARY_COLOR = 0x747474;
    public static final int VESTED_RABBIT_EGG_PRIMARY_COLOR = 0xdedede;
    public static final RegistryObject<ModdedSpawnEggItem> VESTED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME, EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, VESTED_RABBIT_EGG_PRIMARY_COLOR, VESTED_RABBIT_EGG_SECONDARY_COLOR);
    public static final int HARELEQUIN_RABBIT_EGG_SECONDARY_COLOR = 0xb09984;
    public static final int HARELEQUIN_RABBIT_EGG_PRIMARY_COLOR = 0x1d1b1a;
    public static final RegistryObject<ModdedSpawnEggItem> HARELEQUIN_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, HARELEQUIN_RABBIT_EGG_PRIMARY_COLOR, HARELEQUIN_RABBIT_EGG_SECONDARY_COLOR);
    public static final int MUDDY_FOOT_RABBIT_EGG_SECONDARY_COLOR = 0x463832;
    public static final int MUDDY_FOOT_RABBIT_EGG_PRIMARY_COLOR = 0xe5e0dd;
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_FOOT_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, MUDDY_FOOT_RABBIT_EGG_PRIMARY_COLOR, MUDDY_FOOT_RABBIT_EGG_SECONDARY_COLOR);
    public static final int FURNACE_GOLEM_EGG_SECONDARY_COLOR = 0xff5501;
    public static final int FURNACE_GOLEM_EGG_PRIMARY_COLOR = 0x56585a;
    public static final RegistryObject<ModdedSpawnEggItem> FURNACE_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FURNACE_GOLEM_REGISTRY_NAME, EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, FURNACE_GOLEM_EGG_PRIMARY_COLOR, FURNACE_GOLEM_EGG_SECONDARY_COLOR);
    public static final int MELON_GOLEM_EGG_SECONDARY_COLOR = 0x52811c;
    public static final int MELON_GOLEM_EGG_PRIMARY_COLOR = 0xeeffff;
    public static final RegistryObject<ModdedSpawnEggItem> MELON_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, MELON_GOLEM_EGG_PRIMARY_COLOR, MELON_GOLEM_EGG_SECONDARY_COLOR);
    public static final int ALBINO_COW_EGG_SECONDARY_COLOR = 0xf0a590;
    public static final int ALBINO_COW_EGG_PRIMARY_COLOR = 0xdecac3;
    public static final RegistryObject<ModdedSpawnEggItem> ALBINO_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ALBINO_COW_REGISTRY_NAME, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, ALBINO_COW_EGG_PRIMARY_COLOR, ALBINO_COW_EGG_SECONDARY_COLOR);
    public static final int BONE_SPIDER_EGG_SECONDARY_COLOR = 0x442200;
    public static final int BONE_SPIDER_EGG_PRIMARY_COLOR = 0x002244;
    public static final RegistryObject<ModdedSpawnEggItem> BONE_SPIDER_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BONE_SPIDER_EGG_PRIMARY_COLOR, BONE_SPIDER_EGG_SECONDARY_COLOR);


}
