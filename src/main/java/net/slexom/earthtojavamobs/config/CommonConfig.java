package net.slexom.earthtojavamobs.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.slexom.earthtojavamobs.init.EntityTypesInit;

import java.util.Arrays;
import java.util.List;

final class CommonConfig {

    final String CATEGORY_ENTITIES = "entities";

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

    private final int standardSheepWeight = 12;
    private final int standardCowWeight = 8;
    private final int standardChickenWeight = 10;
    private final int standardPigWeight = 10;
    private final int standardRabbitWeight = 4;
    private final int standardWolfWeight = 8;
    private final int standardFoxWeight = 8;

    CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push(CATEGORY_ENTITIES);
        builder.push("amberChicken");
        amberChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.AmberChicken.spawnBiomes));
        amberChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, 100);
        amberChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        amberChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("ashenCow");
        ashenCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.AshenCow.spawnBiomes));
        ashenCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, 100);
        ashenCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        ashenCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("cluckshroom");
        cluckshroomSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.Cluckshroom.spawnBiomes));
        cluckshroomWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, 100);
        cluckshroomGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        cluckshroomGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("fleckedSheep");
        fleckedSheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.FleckedSheep.spawnBiomes));
        fleckedSheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, 100);
        fleckedSheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        fleckedSheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("glowSquid");
        glowSquidSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.GlowSquid.spawnBiomes));
        glowSquidWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 6, 0, 100);
        glowSquidGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        glowSquidGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("harelequinRabbit");
        harelequinRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.HarelequinRabbit.spawnBiomes));
        harelequinRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, 100);
        harelequinRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        harelequinRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("hornedSheep");
        hornedSheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.HornedSheep.spawnBiomes));
        hornedSheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, 100);
        hornedSheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        hornedSheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("inkySheep");
        inkySheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.InkySheep.spawnBiomes));
        inkySheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, 100);
        inkySheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        inkySheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("midnightChicken");
        midnightChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MidnightChicken.spawnBiomes));
        midnightChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, 100);
        midnightChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        midnightChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("moobloom");
        moobloomSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.Moobloom.spawnBiomes));
        moobloomWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, 100);
        moobloomGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        moobloomGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("muddyFootRabbit");
        muddyFootRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MuddyFootRabbit.spawnBiomes));
        muddyFootRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, 100);
        muddyFootRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        muddyFootRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("muddyPig");
        muddyPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.MuddyPig.spawnBiomes));
        muddyPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, 100);
        muddyPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        muddyPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("palePig");
        palePigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.PalePig.spawnBiomes));
        palePigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, 100);
        palePigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        palePigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("piebaldPig");
        piebaldPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.PiebaldPig.spawnBiomes));
        piebaldPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, 100);
        piebaldPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        piebaldPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("rockySheep");
        rockySheepSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.RockySheep.spawnBiomes));
        rockySheepWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardSheepWeight, 0, 100);
        rockySheepGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        rockySheepGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("skeletonWolf");
        skeletonWolfSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SkeletonWolf.spawnBiomes));
        skeletonWolfWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardWolfWeight, 0, 100);
        skeletonWolfGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        skeletonWolfGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("spottedPig");
        spottedPigSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SpottedPig.spawnBiomes));
        spottedPigWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardPigWeight, 0, 100);
        spottedPigGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        spottedPigGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("stormyChicken");
        stormyChickenSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.StormyChicken.spawnBiomes));
        stormyChickenWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardChickenWeight, 0, 100);
        stormyChickenGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        stormyChickenGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("sunsetCow");
        sunsetCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.SunsetCow.spawnBiomes));
        sunsetCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, 100);
        sunsetCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        sunsetCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("tropicalSlime");
        tropicalSlimeSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.TropicalSlime.spawnBiomes));
        tropicalSlimeWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", 12, 0, 100);
        tropicalSlimeGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        tropicalSlimeGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("vestedRabbit");
        vestedRabbitSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.VestedRabbit.spawnBiomes));
        vestedRabbitWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardRabbitWeight, 0, 100);
        vestedRabbitGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        vestedRabbitGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();

        builder.push("woolyCow");
        woolyCowSpawnBiomes = builder
                .comment("Biome where entity Spawn")
                .define("spawnBiomes", Arrays.asList(EntityTypesInit.WoolyCow.spawnBiomes));
        woolyCowWeight = builder
                .comment("Weight of entity in spawn")
                .defineInRange("entityWeight", standardCowWeight, 0, 100);
        woolyCowGroupMin = builder
                .comment("Minimum number of entities in group")
                .defineInRange("entityGroupMin", 2, 0, 100);
        woolyCowGroupMax = builder
                .comment("Maximum number of entities in group")
                .defineInRange("entityGroupMax", 4, 0, 100);
        builder.pop();
        builder.pop();
    }

}