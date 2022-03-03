package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.ListUtil;
import ru.qusarun.quscrypt.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.STR)
public class QusCrypt4 extends Cipher {
    @Override
    public String encrypt(String message) {
        if (strKey.length() != 256)
            return "The key should be a string of length 256 with characters a - p";
        final long[] keys = getKeys();
        final Random[] randoms = getRandoms(keys);
        final List<String> words = generateWordList();
        final StringBuilder result = new StringBuilder();
        final QusCrypt1 qusCrypt1 = new QusCrypt1();
        final QusCrypt2 qusCrypt2 = new QusCrypt2();
        final StringBuilder word = new StringBuilder();
        int random = 0;
        qusCrypt2.setKey(String.valueOf(keys[15]));

        for (final char c: (message + " ").toLowerCase().toCharArray()) {
            if (c == ' ' || c == '\n' || c == '\'' || c == ',' || c == '.' || c == '?' || c == '"' || c == '!' || c == '-' || c == '+') {
                if (word.length() > 0) {
                    final String w = word.toString();
                    if (StringUtil.contains(w, words)) {
                        ListUtil.shuffle(words, words.size(), randoms[(random++) % 14]);
                        result.append(fromLong(StringUtil.indexOf(w, words), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN", 3));
                    } else {
                        qusCrypt1.setKey(String.valueOf(randoms[14].nextInt(Integer.MAX_VALUE)));
                        result.append("O").append(new Base58().encrypt(qusCrypt1.encrypt(w)));
                    }
                }

                result.append(c);
                word.setLength(0);
            } else
                word.append(c);
        }

        return qusCrypt2.encrypt(result.toString());
    }

    @Override
    public String decrypt(String message) {
        if (strKey.length() != 256)
            return "The key should be a string of length 256 with characters a - p";
        final long[] keys = getKeys();
        final Random[] randoms = getRandoms(keys);
        final List<String> words = generateWordList();
        final StringBuilder result = new StringBuilder();
        final QusCrypt1 qusCrypt1 = new QusCrypt1();
        final QusCrypt2 qusCrypt2 = new QusCrypt2();
        final StringBuilder word = new StringBuilder();
        qusCrypt2.setKey(String.valueOf(keys[15]));
        int random = 0;
        message = qusCrypt2.decrypt(message);

        for (int i = 0; i < message.length(); i++) {
            final char c = message.charAt(i);
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN".contains(Character.toString(c))) {
                for (int j = i; j < i + 3; j++)
                    word.append(message.charAt(j));
                ListUtil.shuffle(words, words.size(), randoms[(random++) % 14]);
                result.append(words.get((int) fromString(word.toString(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN", 0)));
                word.setLength(0);
                i += 2;
            } else {
                if (c == 'O') {
                    for (int j = i + 1; j < message.length(); j++) {
                        final char d = message.charAt(j);
                        if (d == ' ' || d == '\n' || d == '\'' || d == ',' || d == '.' || d == '?' || d == '"' || d == '!' || d == '-' || d == '+')
                            break;
                        else
                            i++;
                        word.append(d);
                    }

                    qusCrypt1.setKey(String.valueOf(randoms[14].nextInt(Integer.MAX_VALUE)));
                    result.append(qusCrypt1.decrypt(new Base58().decrypt(word.toString())));
                    word.setLength(0);
                } else {
                    result.append(c);
                }
            }
        }

        return capitalise(result.toString());
    }

    public Random[] getRandoms(final long[] keys) {
        final Random[] randoms = new Random[15];
        for (int i = 0; i < 15; i++)
            randoms[i] = new Random(keys[i]);
        return randoms;
    }

    public long[] getKeys() {
        final long[] keys = new long[16];
        for (int i = 0; i < 16; i++) {
            final StringBuilder part = new StringBuilder();
            for (int j = i * 16; j < i * 16 + 16; j++)
                part.append(strKey.charAt(j));
            keys[i] = fromString(part.toString(), "abcdefghijklmnop", Long.MIN_VALUE);
        }

        return keys;
    }

    public String fromLong(final long in, final String alphabet, final int length) {
        { // size check
            final long max = fromString(Character.toString(alphabet.charAt(alphabet.length() - 1)).repeat(length), alphabet, 0);
            if (in > max) {
                Logger.log(in + " > " + max);
                return null;
            }
        }

        long t = in;
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(alphabet.charAt((int) (t % alphabet.length())));
            t /= alphabet.length();
        }

        return result.toString();
    }

    public long fromString(final String in, final String alphabet, final long start) {
        double result = start;
        for (int i = 0; i < in.length(); i++)
            result += alphabet.indexOf(in.charAt(i)) * Math.pow(alphabet.length(), i);
        return (long) result;
    }

    public String capitalise(final String s) {
        final StringBuilder builder = new StringBuilder();
        boolean capital = true;
        for (final char c: s.toCharArray()) {
            if (c == '.' || c == '!' || c == '?') {
                builder.append(c);
                capital = true;
            } else if (Character.isLetter(c)) {
                builder.append(capital ? CharUtil.toCase(c, 1) : c);
                capital = false;
            } else {
                builder.append(c);
            }
        }

        String result = builder.toString();
        while (result.endsWith(" "))
            result = result.substring(0, result.length() - 1);
        return result;
    }

    public List<String> generateWordList() {
        final List<String> result = new ArrayList<>(StringUtil.EN_WORDS);
        result.addAll(List.of("\"", ",", ".", "!", "-", "?", "'", " ", "+", "\n"));
        return result;
    }

    @Override public boolean producesNonASCII() { return false; }
}