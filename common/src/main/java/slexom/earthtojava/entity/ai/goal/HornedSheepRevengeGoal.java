package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.MobEntity;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepRevengeGoal extends RevengeGoal {

    public HornedSheepRevengeGoal(HornedSheepEntity sheepIn) {
        super(sheepIn);
    }

    protected void setMobEntityTarget(MobEntity mobIn, LivingEntity targetIn) {
        if (mobIn instanceof HornedSheepEntity && this.mob.canSee(targetIn) && ((HornedSheepEntity) mobIn).setSheepAttacker(targetIn)) {
            mobIn.setTarget(targetIn);
        }
    }

}
