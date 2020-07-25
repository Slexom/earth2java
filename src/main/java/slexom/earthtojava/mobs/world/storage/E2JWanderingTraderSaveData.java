package slexom.earthtojava.mobs.world.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;

public class E2JWanderingTraderSaveData extends WorldSavedData {
    private static final String DATA_NAME = EarthToJavaMobsMod.MOD_ID + "_WanderingTraderData";
    private int traderDelay;
    private int traderSpawnTime;

    public E2JWanderingTraderSaveData() {
        super(DATA_NAME);
    }

    public static E2JWanderingTraderSaveData get(ServerWorld world) {
        DimensionSavedDataManager savedDataManager = world.getSavedData();
        return savedDataManager.getOrCreate(E2JWanderingTraderSaveData::new, DATA_NAME);
    }

    public int getTraderDelay() {
        return this.traderDelay;
    }

    public int getTraderSpawnTime() {
        return this.traderSpawnTime;
    }

    public void setTraderDelay(int delay) {
        this.traderDelay = delay;
        this.markDirty();
    }

    public void setTraderSpawnTime(int value) {
        this.traderSpawnTime = value;
        this.markDirty();
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.traderDelay = nbt.getInt("TraderDelay");
        this.traderSpawnTime = nbt.getInt("TraderSpawnTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("TraderDelay", this.traderDelay);
        compound.putInt("TraderSpawnTime", this.traderSpawnTime);
        return compound;
    }
}
