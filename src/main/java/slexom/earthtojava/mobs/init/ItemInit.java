package slexom.earthtojava.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.item.*;

public class ItemInit {

    private static final ItemGroup itemGroup = Earth2JavaMod.ITEM_GROUP;
    private static final Item.Settings spawnEggProps = new Item.Settings().group(itemGroup);
    public static E2JSpawnEggItem ALBINO_COW_SPAWN_EGG;
    public static E2JSpawnEggItem AMBER_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem ASHEN_COW_SPAWN_EGG;
    public static E2JSpawnEggItem BOLD_STRIPED_RABBIT_SPAWN_EGG;
    public static Item BONE_SHARD;
    public static E2JSpawnEggItem BONE_SPIDER_SPAWN_EGG;
    public static E2JSpawnEggItem BOULDERING_ZOMBIE_SPAWN_EGG;
    public static E2JSpawnEggItem BRONZED_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem CLUCKSHROOM_SPAWN_EGG;
    public static E2JSpawnEggItem COOKIE_COW_SPAWN_EGG;
    public static E2JSpawnEggItem CREAM_COW_SPAWN_EGG;
    public static E2JSpawnEggItem DAIRY_COW_SPAWN_EGG;
    public static E2JSpawnEggItem FANCY_CHICKEN_SPAWN_EGG;
    public static Item FANCY_FEATHER;
    public static E2JSpawnEggItem FLECKED_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem FURNACE_GOLEM_SPAWN_EGG;
    public static E2JSpawnEggItem GOLD_CRESTED_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem HARELEQUIN_RABBIT_SPAWN_EGG;
    public static Item HORN;
    public static E2JSpawnEggItem HORNED_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem INKY_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem JOLLY_LLAMA_SPAWN_EGG;
    public static E2JSpawnEggItem JUMBO_RABBIT_SPAWN_EGG;
    public static E2JSpawnEggItem LOBBER_ZOMBIE_SPAWN_EGG;
    public static E2JSpawnEggItem MELON_GOLEM_SPAWN_EGG;
    public static E2JSpawnEggItem MIDNIGHT_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem MOOBLOOM_SPAWN_EGG;
    public static E2JSpawnEggItem MOOLIP_SPAWN_EGG;
    public static E2JSpawnEggItem MUDDY_FOOT_RABBIT_SPAWN_EGG;
    public static E2JSpawnEggItem MUDDY_PIG_SPAWN_EGG;
    public static Item MUD_BUCKET;
    public static E2JSpawnEggItem PALE_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem PATCHED_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem PIEBALD_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem PINK_FOOTED_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem PINTO_COW_SPAWN_EGG;
    public static BedItem RAINBOW_BED;
    public static E2JSpawnEggItem RAINBOW_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem ROCKY_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem SKELETON_WOLF_SPAWN_EGG;
    public static E2JSpawnEggItem SKEWBALD_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem SPOTTED_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem STORMY_CHICKEN_SPAWN_EGG;
    public static E2JSpawnEggItem SUNSET_COW_SPAWN_EGG;
    public static E2JSpawnEggItem TROPICAL_SLIME_SPAWN_EGG;
    public static E2JSpawnEggItem FUZZY_SHEEP_SPAWN_EGG;
    public static E2JSpawnEggItem UMBRA_COW_SPAWN_EGG;
    public static E2JSpawnEggItem VESTED_RABBIT_SPAWN_EGG;
    public static E2JSpawnEggItem VILER_WITCH_SPAWN_EGG;
    public static E2JSpawnEggItem WANDERING_TRADER_SPAWN_EGG;
    public static E2JSpawnEggItem WOOLY_COW_SPAWN_EGG;
    public static E2JSpawnEggItem MOTTLED_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem SOOTY_PIG_SPAWN_EGG;
    public static E2JSpawnEggItem FRECKLED_RABBIT_SPAWN_EGG;
    public static E2JSpawnEggItem LONG_NOSED_SHEEP_SPAWN_EGG;

    public static void init() {
        HORN = RegisterHelper.registerItem("horn", new HornItem(new Item.Settings().group(itemGroup).maxCount(64)));
        FANCY_FEATHER = RegisterHelper.registerItem("fancy_feather", new FancyFeatherItem(new Item.Settings().group(itemGroup).maxCount(64)));
        MUD_BUCKET = RegisterHelper.registerItem("mud_fluid_bucket", new MudBucketItem(FluidInit.MUD_FLUID_STILL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(itemGroup)));
        BONE_SHARD = RegisterHelper.registerItem("bone_shard", new BoneShardItem(new Item.Settings().group(null).maxCount(16)));
        RAINBOW_BED = (BedItem) RegisterHelper.registerItem("rainbow_bed", new BedItem(BlockInit.RAINBOW_BED, (new Item.Settings()).maxCount(1).group(ItemGroup.DECORATIONS)));
        registerSpawnEggs();
    }

    private static E2JSpawnEggItem registerSpawnEgg(String registryName, EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor) {
        return (E2JSpawnEggItem) RegisterHelper.registerItem(registryName + "_spawn_egg", new E2JSpawnEggItem(entity, primaryColor, secondaryColor, spawnEggProps));
    }

