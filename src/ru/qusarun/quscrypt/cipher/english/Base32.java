package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

/* skidded from http://www.herongyang.com/Encoding/Base32-Bitpedia-Java-Implementation.html */

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Base32 extends Cipher {
    private final int[] table = {
            0xFF, 0xFF, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, 
            0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 
            0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 
            0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 
            0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 
            0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 
            0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 
            0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 
            0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 
            0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF
    };

    @Override
    public String encrypt(final String message) {
        final byte[] bytes = message.getBytes();
        final StringBuilder result = new StringBuilder((bytes.length + 7) * 8 / 5);
        int i = 0, index = 0, digit;
        int currByte, nextByte;

        while (i < bytes.length) {
            currByte = (bytes[i] >= 0) ? bytes[i] : (bytes[i] + 256);

            if (index > 3) {
                nextByte = (i + 1) < bytes.length? (bytes[i + 1] >= 0)? bytes[i + 1] : (bytes[i + 1] + 256) : 0;
                digit = currByte & (0xFF >> index);
                index = (index + 5) % 8;
                digit <<= index;
                digit |= nextByte >> (8 - index);
                i++;
            } else {
                digit = (currByte >> (8 - (index + 5))) & 0x1F;
                index = (index + 5) % 8;
                if (index == 0)
                    i++;
            }
            
            result.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".charAt(digit));
        }

        return result.toString();
    }
    
    @Override
    public String decrypt(final String message) {
        final byte[] bytes = new byte[message.length() * 5 / 8];
        int i, index, lookup, offset, digit;

        for (i = 0, index = 0, offset = 0; i < message.length(); i++) {
            lookup = message.charAt(i) - '0';

            if (lookup < 0 || lookup >= table.length)
                continue;

            digit = table[lookup];

            if (digit == 0xFF)
                continue;

            if (index <= 3) {
                index = (index + 5) % 8;
                if (index == 0) {
                    bytes[offset] |= digit;
                    offset++;
                    if (offset >= bytes.length)
                        break;
                } else bytes[offset] |= digit << (8 - index);
            } else {
                index = (index + 5) % 8;
                bytes[offset] |= (digit >>> index);
                offset++;

                if (offset >= bytes.length)
                    break;
                bytes[offset] |= digit << (8 - index);
            }
        }
        
        return new String(bytes);
    }
}
