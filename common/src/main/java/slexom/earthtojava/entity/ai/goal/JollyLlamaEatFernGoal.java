package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.EnumSet;
import java.util.function.Predicate;

public class JollyLlamaEatFernGoal extends Goal {
    private static final Predicate<BlockState> FERN_PREDICATE = BlockStatePredicate.forBlock(Blocks.FERN);
    private final MobEntity mob;
    private final World world;
    private int timer;

    public JollyLlamaEatFernGoal(MobEntity mob) {
        this.mob = mob;
        this.world = mob.world;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    public boolean canStart() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 150 : 1500) == 0) {
            BlockPos blockPos = this.mob.getBlockPos();
            if (FERN_PREDICATE.test(this.world.getBlockState(blockPos))) {
                return true;
            } else {
                return this.world.getBlockState(blockPos.down()).isOf(Blocks.FERN);
            }
        }
        return false;
    }

    public void start() {
        this.timer = 40;
        this.world.sendEntityStatus(this.mob, (byte) 10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.timer = 0;
    }

    public boolean shouldContinue() {
        return this.timer > 0;
    }

    public int getTimer() {
        return this.timer;
    }

    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        if (this.timer == this.getTickCount(4)) {
            BlockPos blockPos = this.mob.getBlockPos();
            if (FERN_PREDICATE.test(this.world.getBlockState(blockPos))) {
                this.mob.playSound(SoundEventsInit.JOLLY_LLAMA_DETECT_FERN.get(), 1.0f, 1.0f);
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.world.breakBlock(blockPos, false);
                }
                this.mob.onEatingGrass();
            } else {
                BlockPos blockPos2 = blockPos.down();
                if (this.world.getBlockState(blockPos2).isOf(Blocks.FERN)) {
                    if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                        this.world.syncWorldEvent(2001, blockPos2, Block.getRawIdFromState(Blocks.FERN.getDefaultState()));
                        this.world.setBlockState(blockPos2, Blocks.DIRT.getDefaultState(), 2);
                    }

                    this.mob.onEatingGrass();
                }
            }
        }
    }

}