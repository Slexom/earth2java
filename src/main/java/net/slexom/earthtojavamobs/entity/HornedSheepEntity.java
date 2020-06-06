package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsModElements;
import net.slexom.earthtojavamobs.client.renderer.entity.HornedSheepRenderer;

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.UUID;

@EarthtojavamobsModElements.ModElement.Tag
public class HornedSheepEntity extends EarthtojavamobsModElements.ModElement {
    public static EntityType entity = null;
    private static final String registryNameEntity = "horned_sheep";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

    public HornedSheepEntity(EarthtojavamobsModElements instance) {
        super(instance, 34);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @Override
    public void initElements() {
        entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.9f, 1.3f)).build(registryNameEntity)
                .setRegistryName(registryNameEntity);
        elements.entities.add(() -> entity);
        elements.items.add(
                () -> new SpawnEggItem(entity, 0xd6d1cc, 0x453d3b, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
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
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new HornedSheepRenderer(renderManager) {
        });
    }

    public static class CustomEntity extends SheepEntity {
        private EatGrassGoal eatGrassGoal;
        private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.createKey(CustomEntity.class, DataSerializers.BYTE);
        private static final DataParameter<Integer> ANGER_TIME = EntityDataManager.createKey(CustomEntity.class, DataSerializers.VARINT);
        private UUID lastHurtBy;

        public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
            this(entity, world);
        }

        public CustomEntity(EntityType<? extends CustomEntity> type, World world) {
            super(type, world);
            experienceValue = (int) Math.ceil(Math.random() * 3);
            ;
            setNoAI(false);
        }

        protected void registerData() {
            super.registerData();
            this.dataManager.register(DATA_FLAGS_ID, (byte) 0);
            this.dataManager.register(ANGER_TIME, 0);
        }

