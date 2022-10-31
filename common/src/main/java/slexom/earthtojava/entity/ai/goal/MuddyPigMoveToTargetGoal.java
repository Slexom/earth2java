package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MuddyPigEntity;

public class MuddyPigMoveToTargetGoal extends MoveToTargetPosGoal {
    private final MuddyPigEntity muddyPig;

    public MuddyPigMoveToTargetGoal(MuddyPigEntity entity, double speedIn) {
        super(entity, speedIn, 16, 3);
        this.muddyPig = entity;
        this.lowestY = -1;
    }

    public boolean canStart() {
        return !this.muddyPig.isInMuddyState() && super.canStart();
    }

    public boolean shouldContinue() {
        if (this.muddyPig.isInMuddyState()) return false;
        if (this.tryingTime > 600) return false;
        return this.isTargetPos(this.muddyPig.world, this.targetPos.down());
    }

    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        return block.equals(Blocks.MUD);

    }
}
