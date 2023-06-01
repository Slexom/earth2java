package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.MobEntity;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepRevengeGoal extends RevengeGoal {

	public HornedSheepRevengeGoal(HornedSheepEntity mob) {
		super(mob);
	}

	@Override
	protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
		if (mob instanceof HornedSheepEntity && this.mob.canSee(target) && ((HornedSheepEntity) mob).setSheepAttacker(target)) {
			mob.setTarget(target);
		}
	}

}
