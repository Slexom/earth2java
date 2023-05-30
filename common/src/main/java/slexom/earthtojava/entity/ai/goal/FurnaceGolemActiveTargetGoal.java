package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.function.Predicate;

public final class FurnaceGolemActiveTargetGoal extends ActiveTargetGoal<LivingEntity> {
    final FurnaceGolemEntity golem;

    public FurnaceGolemActiveTargetGoal(FurnaceGolemEntity entity, Class targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(entity, targetClass, reciprocalChance, checkVisibility, checkCanNavigate, targetPredicate);
        this.golem = entity;
    }

    @Override
    public void start() {
        this.golem.playSound(SoundEventsInit.FURNACE_GOLEM_AGGRO.get(), 1.0F, 1.0F);
        this.golem.setAngry(true);
        super.start();
    }

    @Override
    public void stop() {
        this.golem.setAngry(false);
        super.stop();
    }
}
