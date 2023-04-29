package slexom.earthtojava.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import slexom.earthtojava.Earth2JavaMod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> breakItemTooltip(String input) {
        List<String> cjkLocales = Arrays.asList("ja_jp", "ko_kr", "zh_cn", "zh_tw");
        String currentLocale = MinecraftClient.getInstance().getLanguageManager().getLanguage();
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

    @Environment(EnvType.CLIENT)
    public static void appendE2JTooltip(String translationKey, List<Text> tooltip) {
        if (!I18n.hasTranslation(translationKey)) return;

        MutableText description = Text.translatable(translationKey);
        List<String> strings = Utils.breakItemTooltip(description.getString());
        strings.forEach(string -> tooltip.add(Text.translatable(string).formatted(Formatting.GRAY)));
    }

    public static Identifier modIdentifierOf(String registryName) {
        return new Identifier(Earth2JavaMod.MOD_ID, registryName);
    }
}
