package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.INT)
public class BitShift extends Cipher {
    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();
        for (final char c: message.toCharArray())
            result.append((char) (c << intKeyA));
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final StringBuilder result = new StringBuilder();

        if (message.contains(" ")) {
            for (final String word: message.split(" "))
                result.append((char) (Integer.parseInt(word) >> intKeyA));
        } else {
            for (final char c: message.toCharArray())
                result.append((char) (c >> intKeyA));
        }

        return result.toString();
    }
}