
package net.slexom.earthtojavamobs.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.text.MessageFormat;

public class SunsetCowEntity extends CowEntity {
    private static final String registryNameEntity = "sunset_cow";
    private static final String registryNameSpawnEgg = MessageFormat.format("{0}_spawn_egg", registryNameEntity);

//    public SunsetCowEntity(EarthtojavamobsModElements instance) {
//        super(instance, 21);
//        FMLJavaModLoadingContext.get().getModEventBus().register(this);
//    }
//
//    @Override
//    public void initElements() {
//        entity = (EntityType.Builder.<SunsetCowEntity>create(SunsetCowEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
//                .setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SunsetCowEntity::new).size(0.9f, 1.4f)).build(registryNameEntity)
//                .setRegistryName(registryNameEntity);
//        elements.entities.add(() -> entity);
//        elements.items.add(() -> new SpawnEggItem(entity, 0x993d0d, 0x171514, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(registryNameSpawnEgg));
//    }
//
//    @Override
//    public void init(FMLCommonSetupEvent event) {
//        DeferredWorkQueue.runLater(new Runnable() {
//            @Override
//            public void run() {
//                String[] spawnBiomes = BiomeSpawnHelper.getBiomesListFromBiomes(BiomeSpawnHelper.SAVANNA);
//                BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, 8, 2, 4);
//                EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
//            }
//        });
//    }

//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    public void registerModels(ModelRegistryEvent event) {
//        RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new E2JCowRenderer(renderManager, registryNameEntity));
//    }


    public SunsetCowEntity(EntityType<SunsetCowEntity> type, World world) {
        super(type, world);
        experienceValue = (int) Math.ceil(Math.random() * 3);
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
    public CowEntity createChild(AgeableEntity ageable) {
        return (SunsetCowEntity) getType().create(this.world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
 
