
package net.mcreator.earthtojavamobs.entity;

import net.mcreator.earthtojavamobs.EarthtojavamobsModElements;
import net.mcreator.earthtojavamobs.client.renderer.entity.WoolyCowRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

@EarthtojavamobsModElements.ModElement.Tag
public class WoolyCowEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;

    public WoolyCowEntity(EarthtojavamobsModElements instance) {
        super(instance, 2);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.9f, 1.4f)).build("wooly_cow")
                .setRegistryName("wooly_cow");
        elements.entities.add(() -> entity);
        elements.items
                .add(() -> new SpawnEggItem(entity, -3394816, -26317, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("wooly_cow"));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            boolean biomeCriteria = false;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("taiga")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("taiga_hills")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_taiga")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("snowy_taiga_hills")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("giant_tree_taiga")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("giant_tree_taiga_hills")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("taiga_mountains")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("giant_spruce_taiga")))
                biomeCriteria = true;
            if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("giant_spruce_taiga_hills")))
                biomeCriteria = true;
            if (!biomeCriteria)
                continue;
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 5, 1, 3));
        }
        EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::canAnimalSpawn);
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new WoolyCowRenderer(renderManager) {

        });
    }

    public static class CustomEntity extends CowEntity implements net.minecraftforge.common.IShearable {
        private static final DataParameter<Byte> isSheared = EntityDataManager.createKey(WoolyCowEntity.CustomEntity.class, DataSerializers.BYTE);

        private int shearTimer;
        private EatGrassGoal eatGrassGoal;

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<CustomEntity> type, World world) {
            super(type, world);
            experienceValue = 5;
            setNoAI(false);
        }

        @Override
        protected void registerGoals() {
            super.registerGoals();
            this.eatGrassGoal = new EatGrassGoal(this);
            this.goalSelector.addGoal(5, this.eatGrassGoal);
        }

        protected void updateAITasks() {
            this.shearTimer = this.eatGrassGoal.getEatingGrassTimer();
            super.updateAITasks();
        }

        public void livingTick() {
            if (this.world.isRemote) {
                this.shearTimer = Math.max(0, this.shearTimer - 1);
            }
            super.livingTick();
        }

        /**
         * Handler for {@link World#setEntityState}
         */
        public void handleStatusUpdate(byte id) {
            if (id == 10) {
                this.shearTimer = 40;
            } else {
                super.handleStatusUpdate(id);
            }
        }

        protected void registerData() {
            super.registerData();
            this.dataManager.register(isSheared, (byte) 0);
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
                    ret.add(new ItemStack(Blocks.BROWN_WOOL));
                }
            }
            this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
            return ret;
        }

        @Override
        public CowEntity createChild(AgeableEntity ageable) {
            return (CustomEntity) entity.create(this.world);
        }

        public void writeAdditional(CompoundNBT compound) {
            super.writeAdditional(compound);
            compound.putBoolean("Sheared", this.getSheared());
        }

        public void readAdditional(CompoundNBT compound) {
            super.readAdditional(compound);
            this.setSheared(compound.getBoolean("Sheared"));
        }

    }
}
