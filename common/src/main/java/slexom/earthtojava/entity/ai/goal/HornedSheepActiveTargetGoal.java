package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepActiveTargetGoal extends ActiveTargetGoal<PlayerEntity> {
    public HornedSheepActiveTargetGoal(HornedSheepEntity sheepEntity) {
        super(sheepEntity, PlayerEntity.class, true);
    }

    public boolean canStart() {
        return this.canCharge() && super.canStart();
    }

    public boolean shouldContinue() {
        boolean flag = this.canCharge();
        if (flag && this.mob.getTarget() != null) {
            return super.shouldContinue();
        } else {
            this.target = null;
            return false;
        }
    }

    private boolean canCharge() {
        HornedSheepEntity sheepEntity = (HornedSheepEntity) this.mob;
        return sheepEntity.isAngry();
    }
}
