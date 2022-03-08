package ru.qusarun.quscrypt.cipher.impl.toki_pona;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class A01J14 extends MonoAlphabeticTN {
    public A01J14() {
        super(
                CharUtil.TP_ALPHABET,
                "01 02 03 04 05 06 07 08 09 10 11 12 13 14",
                2
        );
    }
}
