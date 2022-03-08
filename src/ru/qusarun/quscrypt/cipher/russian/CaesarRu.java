package ru.qusarun.quscrypt.cipher.russian;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTI;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.MathUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.INT)
public class CaesarRu extends MonoAlphabeticTI {
    public CaesarRu() {
        super(key -> CharUtil.RU_ALPHABET.substring(key) + CharUtil.RU_ALPHABET.substring(0, key));
        keysA = MathUtil.range(1, 32);
    }
}
