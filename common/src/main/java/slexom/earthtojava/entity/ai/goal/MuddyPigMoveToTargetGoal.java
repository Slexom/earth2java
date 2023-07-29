package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MuddyPigEntity;

public class MuddyPigMoveToTargetGoal extends MoveToTargetPosGoal {
	private final MuddyPigEntity muddyPig;

	public MuddyPigMoveToTargetGoal(MuddyPigEntity entity, double speed) {
		super(entity, speed, 16, 3);
		muddyPig = entity;
		lowestY = -1;
	}

	@Override
	public boolean canStart() {
		return !muddyPig.isInMuddyState() && super.canStart();
	}

	@Override
	public boolean shouldContinue() {
		if (muddyPig.isInMuddyState()) return false;
		if (tryingTime > 600) return false;
		return isTargetPos(muddyPig.getWorld(), targetPos.down());
	}

	@Override
	public boolean shouldResetPath() {
		return tryingTime % 100 == 0;
	}

	@Override
	protected boolean isTargetPos(WorldView worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return block.equals(Blocks.MUD);

	}
}
