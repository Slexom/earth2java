//package slexom.earthtojava.utils;
//
//import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
//import net.fabricmc.fabric.api.tag.TagRegistry;
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionUtil;
//import net.minecraft.tag.ItemTags;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.village.TradeOffer;
//import net.minecraft.village.TradeOffers;
//import slexom.earthtojava.config.ModConfig;
//import slexom.earthtojava.init.ItemInit;
//
//import java.util.Random;
//
//public class TradesHelper {
//
//    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
//
//    private static final float BASE_POTION_PRICE = 4.0F;
//    private static final float BONUS_POTION_MULTIPLIER = 1.25F;
//    private static final float MALUS_POTION_MULTIPLIER = 1.55F;
//    private static final float LONG_POTION_MULTIPLIER = 2.65F;
//    private static final float STRONG_POTION_MULTIPLIER = 1.75F;
//
//    public static TradeOffers.Factory POTION_FIRE_RESISTANCE = new PotionForRubiesTrade("minecraft:fire_resistance", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_HARMING = new PotionForRubiesTrade("minecraft:harming", currencyAmount(MALUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_HEALING = new PotionForRubiesTrade("minecraft:healing", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_INVISIBILITY = new PotionForRubiesTrade("minecraft:invisibility", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LEAPING = new PotionForRubiesTrade("minecraft:leaping", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_FIRE_RESISTANCE = new PotionForRubiesTrade("minecraft:long_fire_resistance", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_INVISIBILITY = new PotionForRubiesTrade("minecraft:long_invisibility", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_LEAPING = new PotionForRubiesTrade("minecraft:long_leaping", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_NIGHT_VISION = new PotionForRubiesTrade("minecraft:long_night_vision", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_POISON = new PotionForRubiesTrade("minecraft:long_poison", currencyAmount(MALUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_REGENERATION = new PotionForRubiesTrade("minecraft:long_regeneration", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_SLOWNESS = new PotionForRubiesTrade("minecraft:long_slowness", currencyAmount(MALUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_SLOW_FALLING = new PotionForRubiesTrade("minecraft:long_slow_falling", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_STRENGTH = new PotionForRubiesTrade("minecraft:long_strength", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_SWIFTNESS = new PotionForRubiesTrade("minecraft:long_swiftness", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_TURTLE_MASTER = new PotionForRubiesTrade("minecraft:long_turtle_master", currencyAmount(LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_WATER_BREATHING = new PotionForRubiesTrade("minecraft:long_water_breathing", currencyAmount(BONUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LONG_WEAKNESS = new PotionForRubiesTrade("minecraft:long_weakness", currencyAmount(MALUS_POTION_MULTIPLIER, LONG_POTION_MULTIPLIER), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_LUCK = new PotionForRubiesTrade("minecraft:luck", currencyAmount(BONUS_POTION_MULTIPLIER, 3.33F), 1, 2, 1, 0.05F);
//    public static TradeOffers.Factory POTION_NIGHT_VISION = new PotionForRubiesTrade("minecraft:night_vision", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_POISON = new PotionForRubiesTrade("minecraft:poison", currencyAmount(MALUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_REGENERATION = new PotionForRubiesTrade("minecraft:regeneration", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_SLOWNESS = new PotionForRubiesTrade("minecraft:slowness", currencyAmount(MALUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_SLOW_FALLING = new PotionForRubiesTrade("minecraft:slow_falling", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRENGTH = new PotionForRubiesTrade("minecraft:strength", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_HARMING = new PotionForRubiesTrade("minecraft:strong_harming", currencyAmount(MALUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_HEALING = new PotionForRubiesTrade("minecraft:strong_healing", currencyAmount(BONUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_LEAPING = new PotionForRubiesTrade("minecraft:strong_leaping", currencyAmount(BONUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_POISON = new PotionForRubiesTrade("minecraft:strong_poison", currencyAmount(MALUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_REGENERATION = new PotionForRubiesTrade("minecraft:strong_regeneration", currencyAmount(BONUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_SLOWNESS = new PotionForRubiesTrade("minecraft:strong_slowness", currencyAmount(MALUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_STRENGTH = new PotionForRubiesTrade("minecraft:strong_strength", currencyAmount(BONUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_SWIFTNESS = new PotionForRubiesTrade("minecraft:strong_swiftness", currencyAmount(BONUS_POTION_MULTIPLIER, STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_STRONG_TURTLE_MASTER = new PotionForRubiesTrade("minecraft:strong_turtle_master", currencyAmount(STRONG_POTION_MULTIPLIER), 1, 3, 1, 0.05F);
//    public static TradeOffers.Factory POTION_SWIFTNESS = new PotionForRubiesTrade("minecraft:swiftness", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_TURTLE_MASTER = new PotionForRubiesTrade("minecraft:turtle_master", currencyAmount(), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_WATER_BREATHING = new PotionForRubiesTrade("minecraft:water_breathing", currencyAmount(BONUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//    public static TradeOffers.Factory POTION_WEAKNESS = new PotionForRubiesTrade("minecraft:weakness", currencyAmount(MALUS_POTION_MULTIPLIER), 1, 4, 1, 0.05F);
//
//    private static int currencyAmount(float... modifiers) {
//        float amount = BASE_POTION_PRICE;
//        for (float value : modifiers) {
//            amount *= value;
//        }
//        return Math.round(amount);
//    }
//
//    private static Item getCurrencyItem() {
//        Item defaultCurrency = Registry.ITEM.get(new Identifier(config.modWanderingTrader.currencyItem));
//        if (defaultCurrency == null) {
//            defaultCurrency = ItemInit.RUBY;
//        }
//        if (!defaultCurrency.isIn(TagRegistry.item(new Identifier("c:rubies")))) {
//            defaultCurrency = ItemInit.RUBY;
//        }
//        return config.rubyOre.canGenerate ? defaultCurrency : Items.EMERALD;
//    }
//
//
//    public static class ItemsForRubiesTrade implements TradeOffers.Factory {
//        private final ItemStack itemStack;
//        private final int currencyAmount;
//        private final int sellingItemAmount;
//        private final int maxInStock;
//        private final int givenExp;
//        private final float priceMultiplier;
//
//        public ItemsForRubiesTrade(Block block, int currencyAmount, int sellingItemAmount, int maxInStock, int givenExp) {
//            this(new ItemStack(block), currencyAmount, sellingItemAmount, maxInStock, givenExp);
//        }
//
//        public ItemsForRubiesTrade(Item item, int currencyAmount, int sellingItemAmount, int givenExp) {
//            this(new ItemStack(item), currencyAmount, sellingItemAmount, 12, givenExp);
//        }
//
//        public ItemsForRubiesTrade(Item item, int currencyAmount, int sellingItemAmount, int maxInStock, int givenExp) {
//            this(new ItemStack(item), currencyAmount, sellingItemAmount, maxInStock, givenExp);
//        }
//
//        public ItemsForRubiesTrade(ItemStack itemStack, int currencyAmount, int sellingItemAmount, int maxInStock, int givenExp) {
//            this(itemStack, currencyAmount, sellingItemAmount, maxInStock, givenExp, 0.05F);
//        }
//
//        public ItemsForRubiesTrade(ItemStack itemStack, int currencyAmount, int sellingItemAmount, int maxInStock, int givenExp, float priceMultiplier) {
//            this.itemStack = itemStack;
//            this.currencyAmount = currencyAmount;
//            this.sellingItemAmount = sellingItemAmount;
//            this.maxInStock = maxInStock;
//            this.givenExp = givenExp;
//            this.priceMultiplier = priceMultiplier;
//        }
//
//        public TradeOffer create(Entity trader, Random rand) {
//            return new TradeOffer(new ItemStack(TradesHelper::getCurrencyItem, this.currencyAmount), new ItemStack(this.itemStack.getItem(), this.sellingItemAmount), this.maxInStock, this.givenExp, this.priceMultiplier);
//        }
//    }
//
//    public static class PotionForRubiesTrade implements TradeOffers.Factory {
//        private final ItemStack itemStack;
//        private final String potionType;
//        private final int currencyAmount;
//        private final int sellingItemAmount;
//        private final int maxInStock;
//        private final int givenExp;
//        private final float priceMultiplier;
//
//        public PotionForRubiesTrade(String potionType, int currencyAmount, int sellingItemAmount, int maxInStock, int givenExp, float priceMultiplier) {
//            this.itemStack = new ItemStack(Items.POTION);
//            this.potionType = potionType;
//            this.currencyAmount = currencyAmount;
//            this.sellingItemAmount = sellingItemAmount;
//            this.maxInStock = maxInStock;
//            this.givenExp = givenExp;
//            this.priceMultiplier = priceMultiplier;
//        }
//
//        public TradeOffer create(Entity trader, Random rand) {
//            Potion potion = Registry.POTION.get(new Identifier(this.potionType));
//            ItemStack potionItemStack = PotionUtil.setPotion(new ItemStack(this.itemStack.getItem(), sellingItemAmount), potion);
//            return new TradeOffer(new ItemStack(TradesHelper::getCurrencyItem, this.currencyAmount), potionItemStack, this.maxInStock, this.givenExp, this.priceMultiplier);
//        }
//    }
//}
