package slexom.earthtojava.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.init.BlockInit;

public class ModEvents {

    public static void init() {
        melonBlockShearEvent();
    }

    private static void melonBlockShearEvent() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClient()) {
                return ActionResult.PASS;
            }
            BlockState blockState = world.getBlockState(hitResult.getBlockPos());
            Block block = blockState.getBlock();
            if (block == Blocks.MELON) {
                BlockPos pos = hitResult.getBlockPos();
                ItemStack itemStack = player.getStackInHand(hand);
                if (itemStack.getItem() instanceof ShearsItem) {
                    Direction direction = hitResult.getSide();
                    Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                    world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, BlockInit.CARVED_MELON.getDefaultState().with(CarvedMelonBlock.FACING, direction2), 11);
                    ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D + (double) direction2.getOffsetX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction2.getOffsetZ() * 0.65D, new ItemStack(Items.MELON_SEEDS, 4));
                    itemEntity.setVelocity(0.05D * (double) direction2.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction2.getOffsetZ() + world.random.nextDouble() * 0.02D);
                    world.spawnEntity(itemEntity);
                    itemStack.damage(1, player, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));
                    return ActionResult.success(world.isClient);
                } else {
                    return ActionResult.PASS;
                }
            }
            return ActionResult.PASS;
        });
    }

}
