package slexom.earthtojava.mobs.config;

/**
 * This bakes the config values to normal fields
 *
 * @author Cadiboo
 * It can be merged into the main ConfigMod class, but is separate because of personal preference and to keep the code organised
 */
public final class ConfigHelper {

    public static void bakeClient(final net.minecraftforge.fml.config.ModConfig config) {
        E2JModConfig.showDescription = ConfigHolder.CLIENT.showDescription.get();
    }

    public static void bakeCommon(final net.minecraftforge.fml.config.ModConfig config) {

        E2JModConfig.mudLakeFrequency = ConfigHolder.COMMON.mudLakeFrequency.get();
        E2JModConfig.canWanderingTraderSpawn = ConfigHolder.COMMON.canWanderingTraderSpawn.get();
        E2JModConfig.canRubyOreGenerate = ConfigHolder.COMMON.canRubyOreGenerate.get();
        E2JModConfig.rubyOreCount = ConfigHolder.COMMON.rubyOreCount.get();
        E2JModConfig.rubyOreBottomOffset = ConfigHolder.COMMON.rubyOreBottomOffset.get();
        E2JModConfig.rubyOreTopOffset = ConfigHolder.COMMON.rubyOreTopOffset.get();
        E2JModConfig.rubyOreMaximum = ConfigHolder.COMMON.rubyOreMaximum.get();


        E2JModConfig.amberChickenSpawnBiomes = ConfigHolder.COMMON.amberChickenSpawnBiomes.get();
        E2JModConfig.amberChickenWeight = ConfigHolder.COMMON.amberChickenWeight.get();
        E2JModConfig.amberChickenGroupMin = ConfigHolder.COMMON.amberChickenGroupMin.get();
        E2JModConfig.amberChickenGroupMax = ConfigHolder.COMMON.amberChickenGroupMax.get();

        E2JModConfig.ashenCowSpawnBiomes = ConfigHolder.COMMON.ashenCowSpawnBiomes.get();
        E2JModConfig.ashenCowWeight = ConfigHolder.COMMON.ashenCowWeight.get();
        E2JModConfig.ashenCowGroupMin = ConfigHolder.COMMON.ashenCowGroupMin.get();
        E2JModConfig.ashenCowGroupMax = ConfigHolder.COMMON.ashenCowGroupMax.get();

        E2JModConfig.cluckshroomSpawnBiomes = ConfigHolder.COMMON.cluckshroomSpawnBiomes.get();
        E2JModConfig.cluckshroomWeight = ConfigHolder.COMMON.cluckshroomWeight.get();
        E2JModConfig.cluckshroomGroupMin = ConfigHolder.COMMON.cluckshroomGroupMin.get();
        E2JModConfig.cluckshroomGroupMax = ConfigHolder.COMMON.cluckshroomGroupMax.get();

        E2JModConfig.fleckedSheepSpawnBiomes = ConfigHolder.COMMON.fleckedSheepSpawnBiomes.get();
        E2JModConfig.fleckedSheepWeight = ConfigHolder.COMMON.fleckedSheepWeight.get();
        E2JModConfig.fleckedSheepGroupMin = ConfigHolder.COMMON.fleckedSheepGroupMin.get();
        E2JModConfig.fleckedSheepGroupMax = ConfigHolder.COMMON.fleckedSheepGroupMax.get();

        E2JModConfig.glowSquidSpawnBiomes = ConfigHolder.COMMON.glowSquidSpawnBiomes.get();
        E2JModConfig.glowSquidWeight = ConfigHolder.COMMON.glowSquidWeight.get();
        E2JModConfig.glowSquidGroupMin = ConfigHolder.COMMON.glowSquidGroupMin.get();
        E2JModConfig.glowSquidGroupMax = ConfigHolder.COMMON.glowSquidGroupMax.get();

        E2JModConfig.harelequinRabbitSpawnBiomes = ConfigHolder.COMMON.harelequinRabbitSpawnBiomes.get();
        E2JModConfig.harelequinRabbitWeight = ConfigHolder.COMMON.harelequinRabbitWeight.get();
        E2JModConfig.harelequinRabbitGroupMin = ConfigHolder.COMMON.harelequinRabbitGroupMin.get();
        E2JModConfig.harelequinRabbitGroupMax = ConfigHolder.COMMON.harelequinRabbitGroupMax.get();

        E2JModConfig.hornedSheepSpawnBiomes = ConfigHolder.COMMON.hornedSheepSpawnBiomes.get();
        E2JModConfig.hornedSheepWeight = ConfigHolder.COMMON.hornedSheepWeight.get();
        E2JModConfig.hornedSheepGroupMin = ConfigHolder.COMMON.hornedSheepGroupMin.get();
        E2JModConfig.hornedSheepGroupMax = ConfigHolder.COMMON.hornedSheepGroupMax.get();

        E2JModConfig.inkySheepSpawnBiomes = ConfigHolder.COMMON.inkySheepSpawnBiomes.get();
        E2JModConfig.inkySheepWeight = ConfigHolder.COMMON.inkySheepWeight.get();
        E2JModConfig.inkySheepGroupMin = ConfigHolder.COMMON.inkySheepGroupMin.get();
        E2JModConfig.inkySheepGroupMax = ConfigHolder.COMMON.inkySheepGroupMax.get();

        E2JModConfig.midnightChickenSpawnBiomes = ConfigHolder.COMMON.midnightChickenSpawnBiomes.get();
        E2JModConfig.midnightChickenWeight = ConfigHolder.COMMON.midnightChickenWeight.get();
        E2JModConfig.midnightChickenGroupMin = ConfigHolder.COMMON.midnightChickenGroupMin.get();
        E2JModConfig.midnightChickenGroupMax = ConfigHolder.COMMON.midnightChickenGroupMax.get();

        E2JModConfig.moobloomSpawnBiomes = ConfigHolder.COMMON.moobloomSpawnBiomes.get();
        E2JModConfig.moobloomWeight = ConfigHolder.COMMON.moobloomWeight.get();
        E2JModConfig.moobloomGroupMin = ConfigHolder.COMMON.moobloomGroupMin.get();
        E2JModConfig.moobloomGroupMax = ConfigHolder.COMMON.moobloomGroupMax.get();

        E2JModConfig.muddyFootRabbitSpawnBiomes = ConfigHolder.COMMON.muddyFootRabbitSpawnBiomes.get();
        E2JModConfig.muddyFootRabbitWeight = ConfigHolder.COMMON.muddyFootRabbitWeight.get();
        E2JModConfig.muddyFootRabbitGroupMin = ConfigHolder.COMMON.muddyFootRabbitGroupMin.get();
        E2JModConfig.muddyFootRabbitGroupMax = ConfigHolder.COMMON.muddyFootRabbitGroupMax.get();

        E2JModConfig.muddyPigSpawnBiomes = ConfigHolder.COMMON.muddyPigSpawnBiomes.get();
        E2JModConfig.muddyPigWeight = ConfigHolder.COMMON.muddyPigWeight.get();
        E2JModConfig.muddyPigGroupMin = ConfigHolder.COMMON.muddyPigGroupMin.get();
        E2JModConfig.muddyPigGroupMax = ConfigHolder.COMMON.muddyPigGroupMax.get();

        E2JModConfig.palePigSpawnBiomes = ConfigHolder.COMMON.palePigSpawnBiomes.get();
        E2JModConfig.palePigWeight = ConfigHolder.COMMON.palePigWeight.get();
        E2JModConfig.palePigGroupMin = ConfigHolder.COMMON.palePigGroupMin.get();
        E2JModConfig.palePigGroupMax = ConfigHolder.COMMON.palePigGroupMax.get();

        E2JModConfig.piebaldPigSpawnBiomes = ConfigHolder.COMMON.piebaldPigSpawnBiomes.get();
        E2JModConfig.piebaldPigWeight = ConfigHolder.COMMON.piebaldPigWeight.get();
        E2JModConfig.piebaldPigGroupMin = ConfigHolder.COMMON.piebaldPigGroupMin.get();
        E2JModConfig.piebaldPigGroupMax = ConfigHolder.COMMON.piebaldPigGroupMax.get();

        E2JModConfig.rockySheepSpawnBiomes = ConfigHolder.COMMON.rockySheepSpawnBiomes.get();
        E2JModConfig.rockySheepWeight = ConfigHolder.COMMON.rockySheepWeight.get();
        E2JModConfig.rockySheepGroupMin = ConfigHolder.COMMON.rockySheepGroupMin.get();
        E2JModConfig.rockySheepGroupMax = ConfigHolder.COMMON.rockySheepGroupMax.get();

        E2JModConfig.skeletonWolfSpawnBiomes = ConfigHolder.COMMON.skeletonWolfSpawnBiomes.get();
        E2JModConfig.skeletonWolfWeight = ConfigHolder.COMMON.skeletonWolfWeight.get();
        E2JModConfig.skeletonWolfGroupMin = ConfigHolder.COMMON.skeletonWolfGroupMin.get();
        E2JModConfig.skeletonWolfGroupMax = ConfigHolder.COMMON.skeletonWolfGroupMax.get();

        E2JModConfig.spottedPigSpawnBiomes = ConfigHolder.COMMON.spottedPigSpawnBiomes.get();
        E2JModConfig.spottedPigWeight = ConfigHolder.COMMON.spottedPigWeight.get();
        E2JModConfig.spottedPigGroupMin = ConfigHolder.COMMON.spottedPigGroupMin.get();
        E2JModConfig.spottedPigGroupMax = ConfigHolder.COMMON.spottedPigGroupMax.get();

        E2JModConfig.stormyChickenSpawnBiomes = ConfigHolder.COMMON.stormyChickenSpawnBiomes.get();
        E2JModConfig.stormyChickenWeight = ConfigHolder.COMMON.stormyChickenWeight.get();
        E2JModConfig.stormyChickenGroupMin = ConfigHolder.COMMON.stormyChickenGroupMin.get();
        E2JModConfig.stormyChickenGroupMax = ConfigHolder.COMMON.stormyChickenGroupMax.get();

        E2JModConfig.sunsetCowSpawnBiomes = ConfigHolder.COMMON.sunsetCowSpawnBiomes.get();
        E2JModConfig.sunsetCowWeight = ConfigHolder.COMMON.sunsetCowWeight.get();
        E2JModConfig.sunsetCowGroupMin = ConfigHolder.COMMON.sunsetCowGroupMin.get();
        E2JModConfig.sunsetCowGroupMax = ConfigHolder.COMMON.sunsetCowGroupMax.get();

        E2JModConfig.tropicalSlimeSpawnBiomes = ConfigHolder.COMMON.tropicalSlimeSpawnBiomes.get();
        E2JModConfig.tropicalSlimeWeight = ConfigHolder.COMMON.tropicalSlimeWeight.get();
        E2JModConfig.tropicalSlimeGroupMin = ConfigHolder.COMMON.tropicalSlimeGroupMin.get();
        E2JModConfig.tropicalSlimeGroupMax = ConfigHolder.COMMON.tropicalSlimeGroupMax.get();

        E2JModConfig.vestedRabbitSpawnBiomes = ConfigHolder.COMMON.vestedRabbitSpawnBiomes.get();
        E2JModConfig.vestedRabbitWeight = ConfigHolder.COMMON.vestedRabbitWeight.get();
        E2JModConfig.vestedRabbitGroupMin = ConfigHolder.COMMON.vestedRabbitGroupMin.get();
        E2JModConfig.vestedRabbitGroupMax = ConfigHolder.COMMON.vestedRabbitGroupMax.get();

        E2JModConfig.woolyCowSpawnBiomes = ConfigHolder.COMMON.woolyCowSpawnBiomes.get();
        E2JModConfig.woolyCowWeight = ConfigHolder.COMMON.woolyCowWeight.get();
        E2JModConfig.woolyCowGroupMin = ConfigHolder.COMMON.woolyCowGroupMin.get();
        E2JModConfig.woolyCowGroupMax = ConfigHolder.COMMON.woolyCowGroupMax.get();

        E2JModConfig.furnaceGolemSpawnBiomes = ConfigHolder.COMMON.furnaceGolemSpawnBiomes.get();
        E2JModConfig.furnaceGolemWeight = ConfigHolder.COMMON.furnaceGolemWeight.get();
        E2JModConfig.furnaceGolemGroupMin = ConfigHolder.COMMON.furnaceGolemGroupMin.get();
        E2JModConfig.furnaceGolemGroupMax = ConfigHolder.COMMON.furnaceGolemGroupMax.get();

        E2JModConfig.melonGolemSpawnBiomes = ConfigHolder.COMMON.melonGolemSpawnBiomes.get();
        E2JModConfig.melonGolemWeight = ConfigHolder.COMMON.melonGolemWeight.get();
        E2JModConfig.melonGolemGroupMin = ConfigHolder.COMMON.melonGolemGroupMin.get();
        E2JModConfig.melonGolemGroupMax = ConfigHolder.COMMON.melonGolemGroupMax.get();

        E2JModConfig.albinoCowSpawnBiomes = ConfigHolder.COMMON.albinoCowSpawnBiomes.get();
        E2JModConfig.albinoCowWeight = ConfigHolder.COMMON.albinoCowWeight.get();
        E2JModConfig.albinoCowGroupMin = ConfigHolder.COMMON.albinoCowGroupMin.get();
        E2JModConfig.albinoCowGroupMax = ConfigHolder.COMMON.albinoCowGroupMax.get();

        E2JModConfig.boneSpiderSpawnBiomes = ConfigHolder.COMMON.boneSpiderSpawnBiomes.get();
        E2JModConfig.boneSpiderWeight = ConfigHolder.COMMON.boneSpiderWeight.get();
        E2JModConfig.boneSpiderGroupMin = ConfigHolder.COMMON.boneSpiderGroupMin.get();
        E2JModConfig.boneSpiderGroupMax = ConfigHolder.COMMON.boneSpiderGroupMax.get();

        E2JModConfig.jumboRabbitSpawnBiomes = ConfigHolder.COMMON.jumboRabbitSpawnBiomes.get();
        E2JModConfig.jumboRabbitWeight = ConfigHolder.COMMON.jumboRabbitWeight.get();
        E2JModConfig.jumboRabbitGroupMin = ConfigHolder.COMMON.jumboRabbitGroupMin.get();
        E2JModConfig.jumboRabbitGroupMax = ConfigHolder.COMMON.jumboRabbitGroupMax.get();

        E2JModConfig.jollyLlamaSpawnBiomes = ConfigHolder.COMMON.jollyLlamaSpawnBiomes.get();
        E2JModConfig.jollyLlamaWeight = ConfigHolder.COMMON.jollyLlamaWeight.get();
        E2JModConfig.jollyLlamaGroupMin = ConfigHolder.COMMON.jollyLlamaGroupMin.get();
        E2JModConfig.jollyLlamaGroupMax = ConfigHolder.COMMON.jollyLlamaGroupMax.get();

        E2JModConfig.rainbowSheepSpawnBiomes = ConfigHolder.COMMON.rainbowSheepSpawnBiomes.get();
        E2JModConfig.rainbowSheepWeight = ConfigHolder.COMMON.rainbowSheepWeight.get();
        E2JModConfig.rainbowSheepGroupMin = ConfigHolder.COMMON.rainbowSheepGroupMin.get();
        E2JModConfig.rainbowSheepGroupMax = ConfigHolder.COMMON.rainbowSheepGroupMax.get();
    }

    public static void bakeServer(final net.minecraftforge.fml.config.ModConfig config) {
    }

}