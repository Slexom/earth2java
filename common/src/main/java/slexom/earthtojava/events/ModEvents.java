package slexom.earthtojava.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import slexom.earthtojava.block.CarvedMelonBlock;
import slexom.earthtojava.init.BlockInit;

public class ModEvents {

    public static void init() {
        melonBlockShearEvent();
    }

    private static void melonBlockShearEvent() {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) -> {
            World world = player.getEntityWorld();
            if (world.isClient()) {
                return EventResult.pass();
            }
            BlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();
            if (block == Blocks.MELON) {
                ItemStack itemStack = player.getStackInHand(hand);
                if (itemStack.getItem() instanceof ShearsItem) {
                    Direction direction = face.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : face;
                    world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, BlockInit.CARVED_MELON.get().getDefaultState().with(CarvedMelonBlock.FACING, direction), 11);
                    ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D + (double) direction.getOffsetX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction.getOffsetZ() * 0.65D, new ItemStack(Items.MELON_SEEDS, 4));
                    itemEntity.setVelocity(0.05D * (double) direction.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction.getOffsetZ() + world.random.nextDouble() * 0.02D);
                    world.spawnEntity(itemEntity);
                    itemStack.damage(1, player, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));
                    return EventResult.interrupt(world.isClient);
                } else {
                    return EventResult.pass();
                }
            }
            return EventResult.pass();
        });


    }

}
