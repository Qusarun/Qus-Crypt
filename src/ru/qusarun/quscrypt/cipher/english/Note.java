package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class Note extends MonoAlphabeticTN {
    public Note() {
        super(
                CharUtil.EN_ALPHABET,
                "AA AB AC AD AE AF AG BA BB BC BD BE BF BG CA CB CC CD CE CF CG DA DB DC DD DE",
                2
        );
    }
}
