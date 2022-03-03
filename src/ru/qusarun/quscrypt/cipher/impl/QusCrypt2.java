package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.MathUtil;
import ru.qusarun.quscrypt.util.StringUtil;

import java.util.Random;

@CipherInfo(cipherType = CipherType.TRANSPOSITION, keyType = KeyType.INT)
public class QusCrypt2 extends Cipher {
    public QusCrypt2() { super(); keysA = MathUtil.range(1, 250); }

    @Override
    public String encrypt(String message) {
        final Random random = new Random(intKeyA);
        for (int i = 0; i < message.length(); i++)
            message = StringUtil.swap(message, random.nextInt(message.length()), random.nextInt(message.length()));
        return message;
    }

    @Override
    public String decrypt(String message) {
        final Random random = new Random(intKeyA);
        final int[][] operations = new int[message.length()][2];
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < 2; j++)
                operations[i][j] = random.nextInt(message.length());
        }

        for (int i = message.length(); i > 0; i--)
            message = StringUtil.swap(message, operations[i - 1][0], operations[i - 1][1]);
        return message;
    }
}