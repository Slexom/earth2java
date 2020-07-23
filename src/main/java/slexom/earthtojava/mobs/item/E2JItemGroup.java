package slexom.earthtojava.mobs.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.mobs.EarthToJavaMobsMod;
import slexom.earthtojava.mobs.init.ItemInit;

public class E2JItemGroup extends ItemGroup {

    public static final E2JItemGroup instance = new E2JItemGroup(ItemGroup.GROUPS.length, EarthToJavaMobsMod.MOD_ID);

    private E2JItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemInit.HORN.get());
    }

}
