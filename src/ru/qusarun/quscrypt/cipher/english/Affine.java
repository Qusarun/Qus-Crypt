package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTII;
import ru.qusarun.quscrypt.util.MathUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.INT_INT)
public class Affine extends MonoAlphabeticTII {
    public Affine() {
        super((a, b) -> {
            final StringBuilder result = new StringBuilder();
            for (int i = 0; i < 26; i++)
                result.append((char) (((a * i + b) % 26 + 'a')));
            return result.toString();
        });

        keysA = new int[] { 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };
        keysB = MathUtil.range(1, 25);
    }
}
