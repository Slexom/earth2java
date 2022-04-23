package slexom.earthtojava.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.JollyLlamaEatFernGoal;
import slexom.earthtojava.entity.base.E2JBaseLlamaEntity;
import slexom.earthtojava.init.SoundEventsInit;

import javax.annotation.Nullable;

public class JollyLlamaEntity extends E2JBaseLlamaEntity {

    private int eatFernTimer;
    private JollyLlamaEatFernGoal eatFernGoal;

    public JollyLlamaEntity(EntityType<JollyLlamaEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void initGoals() {
        this.eatFernGoal = new JollyLlamaEatFernGoal(this);
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
        this.goalSelector.add(2, new FormCaravanGoal(this, 2.1D));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(2, this.eatFernGoal);
        this.goalSelector.add(5, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    protected void mobTick() {
        this.eatFernTimer = this.eatFernGoal.getTimer();
        super.mobTick();
    }

    public void tickMovement() {
        if (this.world.isClient) {
            this.eatFernTimer = Math.max(0, this.eatFernTimer - 1);
        }
        super.tickMovement();
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 10) {
            this.eatFernTimer = 40;
        } else {
            super.handleStatus(status);
        }

    }

    @Environment(EnvType.CLIENT)
    public float getNeckAngle(float delta) {
        if (this.eatFernTimer <= 0) {
            return 0.0F;
        } else if (this.eatFernTimer >= 4 && this.eatFernTimer <= 36) {
            return 1.0F;
        } else {
            return this.eatFernTimer < 4 ? ((float) this.eatFernTimer - delta) / 4.0F : -((float) (this.eatFernTimer - 40) - delta) / 4.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public float getHeadAngle(float delta) {
        if (this.eatFernTimer > 4 && this.eatFernTimer <= 36) {
            float f = ((float) (this.eatFernTimer - 4) - delta) / 32.0F;
            return 0.62831855F + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.eatFernTimer > 0 ? 0.62831855F : this.getPitch() * 0.017453292F;
        }
    }

    public void onEatingGrass() {
        this.playSound(SoundEventsInit.JOLLY_LLAMA_EAT.get(), 0.5f, 1.0f);
    }


    @Override
    public void playAmbientSound() {
        super.playAmbientSound();
        this.playSound(SoundEventsInit.JOLLY_LLAMA_BELL.get(), 0.3f, 1.0f);
    }

    @Override
    public boolean canBeSaddled() {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        return false;
    }

    @Override
    @Nullable
    public DyeColor getCarpetColor() {
        return null;
    }
}
