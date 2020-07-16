package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import slexom.earthtojava.mobs.entity.base.E2JBaseChickenEntity;


public class CluckshroomEntity extends E2JBaseChickenEntity<CluckshroomEntity> {

    public CluckshroomEntity(EntityType<CluckshroomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new AvoidSunlightGoal(this));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
        this.goalSelector.add(3, new PlaceBlockGoal(this));
    }

    static class PlaceBlockGoal extends Goal {
        private final CluckshroomEntity cluckshroom;

        public PlaceBlockGoal(CluckshroomEntity p_i45843_1_) {
            this.cluckshroom = p_i45843_1_;
        }

        public boolean canStart() {
            return this.cluckshroom.getRandom().nextInt(2000) == 0;
        }

        public boolean canPlace(WorldView world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
            return !downTarget.isAir() && downTarget.isFullCube(world, downTargetPos) && target.isAir() && target.canPlaceAt(world, targetPos);
        }

        public void tick() {
            IWorld iworld = this.cluckshroom.world;
            int i = MathHelper.floor(this.cluckshroom.getX());
            int j = MathHelper.floor(this.cluckshroom.getY());
            int k = MathHelper.floor(this.cluckshroom.getZ());
            Block mushroom = Blocks.RED_MUSHROOM;
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = mushroom.getDefaultState();
            BlockPos blockDownPos = blockPos.down();
            BlockState blockDownState = iworld.getBlockState(blockDownPos);
            if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos)) {
                iworld.removeBlock(blockPos, false);
                iworld.setBlockState(blockPos, blockState, 3);
            }
        }
    }

}
