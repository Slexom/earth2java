
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.slexom.earthtojavamobs.entity.base.E2JBaseChickenEntity;


public class CluckshroomEntity extends E2JBaseChickenEntity<CluckshroomEntity> {

    public CluckshroomEntity(EntityType<CluckshroomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new CluckshroomEntity.PlaceBlockGoal(this));
    }

    static class PlaceBlockGoal extends Goal {
        private final CluckshroomEntity cluckshroom;

        public PlaceBlockGoal(CluckshroomEntity p_i45843_1_) {
            this.cluckshroom = p_i45843_1_;
        }

        public boolean shouldExecute() {
            return this.cluckshroom.getRNG().nextInt(2000) == 0;
        }

        public boolean canPlace(IWorldReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
            return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
        }

        public void tick() {
            IWorld iworld = this.cluckshroom.world;
            int i = MathHelper.floor(this.cluckshroom.getPosX());
            int j = MathHelper.floor(this.cluckshroom.getPosY());
            int k = MathHelper.floor(this.cluckshroom.getPosZ());
            Block mushroom = Blocks.RED_MUSHROOM;
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = mushroom.getDefaultState();
            BlockPos blockDownPos = blockPos.down();
            BlockState blockDownState = iworld.getBlockState(blockDownPos);
            if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(cluckshroom, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockPos, blockDownState), net.minecraft.util.Direction.UP)) {
                iworld.destroyBlock(blockPos, false);
                iworld.setBlockState(blockPos, blockState, 3);
            }
        }
    }

}
