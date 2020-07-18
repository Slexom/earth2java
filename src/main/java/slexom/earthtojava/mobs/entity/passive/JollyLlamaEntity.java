package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseLlamaEntity;

import javax.annotation.Nullable;

public class JollyLlamaEntity extends E2JBaseLlamaEntity {

    public JollyLlamaEntity(EntityType<JollyLlamaEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
        this.goalSelector.add(2, new FormCaravanGoal(this, 2.1D));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public boolean setSaddled() {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack stack) {
        return false;
    }

    @Override
    @Nullable
    public DyeColor getCarpetColor() {
        return null;
    }
}
