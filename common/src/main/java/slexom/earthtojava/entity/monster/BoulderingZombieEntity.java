package slexom.earthtojava.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.pathing.ClimberNavigation;
import slexom.earthtojava.entity.base.E2JBaseZombieEntity;

public class BoulderingZombieEntity extends E2JBaseZombieEntity {
    private static final TrackedData<Byte> IS_CLIMBING = DataTracker.registerData(BoulderingZombieEntity.class, TrackedDataHandlerRegistry.BYTE);

    public BoulderingZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    protected EntityNavigation createNavigation(World world) {
        return new ClimberNavigation(this, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_CLIMBING, (byte) 0);
    }

    public boolean isClimbing() {
        return this.isClimbingWall();
    }

    public boolean isClimbingWall() {
        return (this.dataTracker.get(IS_CLIMBING) & 1) != 0;
    }

    public void setClimbingWall(boolean climbing) {
        byte b = this.dataTracker.get(IS_CLIMBING);
        if (climbing) {
            b = (byte) (b | 1);
        } else {
            b &= -2;
        }
        this.dataTracker.set(IS_CLIMBING, b);
    }

    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.setClimbingWall(this.horizontalCollision);
        }
    }

    @Override
    public boolean isHoldingOntoLadder() {
        return isClimbingWall() || super.isHoldingOntoLadder();
    }
}
