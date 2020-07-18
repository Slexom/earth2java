package slexom.earthtojava.mobs.entity.merchant.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TraderOfferList;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.init.ItemInit;
import slexom.earthtojava.mobs.utils.TradesHelper;


public class E2JWanderingTraderEntity extends WanderingTraderEntity {

    public static final Int2ObjectMap<TradeOffers.Factory[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
            1, new TradeOffers.Factory[]{
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
            2, new TradeOffers.Factory[]{new TradesHelper.ItemsForRubiesTrade(ItemInit.MUD_BUCKET, 1, 1, 4, 1)}
    ));

    public E2JWanderingTraderEntity(EntityType<? extends WanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static DefaultAttributeContainer.Builder createWanderingTraderAttributes() {
        return MobEntity.createMobAttributes();
    }

    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = TRADES.get(1);
        TradeOffers.Factory[] factorys2 = TRADES.get(2);
        if (factorys != null && factorys2 != null) {
            TraderOfferList traderOfferList = this.getOffers();
            this.fillRecipesFromPool(traderOfferList, factorys, 5);
            int i = this.random.nextInt(factorys2.length);
            TradeOffers.Factory factory = factorys2[i];
            TradeOffer tradeOffer = factory.create(this, this.random);
            if (tradeOffer != null) {
                traderOfferList.add(tradeOffer);
            }
        }
    }

}
