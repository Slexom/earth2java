package slexom.earthtojava.mobs.entity.ai.goal;

import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import slexom.earthtojava.mobs.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

public class MelonGolemProjectileAttackGoal extends ProjectileAttackGoal {
    private final MelonGolemEntity rangedAttackEntityHost;

    public MelonGolemProjectileAttackGoal(MelonGolemEntity attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
        super(attacker, movespeed, maxAttackTime, maxAttackDistanceIn);
        this.rangedAttackEntityHost = attacker;
    }

    public MelonGolemProjectileAttackGoal(MelonGolemEntity attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
        super(attacker, movespeed, p_i1650_4_, maxAttackTime, maxAttackDistanceIn);
        this.rangedAttackEntityHost = attacker;
    }

    @Override
    public void tick() {
        super.tick();
        ((MelonGolemMoveControl) this.rangedAttackEntityHost.getMoveControl()).setDirection(this.rangedAttackEntityHost.yaw, true);
    }
}
