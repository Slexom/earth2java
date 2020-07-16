
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;
import net.minecraft.world.World;

import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;
import slexom.earthtojava.mobs.init.BlockInit;

public class MoobloomEntity extends E2JBaseCowEntity<MoobloomEntity> implements net.minecraftforge.common.IShearable {

    public MoobloomEntity(EntityType<MoobloomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new PlaceBlockGoal(this));
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.WorldView world, BlockPos pos) {
        return !this.isBaby();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        java.util.List<ItemStack> ret = new java.util.ArrayList<>();
        this.world.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getPosYHeight(0.5D), this.getZ(), 0.0D, 0.0D, 0.0D);
        if (!this.world.isClient) {
            this.remove();
            CowEntity cowentity = EntityType.COW.create(this.world);
            cowentity.setLocationAndAngles(this.getX(), this.getY(), this.getZ(), this.rotationYaw, this.rotationPitch);
            cowentity.setHealth(this.getHealth());
            cowentity.renderYawOffset = this.renderYawOffset;
            if (this.hasCustomName()) {
                cowentity.setCustomName(this.getCustomName());
                cowentity.setCustomNameVisible(this.isCustomNameVisible());
            }
            this.world.addEntity(cowentity);
            for (int i = 0; i < 5; ++i) {
                ret.add(new ItemStack(BlockInit.BUTTERCUP.get()));
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

        public boolean canStart() {
            return this.moobloom.getRandom().nextInt(2000) == 0;
        }

        public boolean canPlace(WorldView world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
            return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
        }

        public void tick() {
            IWorld iworld = this.moobloom.world;
            int i = MathHelper.floor(this.moobloom.getX());
            int j = MathHelper.floor(this.moobloom.getY());
            int k = MathHelper.floor(this.moobloom.getZ());
            Block flower = Math.random() > 0.8 ? Blocks.SUNFLOWER : BlockInit.BUTTERCUP.get();
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



}