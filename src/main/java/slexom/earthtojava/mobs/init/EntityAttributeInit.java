package slexom.earthtojava.mobs.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import slexom.earthtojava.mobs.entity.base.*;
import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;
import slexom.earthtojava.mobs.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.mobs.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

public class EntityAttributeInit {

    public static void init() {

        registerAttributes(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes());
        registerAttributes(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes());
        registerAttributes(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes());
        registerAttributes(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes());


        registerAttributes(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes());
        registerAttributes(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes());
        registerAttributes(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes());
        registerAttributes(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes());
        registerAttributes(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes());


        registerAttributes(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes());
        registerAttributes(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes());
        registerAttributes(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes());
        registerAttributes(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes());

        registerAttributes(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes());
        registerAttributes(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes());
        registerAttributes(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes());
        registerAttributes(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes());

        registerAttributes(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes());
        registerAttributes(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes());
        registerAttributes(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), HornedSheepEntity.registerAttributes());
        registerAttributes(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes());
        registerAttributes(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes());

        registerAttributes(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get(), IronGolemEntity.func_234200_m_());
        registerAttributes(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get(), MelonGolemEntity.registerAttributes());
        registerAttributes(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), MonsterEntity.func_234295_eP_());
        registerAttributes(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT.get(), SquidEntity.func_234227_m_());

        registerAttributes(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), BoneSpiderEntity.registerAttributes());

        registerAttributes(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), SkeletonWolfEntity.registerAttributes());

        registerAttributes(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT.get(), E2JBaseLlamaEntity.registerAttributes());
        
        registerAttributes(EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT.get(), E2JWanderingTraderEntity.initAttributes());
    }
    
    private static void registerAttributes(EntityType<? extends LivingEntity> entity, AttributeModifierMap.MutableAttribute attributes){
        GlobalEntityTypeAttributes.put(entity, attributes.func_233813_a_());
    }
}
