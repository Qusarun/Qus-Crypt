package ru.qusarun.quscrypt.cipher.impl.russian;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class A1A33 extends MonoAlphabeticTN {
    public A1A33() {
        super(
                CharUtil.RU_ALPHABET,
                "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33"
        );
    }
}
