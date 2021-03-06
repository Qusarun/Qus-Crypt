package ru.qusarun.quscrypt.cipher.impl.german;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class NoteDe extends MonoAlphabeticTN {
    public NoteDe() {
        super(
                CharUtil.DE_ALPHABET,
                "AA AB AC AD AE AF AG BA BB BC BD BE BF BG CA CB CC CD CE CF CG DA DB DC DD DE DF DG EA EB",
                2
        );
    }
}
