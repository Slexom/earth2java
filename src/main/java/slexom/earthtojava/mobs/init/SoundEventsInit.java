package slexom.earthtojava.init;

import net.minecraft.sound.SoundEvent;

import java.text.MessageFormat;

public class SoundEventsInit {

    public static final String BONE_SPIDER_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "ambient");
    public static final String BONE_SPIDER_BONE_STAB_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "bone.stab");
    public static final String BONE_SPIDER_DEATH_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "death");
    public static final String BONE_SPIDER_SPIT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "spit");
    public static final String BONE_SPIDER_WALK_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "walk");
    public static final String CLUCKSHROOM_LAY_MUSHROOM_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.CLUCKSHROOM_REGISTRY_NAME, "lay_mushroom");
    public static final String FANCY_CHICKEN_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, "ambient");
    public static final String FANCY_CHICKEN_DEATH_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, "death");
    public static final String FANCY_CHICKEN_FLEE_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, "flee");
    public static final String FANCY_CHICKEN_HURT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME, "hurt");
    public static final String FURNACE_GOLEM_AGGRO_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FURNACE_GOLEM_REGISTRY_NAME, "aggro");
    public static final String FURNACE_GOLEM_ATTACK_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.FURNACE_GOLEM_REGISTRY_NAME, "attack");
    public static final String JOLLY_LLAMA_BELL_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, "bell");
    public static final String JOLLY_LLAMA_DETECT_FERN_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, "detect_fern");
    public static final String JOLLY_LLAMA_EAT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, "eat");
    public static final String JOLLY_LLAMA_PRANCE_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JOLLY_LLAMA_REGISTRY_NAME, "prance");
    public static final String JUMBO_RABBIT_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, "ambient");
    public static final String JUMBO_RABBIT_HURT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, "hurt");
    public static final String JUMBO_RABBIT_JUMP_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME, "jump");
    public static final String LOBBER_ZOMBIE_ATTACK_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_NAME, "attack");
    public static final String LOBBER_ZOMBIE_ROTTEN_FLESH_HIT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_NAME, "rotten_flesh.hit");
    public static final String MELON_GOLEM_AGGRO_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, "aggro");
    public static final String MELON_GOLEM_ATTACK_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, "attack");
    public static final String MELON_GOLEM_CHARGE_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, "charge");
    public static final String MELON_GOLEM_SEED_HIT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MELON_GOLEM_REGISTRY_NAME, "seed.hit");
    public static final String MOOBLOOM_PLANT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MOOBLOOM_REGISTRY_NAME, "plant");
    public static final String MOOLIP_PLANT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.MOOLIP_REGISTRY_NAME, "plant");
    public static final String RAINBOW_SHEEP_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, "ambient");
    public static final String RAINBOW_SHEEP_DEATH_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_NAME, "death");
    public static final String SKELETON_WOLF_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "ambient");
    public static final String SKELETON_WOLF_DEATH_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "death");
    public static final String SKELETON_WOLF_GROWL_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "growl");
    public static final String SKELETON_WOLF_HOWL_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "howl");
    public static final String SKELETON_WOLF_HURT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "hurt");
    public static final String SKELETON_WOLF_PANT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "pant");
    public static final String SKELETON_WOLF_SHAKE_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "shake");
    public static final String SKELETON_WOLF_STEP_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "step");
    public static final String SKELETON_WOLF_WHINE_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "whine");
    public static final String VILER_WITCH_CASTING_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.VILER_WITCH_REGISTRY_NAME, "casting");
    public static SoundEvent BONE_SPIDER_AMBIENT;
    public static SoundEvent BONE_SPIDER_BONE_STAB;
    public static SoundEvent BONE_SPIDER_DEATH;
    public static SoundEvent BONE_SPIDER_SPIT;
    public static SoundEvent BONE_SPIDER_WALK;
    public static SoundEvent CLUCKSHROOM_LAY_MUSHROOM;
    public static SoundEvent FANCY_CHICKEN_AMBIENT;
    public static SoundEvent FANCY_CHICKEN_DEATH;
    public static SoundEvent FANCY_CHICKEN_FLEE;
    public static SoundEvent FANCY_CHICKEN_HURT;
    public static SoundEvent FURNACE_GOLEM_AGGRO;
    public static SoundEvent FURNACE_GOLEM_ATTACK;
    public static SoundEvent JOLLY_LLAMA_BELL;
    public static SoundEvent JOLLY_LLAMA_DETECT_FERN;
    public static SoundEvent JOLLY_LLAMA_EAT;
    public static SoundEvent JOLLY_LLAMA_PRANCE;
    public static SoundEvent JUMBO_RABBIT_AMBIENT;
    public static SoundEvent JUMBO_RABBIT_HURT;
    public static SoundEvent JUMBO_RABBIT_JUMP;
    public static SoundEvent LOBBER_ZOMBIE_ATTACK;
    public static SoundEvent LOBBER_ZOMBIE_PROJECTILE_SOUND;
    public static SoundEvent MELON_GOLEM_AGGRO;
    public static SoundEvent MELON_GOLEM_ATTACK;
    public static SoundEvent MELON_GOLEM_CHARGE;
    public static SoundEvent MELON_GOLEM_SEED_HIT;
    public static SoundEvent MOOBLOOM_PLANT;
    public static SoundEvent MOOLIP_PLANT;
    public static SoundEvent RAINBOW_SHEEP_AMBIENT;
    public static SoundEvent RAINBOW_SHEEP_DEATH;
    public static SoundEvent SKELETON_WOLF_AMBIENT;
    public static SoundEvent SKELETON_WOLF_DEATH;
    public static SoundEvent SKELETON_WOLF_GROWL;
    public static SoundEvent SKELETON_WOLF_HOWL;
    public static SoundEvent SKELETON_WOLF_HURT;
    public static SoundEvent SKELETON_WOLF_PANT;
    public static SoundEvent SKELETON_WOLF_SHAKE;
    public static SoundEvent SKELETON_WOLF_STEP;
    public static SoundEvent SKELETON_WOLF_WHINE;
    public static SoundEvent VILER_WITCH_CASTING;

    public static void init() {
        BONE_SPIDER_AMBIENT = RegisterHelper.registerSoundEvent(BONE_SPIDER_AMBIENT_REGISTRY_NAME);
        BONE_SPIDER_BONE_STAB = RegisterHelper.registerSoundEvent(BONE_SPIDER_BONE_STAB_REGISTRY_NAME);
        BONE_SPIDER_DEATH = RegisterHelper.registerSoundEvent(BONE_SPIDER_DEATH_REGISTRY_NAME);
        BONE_SPIDER_WALK = RegisterHelper.registerSoundEvent(BONE_SPIDER_WALK_REGISTRY_NAME);
        BONE_SPIDER_SPIT = RegisterHelper.registerSoundEvent(BONE_SPIDER_SPIT_REGISTRY_NAME);
        CLUCKSHROOM_LAY_MUSHROOM = RegisterHelper.registerSoundEvent(CLUCKSHROOM_LAY_MUSHROOM_REGISTRY_NAME);
        FANCY_CHICKEN_AMBIENT = RegisterHelper.registerSoundEvent(FANCY_CHICKEN_AMBIENT_REGISTRY_NAME);
        FANCY_CHICKEN_DEATH = RegisterHelper.registerSoundEvent(FANCY_CHICKEN_DEATH_REGISTRY_NAME);
        FANCY_CHICKEN_HURT = RegisterHelper.registerSoundEvent(FANCY_CHICKEN_HURT_REGISTRY_NAME);
        FANCY_CHICKEN_FLEE = RegisterHelper.registerSoundEvent(FANCY_CHICKEN_FLEE_REGISTRY_NAME);
        FURNACE_GOLEM_AGGRO = RegisterHelper.registerSoundEvent(FURNACE_GOLEM_AGGRO_REGISTRY_NAME);
        FURNACE_GOLEM_ATTACK = RegisterHelper.registerSoundEvent(FURNACE_GOLEM_ATTACK_REGISTRY_NAME);
        JOLLY_LLAMA_BELL = RegisterHelper.registerSoundEvent(JOLLY_LLAMA_BELL_REGISTRY_NAME);
        JOLLY_LLAMA_DETECT_FERN = RegisterHelper.registerSoundEvent(JOLLY_LLAMA_DETECT_FERN_REGISTRY_NAME);
        JOLLY_LLAMA_EAT = RegisterHelper.registerSoundEvent(JOLLY_LLAMA_EAT_REGISTRY_NAME);
        JOLLY_LLAMA_PRANCE = RegisterHelper.registerSoundEvent(JOLLY_LLAMA_PRANCE_REGISTRY_NAME);
        JUMBO_RABBIT_AMBIENT = RegisterHelper.registerSoundEvent(JUMBO_RABBIT_AMBIENT_REGISTRY_NAME);
        JUMBO_RABBIT_HURT = RegisterHelper.registerSoundEvent(JUMBO_RABBIT_HURT_REGISTRY_NAME);
        JUMBO_RABBIT_JUMP = RegisterHelper.registerSoundEvent(JUMBO_RABBIT_JUMP_REGISTRY_NAME);
        LOBBER_ZOMBIE_ATTACK = RegisterHelper.registerSoundEvent(LOBBER_ZOMBIE_ATTACK_REGISTRY_NAME);
        LOBBER_ZOMBIE_PROJECTILE_SOUND = RegisterHelper.registerSoundEvent(LOBBER_ZOMBIE_ROTTEN_FLESH_HIT_REGISTRY_NAME);
        MELON_GOLEM_AGGRO = RegisterHelper.registerSoundEvent(MELON_GOLEM_AGGRO_REGISTRY_NAME);
        MELON_GOLEM_ATTACK = RegisterHelper.registerSoundEvent(MELON_GOLEM_ATTACK_REGISTRY_NAME);
        MELON_GOLEM_CHARGE = RegisterHelper.registerSoundEvent(MELON_GOLEM_CHARGE_REGISTRY_NAME);
        MELON_GOLEM_SEED_HIT = RegisterHelper.registerSoundEvent(MELON_GOLEM_SEED_HIT_REGISTRY_NAME);
        MOOBLOOM_PLANT = RegisterHelper.registerSoundEvent(MOOBLOOM_PLANT_REGISTRY_NAME);
        MOOLIP_PLANT = RegisterHelper.registerSoundEvent(MOOLIP_PLANT_REGISTRY_NAME);
        RAINBOW_SHEEP_AMBIENT = RegisterHelper.registerSoundEvent(RAINBOW_SHEEP_AMBIENT_REGISTRY_NAME);
        RAINBOW_SHEEP_DEATH = RegisterHelper.registerSoundEvent(RAINBOW_SHEEP_DEATH_REGISTRY_NAME);
        SKELETON_WOLF_AMBIENT = RegisterHelper.registerSoundEvent(SKELETON_WOLF_AMBIENT_REGISTRY_NAME);
        SKELETON_WOLF_DEATH = RegisterHelper.registerSoundEvent(SKELETON_WOLF_DEATH_REGISTRY_NAME);
        SKELETON_WOLF_GROWL = RegisterHelper.registerSoundEvent(SKELETON_WOLF_GROWL_REGISTRY_NAME);
        SKELETON_WOLF_HOWL = RegisterHelper.registerSoundEvent(SKELETON_WOLF_HOWL_REGISTRY_NAME);
        SKELETON_WOLF_HURT = RegisterHelper.registerSoundEvent(SKELETON_WOLF_HURT_REGISTRY_NAME);
        SKELETON_WOLF_PANT = RegisterHelper.registerSoundEvent(SKELETON_WOLF_PANT_REGISTRY_NAME);
        SKELETON_WOLF_SHAKE = RegisterHelper.registerSoundEvent(SKELETON_WOLF_SHAKE_REGISTRY_NAME);
        SKELETON_WOLF_STEP = RegisterHelper.registerSoundEvent(SKELETON_WOLF_STEP_REGISTRY_NAME);
        SKELETON_WOLF_WHINE = RegisterHelper.registerSoundEvent(SKELETON_WOLF_WHINE_REGISTRY_NAME);
        VILER_WITCH_CASTING = RegisterHelper.registerSoundEvent(VILER_WITCH_CASTING_REGISTRY_NAME);
    }

    private static String getEntitySoundEventName(String registryName, String type) {
        return MessageFormat.format("entity.{0}.{1}", registryName, type);
    }

}
