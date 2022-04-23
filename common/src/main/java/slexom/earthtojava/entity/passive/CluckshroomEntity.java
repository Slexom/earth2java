package slexom.earthtojava.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.CluckshroomPlaceBlockGoal;
import slexom.earthtojava.entity.base.E2JBaseChickenEntity;


public class CluckshroomEntity extends E2JBaseChickenEntity {

    public CluckshroomEntity(EntityType<CluckshroomEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new AvoidSunlightGoal(this));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
        this.goalSelector.add(3, new CluckshroomPlaceBlockGoal(this));
    }

}
