package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MoolipEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class MoolipPlaceBlockGoal extends Goal {
	private final MoolipEntity moolip;

	public MoolipPlaceBlockGoal(MoolipEntity entity) {
		moolip = entity;
	}

	public boolean canStart() {
		return moolip.getRandom().nextInt(2000) == 0;
	}

	public boolean canPlace(WorldView world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
		return !downTarget.isAir() && downTarget.isFullCube(world, downTargetPos) && target.isAir() && target.canPlaceAt(world, targetPos);
	}

	@Override
	public void tick() {
		WorldAccess world = moolip.getWorld();
		int i = MathHelper.floor(moolip.getX());
		int j = MathHelper.floor(moolip.getY());
		int k = MathHelper.floor(moolip.getZ());
		double random = Math.random();
		Block flower = Blocks.PEONY;
		if (random > 0.2) {
			flower = Blocks.ALLIUM;
		}
		if (random > 0.6) {
			flower = Blocks.LILAC;
		}
		BlockPos blockPos = new BlockPos(i, j, k);
		BlockState blockState = flower.getDefaultState();
		BlockPos blockDownPos = blockPos.down();
		BlockState blockDownState = world.getBlockState(blockDownPos);
		if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
			moolip.playSound(SoundEventsInit.MOOLIP_PLANT.get(), 1.0f, 1.0f);
			world.removeBlock(blockPos, false);
			world.setBlockState(blockPos, blockState, 3);
		}
	}
}
