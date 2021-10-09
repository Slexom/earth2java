package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import slexom.earthtojava.mobs.entity.ai.control.GlowSquidMoveControl;

import java.util.Random;

public class GlowSquidEntity extends SquidEntity {

    private int remainingTick = 0;

    public GlowSquidEntity(EntityType<GlowSquidEntity> type, World world) {
        super(type, world);
        experiencePoints = 3;
        setAiDisabled(false);
        this.moveControl = new GlowSquidMoveControl(this);
        this.navigation = new SwimNavigation(this, this.world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SwimAroundGoal(this, 1, 40));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.2));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient()) {
            ((ServerWorld) this.world).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.discard();
            net.minecraft.entity.passive.GlowSquidEntity vanillaGlowSquid = EntityType.GLOW_SQUID.create(this.world);
            vanillaGlowSquid.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            vanillaGlowSquid.setHealth(this.getHealth());
            vanillaGlowSquid.bodyYaw = this.bodyYaw;
            if (this.hasCustomName()) {
                vanillaGlowSquid.setCustomName(this.getCustomName());
                vanillaGlowSquid.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                vanillaGlowSquid.setPersistent();
            }
            vanillaGlowSquid.setInvulnerable(this.isInvulnerable());
            this.world.spawnEntity(vanillaGlowSquid);
        }
    }

    public void tickMovement() {
        super.tickMovement();
    }

    public int getBlinkRemainingTicks() {
        return this.remainingTick;
    }

    @Override
    public boolean canSpawn(WorldView worldreader) {
        return worldreader.intersectsEntities(this, VoxelShapes.cuboid(this.getBoundingBox()));
    }

}
