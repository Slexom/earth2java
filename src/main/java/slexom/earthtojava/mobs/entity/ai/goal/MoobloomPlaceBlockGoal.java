package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.entity.passive.MoobloomEntity;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.SoundEventsInit;

public class MoobloomPlaceBlockGoal extends Goal {
    private final MoobloomEntity moobloom;

    public MoobloomPlaceBlockGoal(MoobloomEntity p_i45843_1_) {
        this.moobloom = p_i45843_1_;
    }

    public boolean canStart() {
        return this.moobloom.getRandom().nextInt(2000) == 0;
    }

    public boolean canPlace(WorldView world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
        return !downTarget.isAir() && downTarget.isFullCube(world, downTargetPos) && target.isAir() && target.canPlaceAt(world, targetPos);
    }

    public void tick() {
        WorldAccess world = this.moobloom.world;
        int i = MathHelper.floor(this.moobloom.getX());
        int j = MathHelper.floor(this.moobloom.getY());
        int k = MathHelper.floor(this.moobloom.getZ());
        Block flower = Math.random() > 0.8 ? Blocks.SUNFLOWER : BlockInit.BUTTERCUP.get();
        BlockPos blockPos = new BlockPos(i, j, k);
        BlockState blockState = flower.getDefaultState();
        BlockPos blockDownPos = blockPos.down();
        BlockState blockDownState = world.getBlockState(blockDownPos);
        if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
            this.moobloom.playSound(SoundEventsInit.MOOBLOOM_PLANT, 1.0f, 1.0f);
            world.removeBlock(blockPos, false);
            world.setBlockState(blockPos, blockState, 3);
        }
    }
}
