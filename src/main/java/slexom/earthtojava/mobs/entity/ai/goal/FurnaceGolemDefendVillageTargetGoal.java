package slexom.earthtojava.mobs.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TrackIronGolemTargetGoal;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

import java.util.EnumSet;

public class FurnaceGolemDefendVillageTargetGoal extends TrackIronGolemTargetGoal {
    private final FurnaceGolemEntity golem;
    private LivingEntity villageAgressorTarget;

    public FurnaceGolemDefendVillageTargetGoal(FurnaceGolemEntity ironGolemIn) {
        super(ironGolemIn);
        this.golem = ironGolemIn;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    @Override
    public void start() {
        this.golem.setAngry(true);
        this.golem.setTarget(this.villageAgressorTarget);
        super.start();
    }

    @Override
    public void stop() {
        this.golem.setAngry(false);
        super.stop();
    }
}
