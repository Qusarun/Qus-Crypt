package ru.qusarun.quscrypt.cipher.impl.toki_pona;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class A1J14 extends MonoAlphabeticTN {
    public A1J14() {
        super(
                CharUtil.TP_ALPHABET,
                "1 2 3 4 5 6 7 8 9 10 11 12 13 14"
        );
    }
}
