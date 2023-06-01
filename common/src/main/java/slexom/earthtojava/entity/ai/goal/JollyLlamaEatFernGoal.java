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
		world = mob.getWorld();
		setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
	}

	public boolean canStart() {
		if (mob.getRandom().nextInt(mob.isBaby() ? 150 : 1500) == 0) {
			BlockPos blockPos = mob.getBlockPos();
			if (FERN_PREDICATE.test(world.getBlockState(blockPos))) {
				return true;
			}
			return world.getBlockState(blockPos.down()).isOf(Blocks.FERN);
		}
		return false;
	}

	@Override
	public void start() {
		timer = 40;
		world.sendEntityStatus(mob, (byte) 10);
		mob.getNavigation().stop();
	}

	@Override
	public void stop() {
		timer = 0;
	}

	@Override
	public boolean shouldContinue() {
		return timer > 0;
	}

	public int getTimer() {
		return timer;
	}

	@Override
	public void tick() {
		timer = Math.max(0, timer - 1);
		if (timer == getTickCount(4)) {
			BlockPos blockPos = mob.getBlockPos();
			if (FERN_PREDICATE.test(world.getBlockState(blockPos))) {
				mob.playSound(SoundEventsInit.JOLLY_LLAMA_DETECT_FERN.get(), 1.0f, 1.0f);
				if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
					world.breakBlock(blockPos, false);
				}
				mob.onEatingGrass();
			} else {
				BlockPos blockPos2 = blockPos.down();
				if (world.getBlockState(blockPos2).isOf(Blocks.FERN)) {
					if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
						world.syncWorldEvent(2001, blockPos2, Block.getRawIdFromState(Blocks.FERN.getDefaultState()));
						world.setBlockState(blockPos2, Blocks.DIRT.getDefaultState(), 2);
					}

					mob.onEatingGrass();
				}
			}
		}
	}

}