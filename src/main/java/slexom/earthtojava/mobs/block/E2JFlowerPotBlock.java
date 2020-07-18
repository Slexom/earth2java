package slexom.earthtojava.mobs.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.init.BlockInit;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Supplier;

public class E2JFlowerPotBlock extends Block {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    private static final Map<Block, Block> POTTED_CONTENT = Maps.newHashMap();
    private final Block flower;

    private final Map<net.minecraft.util.ResourceLocation, java.util.function.Supplier<? extends Block>> fullPots;
    private final Supplier<E2JFlowerPotBlock> emptyPot;
    private final Supplier<? extends Block> flowerDelegate;

    public E2JFlowerPotBlock(@Nullable Supplier<E2JFlowerPotBlock> emptyPot, Supplier<? extends Block> blockIn, Block.Properties properties) {
        super(properties);
        this.flower = blockIn.get();
        this.flowerDelegate = blockIn;
        if (emptyPot == null) {
            this.fullPots = Maps.newHashMap();
            this.emptyPot = null;
        } else {
            this.fullPots = java.util.Collections.emptyMap();
            this.emptyPot = emptyPot;
        }
        if (blockIn.get() != Blocks.AIR) {
            ((E2JFlowerPotBlock) BlockInit.FLOWER_POT.get()).addPlant(blockIn.get().getRegistryName(), () -> this);
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        Block block = item instanceof BlockItem ? getEmptyPot().fullPots.getOrDefault(((BlockItem) item).getBlock().getRegistryName(), Blocks.AIR.delegate).get() : Blocks.AIR;
        boolean flag = block == Blocks.AIR;
        boolean flag1 = this.flower == Blocks.AIR;
        if (flag != flag1) {
            if (flag1) {
                worldIn.setBlockState(pos, block.getDefaultState(), 3);
                player.addStat(Stats.POT_FLOWER);
                if (!player.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
            } else {
                ItemStack itemstack1 = new ItemStack(this.flower);
                if (itemstack.isEmpty()) {
                    player.setHeldItem(handIn, itemstack1);
                } else if (!player.addItemStackToInventory(itemstack1)) {
                    player.dropItem(itemstack1, false);
                }
                worldIn.setBlockState(pos, getEmptyPot().getDefaultState(), 3);
            }

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.CONSUME;
        }
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return this.flower == Blocks.AIR ? super.getItem(worldIn, pos, state) : new ItemStack(this.flower);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public Block func_220276_d() {
        return flowerDelegate.get();
    }

    public E2JFlowerPotBlock getEmptyPot() {
        return emptyPot == null ? this : emptyPot.get();
    }

    public void addPlant(net.minecraft.util.ResourceLocation flower, java.util.function.Supplier<? extends Block> fullPot) {
        if (getEmptyPot() != this) {
            throw new IllegalArgumentException("Cannot add plant to non-empty pot: " + this);
        }
        fullPots.put(flower, fullPot);
    }
}
