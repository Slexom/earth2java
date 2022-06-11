package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MuddyPigEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.FluidInit;

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
        return !this.muddyPig.isInMuddyState() && this.tryingTime <= 600 && this.isTargetPos(this.muddyPig.world, this.targetPos);
    }

    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    @Override
    protected boolean isTargetPos(WorldView worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        return block == BlockInit.MUD_BLOCK.get();
    }
}
