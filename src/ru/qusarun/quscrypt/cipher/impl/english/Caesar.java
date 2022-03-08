package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTI;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.MathUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.INT)
public class Caesar extends MonoAlphabeticTI {
    public Caesar() {
        super(key -> CharUtil.EN_ALPHABET.substring(key) + CharUtil.EN_ALPHABET.substring(0, key));
        keysA = MathUtil.range(1, 25);
    }
}
