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


    // SPAWN EGGS
    private static final Item.Properties spawnEggProps = new Item.Properties().group(itemGroup);

    private static RegistryObject<ModdedSpawnEggItem> registerSpawnEgg(String entityRegistryName, RegistryObject<? extends EntityType<?>> entity, int primaryColor, int secondaryColor) {
        return ITEMS.register(entityRegistryName + "_spawn_egg", () -> new ModdedSpawnEggItem(entity, primaryColor, secondaryColor, spawnEggProps));
    }

    public static final RegistryObject<ModdedSpawnEggItem> AMBER_CHICKEN_SPAWN_EGG = ITEMS.register(EntityTypesInit.AmberChicken.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.AmberChicken.registryObject, EntityTypesInit.AmberChicken.eggPrimaryColor, EntityTypesInit.AmberChicken.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> ASHEN_COW_SPAWN_EGG = ITEMS.register(EntityTypesInit.AshenCow.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.AshenCow.registryObject, EntityTypesInit.AshenCow.eggPrimaryColor, EntityTypesInit.AshenCow.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> CLUCKSHROOM_SPAWN_EGG = ITEMS.register(EntityTypesInit.Cluckshroom.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.Cluckshroom.registryObject, EntityTypesInit.Cluckshroom.eggPrimaryColor, EntityTypesInit.Cluckshroom.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> FLECKED_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.FleckedSheep.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.FleckedSheep.registryObject, EntityTypesInit.FleckedSheep.eggPrimaryColor, EntityTypesInit.FleckedSheep.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> GLOW_SQUID_SPAWN_EGG = ITEMS.register(EntityTypesInit.GlowSquid.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.GlowSquid.registryObject, EntityTypesInit.GlowSquid.eggPrimaryColor, EntityTypesInit.GlowSquid.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> HORNED_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.HornedSheep.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.HornedSheep.registryObject, EntityTypesInit.HornedSheep.eggPrimaryColor, EntityTypesInit.HornedSheep.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> INKY_SHEEP_SPAWN_EGG = ITEMS.register(EntityTypesInit.InkySheep.registryName + "_spawn_egg", () -> new ModdedSpawnEggItem(EntityTypesInit.InkySheep.registryObject, EntityTypesInit.InkySheep.eggPrimaryColor, EntityTypesInit.InkySheep.eggSecondaryColor, spawnEggProps));
    public static final RegistryObject<ModdedSpawnEggItem> MIDNIGHT_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MidnightChicken.registryName, EntityTypesInit.MidnightChicken.registryObject, EntityTypesInit.MidnightChicken.eggPrimaryColor, EntityTypesInit.MidnightChicken.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> MOOBLOOM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.Moobloom.registryName, EntityTypesInit.Moobloom.registryObject, EntityTypesInit.Moobloom.eggPrimaryColor, EntityTypesInit.Moobloom.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MuddyPig.registryName, EntityTypesInit.MuddyPig.registryObject, EntityTypesInit.MuddyPig.eggPrimaryColor, EntityTypesInit.MuddyPig.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> PALE_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PalePig.registryName, EntityTypesInit.PalePig.registryObject, EntityTypesInit.PalePig.eggPrimaryColor, EntityTypesInit.PalePig.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> PIEBALD_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.PiebaldPig.registryName, EntityTypesInit.PiebaldPig.registryObject, EntityTypesInit.PiebaldPig.eggPrimaryColor, EntityTypesInit.PiebaldPig.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> ROCKY_SHEEP_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.RockySheep.registryName, EntityTypesInit.RockySheep.registryObject, EntityTypesInit.RockySheep.eggPrimaryColor, EntityTypesInit.RockySheep.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> SKELETON_WOLF_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SkeletonWolf.registryName, EntityTypesInit.SkeletonWolf.registryObject, EntityTypesInit.SkeletonWolf.eggPrimaryColor, EntityTypesInit.SkeletonWolf.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> SPOTTED_PIG_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SpottedPig.registryName, EntityTypesInit.SpottedPig.registryObject, EntityTypesInit.SpottedPig.eggPrimaryColor, EntityTypesInit.SpottedPig.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> STORMY_CHICKEN_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.StormyChicken.registryName, EntityTypesInit.StormyChicken.registryObject, EntityTypesInit.StormyChicken.eggPrimaryColor, EntityTypesInit.StormyChicken.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> SUNSET_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.SunsetCow.registryName, EntityTypesInit.SunsetCow.registryObject, EntityTypesInit.SunsetCow.eggPrimaryColor, EntityTypesInit.SunsetCow.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> TROPICAL_SLIME_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.TropicalSlime.registryName, EntityTypesInit.TropicalSlime.registryObject, EntityTypesInit.TropicalSlime.eggPrimaryColor, EntityTypesInit.TropicalSlime.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> WOOLY_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.WoolyCow.registryName, EntityTypesInit.WoolyCow.registryObject, EntityTypesInit.WoolyCow.eggPrimaryColor, EntityTypesInit.WoolyCow.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> VESTED_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.VestedRabbit.registryName, EntityTypesInit.VestedRabbit.registryObject, EntityTypesInit.VestedRabbit.eggPrimaryColor, EntityTypesInit.VestedRabbit.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> HARELEQUIN_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.HarelequinRabbit.registryName, EntityTypesInit.HarelequinRabbit.registryObject, EntityTypesInit.HarelequinRabbit.eggPrimaryColor, EntityTypesInit.HarelequinRabbit.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> MUDDY_FOOT_RABBIT_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MuddyFootRabbit.registryName, EntityTypesInit.MuddyFootRabbit.registryObject, EntityTypesInit.MuddyFootRabbit.eggPrimaryColor, EntityTypesInit.MuddyFootRabbit.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> FURNACE_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.FurnaceGolem.registryName, EntityTypesInit.FurnaceGolem.registryObject, EntityTypesInit.FurnaceGolem.eggPrimaryColor, EntityTypesInit.FurnaceGolem.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> MELON_GOLEM_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.MelonGolem.registryName, EntityTypesInit.MelonGolem.registryObject, EntityTypesInit.MelonGolem.eggPrimaryColor, EntityTypesInit.MelonGolem.eggSecondaryColor);
    public static final RegistryObject<ModdedSpawnEggItem> ALBINO_COW_SPAWN_EGG = registerSpawnEgg(EntityTypesInit.AlbinoCow.registryName, EntityTypesInit.AlbinoCow.registryObject, EntityTypesInit.AlbinoCow.eggPrimaryColor, EntityTypesInit.AlbinoCow.eggSecondaryColor);


}
