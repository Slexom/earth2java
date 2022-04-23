package slexom.earthtojava.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.block.MudBlock;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.block.RainbowCarpetBlock;

public class BlockInit {
    private static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = DeferredRegister.create(Earth2JavaMod.MOD_ID, Registry.BLOCK_KEY);

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
        BUTTERCUP = BLOCK_DEFERRED_REGISTER.register("buttercup",()-> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)));
        PINK_DAISY = BLOCK_DEFERRED_REGISTER.register("pink_daisy", () -> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, AbstractBlock.Settings.of(Material.PLANT).noCollision().strength(0.0F).sounds(BlockSoundGroup.GRASS)));
        CARVED_MELON = BLOCK_DEFERRED_REGISTER.register("carved_melon", () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_GOLEM_HEAD_BLINK = BLOCK_DEFERRED_REGISTER.register("melon_golem_blink", () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_GOLEM_HEAD_SHOOT = BLOCK_DEFERRED_REGISTER.register("melon_golem_shoot", () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
        MELON_LANTERN = BLOCK_DEFERRED_REGISTER.register("melon_lantern", () -> new CarvedMelonBlock(AbstractBlock.Settings.of(Material.GOURD, MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 15)));
        MUD_BLOCK = BLOCK_DEFERRED_REGISTER.register("mud_fluid", () -> new MudBlock(FluidInit.MUD_FLUID_STILL.get(), AbstractBlock.Settings.of(Material.WATER, MapColor.BROWN).noCollision().strength(100.0F).dropsNothing()));
        POTTED_BUTTERCUP = BLOCK_DEFERRED_REGISTER.register("potted_buttercup", () -> new FlowerPotBlock(BUTTERCUP.get(), AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        POTTED_PINK_DAISY = BLOCK_DEFERRED_REGISTER.register("potted_pink_daisy", () -> new FlowerPotBlock(PINK_DAISY.get(), AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
        RAINBOW_BED = BLOCK_DEFERRED_REGISTER.register("rainbow_bed", () -> new RainbowBedBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
        RAINBOW_CARPET = BLOCK_DEFERRED_REGISTER.register("rainbow_carpet", () -> new RainbowCarpetBlock(DyeColor.WHITE, AbstractBlock.Settings.of(Material.CARPET, MapColor.WHITE).strength(0.1F).sounds(BlockSoundGroup.WOOL)));
        RAINBOW_WOOL = BLOCK_DEFERRED_REGISTER.register("rainbow_wool", () -> new Block(AbstractBlock.Settings.of(Material.WOOL, MapColor.WHITE).strength(0.8F).sounds(BlockSoundGroup.WOOL)));
    }

    public static void init() {
        BLOCK_DEFERRED_REGISTER.register();

        flammableBlock(BUTTERCUP.get(), 60, 100);
        flammableBlock(PINK_DAISY.get(), 60, 100);
        flammableBlock(RAINBOW_CARPET.get(), 60, 20);
        flammableBlock(RAINBOW_WOOL.get(), 30, 60);

    }


    public static void flammableBlock(Block block, int encouragement, int flammability) {
        FireBlock fire = (FireBlock) Blocks.FIRE;
        fire.registerFlammableBlock(block, encouragement, flammability);
    }

}
