package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

/* skidded from https://github.com/multiformats/java-multibase/blob/master/src/main/java/io/ipfs/multibase/Base16.java */

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Base16 extends Cipher {
    @Override
    public String decrypt(String message) {
        if (message.length() % 2 == 1) throw new IllegalStateException("Must have an even number of hex digits to convert to bytes!");
        final byte[] result = new byte[message.length()/2];
        for (int i = 0; i < result.length; i++)
            result[i] = (byte) Integer.parseInt(message.substring(2*i, 2*i+2), 16);
        return new String(result);
    }

    @Override public String encrypt(String message) { return bytesToHex(message.getBytes()); }

    private static final String[] HEX_DIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final String[] HEX = new String[256];
    static {
        for (int i=0; i < 256; i++)
            HEX[i] = HEX_DIGITS[(i >> 4) & 0xF] + HEX_DIGITS[i & 0xF];
    }

    public String byteToHex(byte b) { return HEX[b & 0xFF]; }

    public String bytesToHex(byte[] data) {
        final StringBuilder s = new StringBuilder();
        for (byte b: data) s.append(byteToHex(b));
        return s.toString();
    }
}
