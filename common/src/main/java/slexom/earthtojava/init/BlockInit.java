package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.block.RainbowBedBlock;
import slexom.earthtojava.utils.Utils;

public final class BlockInit {

	public static final RegistrySupplier<Block> BUTTERCUP;
	public static final RegistrySupplier<Block> CARVED_MELON;
	public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_BLINK;
	public static final RegistrySupplier<Block> MELON_GOLEM_HEAD_SHOOT;
	public static final RegistrySupplier<Block> MELON_LANTERN;
	public static final RegistrySupplier<Block> PINK_DAISY;
	public static final RegistrySupplier<FlowerPotBlock> POTTED_BUTTERCUP;
	public static final RegistrySupplier<FlowerPotBlock> POTTED_PINK_DAISY;
	public static final RegistrySupplier<Block> RAINBOW_BED;
	public static final RegistrySupplier<Block> RAINBOW_CARPET;
	public static final RegistrySupplier<Block> RAINBOW_WOOL;
	private static final AbstractBlock.Settings FLOWERS_SETTINGS = AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY);
	private static final AbstractBlock.Settings CARVED_MELON_SETTINGS = AbstractBlock.Settings.create().mapColor(MapColor.LIME).strength(1.0F).sounds(BlockSoundGroup.WOOD).allowsSpawning((state, world, pos, type) -> true).pistonBehavior(PistonBehavior.DESTROY);
	private static final AbstractBlock.Settings POTTED_FLOWER_SETTINGS = AbstractBlock.Settings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY);


	static {
		BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("buttercup"), () -> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, FLOWERS_SETTINGS));
		PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("pink_daisy"), () -> new FlowerBlock(StatusEffects.JUMP_BOOST, 5, FLOWERS_SETTINGS));
		CARVED_MELON = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("carved_melon"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
		MELON_GOLEM_HEAD_BLINK = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("melon_golem_blink"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
		MELON_GOLEM_HEAD_SHOOT = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("melon_golem_shoot"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS));
		MELON_LANTERN = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("melon_lantern"), () -> new CarvedMelonBlock(CARVED_MELON_SETTINGS.luminance(state -> 15)));
		POTTED_BUTTERCUP = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("potted_buttercup"), () -> new FlowerPotBlock(BUTTERCUP.get(), POTTED_FLOWER_SETTINGS));
		POTTED_PINK_DAISY = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("potted_pink_daisy"), () -> new FlowerPotBlock(PINK_DAISY.get(), POTTED_FLOWER_SETTINGS));
		RAINBOW_BED = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("rainbow_bed"), () -> new RainbowBedBlock(DyeColor.WHITE, AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY)));
		RAINBOW_CARPET = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("rainbow_carpet"), () -> new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(0.1F).sounds(BlockSoundGroup.WOOL).burnable()));
		RAINBOW_WOOL = Earth2JavaMod.BLOCK_REGISTRAR.register(Utils.modIdentifierOf("rainbow_wool"), () -> new Block(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).instrument(Instrument.GUITAR).strength(0.8F).sounds(BlockSoundGroup.WOOL).burnable()));
	}

	private BlockInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {

	}

	public static void onPostInit() {
		registerCompostable();
		registerFlammable();
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
