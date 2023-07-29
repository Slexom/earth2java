package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

public class BoneSpiderMeleeAttackGoal extends MeleeAttackGoal {
	public BoneSpiderMeleeAttackGoal(BoneSpiderEntity spider) {
		super(spider, 1.0D, false);
	}

	@Override
	public boolean canStart() {
		return super.canStart() && !mob.hasPassengers();
	}

	@Override

	@SuppressWarnings("deprecation")
	public boolean shouldContinue() {
		float f = mob.getBrightnessAtEyes();
		if (f >= 0.5F && mob.getRandom().nextInt(100) == 0) {
			mob.setTarget(null);
			return false;
		}
		return super.shouldContinue();
	}

	@Override
	protected double getSquaredMaxAttackDistance(LivingEntity entity) {
		return 4.0F + entity.getWidth();
	}
}
