
package net.slexom.earthtojavamobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class WoolyCowEntity extends CowEntity implements net.minecraftforge.common.IShearable {

    private static final DataParameter<Byte> isSheared = EntityDataManager.createKey(WoolyCowEntity.class, DataSerializers.BYTE);

    private int shearTimer;
    private EatGrassGoal eatGrassGoal;


    public WoolyCowEntity(EntityType<WoolyCowEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
        setNoAI(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(5, this.eatGrassGoal);
    }

    protected void updateAITasks() {
        this.shearTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }

    public void livingTick() {
        if (this.world.isRemote) {
            this.shearTimer = Math.max(0, this.shearTimer - 1);
        }
        super.livingTick();
    }

    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.shearTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(isSheared, (byte) 0);
    }

    public boolean getSheared() {
        return (this.dataManager.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = this.dataManager.get(isSheared);
        if (sheared) {
            this.dataManager.set(isSheared, (byte) (b0 | 16));
        } else {
            this.dataManager.set(isSheared, (byte) (b0 & -17));
        }
    }

    public void eatGrassBonus() {
        this.setSheared(false);
        if (this.isChild()) {
            this.addGrowth(30);
        }
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return !this.getSheared() && !this.isChild();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        java.util.List<ItemStack> ret = new java.util.ArrayList<>();
        if (!this.world.isRemote) {
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);
            for (int j = 0; j < i; ++j) {
                ret.add(new ItemStack(Blocks.BROWN_WOOL));
            }
        }
        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
        return ret;
    }

    @Override
    public CowEntity createChild(AgeableEntity ageable) {
        return (WoolyCowEntity) getType().create(this.world);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Sheared", this.getSheared());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
