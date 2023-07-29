package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

import java.util.EnumSet;
import java.util.List;

public class TrackFurnaceGolemTargetGoal extends TrackTargetGoal {
	private final FurnaceGolemEntity golem;
	private final TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(64.0);
	@Nullable
	private LivingEntity target;

	public TrackFurnaceGolemTargetGoal(FurnaceGolemEntity golem) {
		super(golem, false, true);
		this.golem = golem;
		setControls(EnumSet.of(Control.TARGET));
	}

	@Override
	public boolean canStart() {
		Box box = golem.getBoundingBox().expand(10.0, 8.0, 10.0);
		List<VillagerEntity> list = golem.getWorld().getTargets(VillagerEntity.class, targetPredicate, golem, box);
		List<PlayerEntity> list2 = golem.getWorld().getPlayers(targetPredicate, golem, box);
		for (VillagerEntity livingEntity : list) {
			for (PlayerEntity playerEntity : list2) {
				int i = livingEntity.getReputation(playerEntity);
				if (i > -100) continue;
				target = playerEntity;
			}
		}
		if (target == null) {
			return false;
		}
		return !(target instanceof PlayerEntity) || !target.isSpectator() && !((PlayerEntity) target).isCreative();
	}

	@Override
	public void start() {
		golem.playSound(SoundEventsInit.FURNACE_GOLEM_AGGRO.get(), 1.0F, 1.0F);
		golem.setAngry(true);
		golem.setTarget(target);
		super.start();
	}

	@Override
	public void stop() {
		golem.setAngry(false);
		super.stop();
	}
}
