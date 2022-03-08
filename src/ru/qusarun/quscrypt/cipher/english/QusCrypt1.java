package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.ListUtil;
import ru.qusarun.quscrypt.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.INT)
public class QusCrypt1 extends Cipher {
    public QusCrypt1() { super(); }

    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();
        final Random random = new Random(intKeyA);
        final List<Character> a = buildCharList(), b = buildCharList();
        for (final char c: message.toCharArray()) {
            ListUtil.shuffle(b, b.size(), random);
            result.append(b.get(a.indexOf(c)));
            if (random.nextFloat() > 0.5)
                result.append(a.get((int) (Math.random() * a.size())));
        }

        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final StringBuilder result = new StringBuilder();
        final Random random = new Random(intKeyA);
        final List<Character> a = buildCharList(), b = buildCharList();
        for (int i = 0; i < message.length(); i++) {
            ListUtil.shuffle(b, b.size(), random);
            result.append(a.get(b.indexOf(message.charAt(i))));
            if (random.nextFloat() > 0.5)
                i++;
        }

        return result.toString();
    }

    private List<Character> buildCharList() {
        List<Character> result = new ArrayList<>();
        for (char c = 0; c < 1000; c++) if (Character.isLetter(c) && !Character.isMirrored(c)) result.add(c);
        for (final char c: CharUtil.SYMBOLS.toCharArray()) result.add(c);
        for (final char c: "\t\n ".toCharArray()) result.add(c);
        return result;
    }
}