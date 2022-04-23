package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PigEntity;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.function.Predicate;

public class FancyChickenFleeFromPigEntityGoal extends FleeEntityGoal<PigEntity> {
    public FancyChickenFleeFromPigEntityGoal(PathAwareEntity mob, Class<PigEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    public FancyChickenFleeFromPigEntityGoal(PathAwareEntity mob, Class<PigEntity> fleeFromType, Predicate<LivingEntity> extraInclusionSelector, float distance, double slowSpeed, double fastSpeed, Predicate<LivingEntity> inclusionSelector) {
        super(mob, fleeFromType, extraInclusionSelector, distance, slowSpeed, fastSpeed, inclusionSelector);
    }

    public FancyChickenFleeFromPigEntityGoal(PathAwareEntity fleeingEntity, Class<PigEntity> classToFleeFrom, float fleeDistance, double fleeSlowSpeed, double fleeFastSpeed, Predicate<LivingEntity> inclusionSelector) {
        super(fleeingEntity, classToFleeFrom, fleeDistance, fleeSlowSpeed, fleeFastSpeed, inclusionSelector);
    }

    @Override
    public void start() {
        super.start();
        this.mob.playSound(SoundEventsInit.FANCY_CHICKEN_FLEE, 1.0F, 1.0F);
    }

}
