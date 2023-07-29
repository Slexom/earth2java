package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepActiveTargetGoal extends ActiveTargetGoal<PlayerEntity> {
	public HornedSheepActiveTargetGoal(HornedSheepEntity sheepEntity) {
		super(sheepEntity, PlayerEntity.class, true);
	}

	@Override
	public boolean canStart() {
		return canCharge() && super.canStart();
	}

	@Override
	public boolean shouldContinue() {
		boolean flag = canCharge();
		if (flag && mob.getTarget() != null) {
			return super.shouldContinue();
		}
		target = null;
		return false;
	}

	private boolean canCharge() {
		HornedSheepEntity sheepEntity = (HornedSheepEntity) mob;
		return sheepEntity.isAngry();
	}
}
