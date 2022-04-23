package slexom.earthtojava.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slexom.earthtojava.entity.ai.goal.MoolipPlaceBlockGoal;
import slexom.earthtojava.entity.base.E2JBaseCowEntity;
import slexom.earthtojava.init.BlockInit;

public class MoolipEntity extends E2JBaseCowEntity implements Shearable {

    public MoolipEntity(EntityType<MoolipEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new MoolipPlaceBlockGoal(this));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof ShearsItem && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            if (!this.world.isClient) {
                itemStack.damage(1, player, ((playerEntity) -> playerEntity.sendToolBreakStatus(hand)));
            }
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity((PlayerEntity) null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.world.isClient()) {
            ((ServerWorld) this.world).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.remove(RemovalReason.KILLED);
            CowEntity cowEntity = (CowEntity) EntityType.COW.create(this.world);
            cowEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
            cowEntity.setHealth(this.getHealth());
            cowEntity.bodyYaw = this.bodyYaw;
            if (this.hasCustomName()) {
                cowEntity.setCustomName(this.getCustomName());
                cowEntity.setCustomNameVisible(this.isCustomNameVisible());
            }
            if (this.isPersistent()) {
                cowEntity.setPersistent();
            }
            cowEntity.setInvulnerable(this.isInvulnerable());
            this.world.spawnEntity(cowEntity);
            for (int i = 0; i < 5; ++i) {
                this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getBodyY(1.0D), this.getZ(), new ItemStack(BlockInit.PINK_DAISY.get())));
            }
        }

    }

    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }

}