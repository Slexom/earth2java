package slexom.earthtojava.mobs.entity.passive;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.entity.ai.control.GlowSquidMoveControl;

import java.util.Random;

public class GlowSquidEntity extends SquidEntity {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    static ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public GlowSquidEntity(EntityType<GlowSquidEntity> type, World world) {
        super(type, world);
        experiencePoints = 3;
        setAiDisabled(false);
        this.moveControl = new GlowSquidMoveControl(this);
        this.navigation = new SwimNavigation(this, this.world);
    }

    public static boolean canGlowingSquidSpawn(EntityType<GlowSquidEntity> entity, WorldAccess world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > config.glowSquid.spawnHeight.spawnHeightMin && pos.getY() < config.glowSquid.spawnHeight.spawnHeightMax;
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
