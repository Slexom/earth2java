package slexom.earthtojava.mobs.mixins;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import slexom.earthtojava.mobs.utils.Utils;

import java.util.Arrays;
import java.util.Comparator;

@Mixin(TexturedRenderLayers.class)
public class AddSpriteToAtlas {

    @Mutable
    @Final
    @Shadow
    public static SpriteIdentifier[] BED_TEXTURES;

    static {
        BED_TEXTURES = Utils.addToSpriteArray(
                Arrays.stream(DyeColor.values()).sorted(Comparator.comparingInt(DyeColor::getId)).map((dyeColor) -> new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier("entity/bed/" + dyeColor.getName()))).toArray(SpriteIdentifier[]::new),
                new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier("earthtojavamobs:entity/bed/rainbow"))
        );
    }

}
