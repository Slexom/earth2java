package slexom.earthtojava.mobs.world.spawner;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.TraderLlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;
import slexom.earthtojava.mobs.init.EntityTypesInit;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

public class E2JWanderingTraderManager {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    private final static int CHANCE = config.modWanderingTrader.chance;

    public static void tick(ServerWorld serverWorld) {
        if (config.modWanderingTrader.canSpawn) {
            if (serverWorld.getGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING)) {
                if (serverWorld.getTimeOfDay() % (24000 * config.modWanderingTrader.delay) == 1500) {
                    if (serverWorld.getRandom().nextInt(100) < CHANCE) {
                        spawnTrader(serverWorld);
                    }
                }
            }
        }
    }

    private static void spawnTrader(ServerWorld serverWorld) {
        PlayerEntity playerentity = serverWorld.getRandomAlivePlayer();
        ServerWorldProperties serverWorldProperties = serverWorld.getServer().getSaveProperties().getMainWorldProperties();
        if (playerentity != null) {
            BlockPos blockPos = playerentity.getBlockPos();
            PointOfInterestStorage pointOfInterestStorage = serverWorld.getPointOfInterestStorage();
            Optional<BlockPos> optional = pointOfInterestStorage.getPosition(PointOfInterestType.MEETING.getCompletionCondition(), (blockPosX) -> true, blockPos, 48, PointOfInterestStorage.OccupationStatus.ANY);
            BlockPos blockPos2 = optional.orElse(blockPos);
            BlockPos blockPos3 = getLlamaSpawnPosition(serverWorld, blockPos2, 48);
            if (blockPos3 != null && wontSuffocateAt(serverWorld, blockPos3)) {
                if (serverWorld.method_31081(blockPos3).equals(Optional.of(BiomeKeys.THE_VOID))) {
                    return;
                }
                E2JWanderingTraderEntity traderEntity = EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT.spawn(serverWorld, (CompoundTag) null, (Text) null, (PlayerEntity) null, blockPos3, SpawnReason.EVENT, false, false);
                if (traderEntity != null) {
                    for (int j = 0; j < 2; ++j) {
                        spawnTraderLlama(serverWorld, traderEntity);
                    }
                    serverWorldProperties.setWanderingTraderId(traderEntity.getUuid());
                    traderEntity.setDespawnDelay(32000);
                    traderEntity.setWanderTarget(blockPos2);
                    traderEntity.setPositionTarget(blockPos2, 16);
                }
            }
        }
    }

    private static void spawnTraderLlama(ServerWorld serverWorld, E2JWanderingTraderEntity wanderingTrader) {
        BlockPos blockpos = getLlamaSpawnPosition(wanderingTrader.world, wanderingTrader.getBlockPos(), 4);
        if (blockpos != null) {
            TraderLlamaEntity traderLlamaEntity = EntityType.TRADER_LLAMA.spawn(serverWorld, (CompoundTag) null, (Text) null, (PlayerEntity) null, blockpos, SpawnReason.EVENT, false, false);
            if (traderLlamaEntity != null) {
                traderLlamaEntity.attachLeash(wanderingTrader, true);
            }
        }
    }

    @Nullable
    private static BlockPos getLlamaSpawnPosition(WorldView worldView, BlockPos blockPos, int i) {
        Random random = new Random();
        BlockPos blockPos2 = null;
        for (int j = 0; j < 10; ++j) {
            int k = blockPos.getX() + random.nextInt(i * 2) - i;
            int l = blockPos.getZ() + random.nextInt(i * 2) - i;
            int m = worldView.getTopY(Heightmap.Type.WORLD_SURFACE, k, l);
            BlockPos blockPos3 = new BlockPos(k, m, l);
            if (SpawnHelper.canSpawn(SpawnRestriction.Location.ON_GROUND, worldView, blockPos3, EntityType.WANDERING_TRADER)) {
                blockPos2 = blockPos3;
                break;
            }
        }
        return blockPos2;
    }


    private static boolean wontSuffocateAt(BlockView blockView, BlockPos blockPos) {
        Iterator var3 = BlockPos.iterate(blockPos, blockPos.add(1, 2, 1)).iterator();
        BlockPos blockPos2;
        do {
            if (!var3.hasNext()) {
                return true;
            }
            blockPos2 = (BlockPos) var3.next();
        } while (blockView.getBlockState(blockPos2).getCollisionShape(blockView, blockPos2).isEmpty());
        return false;
    }

}
