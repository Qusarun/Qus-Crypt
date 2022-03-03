package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

@CipherInfo(cipherType = CipherType.TRANSPOSITION, keyType = KeyType.INT)
public class Skip extends Cipher {
    public Skip() { super(); keysA = ONE_TO_LEN; }

    @Override
    public String encrypt(String message) {
        if (MathUtil.gcd(message.length(), (int) intKeyA) > 1)
            return "The key must be coprime to the message length";

        final List<Integer> added = new ArrayList<>();
        final StringBuilder result = new StringBuilder();

        for (int i = 0; added.size() < message.length(); i += intKeyA) {
            if (!added.contains(i %= message.length())) {
                result.append(message.charAt(i));
                added.add(i);
            }
        }

        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++)
            result[i * (int) intKeyA % message.length()] = message.charAt(i);
        return new String(result);
    }
}
