package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.INT)
public class XOR extends Cipher {
    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();
        for (final char c: message.toCharArray())
            result.append((char) (c ^ intKeyA));
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        if (message.contains(" ")) {
            final StringBuilder result = new StringBuilder();
            for (final String word: message.split(" "))
                result.append((char) (Integer.parseInt(word) ^ intKeyA));
            return result.toString();
        } else {
            return encrypt(message);
        }
    }
}