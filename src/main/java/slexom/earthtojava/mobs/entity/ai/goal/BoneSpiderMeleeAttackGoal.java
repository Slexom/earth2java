package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

public class BoneSpiderMeleeAttackGoal extends MeleeAttackGoal {
    public BoneSpiderMeleeAttackGoal(BoneSpiderEntity spider) {
        super(spider, 1.0D, false);
    }

    public boolean canStart() {
        return super.canStart() && !this.mob.hasPassengers();
    }

    public boolean shouldContinue() {
        float f = this.mob.getBrightnessAtEyes();
        if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
            this.mob.setTarget((LivingEntity) null);
            return false;
        } else {
            return super.shouldContinue();
        }
    }

    protected double getSquaredMaxAttackDistance(LivingEntity entity) {
        return (double) (4.0F + entity.getWidth());
    }
}