        protected void registerGoals() {
            this.eatGrassGoal = new EatGrassGoal(this);
            this.goalSelector.addGoal(0, new CustomEntity.ChargeGoal(this, (double) 1.4F, true));
            this.goalSelector.addGoal(1, new SwimGoal(this));
            this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
            this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(Items.WHEAT), false));
            this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
            this.goalSelector.addGoal(5, this.eatGrassGoal);
            this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
            this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
            this.targetSelector.addGoal(1, (new AngerGoal(this)).setCallsForHelp());
            this.targetSelector.addGoal(2, new CustomEntity.AttackPlayerGoal(this));
        }

        public void writeAdditional(CompoundNBT compound) {
            super.writeAdditional(compound);
            compound.putInt("Anger", this.getAnger());
            if (this.lastHurtBy != null) {
                compound.putString("HurtBy", this.lastHurtBy.toString());
            } else {
                compound.putString("HurtBy", "");
            }
        }

        public void readAdditional(CompoundNBT compound) {
            super.readAdditional(compound);
            String s = compound.getString("HurtBy");
            if (!s.isEmpty()) {
                this.lastHurtBy = UUID.fromString(s);
                PlayerEntity playerentity = this.world.getPlayerByUuid(this.lastHurtBy);
                this.setRevengeTarget(playerentity);
                if (playerentity != null) {
                    this.attackingPlayer = playerentity;
                    this.recentlyHit = this.getRevengeTimer();
                }
            }
        }

        public boolean attackEntityAsMob(Entity entityIn) {
            boolean flag = entityIn.attackEntityFrom(DamageSource.func_226252_a_(this), (float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
            if (flag) {
                this.applyEnchantments(this, entityIn);
            }
            return flag;
        }

        public void setRevengeTarget(@Nullable LivingEntity livingBase) {
            super.setRevengeTarget(livingBase);
            if (livingBase != null) {
                this.lastHurtBy = livingBase.getUniqueID();
            }
        }

        protected void updateAITasks() {
            if (this.isAngry()) {
                int i = this.getAnger();
                this.setAnger(i - 1);
                LivingEntity livingentity = this.getAttackTarget();
                if (i == 0 && livingentity != null) {
                    this.setSheepAttacker(livingentity);
                }
            }
        }

        public boolean isAngry() {
            return this.getAnger() > 0;
        }

        private int getAnger() {
            return this.dataManager.get(ANGER_TIME);
        }

        private void setAnger(int angerTime) {
            this.dataManager.set(ANGER_TIME, angerTime);
        }

        public void livingTick() {
            super.livingTick();
            if (!this.world.isRemote) {
                boolean flag = this.isAngry() && this.getAttackTarget() != null && this.getAttackTarget().getDistanceSq(this) < 4.0D;
                this.setNearTarget(flag);
            }
        }

        private boolean isNearTarget() {
            return this.getSheepFlag();
        }

        private void setNearTarget(boolean p_226452_1_) {
            this.setSheepFlag(p_226452_1_);
        }

        private boolean isTooFar(BlockPos pos) {
            return !this.isWithinDistance(pos);
        }

        private void setSheepFlag(boolean p_226404_2_) {
            if (p_226404_2_) {
                this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) | 2));
            } else {
                this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) & ~2));
            }
        }

        private boolean getSheepFlag() {
            return (this.dataManager.get(DATA_FLAGS_ID) & 2) != 0;
        }

        protected void registerAttributes() {
            super.registerAttributes();
            this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
            this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        }

        public ResourceLocation getLootTable() {
            if (this.getSheared()) {
                return this.getType().getLootTable();
            } else {
                switch (this.getFleeceColor()) {
                    case WHITE:
                    default:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/white");
                    case ORANGE:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/orange");
                    case MAGENTA:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/magenta");
                    case LIGHT_BLUE:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/light_blue");
                    case YELLOW:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/yellow");
                    case LIME:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/lime");
                    case PINK:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/pink");
                    case GRAY:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/gray");
                    case LIGHT_GRAY:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/light_gray");
                    case CYAN:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/cyan");
                    case PURPLE:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/purple");
                    case BLUE:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/blue");
                    case BROWN:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/brown");
                    case GREEN:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/green");
                    case RED:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/red");
                    case BLACK:
                        return new ResourceLocation("earthtojavamobs", "entities/horned_sheep/black");
                }
            }
        }


        @Override
        public CustomEntity createChild(AgeableEntity ageable) {
            return (CustomEntity) entity.create(this.world);
        }

        public boolean setSheepAttacker(Entity attacker) {
            this.setAnger(400 + this.rand.nextInt(400));
            if (attacker instanceof LivingEntity) {
                this.setRevengeTarget((LivingEntity) attacker);
            }
            return true;
        }

        public boolean attackEntityFrom(DamageSource source, float amount) {
            if (this.isInvulnerableTo(source)) {
                return false;
            } else {
                Entity entity = source.getTrueSource();
                if (!this.world.isRemote && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity) && !this.isAIDisabled()) {

                    this.setSheepAttacker(entity);
                }
                return super.attackEntityFrom(source, amount);
            }
        }

        public CreatureAttribute getCreatureAttribute() {
            return CreatureAttribute.UNDEFINED;
        }

        private boolean isWithinDistance(BlockPos pos) {
            return pos.withinDistance(new BlockPos(this), (double) 48);
        }

        static class AngerGoal extends HurtByTargetGoal {
            AngerGoal(CustomEntity sheepIn) {
                super(sheepIn);
            }

            protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
                if (mobIn instanceof CustomEntity && this.goalOwner.canEntityBeSeen(targetIn) && ((CustomEntity) mobIn).setSheepAttacker(targetIn)) {
                    mobIn.setAttackTarget(targetIn);
                }

            }
        }

        static class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
            AttackPlayerGoal(CustomEntity beeIn) {
                super(beeIn, PlayerEntity.class, true);
            }

            public boolean shouldExecute() {
                return this.canCharge() && super.shouldExecute();
            }

            public boolean shouldContinueExecuting() {
                boolean flag = this.canCharge();
                if (flag && this.goalOwner.getAttackTarget() != null) {
                    return super.shouldContinueExecuting();
                } else {
                    this.target = null;
                    return false;
                }
            }

            private boolean canCharge() {
                CustomEntity sheepEntity = (CustomEntity) this.goalOwner;
                return sheepEntity.isAngry();
            }
        }

        class ChargeGoal extends MeleeAttackGoal {
            ChargeGoal(CreatureEntity creatureIn, double speedIn, boolean useLongMemory) {
                super(creatureIn, speedIn, useLongMemory);
            }

            public boolean shouldExecute() {
                return super.shouldExecute() && CustomEntity.this.isAngry();
            }

            public boolean shouldContinueExecuting() {
                return super.shouldContinueExecuting() && CustomEntity.this.isAngry();
            }
        }

    }
}
