package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeAttackGoal extends Goal {
	private final TropicalSlimeEntity slime;
	private int growTieredTimer;

	public TropicalSlimeAttackGoal(TropicalSlimeEntity slime) {
		this.slime = slime;
		setControls(EnumSet.of(Control.LOOK));
	}

	public boolean canStart() {
		LivingEntity livingentity = slime.getTarget();
		if (livingentity == null) {
			return false;
		}
		if (!livingentity.isAlive()) {
			return false;
		}
		return (!(livingentity instanceof PlayerEntity) || !((PlayerEntity) livingentity).getAbilities().invulnerable) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
	}

	@Override
	public void start() {
		growTieredTimer = 300;
		super.start();
	}

	@Override
	public boolean shouldContinue() {
		LivingEntity livingentity = slime.getTarget();
		if (livingentity == null) {
			return false;
		}
		if (!livingentity.isAlive()) {
			return false;
		}
		if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).getAbilities().invulnerable) {
			return false;
		}
		return --growTieredTimer > 0;
	}

	@Override
	public void tick() {
		slime.lookAtEntity(slime.getTarget(), 10.0F, 10.0F);
		((TropicalSlimeMoveControl) slime.getMoveControl()).look(slime.getYaw(), slime.canAttack());
	}
}
