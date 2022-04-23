package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TrackIronGolemTargetGoal;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

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
        this.golem.playSound(SoundEventsInit.FURNACE_GOLEM_AGGRO, 1.0F, 1.0F);
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
