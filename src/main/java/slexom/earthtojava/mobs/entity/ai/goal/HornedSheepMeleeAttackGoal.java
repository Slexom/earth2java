package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

public class HornedSheepMeleeAttackGoal extends MeleeAttackGoal {

    final HornedSheepEntity attacker;

    public HornedSheepMeleeAttackGoal(HornedSheepEntity creatureIn, double speedIn, boolean useLongMemory) {
        super(creatureIn, speedIn, useLongMemory);
        this.attacker = creatureIn;
    }

    public boolean canStart() {
        return super.canStart() && this.attacker.isAngry();
    }

    public boolean shouldContinue() {
        return super.shouldContinue() && this.attacker.isAngry();
    }
}
