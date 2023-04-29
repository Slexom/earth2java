/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeSwimGoal
        extends Goal {
    private final TropicalSlimeEntity slime;

    public TropicalSlimeSwimGoal(TropicalSlimeEntity slime) {
        this.slime = slime;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        slime.getNavigation().setCanSwim(true);
    }

    @Override
    public boolean canStart() {
        return (this.slime.isTouchingWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof TropicalSlimeMoveControl;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.slime.getRandom().nextFloat() < 0.8f) {
            this.slime.getJumpControl().setActive();
        }

        MoveControl moveControl = this.slime.getMoveControl();
        if (moveControl instanceof TropicalSlimeMoveControl tropicalSlimeMoveControl) {
            tropicalSlimeMoveControl.move(1.2);
        }
    }
}

