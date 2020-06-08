
package net.slexom.earthtojavamobs.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsModElements;
import net.slexom.earthtojavamobs.client.renderer.entity.CluckshroomRenderer;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

import java.text.MessageFormat;

@EarthtojavamobsModElements.ModElement.Tag
public class CluckshroomEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "cluckshroom";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public CluckshroomEntity(EarthtojavamobsModElements instance) {
        super(instance, 14);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.7f)).build(registryNameEntity)
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, -65536, -1, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.MUSHROOM_FIELDS);
                BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, 8, 2, 4);
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        AnimalEntity::canAnimalSpawn);
            }
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new CluckshroomRenderer(renderManager) {
        });
    }

    public static class CustomEntity extends ChickenEntity {

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = (int) Math.ceil(Math.random() * 3);
            setNoAI(false);
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(1, new RestrictSunGoal(this));
            this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.0D));
            this.goalSelector.addGoal(3, new CustomEntity.PlaceBlockGoal(this));
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        public net.minecraft.util.SoundEvent getAmbientSound() {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.ambient"));
        }

        @Override
        public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.hurt"));
        }

        @Override
        public net.minecraft.util.SoundEvent getDeathSound() {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.death"));
        }

        @Override
        protected float getSoundVolume() {
            return 1.0F;
        }

        @Override
        public ChickenEntity createChild(AgeableEntity ageable) {
            return (CustomEntity) entity.create(this.world);
        }


        static class PlaceBlockGoal extends Goal {
            private final CustomEntity cluckshroom;

            public PlaceBlockGoal(CustomEntity p_i45843_1_) {
                this.cluckshroom = p_i45843_1_;
            }

            public boolean shouldExecute() {
                return this.cluckshroom.getRNG().nextInt(2000) == 0;
            }

            public boolean canPlace(IWorldReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
                return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
            }

            public void tick() {
                IWorld iworld = this.cluckshroom.world;
                int i = MathHelper.floor(this.cluckshroom.getPosX());
                int j = MathHelper.floor(this.cluckshroom.getPosY());
                int k = MathHelper.floor(this.cluckshroom.getPosZ());
                Block mushroom = Blocks.RED_MUSHROOM;
                BlockPos blockPos = new BlockPos(i, j, k);
                BlockState blockState = mushroom.getDefaultState();
                BlockPos blockDownPos = blockPos.down();
                BlockState blockDownState = iworld.getBlockState(blockDownPos);
                if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(cluckshroom, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockPos, blockDownState), net.minecraft.util.Direction.UP)) {
                    iworld.destroyBlock(blockPos, false);
                    iworld.setBlockState(blockPos, blockState, 3);
                }
            }


        }

    }
}
