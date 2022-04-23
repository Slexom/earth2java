package slexom.earthtojava.init;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.block.MudBlock;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.block.RainbowCarpetBlock;

public class BlockInit {

    public static Block BUTTERCUP;
    public static Block CARVED_MELON;
    public static Block MELON_GOLEM_HEAD_BLINK;
    public static Block MELON_GOLEM_HEAD_SHOOT;
    public static Block MELON_LANTERN;
    public static Block MUD_BLOCK;
    public static Block PINK_DAISY;
    public static Block POTTED_BUTTERCUP;
    public static Block POTTED_PINK_DAISY;
    public static Block RAINBOW_BED;
    public static Block RAINBOW_CARPET;
    public static Block RAINBOW_WOOL;

    public static void flammableBlock(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.registerFlammableBlock(block, encouragement, flammability);
    }

    public static void init() {
        BUTTERCUP = RegisterHelper.registerBlock("buttercup", new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)), true);
        PINK_DAISY = RegisterHelper.registerBlock("pink_daisy", new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)), true);
        CARVED_MELON = RegisterHelper.registerBlock("carved_melon", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), true);
        MELON_GOLEM_HEAD_BLINK = RegisterHelper.registerBlock("melon_golem_blink", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), true);
        MELON_GOLEM_HEAD_SHOOT = RegisterHelper.registerBlock("melon_golem_shoot", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), true);
        MELON_LANTERN = RegisterHelper.registerBlock("melon_lantern", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 15)), true);
        MUD_BLOCK = RegisterHelper.registerBlock("mud_fluid", new MudBlock(FluidInit.MUD_FLUID_STILL, AbstractBlock.Settings.of(Material.WATER, MapColor.BROWN).noCollision().strength(100.0F).dropsNothing()), false);
        POTTED_BUTTERCUP = RegisterHelper.registerBlock("potted_buttercup", new FlowerPotBlock(BUTTERCUP, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()), false);
        POTTED_PINK_DAISY = RegisterHelper.registerBlock("potted_pink_daisy", new FlowerPotBlock(PINK_DAISY, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()), false);
        RAINBOW_BED = RegisterHelper.registerBlock("rainbow_bed", new RainbowBedBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), false);
        RAINBOW_CARPET = RegisterHelper.registerBlock("rainbow_carpet", new RainbowCarpetBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.CARPET, MapColor.WHITE).strength(0.1F).sounds(BlockSoundGroup.WOOL)), true);
        RAINBOW_WOOL = RegisterHelper.registerBlock("rainbow_wool", new Block(AbstractBlock.Settings.of(Material.WOOL, MapColor.WHITE).strength(0.8F).sounds(BlockSoundGroup.WOOL)), true);

        //Flammables
        flammableBlock(BUTTERCUP, 60, 100);
        flammableBlock(PINK_DAISY, 60, 100);
        flammableBlock(RAINBOW_CARPET, 60, 20);
        flammableBlock(RAINBOW_WOOL, 30, 60);
    }
}
