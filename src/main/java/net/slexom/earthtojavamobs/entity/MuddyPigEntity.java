package net.slexom.earthtojavamobs.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.init.FluidInit;

import java.util.EnumSet;


public class MuddyPigEntity extends PigEntity {

    private static final DataParameter<Boolean> IS_IN_MUD = EntityDataManager.createKey(MuddyPigEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
    private double outOfMud = 0.0D;
    private double finallyInMud = 0.0D;


    public MuddyPigEntity(EntityType<MuddyPigEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MuddyPigEntity.SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.fromItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new MuddyPigEntity.GoToMudGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new MuddyPigEntity.WanderGoal(this, 1.0D, 100));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        int i = MathHelper.floor(this.getPosX());
        int j = MathHelper.floor(this.getPosY());
        int k = MathHelper.floor(this.getPosZ());
        BlockPos blockPos = new BlockPos(i, j, k);
        boolean condition = this.world.getFluidState(blockPos).getBlockState().getBlock().equals(FluidInit.MUD_BLOCK.get());
        if (condition) {
            if (!isInMud()) {
                finallyInMud++;
                if (finallyInMud > 60) {
                    finallyInMud = 0.0D;
                    setInMud(true);
                }
            }
        } else {
            if (isInMud()) {
                outOfMud++;
                if (outOfMud > 60) {
                    outOfMud = 0.0D;
                    setInMud(false);
                }
            }
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_IN_MUD, false);
    }

//    public boolean processInteract(PlayerEntity player, Hand hand) {
//        if (super.processInteract(player, hand)) {
//            return true;
//        } else {
//            ItemStack itemstack = player.getHeldItem(hand);
//            if (itemstack.getItem() == Items.NAME_TAG) {
//                itemstack.interactWithEntity(player, this, hand);
//                return true;
//            }  else if (this.getSaddled() && !this.isBeingRidden()) {
//                    if (!this.world.isRemote) {
//                        player.startRiding(this);
//                    }
//
//                    return true;
//                } else {
//                    return itemstack.getItem() == Items.SADDLE && itemstack.interactWithEntity(player, this, hand);
//                }
//
//
//        }
//    }

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

    @Override
    public PigEntity createChild(AgeableEntity ageable) {
        return (MuddyPigEntity) getType().create(this.world);
    }

    public boolean handleWaterMovement() {
        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        if (this.getRidingEntity() instanceof BoatEntity) {
            this.inWater = false;
        } else if (this.handleFluidAcceleration(FluidTags.WATER) || this.handleFluidAcceleration(FluidTags.getCollection().getOrCreate(mudTag))) {
            if (!this.inWater && !this.firstUpdate) {
                this.doWaterSplashEffect();
            }
            this.fallDistance = 0.0F;
            this.inWater = true;
            this.extinguish();
        } else {
            this.inWater = false;
        }
        return this.inWater;
    }

    public static class GoToMudGoal extends MoveToBlockGoal {
        private final MuddyPigEntity muddy_pig;

        public GoToMudGoal(MuddyPigEntity entity, double speedIn) {
            super(entity, speedIn, 24);
            this.muddy_pig = entity;
            this.field_203112_e = -1;
        }

        public boolean shouldExecute() {
            return !this.muddy_pig.isInMud() && super.shouldExecute();
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 120 == 0;
        }

        public boolean shouldContinueExecuting() {
            return !this.muddy_pig.isInMud() && this.timeoutCounter <= 600 && this.shouldMoveTo(this.muddy_pig.world, this.destinationBlock);
        }

        @Override
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block == FluidInit.MUD_BLOCK.get();
        }
    }

    public static class SwimGoal extends Goal {
        private final MuddyPigEntity entity;

        public SwimGoal(MuddyPigEntity entityIn) {
            this.entity = entityIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP));
            entityIn.getNavigator().setCanSwim(true);

        }

        public boolean shouldExecute() {
            double d0 = (double) this.entity.getEyeHeight() < 0.4D ? 0.2D : 0.4D;
            return (this.entity.isInWater() || this.entity.isInMud()) && this.entity.getSubmergedHeight() > d0 || this.entity.isInLava();
        }

        public void tick() {
            if (this.entity.getRNG().nextFloat() < 0.8F) {
                this.entity.getJumpController().setJumping();
            }
        }
    }


    public static class WanderGoal extends RandomWalkingGoal {
        private final MuddyPigEntity muddyPig;

        private WanderGoal(MuddyPigEntity entity, double speedIn, int chance) {
            super(entity, speedIn, chance);
            this.muddyPig = entity;
        }

        public boolean shouldExecute() {
            return (!this.creature.isInWater() && !this.muddyPig.isInMud()) && super.shouldExecute();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
 