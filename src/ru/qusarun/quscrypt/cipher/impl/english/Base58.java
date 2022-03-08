package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

import java.util.Arrays;

/* skidded from https://github.com/multiformats/java-multibase/blob/master/src/main/java/io/ipfs/multibase/Base58.java */

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Base58 extends Cipher {
    public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    private static final char ENCODED_ZERO = ALPHABET[0];
    private static final int[] INDEXES = new int[128];
    static {
        Arrays.fill(INDEXES, -1);
        for (int i = 0; i < ALPHABET.length; i++)
            INDEXES[ALPHABET[i]] = i;
    }

    @Override
    public String encrypt(String message) {
        byte[] bytes = message.getBytes();
        if (bytes.length == 0) return "";

        int zeros = 0;
        while (zeros < bytes.length && bytes[zeros] == 0) ++zeros;

        bytes = Arrays.copyOf(bytes, bytes.length);
        char[] encoded = new char[bytes.length * 2];
        int outputStart = encoded.length;
        for (int bytesStart = zeros; bytesStart < bytes.length; ) {
            encoded[--outputStart] = ALPHABET[divmod(bytes, bytesStart, 256, 58)];
            if (bytes[bytesStart] == 0) ++bytesStart;
        }

        while (outputStart < encoded.length && encoded[outputStart] == ENCODED_ZERO) ++outputStart;
        while (--zeros >= 0) encoded[--outputStart] = ENCODED_ZERO;

        return new String(encoded, outputStart, encoded.length - outputStart);
    }

    @Override
    public String decrypt(String message) {
        if (message.length() == 0) return "";
        
        byte[] input58 = new byte[message.length()];
        for (int i = 0; i < message.length(); ++i) {
            final char c = message.charAt(i);
            int digit = c < 128 ? INDEXES[c] : -1;
            if (digit < 0) throw new IllegalStateException("InvalidCharacter in base 58");
            input58[i] = (byte) digit;
        }

        int zeros = 0;
        while (zeros < input58.length && input58[zeros] == 0) ++zeros;

        byte[] decoded = new byte[message.length()];
        int outputStart = decoded.length;
        for (int inputStart = zeros; inputStart < input58.length; ) {
            decoded[--outputStart] = divmod(input58, inputStart, 58, 256);
            if (input58[inputStart] == 0)
                ++inputStart;
        }

        while (outputStart < decoded.length && decoded[outputStart] == 0) ++outputStart;

        return new String(Arrays.copyOfRange(decoded, outputStart - zeros, decoded.length));
    }

    private byte divmod(byte[] number, int firstDigit, int base, int divisor) {
        int remainder = 0;
        for (int i = firstDigit; i < number.length; i++) {
            int digit = (int) number[i] & 0xFF;
            int temp = remainder * base + digit;
            number[i] = (byte) (temp / divisor);
            remainder = temp % divisor;
        }

        return (byte) remainder;
    }
}
