package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import slexom.earthtojava.Earth2JavaMod;

import java.text.MessageFormat;

public final class SoundEventsInit {

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

    public static final RegistrySupplier<SoundEvent> BONE_SPIDER_AMBIENT;
    public static final RegistrySupplier<SoundEvent> BONE_SPIDER_BONE_STAB;
    public static final RegistrySupplier<SoundEvent> BONE_SPIDER_DEATH;
    public static final RegistrySupplier<SoundEvent> BONE_SPIDER_SPIT;
    public static final RegistrySupplier<SoundEvent> BONE_SPIDER_WALK;
    public static final RegistrySupplier<SoundEvent> CLUCKSHROOM_LAY_MUSHROOM;
    public static final RegistrySupplier<SoundEvent> FANCY_CHICKEN_AMBIENT;
    public static final RegistrySupplier<SoundEvent> FANCY_CHICKEN_DEATH;
    public static final RegistrySupplier<SoundEvent> FANCY_CHICKEN_FLEE;
    public static final RegistrySupplier<SoundEvent> FANCY_CHICKEN_HURT;
    public static final RegistrySupplier<SoundEvent> FURNACE_GOLEM_AGGRO;
    public static final RegistrySupplier<SoundEvent> FURNACE_GOLEM_ATTACK;
    public static final RegistrySupplier<SoundEvent> JOLLY_LLAMA_BELL;
    public static final RegistrySupplier<SoundEvent> JOLLY_LLAMA_DETECT_FERN;
    public static final RegistrySupplier<SoundEvent> JOLLY_LLAMA_EAT;
    public static final RegistrySupplier<SoundEvent> JOLLY_LLAMA_PRANCE;
    public static final RegistrySupplier<SoundEvent> JUMBO_RABBIT_AMBIENT;
    public static final RegistrySupplier<SoundEvent> JUMBO_RABBIT_HURT;
    public static final RegistrySupplier<SoundEvent> JUMBO_RABBIT_JUMP;
    public static final RegistrySupplier<SoundEvent> LOBBER_ZOMBIE_ATTACK;
    public static final RegistrySupplier<SoundEvent> LOBBER_ZOMBIE_PROJECTILE_SOUND;
    public static final RegistrySupplier<SoundEvent> MELON_GOLEM_AGGRO;
    public static final RegistrySupplier<SoundEvent> MELON_GOLEM_ATTACK;
    public static final RegistrySupplier<SoundEvent> MELON_GOLEM_CHARGE;
    public static final RegistrySupplier<SoundEvent> MELON_GOLEM_SEED_HIT;
    public static final RegistrySupplier<SoundEvent> MOOBLOOM_PLANT;
    public static final RegistrySupplier<SoundEvent> MOOLIP_PLANT;
    public static final RegistrySupplier<SoundEvent> RAINBOW_SHEEP_AMBIENT;
    public static final RegistrySupplier<SoundEvent> RAINBOW_SHEEP_DEATH;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_AMBIENT;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_DEATH;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_GROWL;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_HOWL;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_HURT;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_PANT;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_SHAKE;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_STEP;
    public static final RegistrySupplier<SoundEvent> SKELETON_WOLF_WHINE;
    public static final RegistrySupplier<SoundEvent> VILER_WITCH_CASTING;

