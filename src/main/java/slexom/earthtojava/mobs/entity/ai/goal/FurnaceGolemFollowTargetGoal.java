package slexom.earthtojava.mobs.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public final class FurnaceGolemFollowTargetGoal extends FollowTargetGoal<LivingEntity> {
    FurnaceGolemEntity golem;

    public FurnaceGolemFollowTargetGoal(FurnaceGolemEntity entity, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate targetPredicate) {
        super(entity, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
        this.golem = entity;
    }

    public void start() {
        this.golem.setAngry(true);
        super.start();
    }

    public void stop() {
        this.golem.setAngry(false);
        super.stop();
    }
}
