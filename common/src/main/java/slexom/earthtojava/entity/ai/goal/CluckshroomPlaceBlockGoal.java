package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.CluckshroomEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class CluckshroomPlaceBlockGoal extends Goal {
	private final CluckshroomEntity cluckshroom;

	public CluckshroomPlaceBlockGoal(CluckshroomEntity entity) {
		cluckshroom = entity;
	}

	public boolean canStart() {
		return cluckshroom.getRandom().nextInt(2000) == 0;
	}

	public boolean canPlace(WorldView world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
		return !downTarget.isAir() && downTarget.isFullCube(world, downTargetPos) && target.isAir() && target.canPlaceAt(world, targetPos);
	}

	@Override
	public void tick() {
		WorldAccess world = cluckshroom.getWorld();
		int i = MathHelper.floor(cluckshroom.getX());
		int j = MathHelper.floor(cluckshroom.getY());
		int k = MathHelper.floor(cluckshroom.getZ());
		Block mushroom = Blocks.RED_MUSHROOM;
		BlockPos blockPos = new BlockPos(i, j, k);
		BlockState blockState = mushroom.getDefaultState();
		BlockPos blockDownPos = blockPos.down();
		BlockState blockDownState = world.getBlockState(blockDownPos);
		if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
			cluckshroom.playSound(SoundEventsInit.CLUCKSHROOM_LAY_MUSHROOM.get(), 1.0f, 1.0f);
			world.removeBlock(blockPos, false);
			world.setBlockState(blockPos, blockState, 3);
		}
	}
}
