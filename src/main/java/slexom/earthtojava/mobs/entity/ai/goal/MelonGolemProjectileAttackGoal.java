package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import slexom.earthtojava.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class MelonGolemProjectileAttackGoal extends ProjectileAttackGoal {
    private final MelonGolemEntity melonGolemEntity;

    public MelonGolemProjectileAttackGoal(MelonGolemEntity attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
        super(attacker, movespeed, maxAttackTime, maxAttackDistanceIn);
        this.melonGolemEntity = attacker;
    }

    public MelonGolemProjectileAttackGoal(MelonGolemEntity attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
        super(attacker, movespeed, p_i1650_4_, maxAttackTime, maxAttackDistanceIn);
        this.melonGolemEntity = attacker;
    }

    @Override
    public void start() {
        super.start();
        this.melonGolemEntity.playSound(SoundEventsInit.MELON_GOLEM_AGGRO, 1.0F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        ((MelonGolemMoveControl) this.melonGolemEntity.getMoveControl()).setDirection(this.melonGolemEntity.getYaw(), true);
    }
}
