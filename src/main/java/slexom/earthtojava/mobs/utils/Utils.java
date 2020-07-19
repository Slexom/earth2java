package slexom.earthtojava.mobs.utils;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {



    public static SpriteIdentifier[] addToSpriteArray(SpriteIdentifier[] ary, SpriteIdentifier bed) {

        List<SpriteIdentifier> beds = new ArrayList<>(Arrays.asList(ary));
        beds.add(bed);
        ary = beds.toArray(ary);
        return ary;

    }
    public static SpriteIdentifier[] addBed(SpriteIdentifier bed) {

        SpriteIdentifier[] vanillaBeds = TexturedRenderLayers.BED_TEXTURES;
        List<SpriteIdentifier> beds = new ArrayList<>(Arrays.asList(vanillaBeds));
        beds.add(bed);
        vanillaBeds = beds.toArray(vanillaBeds);
        return vanillaBeds;

    }

}
