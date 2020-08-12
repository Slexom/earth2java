package slexom.earthtojava.mobs.world.gen.decorator;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;

import java.util.Random;
import java.util.stream.Stream;

public class MudLakeDecorator extends Decorator<ChanceDecoratorConfig> {
    public MudLakeDecorator(Codec<ChanceDecoratorConfig> codec) {
        super(codec);
    }


    public Stream<BlockPos> getPositions(DecoratorContext decoratorContext, Random random, ChanceDecoratorConfig chanceDecoratorConfig, BlockPos blockPos) {
        if (random.nextInt(chanceDecoratorConfig.chance) == 0) {
            int i = random.nextInt(16) + blockPos.getX();
            int j = random.nextInt(16) + blockPos.getZ();
            int k = random.nextInt(decoratorContext.getMaxY());
            return Stream.of(new BlockPos(i, k, j));
        } else {
            return Stream.empty();
        }
    }
}
