package slexom.earthtojava.utils;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String addLinebreaks(String input, int maxLineLength) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n");
                lineLen = 0;
            }
            output.append(word);
            lineLen += word.length();
        }
        return output.toString();
    }

    public static List<String> breakLine(String input, int maxLineLength) {
        List<String> res = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b.{1," + (maxLineLength - 1) + "}\\b\\W?");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            res.add(matcher.group());
        }
        return res;
    }

}
