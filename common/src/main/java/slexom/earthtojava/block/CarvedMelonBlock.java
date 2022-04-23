package slexom.earthtojava.block;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.EntityTypesInit;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class CarvedMelonBlock extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Predicate<BlockState> IS_MELON = (blockState) -> blockState != null && (blockState.getBlock() == BlockInit.CARVED_MELON || blockState.getBlock() == BlockInit.MELON_LANTERN);
    @Nullable
    private BlockPattern snowmanBasePattern;
    @Nullable
    private BlockPattern snowmanPattern;

    public CarvedMelonBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            this.trySpawnGolem(worldIn, pos);
        }
    }

    public boolean canDispense(WorldView worldView, BlockPos pos) {
        return this.getSnowmanBasePattern().searchAround(worldView, pos) != null;
    }

    private void trySpawnGolem(World world, BlockPos pos) {
        BlockPattern.Result result = this.getSnowmanPattern().searchAround(world, pos);
        if (result != null) {
            for (int i = 0; i < this.getSnowmanPattern().getHeight(); ++i) {
                CachedBlockPosition cachedBlockPosition = result.translate(0, i, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
                world.syncWorldEvent(2001, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }

            MelonGolemEntity melonGolemEntity = EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get().create(world);
            BlockPos blockPos = result.translate(0, 2, 0).getBlockPos();
            melonGolemEntity.refreshPositionAndAngles((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.05D, (double) blockPos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(melonGolemEntity);

            for (ServerPlayerEntity serverplayerentity : world.getNonSpectatingEntities(ServerPlayerEntity.class, melonGolemEntity.getBoundingBox().expand(5.0D))) {
                Criteria.SUMMONED_ENTITY.trigger(serverplayerentity, melonGolemEntity);
            }

            for (int l = 0; l < this.getSnowmanPattern().getWidth(); ++l) {
                CachedBlockPosition cachedBlockInfo3 = result.translate(0, l, 0);
                world.updateNeighbors(cachedBlockInfo3.getBlockPos(), Blocks.AIR);
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getSnowmanBasePattern() {
        if (this.snowmanBasePattern == null) {
            this.snowmanBasePattern = BlockPatternBuilder.start().aisle(" ", "#", "#").where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return this.snowmanBasePattern;
    }

    private BlockPattern getSnowmanPattern() {
        if (this.snowmanPattern == null) {
            this.snowmanPattern = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', CachedBlockPosition.matchesBlockState(IS_MELON)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK))).build();
        }
        return this.snowmanPattern;
    }

}