package slexom.earthtojava.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.init.EntityTypesInit;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class SpawnFurnaceGolem {

    @Final
    @Shadow
    private static Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE;

    private BlockPattern furnaceGolemPattern;
    private BlockPattern furnaceGolemDispenserPattern;

    private BlockPattern e2j_getFurnaceGolemPattern() {
        if (this.furnaceGolemPattern == null) {
            this.furnaceGolemPattern = BlockPatternBuilder.start().aisle("~^~", "#@#", "~#~").where('@', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.furnaceGolemPattern;
    }

    private BlockPattern e2j_getFurnaceGolemDispenserPattern() {
        if (this.furnaceGolemDispenserPattern == null) {
            this.furnaceGolemDispenserPattern = BlockPatternBuilder.start().aisle("~ ~", "#@#", "~#~").where('@', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.BLAST_FURNACE))).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.furnaceGolemDispenserPattern;
    }

    @Inject(method = "canDispense", at = @At("RETURN"), cancellable = true)
    public void e2j_canDispense(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(this.e2j_getFurnaceGolemDispenserPattern().searchAround(world, pos) != null);
        }
    }

    @Inject(at = @At("HEAD"), method = "trySpawnEntity")
    public void e2j_spawnFurnaceGolem(World world, BlockPos pos, CallbackInfo ci) {
        BlockPattern.Result result = this.e2j_getFurnaceGolemPattern().searchAround(world, pos);
        if (result == null) return;

        FurnaceGolemEntity furnaceGolemEntity = EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT.get().create(world);
        if (furnaceGolemEntity == null) return;

        furnaceGolemEntity.setPlayerCreated(true);
        CarvedPumpkinBlock.spawnEntity(world, result, furnaceGolemEntity, result.translate(1, 2, 0).getBlockPos());
    }
}
