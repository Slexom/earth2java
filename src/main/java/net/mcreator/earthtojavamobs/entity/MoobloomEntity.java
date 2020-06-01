
package net.mcreator.earthtojavamobs.entity;

import net.mcreator.earthtojavamobs.EarthtojavamobsModElements;
import net.mcreator.earthtojavamobs.client.renderer.entity.MoobloomRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
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
public class MoobloomEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;

    public MoobloomEntity(EarthtojavamobsModElements instance) {
        super(instance, 9);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.9f, 1.4f)).build("moobloom")
                .setRegistryName("moobloom");
        elements.entities.add(() -> entity);
        elements.items.add(() -> new SpawnEggItem(entity, -13056, -76, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("moobloom"));
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
    @OnlyIn(Dist.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new MoobloomRenderer(renderManager) {

        });
    }

    public static class CustomEntity extends CowEntity implements net.minecraftforge.common.IShearable {
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
            this.goalSelector.addGoal(8, new CustomEntity.PlaceBlockGoal(this));
        }

        @Override
        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
            super.dropSpecialItems(source, looting, recentlyHitIn);
        }

        @Override
        public boolean isShearable(ItemStack item, net.minecraft.world.IWorldReader world, net.minecraft.util.math.BlockPos pos) {
            return !this.isChild();
        }

        @Override
        public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IWorld world, net.minecraft.util.math.BlockPos pos, int fortune) {
            java.util.List<ItemStack> ret = new java.util.ArrayList<>();
            this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            if (!this.world.isRemote) {
                this.remove();
                CowEntity cowentity = EntityType.COW.create(this.world);
                cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
                cowentity.setHealth(this.getHealth());
                cowentity.renderYawOffset = this.renderYawOffset;
                if (this.hasCustomName()) {
                    cowentity.setCustomName(this.getCustomName());
                    cowentity.setCustomNameVisible(this.isCustomNameVisible());
                }
                this.world.addEntity(cowentity);
                for (int i = 0; i < 5; ++i) {
                    ret.add(new ItemStack(Blocks.DANDELION));
                }
                this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
            }
            return ret;
        }

        @Override
        public CowEntity createChild(AgeableEntity ageable) {
            return (MoobloomEntity.CustomEntity) entity.create(this.world);
        }

        static class PlaceBlockGoal extends Goal {
            private final CustomEntity moobloom;

            public PlaceBlockGoal(CustomEntity p_i45843_1_) {
                this.moobloom = p_i45843_1_;
            }

            public boolean shouldExecute() {
                return this.moobloom.getRNG().nextInt(2000) == 0;
            }

            public boolean canPlace(IWorldReader world, BlockState target, BlockPos targetPos, BlockState downTarget, BlockPos downTargetPos) {
                return !downTarget.isAir(world, downTargetPos) && downTarget.isCollisionShapeOpaque(world, downTargetPos) && target.isValidPosition(world, targetPos);
            }

            public void tick() {
                IWorld iworld = this.moobloom.world;
                int i = MathHelper.floor(this.moobloom.getPosX());
                int j = MathHelper.floor(this.moobloom.getPosY());
                int k = MathHelper.floor(this.moobloom.getPosZ());
                Block dandelion = Blocks.DANDELION;
                BlockPos blockPos = new BlockPos(i, j, k);
                BlockState blockState = dandelion.getDefaultState();
                BlockPos blockDownPos = blockPos.down();
                BlockState blockDownState = iworld.getBlockState(blockDownPos);
                if (canPlace(iworld, blockState, blockPos, blockDownState, blockDownPos) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(moobloom, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockPos, blockDownState), net.minecraft.util.Direction.UP)) {
                    iworld.destroyBlock(blockPos, false);
                    iworld.setBlockState(blockPos, blockState, 3);
                }

            }

        }
    }
}
