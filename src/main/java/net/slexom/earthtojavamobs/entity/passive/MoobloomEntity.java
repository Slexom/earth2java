
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.slexom.earthtojavamobs.entity.base.E2JBaseCowEntity;

public class MoobloomEntity extends E2JBaseCowEntity<MoobloomEntity> implements net.minecraftforge.common.IShearable {

    public MoobloomEntity(EntityType<MoobloomEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new MoobloomEntity.PlaceBlockGoal(this));
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, net.minecraft.util.math.BlockPos pos) {
        return !this.isChild();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, net.minecraft.util.math.BlockPos pos, int fortune) {
        java.util.List<ItemStack> ret = new java.util.ArrayList<>();
        this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        if (!this.world.isRemote) {
            this.remove();
            CowEntity cowentity = EntityType.COW.create(this.world);
            cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
            cowentity.setHealth(this.getHealth());
            cowentity.renderYawOffset = this.renderYawOffset;
            if (this.hasCustomName()) {
                cowentity.setCustomName(this.getCustomName());
                cowentity.setCustomNameVisible(this.isCustomNameVisible());
            }
            this.world.addEntity(cowentity);
            for (int i = 0; i < 5; ++i) {
                ret.add(new ItemStack(Blocks.DANDELION));
            }
            this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
        }
        return ret;
    }

    static class PlaceBlockGoal extends Goal {
        private final MoobloomEntity moobloom;

        public PlaceBlockGoal(MoobloomEntity p_i45843_1_) {
            this.moobloom = p_i45843_1_;
        }

        public boolean shouldExecute() {
            return this.moobloom.getRNG().nextInt(2000) == 0;
        }

        public boolean canPlace(IWorldReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
            return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
        }

        public void tick() {
            IWorld iworld = this.moobloom.world;
            int i = MathHelper.floor(this.moobloom.getPosX());
            int j = MathHelper.floor(this.moobloom.getPosY());
            int k = MathHelper.floor(this.moobloom.getPosZ());
            Block flower = Math.random() > 0.8 ? Blocks.SUNFLOWER : Blocks.DANDELION;
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = flower.getDefaultState();
            BlockPos blockDownPos = blockPos.down();
            BlockState blockDownState = iworld.getBlockState(blockDownPos);
            if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(moobloom, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockPos, blockDownState), net.minecraft.util.Direction.UP)) {
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