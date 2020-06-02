
package net.slexom.earthtojavamobs.entity;

import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
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
import net.slexom.earthtojavamobs.client.renderer.entity.TropicalSlimeRenderer;

@EarthtojavamobsModElements.ModElement.Tag
public class TropicalSlimeEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;

    public TropicalSlimeEntity(EarthtojavamobsModElements instance) {
        super(instance, 12);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.7f)).build("tropical_slime")
                .setRegistryName("tropical_slime");
        elements.entities.add(() -> entity);
        elements.items.add(
                () -> new SpawnEggItem(entity, 0x0e496e, 0x8ed3ff, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("tropical_slime"));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    boolean biomeCriteria = false;
                    if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("beach")))
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
        RenderingRegistry.registerEntityRenderingHandler(entity, TropicalSlimeRenderer::new);
    }

    public static class CustomEntity extends SlimeEntity {
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
            return super.getCreatureAttribute();
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        protected void registerAttributes() {
            super.registerAttributes();
        }

        public boolean processInteract(PlayerEntity player, Hand hand) {
            ItemStack itemstack = player.getHeldItem(hand);
            if (itemstack.getItem() == Items.BUCKET && !player.abilities.isCreativeMode && !this.isChild()) {
                if (!this.world.isRemote) {
                    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 0.0D, 0.0D, 0.0D);
                    this.remove();
                    player.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1.0F, 1.0F);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.setHeldItem(hand, new ItemStack(Items.TROPICAL_FISH_BUCKET));
                    } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.TROPICAL_FISH_BUCKET))) {
                        player.dropItem(new ItemStack(Items.TROPICAL_FISH_BUCKET), false);
                    }
                    return true;
                } else {
                    return super.processInteract(player, hand);
                }

            } else {
                return super.processInteract(player, hand);
            }
        }

        protected IParticleData getSquishParticle() {
            return ParticleTypes.DRIPPING_WATER;
        }

    }
}
