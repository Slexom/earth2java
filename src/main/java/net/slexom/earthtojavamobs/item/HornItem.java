//package net.slexom.earthtojavamobs.item;
//
//import net.minecraftforge.fml.DeferredWorkQueue;
//import net.minecraftforge.registries.ObjectHolder;
//
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.Item;
//import net.minecraft.block.BlockState;
//import net.slexom.earthtojavamobs.EarthtojavamobsModElements;
//
//
//@EarthtojavamobsModElements.ModElement.Tag
//public class HornItem extends EarthtojavamobsModElements.ModElement {
//    @ObjectHolder("earthtojavamobs:horn")
//    public static final Item block = null;
//    public HornItem(EarthtojavamobsModElements instance) {
//        super(instance, 9);
//    }
//
//    @Override
//    public void initElements() {
//        DeferredWorkQueue.runLater(() -> elements.items.add(ItemCustom::new));
//    }
//
//    public static class ItemCustom extends Item {
//        public ItemCustom() {
//            super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64));
//            setRegistryName("horn");
//        }
//
//        @Override
//        public int getItemEnchantability() {
//            return 0;
//        }
//
//        @Override
//        public int getUseDuration(ItemStack itemstack) {
//            return 0;
//        }
//
//        @Override
//        public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
//            return 1F;
//        }
//    }
//}
