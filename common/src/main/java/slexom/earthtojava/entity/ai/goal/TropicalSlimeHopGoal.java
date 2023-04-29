package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeHopGoal extends Goal {
    private final TropicalSlimeEntity slime;

    public TropicalSlimeHopGoal(TropicalSlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
    }

    public boolean canStart() {
        return !this.slime.hasVehicle();
    }

    @Override
    public void tick() {
        ((TropicalSlimeMoveControl) this.slime.getMoveControl()).move(1.0D);
    }
}
