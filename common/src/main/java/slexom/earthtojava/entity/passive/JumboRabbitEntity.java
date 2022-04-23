package slexom.earthtojava.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import slexom.earthtojava.entity.base.E2JBaseRabbitEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class JumboRabbitEntity extends E2JBaseRabbitEntity {

    public JumboRabbitEntity(EntityType<JumboRabbitEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected SoundEvent getJumpSound() {
        return SoundEventsInit.JUMBO_RABBIT_JUMP.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEventsInit.JUMBO_RABBIT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEventsInit.JUMBO_RABBIT_HURT.get();
    }

}
