package slexom.earthtojava.entity.ai.pathing;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClimberNavigation extends MobNavigation {
	private BlockPos targetPos;

	public ClimberNavigation(MobEntity mobEntity, World world) {
		super(mobEntity, world);
	}

	@Override
	public Path findPathTo(BlockPos target, int distance) {
		targetPos = target;
		return super.findPathTo(target, distance);
	}

	@Override
	public Path findPathTo(Entity entity, int distance) {
		targetPos = entity.getBlockPos();
		return super.findPathTo(entity, distance);
	}

	@Override
	public boolean startMovingTo(Entity entity, double speed) {
		Path path = findPathTo(entity, 0);
		if (path != null) {
			return startMovingAlong(path, speed);
		}
		targetPos = entity.getBlockPos();
		this.speed = speed;
		return true;
	}

	@Override
	public void tick() {
		if (isIdle()) {
			if (targetPos != null) {
				if (!targetPos.isWithinDistance(entity.getPos(), Math.max(entity.getWidth(), 1.0D)) && ((entity.getY() <= targetPos.getY()) || !(BlockPos.ofFloored(targetPos.getX(), entity.getY(), targetPos.getZ())).isWithinDistance(entity.getPos(), Math.max(entity.getWidth(), 1.0D)))) {
					entity.getMoveControl().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
				} else {
					targetPos = null;
				}
			}
			return;
		}
		super.tick();
	}
}