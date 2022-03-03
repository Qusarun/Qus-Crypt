package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class Cross extends MonoAlphabeticTN {
    public Cross() {
        super(
                CharUtil.EN_ALPHABET,
                "bbb abb bab bba baa aba aab cbb bcb bbc caa aca aac bcc cbc ccb acc cac cca cba bac acb cab abc bca aaa".replace("a", "+").replace("b", "†").replace("c", "‡"),
                3
        );
    }
}
