package ru.qusarun.quscrypt.cipher.impl.toki_pona;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class NoteTp extends MonoAlphabeticTN {
    public NoteTp() {
        super(
                CharUtil.TP_ALPHABET,
                "AA AB AC AD AE AF AG BA BB BC BD BE BF BG",
                2
        );
    }
}
