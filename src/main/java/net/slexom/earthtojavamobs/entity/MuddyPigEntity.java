package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;

import java.text.MessageFormat;


public class MuddyPigEntity extends PigEntity {

    private static final String registryNameEntity = "muddy_pig";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

//    public MuddyPigEntity(EarthtojavamobsModElements instance) {
//        super(instance, 44);
//        FMLJavaModLoadingContext.get().getModEventBus().register(this);
//    }
//
//    @Override
//    public void initElements() {
//        entity = (EntityType.Builder.<MuddyPigEntity>create(MuddyPigEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
//                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MuddyPigEntity::new).size(0.9f, 0.9f)).build(registryNameEntity)
//                .setRegistryName(registryNameEntity);
//        elements.entities.add(() -> entity);
//        elements.items.add(() -> new SpawnEggItem(entity, 0xe6918b, 0x573621, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
//    }
//
//    @Override
//    public void init(FMLCommonSetupEvent event) {
//        DeferredWorkQueue.runLater(new Runnable() {
//            @Override
//            public void run() {
//                String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.PLAINS, BiomeSpawnHelper.MOUNTAINS, BiomeSpawnHelper.RIVER);
//                BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, 10, 2, 4);
//                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
//                        AnimalEntity::canAnimalSpawn);
//            }
//        });
//    }

//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    public void registerModels(ModelRegistryEvent event) {
//        RenderingRegistry.registerEntityRenderingHandler(entity, MuddyPigRenderer::new);
//    }

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
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new FindMudGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.fromItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new RandomWalkingGoal(this, 1.0D));
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
        boolean condition = this.world.getFluidState(blockPos).getBlockState().getBlock().getRegistryName().equals(new ResourceLocation("earthtojavamobs:mud_fluid"));
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

    public boolean processInteract(PlayerEntity player, Hand hand) {
        if (super.processInteract(player, hand)) {
            return true;
        } else {
            ItemStack itemstack = player.getHeldItem(hand);
            if (itemstack.getItem() == Items.NAME_TAG) {
                itemstack.interactWithEntity(player, this, hand);
                return true;
            } /*else if (this.getSaddled() && !this.isBeingRidden()) {
                    if (!this.world.isRemote) {
                        player.startRiding(this);
                    }

                    return true;
                } else {
                    return itemstack.getItem() == Items.SADDLE && itemstack.interactWithEntity(player, this, hand);
                }*/

            return false;
        }
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

    @Override
    public PigEntity createChild(AgeableEntity ageable) {
        return (MuddyPigEntity) getType().create(this.world);
    }

    public class FindMudGoal extends Goal {
        private final MuddyPigEntity muddy_pig;

        public FindMudGoal(MuddyPigEntity entity) {
            this.muddy_pig = entity;
            entity.getNavigator().setCanSwim(true);
        }

        public boolean shouldExecute() {
            return !this.muddy_pig.isInMud();
        }

        public void startExecuting() {
            BlockPos blockpos = null;
            double posX = this.muddy_pig.getPosX();
            double posY = this.muddy_pig.getPosY();
            double posZ = this.muddy_pig.getPosZ();
            double dist = 16.0D;
            for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(posX - dist), MathHelper.floor(posY - dist), MathHelper.floor(posZ - dist), MathHelper.floor(posX + dist), MathHelper.floor(posY + dist), MathHelper.floor(posZ + dist))) {
                boolean condition = this.muddy_pig.world.getFluidState(blockpos1).getBlockState().getBlock().getRegistryName().equals(new ResourceLocation("earthtojavamobs:mud_fluid"));
                if (condition) {
                    blockpos = blockpos1;
                    break;
                }
            }
            if (blockpos != null) {
                this.muddy_pig.getMoveHelper().setMoveTo((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), 1.2D);
            }
        }
    }

    private boolean eyesInMud() {
        ResourceLocation mudTag = new ResourceLocation(EarthtojavamobsMod.MOD_ID, "mud");
        return this.areEyesInFluid(FluidTags.getCollection().get(mudTag), true);
    }

    public void baseTick() {
        super.baseTick();
        updateSwimming();
    }

    public void updateSwimming() {
        if (this.isSwimming()) {
            this.setSwimming(this.isSprinting() && (this.isInWater() || this.isInMud()) && !this.isPassenger());
        } else {
            this.setSwimming(this.isSprinting() && this.canSwim() && !this.isPassenger());
        }

    }

    public boolean canSwim() {
        return (this.eyesInWater && this.isInWater()) || (this.isInMud());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
 