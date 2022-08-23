package slexom.earthtojava.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import org.jetbrains.annotations.ApiStatus;

import java.text.BreakIterator;
import java.util.*;
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

    public static List<String> breakItemTooltip(String input) {
        List<String> cjkLocales = Arrays.asList("ja_jp", "ko_kr", "zh_cn", "zh_tw");
        String currentLocale = MinecraftClient.getInstance().getLanguageManager().getLanguage().getCode();
        if (cjkLocales.contains(currentLocale)) {
            return breakLine(input, 30);
        }
        return breakLine(input, 40);
    }

    public static List<String> breakLine(String input, int maxLineLength) {
        List<String> res = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b.{1," + (maxLineLength - 1) + "}\\b\\W?", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            res.add(matcher.group());
        }
        return res;
    }

    @ApiStatus.Experimental
    public static List<String> breakCJKLine(String input, Locale locale) {
        List<String> res = new ArrayList<>();
        BreakIterator boundary = BreakIterator.getSentenceInstance(locale);
        boundary.setText(input);
        int start = boundary.first();
        for (int end = boundary.next();
             end != BreakIterator.DONE;
             start = end, end = boundary.next()) {
            res.add(input.substring(start, end));
        }
        return res;
    }

}
