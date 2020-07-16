package slexom.earthtojava.mobs.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class RubyOreBlock extends OreBlock {

    public RubyOreBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected int getExperienceWhenMined(Random rand) {
        return MathHelper.nextInt(rand, 3, 7);
    }

    public void onStacksDropped(BlockState state, World worldIn, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, worldIn, pos, stack);
    }

}
