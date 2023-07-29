package slexom.earthtojava.block;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.function.Predicate;

public class CarvedMelonBlock extends HorizontalFacingBlock {
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
	private static final Predicate<BlockState> IS_MELON = blockState -> blockState != null && (blockState.getBlock() == BlockInit.CARVED_MELON.get() || blockState.getBlock() == BlockInit.MELON_LANTERN.get());
	@Nullable
	private BlockPattern snowmanBasePattern;
	@Nullable
	private BlockPattern snowmanPattern;

	public CarvedMelonBlock(Settings settings) {
		super(settings);
		setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	public static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
		breakPatternBlocks(world, patternResult);
		entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F);
		world.spawnEntity(entity);
		for (ServerPlayerEntity serverplayerentity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0D))) {
			Criteria.SUMMONED_ENTITY.trigger(serverplayerentity, entity);
		}

		updatePatternBlocks(world, patternResult);
	}

	public static void breakPatternBlocks(World world, BlockPattern.Result patternResult) {
		for (int i = 0; i < patternResult.getWidth(); ++i) {
			for (int j = 0; j < patternResult.getHeight(); ++j) {
				CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
				world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
				world.syncWorldEvent(2001, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
			}
		}

	}

	public static void updatePatternBlocks(World world, BlockPattern.Result patternResult) {
		for (int i = 0; i < patternResult.getWidth(); ++i) {
			for (int j = 0; j < patternResult.getHeight(); ++j) {
				CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
				world.updateNeighbors(cachedBlockPosition.getBlockPos(), Blocks.AIR);
			}
		}

	}

	@Override
	@SuppressWarnings("deprecation")
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (!oldState.isOf(state.getBlock())) {
			trySpawnGolem(world, pos);
		}
	}

	public boolean canDispense(WorldView worldView, BlockPos pos) {
		return getSnowmanBasePattern().searchAround(worldView, pos) != null;
	}

	private void trySpawnGolem(World world, BlockPos pos) {
		BlockPattern.Result result = getSnowmanPattern().searchAround(world, pos);
		if (result != null) {
			MelonGolemEntity melonGolemEntity = EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get().create(world);

			if (melonGolemEntity != null) {
				spawnEntity(world, result, melonGolemEntity, result.translate(0, 2, 0).getBlockPos());
			}
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	private BlockPattern getSnowmanBasePattern() {
		if (snowmanBasePattern == null) {
			snowmanBasePattern = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
		}
		return snowmanBasePattern;
	}

	private BlockPattern getSnowmanPattern() {
		if (snowmanPattern == null) {
			snowmanPattern = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', CachedBlockPosition.matchesBlockState(IS_MELON)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
		}
		return snowmanPattern;
	}

}