
package net.slexom.earthtojavamobs.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
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
import net.slexom.earthtojavamobs.client.renderer.entity.RockySheepRenderer;

import java.text.MessageFormat;

@EarthtojavamobsModElements.ModElement.Tag
public class RockySheepEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "rocky_sheep";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public RockySheepEntity(EarthtojavamobsModElements instance) {
        super(instance, 32);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.7f)).build(registryNameEntity)
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(
                () -> new SpawnEggItem(entity, 0xd6d1cc, 0x453d3b, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
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
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("mountains")))
                        biomeCriteria = true;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wooded_mountains")))
                        biomeCriteria = true;
                    if (!biomeCriteria)
                        continue;
                    biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 10, 1, 4));
                }
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        AnimalEntity::canAnimalSpawn);
            }
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new RockySheepRenderer(renderManager) {
        });
    }

    public static class CustomEntity extends AnimalEntity implements net.minecraftforge.common.IShearable {
        private static final DataParameter<Byte> isSheared = EntityDataManager.createKey(RockySheepEntity.CustomEntity.class, DataSerializers.BYTE);

        private int sheepTimer;
        private EatGrassGoal eatGrassGoal;

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<? extends CustomEntity> type, World world) {
            super(type, world);
            experienceValue = 2;
            setNoAI(false);
        }

        protected void registerGoals() {
            this.eatGrassGoal = new EatGrassGoal(this);
            this.goalSelector.addGoal(0, new SwimGoal(this));
            this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
            this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
            this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
            this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
            this.goalSelector.addGoal(5, this.eatGrassGoal);
            this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
            this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        }

        protected void registerAttributes() {
            super.registerAttributes();
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
            this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.23F);
        }

        @Override
        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        protected float getSoundVolume() {
            return 1.0F;
        }


        protected void updateAITasks() {
            this.sheepTimer = this.eatGrassGoal.getEatingGrassTimer();
            super.updateAITasks();
        }

        public void livingTick() {
            if (this.world.isRemote) {
                this.sheepTimer = Math.max(0, this.sheepTimer - 1);
            }
            super.livingTick();
        }

        /**
         * Handler for {@link World#setEntityState}
         */
        public void handleStatusUpdate(byte id) {
            if (id == 10) {
                this.sheepTimer = 40;
            } else {
                super.handleStatusUpdate(id);
            }
        }

        protected void registerData() {
            super.registerData();
            this.dataManager.register(isSheared, (byte) 0);
        }


        @Override
        public RockySheepEntity.CustomEntity createChild(AgeableEntity ageable) {
            return (RockySheepEntity.CustomEntity) entity.create(this.world);
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
                    ret.add(new ItemStack(Blocks.GRAY_WOOL));
                }
            }
            this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
            return ret;
        }


        public void writeAdditional(CompoundNBT compound) {
            super.writeAdditional(compound);
            compound.putBoolean("Sheared", this.getSheared());
        }

        public void readAdditional(CompoundNBT compound) {
            super.readAdditional(compound);
            this.setSheared(compound.getBoolean("Sheared"));
        }


        public float getHeadRotationPointY(float p_70894_1_) {
            if (this.sheepTimer <= 0) {
                return 0.0F;
            } else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
                return 1.0F;
            } else {
                return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F : -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
            }
        }

        public float getHeadRotationAngleX(float p_70890_1_) {
            if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
                float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
                return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
            } else {
                return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * ((float) Math.PI / 180F);
            }
        }

        protected SoundEvent getAmbientSound() {
            return SoundEvents.ENTITY_SHEEP_AMBIENT;
        }

        protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
            return SoundEvents.ENTITY_SHEEP_HURT;
        }

        protected SoundEvent getDeathSound() {
            return SoundEvents.ENTITY_SHEEP_DEATH;
        }

        protected void playStepSound(BlockPos pos, BlockState blockIn) {
            this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.35F, 1.0F);
        }
    }
}
