package net.mcreator.earthtojavamobs.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.earthtojavamobs.EarthtojavamobsModElements;

import java.util.Random;

@EarthtojavamobsModElements.ModElement.Tag
public class WoolyCowRightClickedOnEntityProcedure extends EarthtojavamobsModElements.ModElement {
    public WoolyCowRightClickedOnEntityProcedure(EarthtojavamobsModElements instance) {
        super(instance, 4);
    }

    public static boolean executeProcedure(java.util.HashMap<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            System.err.println("Failed to load dependency entity for procedure WoolyCowRightClickedOnEntity!");
            return false;
        }
        if (dependencies.get("sourceentity") == null) {
            System.err.println("Failed to load dependency sourceentity for procedure WoolyCowRightClickedOnEntity!");
            return false;
        }
        if (dependencies.get("itemstack") == null) {
            System.err.println("Failed to load dependency itemstack for procedure WoolyCowRightClickedOnEntity!");
            return false;
        }
        Entity entity = (Entity) dependencies.get("entity");
        Entity sourceentity = (Entity) dependencies.get("sourceentity");
        ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
        boolean sheared = (boolean) dependencies.get("sheared");

        System.out.println("in procedure");
        System.out.println(sheared);
        if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
                .getItem() == new ItemStack(Items.SHEARS, (int) (1)).getItem())) {
            if (!sheared) {
                // entity.playSound("mob.sheep.shear", 1.0F, 1.0F); TODO: sound
                {
                    ItemStack _ist = (itemstack);
                    if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
                        _ist.shrink(1);
                        _ist.setDamage(0);
                    }
                }
                if (sourceentity instanceof PlayerEntity) {
                    ItemStack _setstack = new ItemStack(Blocks.BROWN_WOOL, (int) (1));
                    _setstack.setCount((int) Math.round((2 + (Math.random() + Math.random()))));
                    ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
                }
                return true;
            }
        }
        return sheared;
    }
}
