
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.network.IPacket;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class GlowSquidEntity extends SquidEntity {

    public GlowSquidEntity(EntityType<GlowSquidEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
        this.moveController = new MovementController(this) {
            @Override
            public void tick() {
                if (GlowSquidEntity.this.areEyesInFluid(FluidTags.WATER))
                    GlowSquidEntity.this.setMotion(GlowSquidEntity.this.getMotion().add(0, 0.005, 0));
                if (this.action == MovementController.Action.MOVE_TO && !GlowSquidEntity.this.getNavigator().noPath()) {
                    double dx = this.posX - GlowSquidEntity.this.getPosX();
                    double dy = this.posY - GlowSquidEntity.this.getPosY();
                    double dz = this.posZ - GlowSquidEntity.this.getPosZ();
                    dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
                    GlowSquidEntity.this.rotationYaw = this.limitAngle(GlowSquidEntity.this.rotationYaw,
                            (float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
                    GlowSquidEntity.this.renderYawOffset = GlowSquidEntity.this.rotationYaw;
                    GlowSquidEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, GlowSquidEntity.this.getAIMoveSpeed(),
                            (float) (this.speed * GlowSquidEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue())));
                    GlowSquidEntity.this.setMotion(GlowSquidEntity.this.getMotion().add(0, GlowSquidEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
                } else {
                    GlowSquidEntity.this.setAIMoveSpeed(0);
                }
            }
        };
        this.navigator = new SwimmerPathNavigator(this, this.world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1, 40));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.2));
    }

    @Override
    public boolean isNotColliding(IWorldReader worldreader) {
        return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
    }

    public static boolean canGlowingSquidSpawn(EntityType<GlowSquidEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > 45 && pos.getY() < world.getSeaLevel();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
