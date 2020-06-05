package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsModElements;
import net.slexom.earthtojavamobs.client.renderer.entity.MuddyPigRenderer;

import java.text.MessageFormat;


@EarthtojavamobsModElements.ModElement.Tag
public class MuddyPigEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "muddy_pig";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public MuddyPigEntity(EarthtojavamobsModElements instance) {
        super(instance, 44);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.9f, 0.9f)).build(registryNameEntity)
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, 0xe6918b, 0x573621, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    boolean biomeCriteria = false;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("plains")))
                        biomeCriteria = true;
                    if (!biomeCriteria)
                        continue;
                    biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
                }
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        AnimalEntity::canAnimalSpawn);
            }
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, MuddyPigRenderer::new);
    }

    public static class CustomEntity extends PigEntity {
        private static final DataParameter<Boolean> isInMud = EntityDataManager.createKey(CustomEntity.class, DataSerializers.BOOLEAN);
        private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
        private double outOfMud = 0.0D;

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
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
            this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
            this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
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
                setInMud(true);
            } else {
                if (getInMud()) {
                    outOfMud++;
                    if (outOfMud > 60) {
                        setInMud(false);
                        outOfMud = 0.0D;
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
            this.dataManager.register(isInMud, false);
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

        public boolean getInMud() {
            return this.dataManager.get(isInMud);
        }

        public void setInMud(boolean inMud) {
            this.dataManager.set(isInMud, inMud);
        }

        public void writeAdditional(CompoundNBT compound) {
            super.writeAdditional(compound);
            compound.putBoolean("IsInMud", this.getInMud());
        }

        public void readAdditional(CompoundNBT compound) {
            super.readAdditional(compound);
            this.setInMud(compound.getBoolean("IsInMud"));
        }

        @Override
        public PigEntity createChild(AgeableEntity ageable) {
            return (CustomEntity) entity.create(this.world);
        }

        public class FindMudGoal extends Goal {
            private final CustomEntity muddy_pig;

            public FindMudGoal(CustomEntity entity) {
                this.muddy_pig = entity;
            }

            /**
             * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
             * method as well.
             */
            public boolean shouldExecute() {
                return this.muddy_pig.onGround && !this.muddy_pig.getInMud();
            }

            /**
             * Execute a one shot task or start executing a continuous task
             */
            public void startExecuting() {
                BlockPos blockpos = null;
                double posX = this.muddy_pig.getPosX();
                double posY = this.muddy_pig.getPosY();
                double posZ = this.muddy_pig.getPosZ();
                for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(MathHelper.floor(posX - 4.0D), MathHelper.floor(posY - 1.0D), MathHelper.floor(posZ - 4.0D), MathHelper.floor(posX + 4.0D), MathHelper.floor(posY), MathHelper.floor(posZ + 4.0D))) {
                    boolean condition = this.muddy_pig.world.getFluidState(blockpos1).getBlockState().getBlock().getRegistryName().equals(new ResourceLocation("earthtojavamobs:mud_fluid"));
                    if (condition) {
                        blockpos = blockpos1;
                        break;
                    }
                }
                if (blockpos != null) {
                    this.muddy_pig.getMoveHelper().setMoveTo((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), 1.0D);
                }

            }
        }
    }
}