
package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.IPacket;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;

public class WoolyCowEntity extends E2JBaseCowEntity<WoolyCowEntity> implements net.minecraftforge.common.IShearable {

    private static final TrackedData<Byte> isSheared = DataTracker.registerData(WoolyCowEntity.class, TrackedDataHandlerRegistry.BYTE);

    private int shearTimer;
    private EatGrassGoal eatGrassGoal;

    public WoolyCowEntity(EntityType<WoolyCowEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(5, this.eatGrassGoal);
    }

    protected void mobTick() {
        this.shearTimer = this.eatGrassGoal.getTimer();
        super.mobTick();
    }

    public void tickMovement() {
        if (this.world.isClient) {
            this.shearTimer = Math.max(0, this.shearTimer - 1);
        }
        super.tickMovement();
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte id) {
        if (id == 10) {
            this.shearTimer = 40;
        } else {
            super.handleStatus(id);
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(isSheared, (byte) 0);
    }

    public boolean getSheared() {
        return (this.dataTracker.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = this.dataTracker.get(isSheared);
        if (sheared) {
            this.dataTracker.set(isSheared, (byte) (b0 | 16));
        } else {
            this.dataTracker.set(isSheared, (byte) (b0 & -17));
        }
    }

    public void onEatingGrass() {
        this.setSheared(false);
        if (this.isBaby()) {
            this.growUp(30);
        }
    }

    @Override
    public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return !this.getSheared() && !this.isBaby();
    }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, BlockPos pos, int fortune) {
        java.util.List<ItemStack> ret = new java.util.ArrayList<>();
        if (!this.world.isClient) {
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);
            for (int j = 0; j < i; ++j) {
                ret.add(new ItemStack(Blocks.BROWN_WOOL));
            }
        }
        this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
        return ret;
    }

    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("Sheared", this.getSheared());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }



}
