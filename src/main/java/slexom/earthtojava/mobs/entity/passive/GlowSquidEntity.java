package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public class GlowSquidEntity extends SquidEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public GlowSquidEntity(EntityType<GlowSquidEntity> type, World world) {
        super(type, world);
        experiencePoints = 3;
        setAiDisabled(false);
        this.moveControl = new MoveControl(this) {
            @Override
            public void tick() {
                if (GlowSquidEntity.this.isSubmergedIn(FluidTags.WATER))
                    GlowSquidEntity.this.setVelocity(GlowSquidEntity.this.getVelocity().add(0, 0.005, 0));
                if (this.state == MoveControl.State.MOVE_TO && !GlowSquidEntity.this.getNavigation().isIdle()) {
                    double dx = this.targetX - GlowSquidEntity.this.getX();
                    double dy = this.targetY - GlowSquidEntity.this.getY();
                    double dz = this.targetZ - GlowSquidEntity.this.getZ();
                    dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
                    GlowSquidEntity.this.yaw = this.changeAngle(GlowSquidEntity.this.yaw, (float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
                    GlowSquidEntity.this.bodyYaw = GlowSquidEntity.this.yaw;
                    GlowSquidEntity.this.setMovementSpeed(MathHelper.lerp(0.125f, GlowSquidEntity.this.getMovementSpeed(),
                            (float) (this.speed * GlowSquidEntity.this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue())));
                    GlowSquidEntity.this.setVelocity(GlowSquidEntity.this.getVelocity().add(0, GlowSquidEntity.this.getMovementSpeed() * dy * 0.1, 0));
                } else {
                    GlowSquidEntity.this.setMovementSpeed(0);
                }
            }
        };
        this.navigation = new SwimNavigation(this, this.world);
    }

    public static boolean canGlowingSquidSpawn(EntityType<GlowSquidEntity> entity, WorldAccess world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > 45 && pos.getY() < world.getSeaLevel();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SwimAroundGoal(this, 1, 40));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.2));
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.remainingTick > 0) {
            --this.remainingTick;
        }
        if (this.internalBlinkTick == (this.lastBlink + this.nextBlinkInterval)) {
            this.lastBlink = this.internalBlinkTick;
            this.nextBlinkInterval = new Random().nextInt(740) + 60;
            this.remainingTick = 4;
        }
        ++this.internalBlinkTick;
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }

    @Override
    public boolean canSpawn(WorldView worldreader) {
        return worldreader.intersectsEntities(this, VoxelShapes.cuboid(this.getBoundingBox()));
    }


}