    static {
        BONE_SPIDER_AMBIENT = registerSoundEvent(BONE_SPIDER_AMBIENT_REGISTRY_NAME);
        BONE_SPIDER_BONE_STAB = registerSoundEvent(BONE_SPIDER_BONE_STAB_REGISTRY_NAME);
        BONE_SPIDER_DEATH = registerSoundEvent(BONE_SPIDER_DEATH_REGISTRY_NAME);
        BONE_SPIDER_WALK = registerSoundEvent(BONE_SPIDER_WALK_REGISTRY_NAME);
        BONE_SPIDER_SPIT = registerSoundEvent(BONE_SPIDER_SPIT_REGISTRY_NAME);
        CLUCKSHROOM_LAY_MUSHROOM = registerSoundEvent(CLUCKSHROOM_LAY_MUSHROOM_REGISTRY_NAME);
        FANCY_CHICKEN_AMBIENT = registerSoundEvent(FANCY_CHICKEN_AMBIENT_REGISTRY_NAME);
        FANCY_CHICKEN_DEATH = registerSoundEvent(FANCY_CHICKEN_DEATH_REGISTRY_NAME);
        FANCY_CHICKEN_HURT = registerSoundEvent(FANCY_CHICKEN_HURT_REGISTRY_NAME);
        FANCY_CHICKEN_FLEE = registerSoundEvent(FANCY_CHICKEN_FLEE_REGISTRY_NAME);
        FURNACE_GOLEM_AGGRO = registerSoundEvent(FURNACE_GOLEM_AGGRO_REGISTRY_NAME);
        FURNACE_GOLEM_ATTACK = registerSoundEvent(FURNACE_GOLEM_ATTACK_REGISTRY_NAME);
        JOLLY_LLAMA_BELL = registerSoundEvent(JOLLY_LLAMA_BELL_REGISTRY_NAME);
        JOLLY_LLAMA_DETECT_FERN = registerSoundEvent(JOLLY_LLAMA_DETECT_FERN_REGISTRY_NAME);
        JOLLY_LLAMA_EAT = registerSoundEvent(JOLLY_LLAMA_EAT_REGISTRY_NAME);
        JOLLY_LLAMA_PRANCE = registerSoundEvent(JOLLY_LLAMA_PRANCE_REGISTRY_NAME);
        JUMBO_RABBIT_AMBIENT = registerSoundEvent(JUMBO_RABBIT_AMBIENT_REGISTRY_NAME);
        JUMBO_RABBIT_HURT = registerSoundEvent(JUMBO_RABBIT_HURT_REGISTRY_NAME);
        JUMBO_RABBIT_JUMP = registerSoundEvent(JUMBO_RABBIT_JUMP_REGISTRY_NAME);
        LOBBER_ZOMBIE_ATTACK = registerSoundEvent(LOBBER_ZOMBIE_ATTACK_REGISTRY_NAME);
        LOBBER_ZOMBIE_PROJECTILE_SOUND = registerSoundEvent(LOBBER_ZOMBIE_ROTTEN_FLESH_HIT_REGISTRY_NAME);
        MELON_GOLEM_AGGRO = registerSoundEvent(MELON_GOLEM_AGGRO_REGISTRY_NAME);
        MELON_GOLEM_ATTACK = registerSoundEvent(MELON_GOLEM_ATTACK_REGISTRY_NAME);
        MELON_GOLEM_CHARGE = registerSoundEvent(MELON_GOLEM_CHARGE_REGISTRY_NAME);
        MELON_GOLEM_SEED_HIT = registerSoundEvent(MELON_GOLEM_SEED_HIT_REGISTRY_NAME);
        MOOBLOOM_PLANT = registerSoundEvent(MOOBLOOM_PLANT_REGISTRY_NAME);
        MOOLIP_PLANT = registerSoundEvent(MOOLIP_PLANT_REGISTRY_NAME);
        RAINBOW_SHEEP_AMBIENT = registerSoundEvent(RAINBOW_SHEEP_AMBIENT_REGISTRY_NAME);
        RAINBOW_SHEEP_DEATH = registerSoundEvent(RAINBOW_SHEEP_DEATH_REGISTRY_NAME);
        SKELETON_WOLF_AMBIENT = registerSoundEvent(SKELETON_WOLF_AMBIENT_REGISTRY_NAME);
        SKELETON_WOLF_DEATH = registerSoundEvent(SKELETON_WOLF_DEATH_REGISTRY_NAME);
        SKELETON_WOLF_GROWL = registerSoundEvent(SKELETON_WOLF_GROWL_REGISTRY_NAME);
        SKELETON_WOLF_HOWL = registerSoundEvent(SKELETON_WOLF_HOWL_REGISTRY_NAME);
        SKELETON_WOLF_HURT = registerSoundEvent(SKELETON_WOLF_HURT_REGISTRY_NAME);
        SKELETON_WOLF_PANT = registerSoundEvent(SKELETON_WOLF_PANT_REGISTRY_NAME);
        SKELETON_WOLF_SHAKE = registerSoundEvent(SKELETON_WOLF_SHAKE_REGISTRY_NAME);
        SKELETON_WOLF_STEP = registerSoundEvent(SKELETON_WOLF_STEP_REGISTRY_NAME);
        SKELETON_WOLF_WHINE = registerSoundEvent(SKELETON_WOLF_WHINE_REGISTRY_NAME);
        VILER_WITCH_CASTING = registerSoundEvent(VILER_WITCH_CASTING_REGISTRY_NAME);
    }

    public static void init() {

    }

    public static RegistrySupplier<SoundEvent> registerSoundEvent(String registryName) {
        final Identifier identifier = Earth2JavaMod.toIdentifier(registryName);
        return Earth2JavaMod.SOUND_EVENT_REGISTRAR.register(identifier, () -> new SoundEvent(identifier));
    }

    private static String getEntitySoundEventName(String registryName, String type) {
        return MessageFormat.format("entity.{0}.{1}", registryName, type);
    }

}
