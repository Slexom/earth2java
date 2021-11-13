package slexom.earthtojava.mobs.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.mobs.init.SoundEventsInit;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public final class FurnaceGolemActiveTargetGoal extends ActiveTargetGoal<LivingEntity> {
    FurnaceGolemEntity golem;

    public FurnaceGolemActiveTargetGoal(FurnaceGolemEntity entity, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate targetPredicate) {
        super(entity, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
        this.golem = entity;
    }

    public void start() {
        this.golem.playSound(SoundEventsInit.FURNACE_GOLEM_AGGRO, 1.0F, 1.0F);
        this.golem.setAngry(true);
        super.start();
    }

    public void stop() {
        this.golem.setAngry(false);
        super.stop();
    }
}
