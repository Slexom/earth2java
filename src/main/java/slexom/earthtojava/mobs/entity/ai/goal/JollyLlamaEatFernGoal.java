package slexom.earthtojava.entity.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
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
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK, Goal.Control.JUMP));
    }

    public boolean canStart() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 150 : 1500) == 0) {
            BlockPos blockPos = this.mob.getBlockPos();
            return FERN_PREDICATE.test(this.world.getBlockState(blockPos));
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
        if (this.timer == 4) {
            BlockPos blockPos = this.mob.getBlockPos();
            if (FERN_PREDICATE.test(this.world.getBlockState(blockPos))) {
                this.mob.playSound(SoundEventsInit.JOLLY_LLAMA_DETECT_FERN, 1.0f, 1.0f);
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.world.breakBlock(blockPos, false);
                }
                this.mob.onEatingGrass();
                this.mob.emitGameEvent(GameEvent.EAT, this.mob.getCameraBlockPos());
            }
        }
    }

}