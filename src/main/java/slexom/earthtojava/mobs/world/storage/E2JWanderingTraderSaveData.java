//package slexom.earthtojava.mobs.world.storage;
//
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraft.world.storage.DimensionSavedDataManager;
//import net.minecraft.world.storage.WorldSavedData;
//import slexom.earthtojava.mobs.EarthToJavaMobsMod;
//
//public class E2JWanderingTraderSaveData extends WorldSavedData {
//
//    private static final String DATA_NAME = EarthToJavaMobsMod.MOD_ID + "_WanderingTraderData";
//    private int traderDelay;
//
//    public E2JWanderingTraderSaveData() {
//        super(DATA_NAME);
//    }
//
//    public static E2JWanderingTraderSaveData get(ServerWorld world) {
//        DimensionSavedDataManager savedDataManager = world.getSavedData();
//        return savedDataManager.getOrCreate(E2JWanderingTraderSaveData::new, DATA_NAME);
//    }
//
//    public int getTraderDelay() {
//        return this.traderDelay;
//    }
//
//    public void setTraderDelay(int delay) {
//        this.traderDelay = delay;
//        this.markDirty();
//    }
//
//    @Override
//    public void read(CompoundNBT nbt) {
//        this.traderDelay = nbt.getInt("TraderDelay");
//    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT compound) {
//        compound.putInt("TraderDelay", this.traderDelay);
//        return compound;
//    }
//
//}
