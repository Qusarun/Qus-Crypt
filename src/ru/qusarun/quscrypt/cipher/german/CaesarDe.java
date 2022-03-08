package ru.qusarun.quscrypt.cipher.german;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTI;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.MathUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.INT)
public class CaesarDe extends MonoAlphabeticTI {
    public CaesarDe() {
        super(key -> CharUtil.DE_ALPHABET.substring(key) + CharUtil.DE_ALPHABET.substring(0, key));
        keysA = MathUtil.range(1, 29);
    }
}
