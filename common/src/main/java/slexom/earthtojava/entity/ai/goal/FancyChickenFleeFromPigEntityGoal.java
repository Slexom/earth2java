package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PigEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class FancyChickenFleeFromPigEntityGoal extends FleeEntityGoal<PigEntity> {
	public FancyChickenFleeFromPigEntityGoal(PathAwareEntity mob, Class<PigEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
		super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
	}

	@Override
	public void start() {
		super.start();
		mob.playSound(SoundEventsInit.FANCY_CHICKEN_FLEE.get(), 1.0F, 1.0F);
	}

}
