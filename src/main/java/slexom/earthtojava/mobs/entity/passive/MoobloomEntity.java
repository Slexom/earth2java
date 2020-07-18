package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;
import slexom.earthtojava.mobs.init.BlockInit;

public class MoobloomEntity extends E2JBaseCowEntity<MoobloomEntity> implements Shearable {

    public MoobloomEntity(EntityType<MoobloomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new PlaceBlockGoal(this));
    }


    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.SHEARS && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            if (!this.world.isClient) {
                itemStack.damage(1, player, ((playerEntity) -> playerEntity.sendToolBreakStatus(hand)));
            }

            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity((PlayerEntity) null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.world.isClient()) {
            ((ServerWorld) this.world).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.remove();
            CowEntity cowEntity = (CowEntity) EntityType.COW.create(this.world);
            cowEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.yaw, this.pitch);
            cowEntity.setHealth(this.getHealth());
            cowEntity.bodyYaw = this.bodyYaw;
            if (this.hasCustomName()) {
                cowEntity.setCustomName(this.getCustomName());
                cowEntity.setCustomNameVisible(this.isCustomNameVisible());
            }

            if (this.isPersistent()) {
                cowEntity.setPersistent();
            }

            cowEntity.setInvulnerable(this.isInvulnerable());
            this.world.spawnEntity(cowEntity);

            for (int i = 0; i < 5; ++i) {
                this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getBodyY(1.0D), this.getZ(), new ItemStack(BlockInit.BUTTERCUP)));
            }
        }

    }

    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
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
            return !downTarget.isAir() && downTarget.isFullCube(world, downTargetPos) && target.isAir() && target.canPlaceAt(world, targetPos);
        }

        public void tick() {
            WorldAccess world = this.moobloom.world;
            int i = MathHelper.floor(this.moobloom.getX());
            int j = MathHelper.floor(this.moobloom.getY());
            int k = MathHelper.floor(this.moobloom.getZ());
            Block flower = Math.random() > 0.8 ? Blocks.SUNFLOWER : BlockInit.BUTTERCUP;
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = flower.getDefaultState();
            BlockPos blockDownPos = blockPos.down();
            BlockState blockDownState = world.getBlockState(blockDownPos);
            if (canPlace(world, blockState, blockPos, blockDownState, blockDownPos)) {
                world.removeBlock(blockPos, false);
                world.setBlockState(blockPos, blockState, 3);
            }
        }
    }


}