package net.slexom.earthtojavamobs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.slexom.earthtojavamobs.init.EntityTypesInit;

import java.util.Arrays;
import java.util.List;

final class CommonConfig {

    final String CATEGORY_GENERAL = "general";
    final String CATEGORY_ENTITIES = "entities";

    final ForgeConfigSpec.IntValue mudLakeFrequency;

    final ForgeConfigSpec.ConfigValue<List<String>> amberChickenSpawnBiomes;
    final ForgeConfigSpec.IntValue amberChickenWeight;
    final ForgeConfigSpec.IntValue amberChickenGroupMin;
    final ForgeConfigSpec.IntValue amberChickenGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> ashenCowSpawnBiomes;
    final ForgeConfigSpec.IntValue ashenCowWeight;
    final ForgeConfigSpec.IntValue ashenCowGroupMin;
    final ForgeConfigSpec.IntValue ashenCowGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> cluckshroomSpawnBiomes;
    final ForgeConfigSpec.IntValue cluckshroomWeight;
    final ForgeConfigSpec.IntValue cluckshroomGroupMin;
    final ForgeConfigSpec.IntValue cluckshroomGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> fleckedSheepSpawnBiomes;
    final ForgeConfigSpec.IntValue fleckedSheepWeight;
    final ForgeConfigSpec.IntValue fleckedSheepGroupMin;
    final ForgeConfigSpec.IntValue fleckedSheepGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> glowSquidSpawnBiomes;
    final ForgeConfigSpec.IntValue glowSquidWeight;
    final ForgeConfigSpec.IntValue glowSquidGroupMin;
    final ForgeConfigSpec.IntValue glowSquidGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> harelequinRabbitSpawnBiomes;
    final ForgeConfigSpec.IntValue harelequinRabbitWeight;
    final ForgeConfigSpec.IntValue harelequinRabbitGroupMin;
    final ForgeConfigSpec.IntValue harelequinRabbitGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> hornedSheepSpawnBiomes;
    final ForgeConfigSpec.IntValue hornedSheepWeight;
    final ForgeConfigSpec.IntValue hornedSheepGroupMin;
    final ForgeConfigSpec.IntValue hornedSheepGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> inkySheepSpawnBiomes;
    final ForgeConfigSpec.IntValue inkySheepWeight;
    final ForgeConfigSpec.IntValue inkySheepGroupMin;
    final ForgeConfigSpec.IntValue inkySheepGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> midnightChickenSpawnBiomes;
    final ForgeConfigSpec.IntValue midnightChickenWeight;
    final ForgeConfigSpec.IntValue midnightChickenGroupMin;
    final ForgeConfigSpec.IntValue midnightChickenGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> moobloomSpawnBiomes;
    final ForgeConfigSpec.IntValue moobloomWeight;
    final ForgeConfigSpec.IntValue moobloomGroupMin;
    final ForgeConfigSpec.IntValue moobloomGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> muddyFootRabbitSpawnBiomes;
    final ForgeConfigSpec.IntValue muddyFootRabbitWeight;
    final ForgeConfigSpec.IntValue muddyFootRabbitGroupMin;
    final ForgeConfigSpec.IntValue muddyFootRabbitGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> muddyPigSpawnBiomes;
    final ForgeConfigSpec.IntValue muddyPigWeight;
    final ForgeConfigSpec.IntValue muddyPigGroupMin;
    final ForgeConfigSpec.IntValue muddyPigGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> palePigSpawnBiomes;
    final ForgeConfigSpec.IntValue palePigWeight;
    final ForgeConfigSpec.IntValue palePigGroupMin;
    final ForgeConfigSpec.IntValue palePigGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> piebaldPigSpawnBiomes;
    final ForgeConfigSpec.IntValue piebaldPigWeight;
    final ForgeConfigSpec.IntValue piebaldPigGroupMin;
    final ForgeConfigSpec.IntValue piebaldPigGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> rockySheepSpawnBiomes;
    final ForgeConfigSpec.IntValue rockySheepWeight;
    final ForgeConfigSpec.IntValue rockySheepGroupMin;
    final ForgeConfigSpec.IntValue rockySheepGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> skeletonWolfSpawnBiomes;
    final ForgeConfigSpec.IntValue skeletonWolfWeight;
    final ForgeConfigSpec.IntValue skeletonWolfGroupMin;
    final ForgeConfigSpec.IntValue skeletonWolfGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> spottedPigSpawnBiomes;
    final ForgeConfigSpec.IntValue spottedPigWeight;
    final ForgeConfigSpec.IntValue spottedPigGroupMin;
    final ForgeConfigSpec.IntValue spottedPigGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> stormyChickenSpawnBiomes;
    final ForgeConfigSpec.IntValue stormyChickenWeight;
    final ForgeConfigSpec.IntValue stormyChickenGroupMin;
    final ForgeConfigSpec.IntValue stormyChickenGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> sunsetCowSpawnBiomes;
    final ForgeConfigSpec.IntValue sunsetCowWeight;
    final ForgeConfigSpec.IntValue sunsetCowGroupMin;
    final ForgeConfigSpec.IntValue sunsetCowGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> tropicalSlimeSpawnBiomes;
    final ForgeConfigSpec.IntValue tropicalSlimeWeight;
    final ForgeConfigSpec.IntValue tropicalSlimeGroupMin;
    final ForgeConfigSpec.IntValue tropicalSlimeGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> vestedRabbitSpawnBiomes;
    final ForgeConfigSpec.IntValue vestedRabbitWeight;
    final ForgeConfigSpec.IntValue vestedRabbitGroupMin;
    final ForgeConfigSpec.IntValue vestedRabbitGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> woolyCowSpawnBiomes;
    final ForgeConfigSpec.IntValue woolyCowWeight;
    final ForgeConfigSpec.IntValue woolyCowGroupMin;
    final ForgeConfigSpec.IntValue woolyCowGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> furnaceGolemSpawnBiomes;
    final ForgeConfigSpec.IntValue furnaceGolemWeight;
    final ForgeConfigSpec.IntValue furnaceGolemGroupMin;
    final ForgeConfigSpec.IntValue furnaceGolemGroupMax;

