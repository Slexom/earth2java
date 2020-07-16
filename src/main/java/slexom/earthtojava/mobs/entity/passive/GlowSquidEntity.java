
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.network.IPacket;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.World;


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
                if (GlowSquidEntity.this.areEyesInFluid(FluidTags.WATER))
                    GlowSquidEntity.this.setMotion(GlowSquidEntity.this.getMotion().add(0, 0.005, 0));
                if (this.action == MoveControl.Action.MOVE_TO && !GlowSquidEntity.this.getNavigator().noPath()) {
                    double dx = this.posX - GlowSquidEntity.this.getX();
                    double dy = this.posY - GlowSquidEntity.this.getY();
                    double dz = this.posZ - GlowSquidEntity.this.getZ();
                    dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
                    GlowSquidEntity.this.rotationYaw = this.limitAngle(GlowSquidEntity.this.rotationYaw,
                            (float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
                    GlowSquidEntity.this.renderYawOffset = GlowSquidEntity.this.rotationYaw;
                    GlowSquidEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, GlowSquidEntity.this.getAIMoveSpeed(),
                            (float) (this.speed * GlowSquidEntity.this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue())));
                    GlowSquidEntity.this.setMotion(GlowSquidEntity.this.getMotion().add(0, GlowSquidEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
                } else {
                    GlowSquidEntity.this.setAIMoveSpeed(0);
                }
            }
        };
        this.navigator = new SwimmerPathNavigator(this, this.world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new RandomSwimmingGoal(this, 1, 40));
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
    public boolean isNotColliding(WorldView worldreader) {
        return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
    }

    public static boolean canGlowingSquidSpawn(EntityType<GlowSquidEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > 45 && pos.getY() < world.getSeaLevel();
    }



}
