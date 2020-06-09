
package net.slexom.earthtojavamobs.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShapes;
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
import net.slexom.earthtojavamobs.client.renderer.entity.GlowSquidRenderer;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

import java.text.MessageFormat;

@EarthtojavamobsModElements.ModElement.Tag
public class GlowSquidEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "glow_squid";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public GlowSquidEntity(EarthtojavamobsModElements instance) {
        super(instance, 121);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.WATER_CREATURE)
                .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
                .size(0.6f, 1.8f)).build(registryNameEntity).setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items
                .add(() -> new SpawnEggItem(entity, -16165290, -8323136, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.OCEAN, BiomeSpawnHelper.RIVER, BiomeSpawnHelper.SWAMP);
                BiomeSpawnHelper.setWaterCreatureSpawnBiomes(entity, spawnBiomes, 6, 1, 4);
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        SquidEntity::func_223365_b);
            }
        });

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
            GlowSquidRenderer customRender = new GlowSquidRenderer(renderManager);
            customRender.addLayer(new GlowingLayer<>(customRender));
            return customRender;
        });
    }

    public static class CustomEntity extends SquidEntity {

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = (int) Math.ceil(Math.random() * 3);
            setNoAI(false);
            this.moveController = new MovementController(this) {
                @Override
                public void tick() {
                    if (CustomEntity.this.areEyesInFluid(FluidTags.WATER))
                        CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, 0.005, 0));
                    if (this.action == MovementController.Action.MOVE_TO && !CustomEntity.this.getNavigator().noPath()) {
                        double dx = this.posX - CustomEntity.this.getPosX();
                        double dy = this.posY - CustomEntity.this.getPosY();
                        double dz = this.posZ - CustomEntity.this.getPosZ();
                        dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
                        CustomEntity.this.rotationYaw = this.limitAngle(CustomEntity.this.rotationYaw,
                                (float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
                        CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
                        CustomEntity.this.setAIMoveSpeed(MathHelper.lerp(0.125f, CustomEntity.this.getAIMoveSpeed(),
                                (float) (this.speed * CustomEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue())));
                        CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, CustomEntity.this.getAIMoveSpeed() * dy * 0.1, 0));
                    } else {
                        CustomEntity.this.setAIMoveSpeed(0);
                    }
                }
            };
            this.navigator = new SwimmerPathNavigator(this, this.world);
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1, 40));
            this.goalSelector.addGoal(2, new PanicGoal(this, 1.2));
        }

        @Override
        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        public net.minecraft.util.SoundEvent getAmbientSound() {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.ambient"));
        }

        @Override
        public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.hurt"));
        }

        @Override
        public net.minecraft.util.SoundEvent getDeathSound() {
            return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.death"));
        }

        @Override
        protected float getSoundVolume() {
            return 1.0F;
        }

        @Override
        protected void registerAttributes() {
            super.registerAttributes();
        }

        @Override
        public boolean canBreatheUnderwater() {
            return true;
        }

        @Override
        public boolean isNotColliding(IWorldReader worldreader) {
            return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
        }

        @Override
        public boolean isPushedByWater() {
            return false;
        }

    }


    private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
        public GlowingLayer(IEntityRenderer<T, M> er) {
            super(er);
        }

        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
                           float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            IVertexBuilder ivertexbuilder = bufferIn
                    .getBuffer(RenderType.getEyes(new ResourceLocation("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_glow_layer.png")));
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }
}