    final ForgeConfigSpec.ConfigValue<List<String>> melonGolemSpawnBiomes;
    final ForgeConfigSpec.IntValue melonGolemWeight;
    final ForgeConfigSpec.IntValue melonGolemGroupMin;
    final ForgeConfigSpec.IntValue melonGolemGroupMax;

    private final int standardSheepWeight = 12;
    private final int standardCowWeight = 8;
    private final int standardChickenWeight = 10;
    private final int standardPigWeight = 10;
    private final int standardRabbitWeight = 4;
    private final int standardWolfWeight = 8;
    private final int standardFoxWeight = 8;

    CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push(CATEGORY_GENERAL);
        mudLakeFrequency = builder
                .comment("Frequency of mud lake generation. Lower number -> High appearance chance")
                .defineInRange("mudLakeFrequency", 40, 1, Integer.MAX_VALUE);
        builder.pop();
        builder.push(CATEGORY_ENTITIES);
        builder.push("amberChicken");
        amberChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.AmberChicken.spawnBiomes));
        amberChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, Integer.MAX_VALUE);
        amberChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        amberChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("ashenCow");
        ashenCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.AshenCow.spawnBiomes));
        ashenCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, Integer.MAX_VALUE);
        ashenCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        ashenCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("cluckshroom");
        cluckshroomSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.Cluckshroom.spawnBiomes));
        cluckshroomWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, Integer.MAX_VALUE);
        cluckshroomGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        cluckshroomGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("fleckedSheep");
        fleckedSheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.FleckedSheep.spawnBiomes));
        fleckedSheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, Integer.MAX_VALUE);
        fleckedSheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        fleckedSheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("glowSquid");
        glowSquidSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.GlowSquid.spawnBiomes));
        glowSquidWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 6, 0, Integer.MAX_VALUE);
        glowSquidGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        glowSquidGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("harelequinRabbit");
        harelequinRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.HarelequinRabbit.spawnBiomes));
        harelequinRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, Integer.MAX_VALUE);
        harelequinRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        harelequinRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("hornedSheep");
        hornedSheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.HornedSheep.spawnBiomes));
        hornedSheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, Integer.MAX_VALUE);
        hornedSheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        hornedSheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("inkySheep");
        inkySheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.InkySheep.spawnBiomes));
        inkySheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, Integer.MAX_VALUE);
        inkySheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        inkySheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("midnightChicken");
        midnightChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MidnightChicken.spawnBiomes));
        midnightChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, Integer.MAX_VALUE);
        midnightChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        midnightChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("moobloom");
        moobloomSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.Moobloom.spawnBiomes));
        moobloomWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, Integer.MAX_VALUE);
        moobloomGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        moobloomGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("muddyFootRabbit");
        muddyFootRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MuddyFootRabbit.spawnBiomes));
        muddyFootRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, Integer.MAX_VALUE);
        muddyFootRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        muddyFootRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("muddyPig");
        muddyPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MuddyPig.spawnBiomes));
        muddyPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, Integer.MAX_VALUE);
        muddyPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        muddyPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("palePig");
        palePigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.PalePig.spawnBiomes));
        palePigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, Integer.MAX_VALUE);
        palePigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        palePigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("piebaldPig");
        piebaldPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.PiebaldPig.spawnBiomes));
        piebaldPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, Integer.MAX_VALUE);
        piebaldPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        piebaldPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("rockySheep");
        rockySheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.RockySheep.spawnBiomes));
        rockySheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, Integer.MAX_VALUE);
        rockySheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        rockySheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("skeletonWolf");
        skeletonWolfSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SkeletonWolf.spawnBiomes));
        skeletonWolfWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardWolfWeight, 0, Integer.MAX_VALUE);
        skeletonWolfGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        skeletonWolfGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("spottedPig");
        spottedPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SpottedPig.spawnBiomes));
        spottedPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, Integer.MAX_VALUE);
        spottedPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        spottedPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("stormyChicken");
        stormyChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.StormyChicken.spawnBiomes));
        stormyChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, Integer.MAX_VALUE);
        stormyChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        stormyChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("sunsetCow");
        sunsetCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SunsetCow.spawnBiomes));
        sunsetCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, Integer.MAX_VALUE);
        sunsetCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        sunsetCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("tropicalSlime");
        tropicalSlimeSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.TropicalSlime.spawnBiomes));
        tropicalSlimeWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 12, 0, Integer.MAX_VALUE);
        tropicalSlimeGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        tropicalSlimeGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("vestedRabbit");
        vestedRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.VestedRabbit.spawnBiomes));
        vestedRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, Integer.MAX_VALUE);
        vestedRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        vestedRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("woolyCow");
        woolyCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.WoolyCow.spawnBiomes));
        woolyCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, Integer.MAX_VALUE);
        woolyCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        woolyCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("furnaceGolem");
        furnaceGolemSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.WoolyCow.spawnBiomes));
        furnaceGolemWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 20, 0, Integer.MAX_VALUE);
        furnaceGolemGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        furnaceGolemGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("melonGolem");
        melonGolemSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.WoolyCow.spawnBiomes));
        melonGolemWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 20, 0, Integer.MAX_VALUE);
        melonGolemGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, Integer.MAX_VALUE);
        melonGolemGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, Integer.MAX_VALUE);
        builder.pop();
        builder.pop();
    }

}