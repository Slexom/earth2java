
package net.mcreator.earthtojavamobs.entity;

import net.mcreator.earthtojavamobs.EarthtojavamobsModElements;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.entity.*;
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

@EarthtojavamobsModElements.ModElement.Tag
public class StormyChickenEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;

    public StormyChickenEntity(EarthtojavamobsModElements instance) {
        super(instance, 12);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.7f)).build("stormy_chicken")
                .setRegistryName("stormy_chicken");
        elements.entities.add(() -> entity);
        elements.items.add(
                () -> new SpawnEggItem(entity, -12704475, -4144960, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("stormy_chicken"));
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
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("river")))
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
    @OnlyIn(Dist.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new ChickenRenderer(renderManager) {
            @Override
            public ResourceLocation getEntityTexture(ChickenEntity entity) {
                return new ResourceLocation("earthtojavamobs:textures/texture_stormy_chicken.png");
            }
        });
    }

    public static class CustomEntity extends ChickenEntity {
        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = 0;
            setNoAI(false);
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
        }

        @Override
        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        protected void registerAttributes() {
            super.registerAttributes();
        }

        @Override
        public ChickenEntity createChild(AgeableEntity ageable) {
            return (CustomEntity) entity.create(this.world);
        }

    }
}
