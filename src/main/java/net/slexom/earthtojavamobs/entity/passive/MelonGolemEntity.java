package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slexom.earthtojavamobs.entity.projectile.MelonSeedProjectileEntity;

import javax.annotation.Nullable;

public class MelonGolemEntity extends GolemEntity implements IRangedAttackMob, net.minecraftforge.common.IShearable {
    private static final DataParameter<Byte> MELON_EQUIPPED = EntityDataManager.createKey(MelonGolemEntity.class, DataSerializers.BYTE);

    public MelonGolemEntity(EntityType<? extends MelonGolemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, true, false, (entity) -> entity instanceof IMob && !(entity instanceof TropicalSlimeEntity)));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(MELON_EQUIPPED, (byte) 16);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Melon", this.isMelonEquipped());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("Melon")) {
            this.setMelonEquipped(compound.getBoolean("Melon"));
        }

    }

    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            int i = MathHelper.floor(this.getPosX());
            int j = MathHelper.floor(this.getPosY());
            int k = MathHelper.floor(this.getPosZ());
            if (this.isInWaterRainOrBubbleColumn()) {
                this.attackEntityFrom(DamageSource.DROWN, 1.0F);
            }
            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
                this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
            }
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                return;
            }
            BlockState blockstate = Blocks.SNOW.getDefaultState();
            for (int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getPosX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getPosY());
                k = MathHelper.floor(this.getPosZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);
                if (this.world.isAirBlock(blockpos) && this.world.getBiome(blockpos).getTemperature(blockpos) < 0.8F && blockstate.isValidPosition(this.world, blockpos)) {
                    this.world.setBlockState(blockpos, blockstate);
                }
            }
        }

    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
//        MelonSeedProjectileEntity melonSeedEntity = new MelonSeedProjectileEntity(this.world, this);
        SnowballEntity melonSeedEntity = new SnowballEntity(this.world, this);

        double d0 = target.getPosYEye() - (double) 1.1F;
        double d1 = target.getPosX() - this.getPosX();
        double d2 = d0 - melonSeedEntity.getPosY();
        double d3 = target.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        melonSeedEntity.shoot(d1, d2 + (double) f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(melonSeedEntity);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.7F;
    }

    public boolean isMelonEquipped() {
        return (this.dataManager.get(MELON_EQUIPPED) & 16) != 0;
    }

    public void setMelonEquipped(boolean melonEquipped) {
        byte b0 = this.dataManager.get(MELON_EQUIPPED);
        if (melonEquipped) {
            this.dataManager.set(MELON_EQUIPPED, (byte) (b0 | 16));
        } else {
            this.dataManager.set(MELON_EQUIPPED, (byte) (b0 & -17));
        }

    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SNOW_GOLEM_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_DEATH;
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return this.isMelonEquipped();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        this.setMelonEquipped(false);
        return new java.util.ArrayList<>();
    }
}