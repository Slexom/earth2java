package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffects;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveController;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeFaceRandomGoal extends Goal {
    private final TropicalSlimeEntity slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public TropicalSlimeFaceRandomGoal(TropicalSlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public boolean canStart() {
        return this.slime.getTarget() == null && (this.slime.isOnGround() || this.slime.isTouchingWater() || this.slime.isInLava() || this.slime.hasStatusEffect(StatusEffects.LEVITATION)) && this.slime.getMoveControl() instanceof TropicalSlimeMoveController;
    }

    public void tick() {
        if (--this.nextRandomizeTime <= 0) {
            this.nextRandomizeTime = 40 + this.slime.getRandom().nextInt(60);
            this.chosenDegrees = (float) this.slime.getRandom().nextInt(360);
        }
        ((TropicalSlimeMoveController) this.slime.getMoveControl()).look(this.chosenDegrees, false);
    }

}
