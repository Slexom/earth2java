package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffects;
import slexom.earthtojava.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

import java.util.EnumSet;

public class MelonGolemFaceRandomGoal extends Goal {
    private final MelonGolemEntity melonGolem;
    private float targetYaw;
    private int timer;

    public MelonGolemFaceRandomGoal(MelonGolemEntity melonGolem) {
        this.melonGolem = melonGolem;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public boolean canStart() {
        return this.melonGolem.getTarget() == null && (this.melonGolem.isOnGround() || this.melonGolem.isInsideWaterOrBubbleColumn() || this.melonGolem.isInLava() || this.melonGolem.hasStatusEffect(StatusEffects.LEVITATION)) && this.melonGolem.getMoveControl() instanceof MelonGolemMoveControl;
    }

    public void tick() {
        if (--this.timer <= 0) {
            this.timer = 40 + this.melonGolem.getRandom().nextInt(60);
            this.targetYaw = (float) this.melonGolem.getRandom().nextInt(360);
        }
        ((MelonGolemMoveControl) this.melonGolem.getMoveControl()).setDirection(this.targetYaw, false);
    }
}
