package slexom.earthtojava.init;

import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import slexom.earthtojava.utils.Utils;

public class ModTags {


    public static final TagKey<Biome> HAS_ALBINO_COW = registerBiomeTag("has_albino_cow");
    public static final TagKey<Biome> HAS_AMBER_CHICKEN = registerBiomeTag("has_amber_chicken");
    public static final TagKey<Biome> HAS_ASHEN_COW = registerBiomeTag("has_ashen_cow");
    public static final TagKey<Biome> HAS_BRONZED_CHICKEN = registerBiomeTag("has_bronzed_chicken");
    public static final TagKey<Biome> HAS_BOLD_STRIPED_RABBIT = registerBiomeTag("has_bold_striped_rabbit");
    public static final TagKey<Biome> HAS_COOKIE_COW = registerBiomeTag("has_cookie_cow");
    public static final TagKey<Biome> HAS_CREAM_COW = registerBiomeTag("has_cream_cow");
    public static final TagKey<Biome> HAS_DAIRY_COW = registerBiomeTag("has_dairy_cow");
    public static final TagKey<Biome> HAS_PINTO_COW = registerBiomeTag("has_pinto_cow");
    public static final TagKey<Biome> HAS_CLUCKSHROOM = registerBiomeTag("has_cluckshroom");
    public static final TagKey<Biome> HAS_FANCY_CHICKEN = registerBiomeTag("has_fancy_chicken");
    public static final TagKey<Biome> HAS_FLECKED_SHEEP = registerBiomeTag("has_flecked_sheep");
    public static final TagKey<Biome> HAS_FRECKLED_RABBIT = registerBiomeTag("has_freckled_rabbit");
    public static final TagKey<Biome> HAS_FUZZY_SHEEP = registerBiomeTag("has_fuzzy_sheep");
    public static final TagKey<Biome> HAS_GOLD_CRESTED_CHICKEN = registerBiomeTag("has_gold_crested_chicken");
    public static final TagKey<Biome> HAS_HARELEQUIN_RABBIT = registerBiomeTag("has_harelequin_rabbit");
    public static final TagKey<Biome> HAS_HORNED_SHEEP = registerBiomeTag("has_horned_sheep");
    public static final TagKey<Biome> HAS_INKY_SHEEP = registerBiomeTag("has_inky_sheep");
    public static final TagKey<Biome> HAS_JOLLY_LLAMA = registerBiomeTag("has_jolly_llama");
    public static final TagKey<Biome> HAS_JUMBO_RABBIT = registerBiomeTag("has_jumbo_rabbit");
    public static final TagKey<Biome> HAS_LONG_NOSED_SHEEP = registerBiomeTag("has_long_nosed_sheep");
    public static final TagKey<Biome> HAS_MIDNIGHT_CHICKEN = registerBiomeTag("has_midnight_chicken");
    public static final TagKey<Biome> HAS_MOOBLOOM = registerBiomeTag("has_moobloom");
    public static final TagKey<Biome> HAS_MOOLIP = registerBiomeTag("has_moolip");
    public static final TagKey<Biome> HAS_MOTTLED_PIG = registerBiomeTag("has_mottled_pig");
    public static final TagKey<Biome> HAS_MUDDY_PIG = registerBiomeTag("has_muddy_pig");
    public static final TagKey<Biome> HAS_MUDDY_FOOT_RABBIT = registerBiomeTag("has_muddy_foot_rabbit");
    public static final TagKey<Biome> HAS_PALE_PIG = registerBiomeTag("has_pale_pig");
    public static final TagKey<Biome> HAS_PATCHED_SHEEP = registerBiomeTag("has_patched_sheep");
    public static final TagKey<Biome> HAS_PIEBALD_PIG = registerBiomeTag("has_piebald_pig");
    public static final TagKey<Biome> HAS_PINK_FOOTED_PIG = registerBiomeTag("has_pink_footed_pig");
    public static final TagKey<Biome> HAS_RAINBOW_SHEEP = registerBiomeTag("has_rainbow_sheep");
    public static final TagKey<Biome> HAS_ROCKY_SHEEP = registerBiomeTag("has_rocky_sheep");
    public static final TagKey<Biome> HAS_SKEWBALD_CHICKEN = registerBiomeTag("has_skewbald_chicken");
    public static final TagKey<Biome> HAS_SOOTY_PIG = registerBiomeTag("has_sooty_pig");
    public static final TagKey<Biome> HAS_SPOTTED_PIG = registerBiomeTag("has_spotted_pig");
    public static final TagKey<Biome> HAS_STORMY_CHICKEN = registerBiomeTag("has_stormy_chicken");
    public static final TagKey<Biome> HAS_SUNSET_COW = registerBiomeTag("has_sunset_cow");
    public static final TagKey<Biome> HAS_UMBRA_COW = registerBiomeTag("has_umbra_cow");
    public static final TagKey<Biome> HAS_VESTED_RABBIT = registerBiomeTag("has_vested_rabbit");
    public static final TagKey<Biome> HAS_WOOLY_COW = registerBiomeTag("has_wooly_cow");
    public static final TagKey<Biome> HAS_BONE_SPIDER = registerBiomeTag("has_bone_spider");
    public static final TagKey<Biome> HAS_BOULDERING_ZOMBIE = registerBiomeTag("has_bouldering_zombie");
    public static final TagKey<Biome> HAS_LOBBER_ZOMBIE = registerBiomeTag("has_lobber_zombie");
    public static final TagKey<Biome> HAS_SKELETON_WOLF = registerBiomeTag("has_skeleton_wolf");
    public static final TagKey<Biome> HAS_TROPICAL_SLIME = registerBiomeTag("has_tropical_slime");
    public static final TagKey<Biome> HAS_VILER_WITCH = registerBiomeTag("has_viler_witch");


    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.of(Registry.BIOME_KEY, Utils.modIdentifierOf(name));
    }

    public static void init() {
    }
}
