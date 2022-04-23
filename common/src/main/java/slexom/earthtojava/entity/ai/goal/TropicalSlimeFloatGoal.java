package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveController;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeFloatGoal extends Goal {
    private final TropicalSlimeEntity slime;

    public TropicalSlimeFloatGoal(TropicalSlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
        slime.getNavigation().setCanSwim(true);
    }

    public boolean canStart() {
        return (this.slime.isTouchingWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof TropicalSlimeMoveController;
    }

    public void tick() {
        if (this.slime.getRandom().nextFloat() < 0.8F) {
            this.slime.getJumpControl().setActive();
        }
        ((TropicalSlimeMoveController) this.slime.getMoveControl()).move(1.2D);
    }
}
