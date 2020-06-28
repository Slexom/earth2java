package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.slexom.earthtojavamobs.entity.base.E2JBasePigEntity;
import net.slexom.earthtojavamobs.init.BlockInit;


public class MuddyPigEntity extends E2JBasePigEntity<MuddyPigEntity> {

    private static final DataParameter<Boolean> IS_IN_MUD = EntityDataManager.createKey(MuddyPigEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private int outOfMud = 0;
    private int finallyInMud = 0;


    public MuddyPigEntity(EntityType<MuddyPigEntity> type, World world) {
        super(type, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.fromItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new MuddyPigEntity.GoToMudGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0D, 100));
    }

    @Override
    public void livingTick() {
        super.livingTick();
        int i = MathHelper.floor(this.getPosX());
        int j = MathHelper.floor(this.getPosY());
        int k = MathHelper.floor(this.getPosZ());
        BlockPos blockPos = new BlockPos(i, j, k);
        boolean condition = this.world.getFluidState(blockPos).getBlockState().getBlock().equals(BlockInit.MUD_BLOCK.get());
        if (condition) {
            if (!isInMud()) {
                finallyInMud++;
                if (finallyInMud > 60) {
                    finallyInMud = 0;
                    setInMud(true);
                }
            }
        } else {
            if (isInMud()) {
                outOfMud++;
                if (outOfMud > 60) {
                    outOfMud = 0;
                    setInMud(false);

                }
            }
        }
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_IN_MUD, false);
    }

    public boolean isInMud() {
        return this.dataManager.get(IS_IN_MUD);
    }

    public void setInMud(boolean inMud) {
        this.dataManager.set(IS_IN_MUD, inMud);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("IsInMud", this.isInMud());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setInMud(compound.getBoolean("IsInMud"));
    }

    public static class GoToMudGoal extends MoveToBlockGoal {
        private final MuddyPigEntity muddyPig;

        public GoToMudGoal(MuddyPigEntity entity, double speedIn) {
            super(entity, speedIn, 16);
            this.muddyPig = entity;
            this.field_203112_e = -1;
        }

        public boolean shouldExecute() {
            return !this.muddyPig.isInMud() && super.shouldExecute();
        }

        public boolean shouldContinueExecuting() {
            return !this.muddyPig.isInMud() && this.timeoutCounter <= 600 && this.shouldMoveTo(this.muddyPig.world, this.destinationBlock);
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 100 == 0;
        }

        @Override
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block == BlockInit.MUD_BLOCK.get();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
 