package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.TRANSPOSITION, keyType = KeyType.INT)
public class RailFence extends Cipher {
    public RailFence() { super(); keysA = ONE_TO_LEN; }

    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();

        for (int row = 0; row < intKeyA; row++) {
            for (int i = row, it = 0; i < message.length(); i += getTerm(it++, row, (int) intKeyA))
                result.append(message.charAt(i));
        }

        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final StringBuilder result = new StringBuilder(message);

        int pos = 0;
        for (int row = 0; row < intKeyA; row++) {
            for(int i = row, it = 0; i < message.length(); i += getTerm(it++, row, (int) intKeyA))
                result.setCharAt(i, message.charAt(pos++));
        }

        return result.toString();
    }

    private int getTerm(int iteration, int row, int size) { return size == 0 || size == 1? 1 : row == 0 || row == size - 1? (size - 1) * 2 : iteration % 2 == 0? (size - 1 - row) * 2 : row * 2; }
}
