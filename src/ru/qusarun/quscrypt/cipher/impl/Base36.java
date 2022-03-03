package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

import java.math.BigInteger;

/* skidded from https://github.com/multiformats/java-multibase/blob/master/src/main/java/io/ipfs/multibase/Base36.java */

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Base36 extends Cipher {
    @Override
    public String decrypt(String message) {
        final byte[] result = new byte[preflen(message) + new BigInteger(message, 36).toByteArray().length];
        System.arraycopy(new BigInteger(message, 36).toByteArray(), 0, result, preflen(message), new BigInteger(message, 36).toByteArray().length);
        return new String(result);
    }

    @Override
    public String encrypt(String message) {
        return "0".repeat(Math.max(0, preflen(message.getBytes()))) + new BigInteger(1, message.getBytes()).toString(36);
    }

    private int preflen(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] != 0)
                return i;
        }

        return bytes.length;
    }

    private int preflen(String in) {
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) != '0')
                return i;
        }

        return in.length();
    }
}
