package slexom.earthtojava.mobs.init;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import slexom.earthtojava.mobs.block.*;

public class BlockInit {

    public static Block BUTTERCUP;
    public static Block PINK_DAISY;
    public static Block CARVED_MELON;
    //  public static E2JFlowerPotBlock FLOWER_POT;
    public static Block MELON_GOLEM_HEAD_BLINK;
    public static Block MELON_GOLEM_HEAD_SHOOT;
    public static Block MELON_LANTERN;
    public static Block MUD_BLOCK;
    public static Block POTTED_BUTTERCUP;
    public static Block POTTED_PINK_DAISY;
    public static Block RAINBOW_BED;
    public static Block RAINBOW_CARPET;
    public static Block RAINBOW_WOOL;
    public static Block RUBY_BLOCK;
    public static Block RUBY_ORE;


    public static void init() {

        BUTTERCUP = RegisterHelper.registerBlock("buttercup", new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)), true);
        PINK_DAISY = RegisterHelper.registerBlock("pink_daisy", new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)), true);
        CARVED_MELON = RegisterHelper.registerBlock("carved_melon", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MaterialColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), true);
        //FLOWER_POT = RegisterHelper.registerBlock("flower_pot", () -> new E2JFlowerPotBlock(null, Blocks.AIR, AbstractBlock.Settings.of(Material.SUPPORTED).strength(0.0f).nonOpaque()), true);
        MELON_GOLEM_HEAD_BLINK = RegisterHelper.registerBlock("melon_golem_blink", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MaterialColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), false);
        MELON_GOLEM_HEAD_SHOOT = RegisterHelper.registerBlock("melon_golem_shoot", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MaterialColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)), false);
        MELON_LANTERN = RegisterHelper.registerBlock("melon_lantern", new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MaterialColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 15)), true);
        MUD_BLOCK = RegisterHelper.registerBlock("mud_fluid", new MudBlock(FluidInit.MUD_FLUID_STILL, AbstractBlock.Settings.of(Material.WATER, MaterialColor.BROWN).noCollision().strength(100.0F).dropsNothing()), false);
        POTTED_BUTTERCUP = RegisterHelper.registerBlock("potted_buttercup", new FlowerPotBlock(BUTTERCUP, AbstractBlock.Settings.of(Material.SUPPORTED).breakInstantly().nonOpaque()), false);
        POTTED_PINK_DAISY = RegisterHelper.registerBlock("potted_pink_daisy", new FlowerPotBlock(PINK_DAISY, AbstractBlock.Settings.of(Material.SUPPORTED).breakInstantly().nonOpaque()), false);
        RAINBOW_BED = RegisterHelper.registerBlock("rainbow_bed", new RainbowBedBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), false);
        RAINBOW_CARPET = RegisterHelper.registerBlock("rainbow_carpet", new RainbowCarpetBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.CARPET, MaterialColor.WHITE).strength(0.1F).sounds(BlockSoundGroup.WOOL)), true);
        RAINBOW_WOOL = RegisterHelper.registerBlock("rainbow_wool", new Block(AbstractBlock.Settings.of(Material.WOOL, MaterialColor.WHITE).strength(0.8F).sounds(BlockSoundGroup.WOOL)), true);
        RUBY_BLOCK = RegisterHelper.registerBlock("ruby_block", new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.RED).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)), true);
        RUBY_ORE = RegisterHelper.registerBlock("ruby_ore", new RubyOreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F)), true);
    }
}
