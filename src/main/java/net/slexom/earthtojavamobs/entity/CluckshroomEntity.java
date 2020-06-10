
package net.slexom.earthtojavamobs.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;


public class CluckshroomEntity extends ChickenEntity {

    public CluckshroomEntity(EntityType<CluckshroomEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new CluckshroomEntity.PlaceBlockGoal(this));
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
    }

    @Override
    public net.minecraft.util.SoundEvent getAmbientSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.ambient"));
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.death"));
    }

    @Override
    protected float getSoundVolume() {
        return 1.0F;
    }

    @Override
    public ChickenEntity createChild(AgeableEntity ageable) {
        return (CluckshroomEntity) getType().create(this.world);
    }


    static class PlaceBlockGoal extends Goal {
        private final CluckshroomEntity cluckshroom;

        public PlaceBlockGoal(CluckshroomEntity p_i45843_1_) {
            this.cluckshroom = p_i45843_1_;
        }

        public boolean shouldExecute() {
            return this.cluckshroom.getRNG().nextInt(2000) == 0;
        }

        public boolean canPlace(IWorldReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
            return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
        }

        public void tick() {
            IWorld iworld = this.cluckshroom.world;
            int i = MathHelper.floor(this.cluckshroom.getPosX());
            int j = MathHelper.floor(this.cluckshroom.getPosY());
            int k = MathHelper.floor(this.cluckshroom.getPosZ());
            Block mushroom = Blocks.RED_MUSHROOM;
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = mushroom.getDefaultState();
            BlockPos blockDownPos = blockPos.down();
            BlockState blockDownState = iworld.getBlockState(blockDownPos);
            if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(cluckshroom, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockPos, blockDownState), net.minecraft.util.Direction.UP)) {
                iworld.destroyBlock(blockPos, false);
                iworld.setBlockState(blockPos, blockState, 3);
            }
        }


    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
