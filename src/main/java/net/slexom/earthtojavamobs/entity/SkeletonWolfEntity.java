package net.slexom.earthtojavamobs.entity;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.slexom.earthtojavamobs.EarthtojavamobsModElements;
import net.slexom.earthtojavamobs.client.renderer.entity.SkeletonWolfRenderer;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

import javax.annotation.Nullable;
import java.text.MessageFormat;

@EarthtojavamobsModElements.ModElement.Tag
public class SkeletonWolfEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "skeleton_wolf";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public SkeletonWolfEntity(EarthtojavamobsModElements instance) {
        super(instance, 101);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER)
                .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
                .setCustomClientFactory(CustomEntity::new).size(0.6F, 0.85F).build(registryNameEntity))
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, 0xededed, 0xbababa, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.FOREST, BiomeSpawnHelper.TAIGA, BiomeSpawnHelper.SNOWY_TAIGA, BiomeSpawnHelper.GIANT_TAIGA);
                BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, 7, 2, 6);
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
            }
        });
    }

    @SubscribeEvent
    public void registerModel(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, SkeletonWolfRenderer::new);
    }

    public static class CustomEntity extends AbstractSkeletonEntity {
        protected static final DataParameter<Boolean> ANGRY = EntityDataManager.createKey(CustomEntity.class, DataSerializers.BOOLEAN);

        private float headRotationCourse;
        private float headRotationCourseOld;

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = (int) Math.ceil(Math.random() * 3);
            setNoAI(false);
        }

        protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, false));
        }

        protected void registerAttributes() {
            super.registerAttributes();
            this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.3F);
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
            this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0F);
        }

        protected SoundEvent getAmbientSound() {
            return SoundEvents.ENTITY_SKELETON_AMBIENT;
        }

        protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
            return SoundEvents.ENTITY_SKELETON_HURT;
        }

        protected SoundEvent getDeathSound() {
            return SoundEvents.ENTITY_SKELETON_DEATH;
        }

        protected SoundEvent getStepSound() {
            return SoundEvents.ENTITY_SKELETON_STEP;
        }

        public void updateRidden() {
            /* No rides for you, little wolf */
        }


        public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
            spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
            this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
            this.setCombatTask();
            this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficultyIn.getClampedAdditionalDifficulty());
            return spawnDataIn;
        }

        public void setCombatTask() {


        }

        public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        }


        protected void registerData() {
            super.registerData();
            this.dataManager.register(ANGRY, false);
        }

        public void writeAdditional(CompoundNBT compound) {
            super.writeAdditional(compound);
            compound.putBoolean("Angry", this.isAngry());
        }

        public void readAdditional(CompoundNBT compound) {
            super.readAdditional(compound);
            this.setAngry(compound.getBoolean("Angry"));
        }

        public float getTailRotation() {
            if (this.isAngry()) {
                return 1.5393804F;
            } else {
                return ((float) Math.PI / 5F);
            }
        }

        public boolean isAngry() {
            return this.dataManager.get(ANGRY);
        }

        public void setAngry(boolean angry) {
            this.dataManager.set(ANGRY, angry);
        }

        public void handleStatusUpdate(byte id) {
            super.handleStatusUpdate(id);
        }

        public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
            super.setAttackTarget(entitylivingbaseIn);
            this.setAngry(entitylivingbaseIn != null);
        }

        public void livingTick() {
            super.livingTick();
            if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
                this.setAngry(false);
            }
        }

        public void tick() {
            super.tick();
            if (this.isAlive()) {
                this.headRotationCourseOld = this.headRotationCourse;
                this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
            }
        }

        public float getInterestedAngle(float p_70917_1_) {
            return MathHelper.lerp(p_70917_1_, this.headRotationCourseOld, this.headRotationCourse) * 0.15F * (float) Math.PI;
        }

    }
}
