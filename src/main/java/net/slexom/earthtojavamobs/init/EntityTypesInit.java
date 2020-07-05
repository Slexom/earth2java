package net.slexom.earthtojavamobs.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.EarthtojavamobsMod;
import net.slexom.earthtojavamobs.entity.monster.BoneSpiderEntity;
import net.slexom.earthtojavamobs.entity.monster.SkeletonWolfEntity;
import net.slexom.earthtojavamobs.entity.passive.*;
import net.slexom.earthtojavamobs.entity.projectile.BoneShardEntity;
import net.slexom.earthtojavamobs.entity.projectile.MelonSeedProjectileEntity;

public class EntityTypesInit {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, EarthtojavamobsMod.MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String BONE_SHARD_REGISTRY_NAME = "bone_shard";
    public static final RegistryObject<EntityType<BoneShardEntity>> BONE_SHARD_REGISTRY_OBJECT = ENTITY_TYPES.register(
            BONE_SHARD_REGISTRY_NAME,
            () -> EntityType.Builder.<BoneShardEntity>create(BoneShardEntity::new, EntityClassification.MISC)
                    .size(0.25F, 0.25F)
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, BONE_SHARD_REGISTRY_NAME).toString())
    );

    public static final String MELON_SEED_PROJECTILE_REGISTRY_NAME = "melon_seed_projectile";
    public static final RegistryObject<EntityType<MelonSeedProjectileEntity>> MELON_SEED_PROJECTILE_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MELON_SEED_PROJECTILE_REGISTRY_NAME,
            () -> EntityType.Builder.<MelonSeedProjectileEntity>create(MelonSeedProjectileEntity::new, EntityClassification.MISC)
                    .size(0.25F, 0.25F)
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MELON_SEED_PROJECTILE_REGISTRY_NAME).toString())
    );

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String AMBER_CHICKEN_REGISTRY_NAME = "amber_chicken";
    public static final RegistryObject<EntityType<AmberChickenEntity>> AMBER_CHICKEN_REGISTRY_OBJECT = ENTITY_TYPES.register(
            AMBER_CHICKEN_REGISTRY_NAME,
            () -> EntityType.Builder.create(AmberChickenEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, AMBER_CHICKEN_REGISTRY_NAME).toString())
    );

    public static final String ASHEN_COW_REGISTRY_NAME = "ashen_cow";
    public static final RegistryObject<EntityType<AshenCowEntity>> ASHEN_COW_REGISTRY_OBJECT = ENTITY_TYPES.register(
            ASHEN_COW_REGISTRY_NAME,
            () -> EntityType.Builder.create(AshenCowEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, ASHEN_COW_REGISTRY_NAME).toString())
    );

    public static final String CLUCKSHROOM_REGISTRY_NAME = "cluckshroom";
    public static final RegistryObject<EntityType<CluckshroomEntity>> CLUCKSHROOM_REGISTRY_OBJECT = ENTITY_TYPES.register(
            CLUCKSHROOM_REGISTRY_NAME,
            () -> EntityType.Builder.create(CluckshroomEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, CLUCKSHROOM_REGISTRY_NAME).toString())
    );

    public static final String FLECKED_SHEEP_REGISTRY_NAME = "flecked_sheep";
    public static final RegistryObject<EntityType<FleckedSheepEntity>> FLECKED_SHEEP_REGISTRY_OBJECT = ENTITY_TYPES.register(
            FLECKED_SHEEP_REGISTRY_NAME,
            () -> EntityType.Builder.create(FleckedSheepEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, FLECKED_SHEEP_REGISTRY_NAME).toString())
    );

    public static final String GLOW_SQUID_REGISTRY_NAME = "glow_squid";
    public static final RegistryObject<EntityType<GlowSquidEntity>> GLOW_SQUID_REGISTRY_OBJECT = EntityTypesInit.ENTITY_TYPES.register(
            GLOW_SQUID_REGISTRY_NAME,
            () -> EntityType.Builder.create(GlowSquidEntity::new, EntityClassification.WATER_CREATURE)
                    .size(EntityType.SQUID.getWidth(), EntityType.SQUID.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, GLOW_SQUID_REGISTRY_NAME).toString())
    );

    public static final String HORNED_SHEEP_REGISTRY_NAME = "horned_sheep";
    public static final RegistryObject<EntityType<HornedSheepEntity>> HORNED_SHEEP_REGISTRY_OBJECT = ENTITY_TYPES.register(
            HORNED_SHEEP_REGISTRY_NAME,
            () -> EntityType.Builder.create(HornedSheepEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, HORNED_SHEEP_REGISTRY_NAME).toString())
    );

    public static final String INKY_SHEEP_REGISTRY_NAME = "inky_sheep";
    public static final RegistryObject<EntityType<InkySheepEntity>> INKY_SHEEP_REGISTRY_OBJECT = ENTITY_TYPES.register(
            INKY_SHEEP_REGISTRY_NAME,
            () -> EntityType.Builder.create(InkySheepEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, INKY_SHEEP_REGISTRY_NAME).toString())
    );

    public static final String MIDNIGHT_CHICKEN_REGISTRY_NAME = "midnight_chicken";
    public static final RegistryObject<EntityType<MidnightChickenEntity>> MIDNIGHT_CHICKEN_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MIDNIGHT_CHICKEN_REGISTRY_NAME,
            () -> EntityType.Builder.create(MidnightChickenEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MIDNIGHT_CHICKEN_REGISTRY_NAME).toString())
    );

    public static final String MOOBLOOM_REGISTRY_NAME = "moobloom";
    public static final RegistryObject<EntityType<MoobloomEntity>> MOOBLOOM_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MOOBLOOM_REGISTRY_NAME,
            () -> EntityType.Builder.create(MoobloomEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MOOBLOOM_REGISTRY_NAME).toString())
    );

    public static final String MUDDY_PIG_REGISTRY_NAME = "muddy_pig";
    public static final RegistryObject<EntityType<MuddyPigEntity>> MUDDY_PIG_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MUDDY_PIG_REGISTRY_NAME,
            () -> EntityType.Builder.create(MuddyPigEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MUDDY_PIG_REGISTRY_NAME).toString())
    );

    public static final String PALE_PIG_REGISTRY_NAME = "pale_pig";
    public static final RegistryObject<EntityType<PalePigEntity>> PALE_PIG_REGISTRY_OBJECT = ENTITY_TYPES.register(
            PALE_PIG_REGISTRY_NAME,
            () -> EntityType.Builder.create(PalePigEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, PALE_PIG_REGISTRY_NAME).toString())
    );

    public static final String PIEBALD_PIG_REGISTRY_NAME = "piebald_pig";
    public static final RegistryObject<EntityType<PiebaldPigEntity>> PIEBALD_PIG_REGISTRY_OBJECT = ENTITY_TYPES.register(
            PIEBALD_PIG_REGISTRY_NAME,
            () -> EntityType.Builder.create(PiebaldPigEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, PIEBALD_PIG_REGISTRY_NAME).toString())
    );

    public static final String ROCKY_SHEEP_REGISTRY_NAME = "rocky_sheep";
    public static final RegistryObject<EntityType<RockySheepEntity>> ROCKY_SHEEP_REGISTRY_OBJECT = ENTITY_TYPES.register(
            ROCKY_SHEEP_REGISTRY_NAME,
            () -> EntityType.Builder.create(RockySheepEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, ROCKY_SHEEP_REGISTRY_NAME).toString())
    );

    public static final String SKELETON_WOLF_REGISTRY_NAME = "skeleton_wolf";
    public static final RegistryObject<EntityType<SkeletonWolfEntity>> SKELETON_WOLF_REGISTRY_OBJECT = ENTITY_TYPES.register(
            SKELETON_WOLF_REGISTRY_NAME,
            () -> EntityType.Builder.create(SkeletonWolfEntity::new, EntityClassification.MONSTER)
                    .size(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, SKELETON_WOLF_REGISTRY_NAME).toString())
    );

    public static final String SPOTTED_PIG_REGISTRY_NAME = "spotted_pig";
    public static final RegistryObject<EntityType<SpottedPigEntity>> SPOTTED_PIG_REGISTRY_OBJECT = ENTITY_TYPES.register(
            SPOTTED_PIG_REGISTRY_NAME,
            () -> EntityType.Builder.create(SpottedPigEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, SPOTTED_PIG_REGISTRY_NAME).toString())
    );

    public static final String STORMY_CHICKEN_REGISTRY_NAME = "stormy_chicken";
    public static final RegistryObject<EntityType<StormyChickenEntity>> STORMY_CHICKEN_REGISTRY_OBJECT = ENTITY_TYPES.register(
            STORMY_CHICKEN_REGISTRY_NAME,
            () -> EntityType.Builder.create(StormyChickenEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, STORMY_CHICKEN_REGISTRY_NAME).toString())
    );

    public static final String SUNSET_COW_REGISTRY_NAME = "sunset_cow";
    public static final RegistryObject<EntityType<SunsetCowEntity>> SUNSET_COW_REGISTRY_OBJECT = ENTITY_TYPES.register(
            SUNSET_COW_REGISTRY_NAME,
            () -> EntityType.Builder.create(SunsetCowEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, SUNSET_COW_REGISTRY_NAME).toString())
    );

    public static final String TROPICAL_SLIME_REGISTRY_NAME = "tropical_slime";
    public static final RegistryObject<EntityType<TropicalSlimeEntity>> TROPICAL_SLIME_REGISTRY_OBJECT = ENTITY_TYPES.register(
            TROPICAL_SLIME_REGISTRY_NAME,
            () -> EntityType.Builder.create(TropicalSlimeEntity::new, EntityClassification.CREATURE)
                    .size(2.0F, 2.0F)
                    .immuneToFire()
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, TROPICAL_SLIME_REGISTRY_NAME).toString())
    );

    public static final String WOOLY_COW_REGISTRY_NAME = "wooly_cow";
    public static final RegistryObject<EntityType<WoolyCowEntity>> WOOLY_COW_REGISTRY_OBJECT = ENTITY_TYPES.register(
            WOOLY_COW_REGISTRY_NAME,
            () -> EntityType.Builder.create(WoolyCowEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, WOOLY_COW_REGISTRY_NAME).toString())
    );

    public static final String VESTED_RABBIT_REGISTRY_NAME = "vested_rabbit";
    public static final RegistryObject<EntityType<VestedRabbitEntity>> VESTED_RABBIT_REGISTRY_OBJECT = ENTITY_TYPES.register(
            VESTED_RABBIT_REGISTRY_NAME,
            () -> EntityType.Builder.create(VestedRabbitEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, VESTED_RABBIT_REGISTRY_NAME).toString())
    );

    public static final String HARELEQUIN_RABBIT_REGISTRY_NAME = "harelequin_rabbit";
    public static final RegistryObject<EntityType<HarelequinRabbitEntity>> HARELEQUIN_RABBIT_REGISTRY_OBJECT = ENTITY_TYPES.register(
            HARELEQUIN_RABBIT_REGISTRY_NAME,
            () -> EntityType.Builder.create(HarelequinRabbitEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, HARELEQUIN_RABBIT_REGISTRY_NAME).toString())
    );

    public static final String MUDDY_FOOT_RABBIT_REGISTRY_NAME = "muddy_foot_rabbit";
    public static final RegistryObject<EntityType<MuddyFootRabbitEntity>> MUDDY_FOOT_RABBIT_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MUDDY_FOOT_RABBIT_REGISTRY_NAME,
            () -> EntityType.Builder.create(MuddyFootRabbitEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MUDDY_FOOT_RABBIT_REGISTRY_NAME).toString())
    );

    public static final String FURNACE_GOLEM_REGISTRY_NAME = "furnace_golem";
    public static final RegistryObject<EntityType<FurnaceGolemEntity>> FURNACE_GOLEM_REGISTRY_OBJECT = ENTITY_TYPES.register(
            FURNACE_GOLEM_REGISTRY_NAME,
            () -> EntityType.Builder.create(FurnaceGolemEntity::new, EntityClassification.MISC)
                    .size(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
                    .immuneToFire()
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, FURNACE_GOLEM_REGISTRY_NAME).toString())
    );

    public static final String MELON_GOLEM_REGISTRY_NAME = "melon_golem";
    public static final RegistryObject<EntityType<MelonGolemEntity>> MELON_GOLEM_REGISTRY_OBJECT = ENTITY_TYPES.register(
            MELON_GOLEM_REGISTRY_NAME,
            () -> EntityType.Builder.create(MelonGolemEntity::new, EntityClassification.MISC)
                    .size(EntityType.SNOW_GOLEM.getWidth(), EntityType.SNOW_GOLEM.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, MELON_GOLEM_REGISTRY_NAME).toString())
    );

    public static final String ALBINO_COW_REGISTRY_NAME = "albino_cow";
    public static final RegistryObject<EntityType<AlbinoCowEntity>> ALBINO_COW_REGISTRY_OBJECT = ENTITY_TYPES.register(
            ALBINO_COW_REGISTRY_NAME,
            () -> EntityType.Builder.create(AlbinoCowEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, ALBINO_COW_REGISTRY_NAME).toString())
    );

    public static final String BONE_SPIDER_REGISTRY_NAME = "bone_spider";
    public static final RegistryObject<EntityType<BoneSpiderEntity>> BONE_SPIDER_REGISTRY_OBJECT = ENTITY_TYPES.register(
            BONE_SPIDER_REGISTRY_NAME,
            () -> EntityType.Builder.create(BoneSpiderEntity::new, EntityClassification.MONSTER)
                    .size(0.6F, 0.7F)
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, BONE_SPIDER_REGISTRY_NAME).toString())
    );

    public static final String JUMBO_RABBIT_REGISTRY_NAME = "jumbo_rabbit";
    public static final RegistryObject<EntityType<JumboRabbitEntity>> JUMBO_RABBIT_REGISTRY_OBJECT = ENTITY_TYPES.register(
            JUMBO_RABBIT_REGISTRY_NAME,
            () -> EntityType.Builder.create(JumboRabbitEntity::new, EntityClassification.CREATURE)
                    .size(0.8F, 1.0F)
                    .build(new ResourceLocation(EarthtojavamobsMod.MOD_ID, JUMBO_RABBIT_REGISTRY_NAME).toString())
    );

}


