package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveController;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeAttackGoal extends Goal {
    private final TropicalSlimeEntity slime;
    private int growTieredTimer;

    public TropicalSlimeAttackGoal(TropicalSlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public boolean canStart() {
        LivingEntity livingentity = this.slime.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else {
            return (!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).getAbilities().invulnerable) && this.slime.getMoveControl() instanceof TropicalSlimeMoveController;
        }
    }

    public void start() {
        this.growTieredTimer = 300;
        super.start();
    }

    public boolean shouldContinue() {
        LivingEntity livingentity = this.slime.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).getAbilities().invulnerable) {
            return false;
        } else {
            return --this.growTieredTimer > 0;
        }
    }

    public void tick() {
        this.slime.lookAtEntity(this.slime.getTarget(), 10.0F, 10.0F);
        ((TropicalSlimeMoveController) this.slime.getMoveControl()).look(this.slime.getYaw(), this.slime.canDamagePlayer());
    }
}
