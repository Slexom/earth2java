package slexom.earthtojava.mobs.entity.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.BlinkManager;

public class E2JBasePigEntity extends PigEntity {

    public BlinkManager blinkManager;

    public E2JBasePigEntity(EntityType<? extends E2JBasePigEntity> type, World worldIn) {
        super(type, worldIn);
        blinkManager = new BlinkManager();
        experiencePoints = 3;
        setAiDisabled(false);
    }

    public static DefaultAttributeContainer.Builder createPigAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }
/*
    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.remove(new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D, E2JBasePigEntity.class));
    }
*/
    @Override
    public void tickMovement() {
        super.tickMovement();
        blinkManager.tickBlink();
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) {
            return false;
        } else if (!(other instanceof PigEntity)) {
            return false;
        } else {
            return this.isInLove() && other.isInLove();
        }
    }

    @Override
    public E2JBasePigEntity createChild(ServerWorld world, PassiveEntity other) {
        if (this.random.nextInt(100) > 50) {
            return (E2JBasePigEntity) this.getType().create(world);
        } else {
            return (E2JBasePigEntity) other.getType().create(world);
        }
    }
}
