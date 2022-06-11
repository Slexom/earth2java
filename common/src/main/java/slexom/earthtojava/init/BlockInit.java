package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.block.MudBlock;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.block.RainbowCarpetBlock;

public final class BlockInit {

    public static final RegistrySupplier<Block> BUTTERCUP;
    public static final RegistrySupplier<Block> CARVED_MELON;
    public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_BLINK;
    public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_SHOOT;
    public static final RegistrySupplier<Block> MELON_LANTERN;
    public static final RegistrySupplier<Block> MUD_BLOCK;
    public static final RegistrySupplier<Block> PINK_DAISY;
    public static final RegistrySupplier<Block> POTTED_BUTTERCUP;
    public static final RegistrySupplier<Block> POTTED_PINK_DAISY;
    public static final RegistrySupplier<Block> RAINBOW_BED;
    public static final RegistrySupplier<Block> RAINBOW_CARPET;
    public static final RegistrySupplier<Block> RAINBOW_WOOL;

    static {
        BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("buttercup"), () -> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
        PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("pink_daisy"), () -> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
        CARVED_MELON = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("carved_melon"), () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_GOLEM_HEAD_BLINK = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("melon_golem_blink"), () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_GOLEM_HEAD_SHOOT = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("melon_golem_shoot"), () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_LANTERN = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("melon_lantern"), () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 15)));
        MUD_BLOCK = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("mud_fluid"), () -> new MudBlock(FluidInit.MUD_FLUID_STILL.get(), AbstractBlock.Settings.of(Material.WATER, MapColor.BROWN).noCollision().strength(100.0F).dropsNothing()));
        POTTED_BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("potted_buttercup"), () -> new FlowerPotBlock(BUTTERCUP.get(), AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        POTTED_PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("potted_pink_daisy"), () -> new FlowerPotBlock(PINK_DAISY.get(), AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        RAINBOW_BED = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("rainbow_bed"), () -> new RainbowBedBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
        RAINBOW_CARPET = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("rainbow_carpet"), () -> new RainbowCarpetBlock(AbstractBlock.Settings.of(Material.CARPET, MapColor.WHITE).strength(0.1F).sounds(BlockSoundGroup.WOOL)));
        RAINBOW_WOOL = Earth2JavaMod.BLOCK_REGISTRAR.register(Earth2JavaMod.toIdentifier("rainbow_wool"), () -> new Block(AbstractBlock.Settings.of(Material.WOOL, MapColor.WHITE).strength(0.8F).sounds(BlockSoundGroup.WOOL)));
    }

    public static void init() {

    }


    public static void registerCompostable() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BUTTERCUP.get().asItem(), 0.65F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(PINK_DAISY.get().asItem(), 0.65F);
    }

    public static void registerFlammable() {
        flammableBlock(BUTTERCUP.get(), 60, 100);
        flammableBlock(PINK_DAISY.get(), 60, 100);
        flammableBlock(RAINBOW_CARPET.get(), 60, 20);
        flammableBlock(RAINBOW_WOOL.get(), 30, 60);
    }

    private static void flammableBlock(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.registerFlammableBlock(block, encouragement, flammability);
    }

}