    private static void registerSpawnEggs() {
        ALBINO_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ALBINO_COW_REGISTRY_NAME, EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, 0xdecac3, 0xf0a590);
        COOKIE_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.COOKIE_COW_REGISTRY_NAME, EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, 0x4c5662, 0xdbcdbe);
        AMBER_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.AMBER_CHICKEN_REGISTRY_NAME, EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, 0xd13719, 0xe38a2b);
        ASHEN_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ASHEN_COW_REGISTRY_NAME, EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, 0x3c3c49, 0x898491);
        BOLD_STRIPED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_NAME, EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, 0x030303, 0xa4632b);
        BONE_SPIDER_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, 0x200d16, 0xd6e7e5);
        BOULDERING_ZOMBIE_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_NAME, EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, 0x3a4046, 0x492320);
        BRONZED_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_NAME, EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, 0x040f30, 0xb2492a);
        CLUCKSHROOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.CLUCKSHROOM_REGISTRY_NAME, EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, 0xef0000, 0xffffee);
        CREAM_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.CREAM_COW_REGISTRY_NAME, EntityTypesInit.CREAM_COW_REGISTRY_OBJECT, 0xfcbf66, 0xfff2cd);
        DAIRY_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.DAIRY_COW_REGISTRY_NAME, EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT, 0xf6f4f9, 0x2e2e2d);
        FANCY_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, 0xf7b035, 0x478e8b);
        FLECKED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FLECKED_SHEEP_REGISTRY_NAME, EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, 0x2c1e17, 0x907666);
        FRECKLED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_NAME, EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT, 0xf6f1e8, 0xab9e8d);
        FUZZY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FUZZY_SHEEP_REGISTRY_NAME, EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT, 0xf8f6f5, 0x3d312b);
        FURNACE_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FURNACE_GOLEM_REGISTRY_NAME, EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, 0x56585a, 0xff5501);
        GOLD_CRESTED_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_NAME, EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT, 0xd1cec2, 0xe5aa40);
        HARELEQUIN_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_NAME, EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, 0x1d1b1a, 0xb09984);
        HORNED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HORNED_SHEEP_REGISTRY_NAME, EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, 0xececec, 0x291811);
        INKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.INKY_SHEEP_REGISTRY_NAME, EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, 0x181716, 0x8a7564);
        JOLLY_LLAMA_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, 0x5f3425, 0x3b7e3e);
        JUMBO_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, 0xb57766, 0xf7d1c0);
        LOBBER_ZOMBIE_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_NAME, EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, 0x8e9c7e, 0x607c17);
        LONG_NOSED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_NAME, EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT, 0x2c231e, 0x9e8061);
        MELON_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, 0xeeffff, 0x52811c);
        MIDNIGHT_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_NAME, EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, 0x06050B, 0x17225a);
        MOOBLOOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MOOBLOOM_REGISTRY_NAME, EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, 0xfaca00, 0xf7edc1);
        MOOLIP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MOOLIP_REGISTRY_NAME, EntityTypesInit.MOOLIP_REGISTRY_OBJECT, 0xea88be, 0xf9e7eb);
        MOTTLED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MOTTLED_PIG_REGISTRY_NAME, EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT, 0x50403c, 0x806a68);
        MUDDY_FOOT_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_NAME, EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, 0xe5e0dd, 0x463832);
        MUDDY_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MUDDY_PIG_REGISTRY_NAME, EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, 0xe6918b, 0x573621);
        PALE_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PALE_PIG_REGISTRY_NAME, EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, 0xd3a0a0, 0xead3d3);
        PATCHED_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PATCHED_SHEEP_REGISTRY_NAME, EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, 0xf3f0ee, 0x3b4054);
        PIEBALD_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PIEBALD_PIG_REGISTRY_NAME, EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, 0xd7c0a9, 0x9b4628);
        PINK_FOOTED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_NAME, EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, 0x514246, 0xb39da2);
        PINTO_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PINTO_COW_REGISTRY_NAME, EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, 0xc16921, 0xd8c4ad);
        RAINBOW_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, 0xffffff, 0xffffff);
        ROCKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.ROCKY_SHEEP_REGISTRY_NAME, EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, 0xa69f9b, 0xe9d0bd);
        SKELETON_WOLF_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, 0xededed, 0xbababa);
        SKEWBALD_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_NAME, EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, 0xffe8cf, 0x353028);
        SOOTY_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SOOTY_PIG_REGISTRY_NAME, EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT, 0x3f3c4c, 0xefcbc1);
        SPOTTED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SPOTTED_PIG_REGISTRY_NAME, EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, 0xedd4d1, 0x413938);
        STORMY_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.STORMY_CHICKEN_REGISTRY_NAME, EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, 0x3e2525, 0xc0c0c0);
        SUNSET_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SUNSET_COW_REGISTRY_NAME, EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, 0x993d0d, 0x171514);
        TROPICAL_SLIME_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.TROPICAL_SLIME_REGISTRY_NAME, EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, 0x0e496e, 0x8ed3ff);
        UMBRA_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.UMBRA_COW_REGISTRY_NAME, EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, 0x090c1a, 0x2e2c44);
        VESTED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.VESTED_RABBIT_REGISTRY_NAME, EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, 0xdedede, 0x747474);
        VILER_WITCH_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.VILER_WITCH_REGISTRY_NAME, EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, 0x0d0e19, 0xa09280);
        WOOLY_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.WOOLY_COW_REGISTRY_NAME, EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, 0xcc3300, 0xff9933);
    }

}
