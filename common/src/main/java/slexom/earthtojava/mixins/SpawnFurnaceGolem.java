package slexom.earthtojava.mixins;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class SpawnFurnaceGolem {

    @Final
    @Shadow
    private static Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE;

    private BlockPattern furnaceGolemPattern;

    private BlockPattern getFurnaceGolemPattern() {
        if (this.furnaceGolemPattern == null) {
            this.furnaceGolemPattern = BlockPatternBuilder.start().aisle("~^~", "#@#", "~#~").where('@', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.furnaceGolemPattern;
    }

    @Inject(at = @At("HEAD"), method = "trySpawnEntity")
    public void spawnFurnaceGolem(World world, BlockPos pos, CallbackInfo ci) {
        BlockPattern.Result furnaceGolemResult = this.getFurnaceGolemPattern().searchAround(world, pos);
        if (furnaceGolemResult != null) {
            for (int w = 0; w < this.getFurnaceGolemPattern().getWidth(); ++w) {
                for (int h = 0; h < this.getFurnaceGolemPattern().getHeight(); ++h) {
                    CachedBlockPosition cachedBlockPosition3 = furnaceGolemResult.translate(w, h, 0);
                    world.setBlockState(cachedBlockPosition3.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
                    world.syncWorldEvent(2001, cachedBlockPosition3.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition3.getBlockState()));
                }
            }
            BlockPos blockPos2 = furnaceGolemResult.translate(1, 2, 0).getBlockPos();
            FurnaceGolemEntity furnaceGolemEntity = EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.create(world);
            furnaceGolemEntity.setPlayerCreated(true);
            furnaceGolemEntity.refreshPositionAndAngles((double) blockPos2.getX() + 0.5D, (double) blockPos2.getY() + 0.05D, (double) blockPos2.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(furnaceGolemEntity);
            for (ServerPlayerEntity serverPE : world.getNonSpectatingEntities(ServerPlayerEntity.class, furnaceGolemEntity.getBoundingBox().expand(5.0D))) {
                Criteria.SUMMONED_ENTITY.trigger(serverPE, furnaceGolemEntity);
            }
            for (int w2 = 0; w2 < this.getFurnaceGolemPattern().getWidth(); ++w2) {
                for (int h2 = 0; h2 < this.getFurnaceGolemPattern().getHeight(); ++h2) {
                    CachedBlockPosition cachedBlockPosition4 = furnaceGolemResult.translate(w2, h2, 0);
                    world.updateNeighbors(cachedBlockPosition4.getBlockPos(), Blocks.AIR);
                }
            }
        }
    }
}
