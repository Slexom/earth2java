
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.fml.network.NetworkHooks;
import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MoobloomEntity extends E2JBaseCowEntity<MoobloomEntity> implements IForgeShearable {

    public MoobloomEntity(EntityType<MoobloomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new MoobloomEntity.PlaceBlockGoal(this));
    }

    public boolean isShearable(@javax.annotation.Nonnull ItemStack item, World world, BlockPos pos) {
        return this.isAlive() && !this.isChild();
    }

    @Nonnull
    public java.util.List<ItemStack> onSheared(@Nullable PlayerEntity player, @javax.annotation.Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
        world.playMovingSound(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!this.world.isRemote) {
            ((ServerWorld) this.world).spawnParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.remove();
            CowEntity cowentity = EntityType.COW.create(this.world);
            cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
            cowentity.setHealth(this.getHealth());
            cowentity.renderYawOffset = this.renderYawOffset;
            if (this.hasCustomName()) {
                cowentity.setCustomName(this.getCustomName());
                cowentity.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isNoDespawnRequired()) {
                cowentity.enablePersistence();
            }
            cowentity.setInvulnerable(this.isInvulnerable());
            this.world.addEntity(cowentity);
            java.util.List<ItemStack> ret = new java.util.ArrayList<>();
            for (int i = 0; i < 5; ++i) {
                ret.add(new ItemStack(Blocks.DANDELION));
            }
         }
        return java.util.Collections.emptyList();
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
            return !downTarget.isAir(world, downTargetPos) && downTarget.isOpaqueCube(world, downTargetPos) && target.isValidPosition(world, targetPos);
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
            if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(moobloom, BlockSnapshot.create(iworld, blockDownPos), net.minecraft.util.Direction.UP)) {
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