package slexom.earthtojava.mobs.init;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import slexom.earthtojava.mobs.entity.base.*;
import slexom.earthtojava.mobs.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.mobs.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

public class EntityAttributeInit {

    public static void init() {

        GlobalEntityTypeAttributes.put(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), E2JBaseChickenEntity.registerAttributes().func_233813_a_());


        GlobalEntityTypeAttributes.put(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), E2JBaseCowEntity.registerAttributes().func_233813_a_());


        GlobalEntityTypeAttributes.put(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), E2JBasePigEntity.registerAttributes().func_233813_a_());

        GlobalEntityTypeAttributes.put(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), E2JBaseRabbitEntity.registerAttributes().func_233813_a_());

        GlobalEntityTypeAttributes.put(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), HornedSheepEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), E2JOneColorSheepEntity.registerAttributes().func_233813_a_());

        GlobalEntityTypeAttributes.put(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get(), IronGolemEntity.func_234200_m_().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT.get(), MelonGolemEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), MonsterEntity.func_234295_eP_().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT.get(), SquidEntity.func_234227_m_().func_233813_a_());

        GlobalEntityTypeAttributes.put(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), BoneSpiderEntity.registerAttributes().func_233813_a_());
        GlobalEntityTypeAttributes.put(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), SkeletonWolfEntity.registerAttributes().func_233813_a_());
    }
}
