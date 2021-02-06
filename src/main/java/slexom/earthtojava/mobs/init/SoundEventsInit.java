package slexom.earthtojava.mobs.init;

import net.minecraft.sound.SoundEvent;

import java.text.MessageFormat;

public class SoundEventsInit {

    public static final String BONE_SPIDER_AMBIENT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "ambient");
    public static final String BONE_SPIDER_BONE_STAB_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "bone.stab");
    public static final String BONE_SPIDER_DEATH_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "death");
    public static final String BONE_SPIDER_SPIT_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "spit");
    public static final String BONE_SPIDER_WALK_REGISTRY_NAME = getEntitySoundEventName(EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "walk");
    public static SoundEvent BONE_SPIDER_AMBIENT;
    public static SoundEvent BONE_SPIDER_BONE_STAB;
    public static SoundEvent BONE_SPIDER_DEATH;
    public static SoundEvent BONE_SPIDER_SPIT;
    public static SoundEvent BONE_SPIDER_WALK;

    public static void init() {
        BONE_SPIDER_AMBIENT = RegisterHelper.registerSoundEvent(BONE_SPIDER_AMBIENT_REGISTRY_NAME);
        BONE_SPIDER_BONE_STAB = RegisterHelper.registerSoundEvent(BONE_SPIDER_BONE_STAB_REGISTRY_NAME);
        BONE_SPIDER_DEATH = RegisterHelper.registerSoundEvent(BONE_SPIDER_DEATH_REGISTRY_NAME);
        BONE_SPIDER_WALK = RegisterHelper.registerSoundEvent(BONE_SPIDER_WALK_REGISTRY_NAME);
        BONE_SPIDER_SPIT = RegisterHelper.registerSoundEvent(BONE_SPIDER_SPIT_REGISTRY_NAME);
    }

    private static String getEntitySoundEventName(String registryName, String type) {
        return MessageFormat.format("entity.{0}.{1}", registryName, type);
    }

}
