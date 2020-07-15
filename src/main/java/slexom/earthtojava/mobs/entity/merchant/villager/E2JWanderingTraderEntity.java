package slexom.earthtojava.mobs.entity.merchant.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.init.ItemInit;
import slexom.earthtojava.mobs.utils.TradesHelper;


public class E2JWanderingTraderEntity extends WanderingTraderEntity {

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
            1, new VillagerTrades.ITrade[]{
                    TradesHelper.POTION_FIRE_RESISTANCE,
                    TradesHelper.POTION_HARMING,
                    TradesHelper.POTION_HEALING,
                    TradesHelper.POTION_INVISIBILITY,
                    TradesHelper.POTION_LEAPING,
                    TradesHelper.POTION_LONG_FIRE_RESISTANCE,
                    TradesHelper.POTION_LONG_INVISIBILITY,
                    TradesHelper.POTION_LONG_LEAPING,
                    TradesHelper.POTION_LONG_NIGHT_VISION,
                    TradesHelper.POTION_LONG_POISON,
                    TradesHelper.POTION_LONG_REGENERATION,
                    TradesHelper.POTION_LONG_SLOW_FALLING,
                    TradesHelper.POTION_LONG_SLOWNESS,
                    TradesHelper.POTION_LONG_STRENGTH,
                    TradesHelper.POTION_LONG_SWIFTNESS,
                    TradesHelper.POTION_LONG_TURTLE_MASTER,
                    TradesHelper.POTION_LONG_WATER_BREATHING,
                    TradesHelper.POTION_LONG_WEAKNESS,
                    TradesHelper.POTION_LUCK,
                    TradesHelper.POTION_NIGHT_VISION,
                    TradesHelper.POTION_POISON,
                    TradesHelper.POTION_REGENERATION,
                    TradesHelper.POTION_SLOW_FALLING,
                    TradesHelper.POTION_SLOWNESS,
                    TradesHelper.POTION_STRENGTH,
                    TradesHelper.POTION_STRONG_HARMING,
                    TradesHelper.POTION_STRONG_HEALING,
                    TradesHelper.POTION_STRONG_LEAPING,
                    TradesHelper.POTION_STRONG_POISON,
                    TradesHelper.POTION_STRONG_REGENERATION,
                    TradesHelper.POTION_STRONG_SLOWNESS,
                    TradesHelper.POTION_STRONG_STRENGTH,
                    TradesHelper.POTION_STRONG_SWIFTNESS,
                    TradesHelper.POTION_STRONG_TURTLE_MASTER,
                    TradesHelper.POTION_SWIFTNESS,
                    TradesHelper.POTION_TURTLE_MASTER,
                    TradesHelper.POTION_WATER_BREATHING,
                    TradesHelper.POTION_WEAKNESS,
            },
            2, new VillagerTrades.ITrade[]{new TradesHelper.ItemsForRubiesTrade(ItemInit.MUD_BUCKET.get(), 1, 1, 4, 1)}
    ));

    public E2JWanderingTraderEntity(EntityType<? extends WanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
    }


    protected void populateTradeData() {
        VillagerTrades.ITrade[] avillagertrades$itrade = TRADES.get(1);
        VillagerTrades.ITrade[] avillagertrades$itrade1 = TRADES.get(2);
        if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addTrades(merchantoffers, avillagertrades$itrade, 7);
            int i = this.rand.nextInt(avillagertrades$itrade1.length);
            VillagerTrades.ITrade villagertrades$itrade = avillagertrades$itrade1[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.rand);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }
    }

}
