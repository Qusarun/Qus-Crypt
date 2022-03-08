package ru.qusarun.quscrypt.util;

import ru.qusarun.flogger.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {
    public static final List<String> EN_WORDS = new ArrayList<>(), DE_WORDS = new ArrayList<>(), RU_WORDS = new ArrayList<>(), TP_WORDS = new ArrayList<>();

    static {
        read(EN_WORDS, "dictionaries/english");
        read(DE_WORDS, "dictionaries/deutsch");

        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("dictionaries/русский"), "Cp1251"));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty())
                    RU_WORDS.add(line.toLowerCase());
            }

            br.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        read(TP_WORDS, "dictionaries/toki pona");
    }

    public static void read(final List<String> list, final String file) {
        list.clear();
        try {
            final BufferedReader br = new BufferedReader(new FileReader(file, file.equals("русский")? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("#!comment"))
                    list.add(line.toLowerCase());
            }

            br.close();
        } catch (Exception ignored) {
            Logger.log("Failed to read %b" + file);
        }
    }

    public static int indexOf(final String s, final List<String> list) {
        for (int i = 0; i < list.size(); i++) if (s.equals(list.get(i))) return i;
        return -1;
    }

    public static boolean contains(final String s, final List<String> list) { return indexOf(s, list) != -1; }

    public static String reverse(final String s) { return ClassUtil.transform(s, str -> new StringBuilder(str).reverse().toString()); }

    public static String map(final String s, final String a, final String b) { return matchCase(forEach(s, c -> str(a.contains(str(CharUtil.toCase(c, 0))) ? b.charAt(a.indexOf(CharUtil.toCase(c, 0))) : c)), s); }

    public static String matchCase(final String a, final String b) {
        if (a.length() != b.length()) {
            Logger.log("Failed to match case of strings \"" + a + "\" and \"" + b + "\"");
            return null;
        }

        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
            result.append(CharUtil.toCase(a.charAt(i), CharUtil.getCase(b.charAt(i))));
        return result.toString();
    }

    public static String forEach(final String s, final Transformer<String, Character> transformer) {
        final StringBuilder result = new StringBuilder();
        for (final char c : s.toCharArray())
            result.append(transformer.transform(c));
        return result.toString();
    }

    public static String[] subArray(final String[] arr, final int start) {
        final String[] result = new String[arr.length - start];
        System.arraycopy(arr, start, result, 0, arr.length - start);
        return result;
    }

    public static String join(final String[] arr) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) result.append(i > 0 ? " " + arr[i] : arr[i]);
        return result.toString();
    }

    public static boolean isValidTokiPonaString(final String s) {
        for (final char c: s.toCharArray()) if (!CharUtil.isValidCharTp(c)) return false;
        return true;
    }

    public static boolean isValidRussianString(final String s) {
        for (final char c: s.toCharArray()) if (!CharUtil.isValidCharRu(c)) return false;
        return true;
    }

    public static boolean isValidGermanString(final String s) {
        for (final char c: s.toCharArray()) if (!CharUtil.isValidCharDe(c)) return false;
        return true;
    }

    public static boolean isValidEnglishString(final String s) {
        for (final char c: s.toCharArray()) if (!CharUtil.isValidChar(c)) return false;
        return true;
    }

    public static int getValidTokiPona(final String s) {
        int valid = 0;
        for (final String word: s.replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(":", "").split(" "))
            valid += TP_WORDS.contains(word) ? 1 : 0;
        return valid;
    }

    public static boolean isValidTokiPona(final String s) {
        if (!isValidTokiPonaString(s)) return false;
        int valid = 0;
        for (final String word: s.replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(":", "").split(" "))
            valid += TP_WORDS.contains(word) ? 1 : 0;
        return valid > s.split(" ").length * 0.75;
    }

    public static boolean isValidRussian(final String s) {
        if (!isValidRussianString(s)) return false;
        int valid = 0;
        for (final String word: s.replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(":", "").split(" "))
            valid += RU_WORDS.contains(word.toLowerCase()) ? 1 : 0;
        return valid > s.split(" ").length * 0.75;
    }

    public static boolean isValidGerman(final String s) {
        if (!isValidGermanString(s)) return false;
        int valid = 0;
        for (final String word: s.replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(":", "").split(" "))
            valid += DE_WORDS.contains(word.toLowerCase()) ? 1 : 0;
        return valid > s.split(" ").length * 0.75;
    }

    public static boolean isValidEnglish(final String s) {
        if (!isValidEnglishString(s)) return false;
        int valid = 0;
        for (final String word: s.replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(":", "").split(" "))
            valid += EN_WORDS.contains(word.toLowerCase()) ? 1 : 0;
        return valid > s.split(" ").length * 0.75;
    }

    public static float getDifference(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        float diff = 0;
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            final char c = a.charAt(i), d = b.charAt(i);

            if (c == d)
                continue;

            if (i + 1 >= a.length() || i + 1 >= b.length()) {
                diff++;
                continue;
            }

            final char e = a.charAt(i + 1), f = b.charAt(i + 1);
            diff += c == f && e == d ? 0.5F : 1F;
        }

        if (a.length() != b.length()) diff += Math.abs(a.length() - b.length());

        return diff / Math.max(a.length(), b.length());
    }

    public static String getClosestWord(final String s) {
        String closest = EN_WORDS.get(0);
        float  minDiff = getDifference(s, EN_WORDS.get(0));
        for (final String word: EN_WORDS) {
            float diff = getDifference(s, word);
            if (diff < minDiff) {
                closest = word;
                minDiff = diff;
            }
        }

        return closest;
    }

    public static String switchCase(final String s, final int pos) {
        final char[] arr = s.toCharArray();
        arr[pos] = CharUtil.toCase(arr[pos], CharUtil.getCase(arr[pos]) == 0? 1 : 0);
        return new String(arr);
    }

    public static String swap(final String s, final int a, final int b) {
        final char[] arr = s.toCharArray();
        final char c = arr[a], d = arr[b];
        arr[b] = c;
        arr[a] = d;
        return new String(arr);
    }

    public static String shuffle(String s, final int times, final Random random) {
        for (int i = 0; i < times; i++)
            s = swap(s, random.nextInt(s.length()), random.nextInt(s.length()));
        return s;
    }

    /* I'm too lazy to type Character.toString(c), ok? */
    private static String str(final char c) { return Character.toString(c); }

    public static String genKey() {
        final Random random = new Random();
        final String chars = "abcdefghijklmnop";
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16 * 16; i++)
            builder.append(chars.charAt(random.nextInt(chars.length())));
        return builder.toString();
    }
}
