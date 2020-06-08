
package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
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
import net.slexom.earthtojavamobs.client.renderer.entity.E2JChickenRenderer;

import java.text.MessageFormat;

@EarthtojavamobsModElements.ModElement.Tag
public class MidnightChickenEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "midnight_chicken";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public MidnightChickenEntity(EarthtojavamobsModElements instance) {
        super(instance, 12);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.7f)).build(registryNameEntity)
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, -16382709, -15261094, new Item.Properties().group(ItemGroup.MISC))
                .setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    boolean biomeCriteria = false;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("forest")))
                        biomeCriteria = true;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("dark_forest")))
                        biomeCriteria = true;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("dark_forest_hills")))
                        biomeCriteria = true;
                    if (!biomeCriteria)
                        continue;
                    biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 11, 1, 4));
                }
                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        AnimalEntity::canAnimalSpawn);
            }
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new E2JChickenRenderer(renderManager, registryNameEntity));
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

    }
}
