package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class Morse extends MonoAlphabeticTN {
    public Morse() {
        super(
                CharUtil.EN_ALPHABET + "1234567890,.?;:/+-=()",
                ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.. .---- ..--- ...-- ....- ..... -.... --... ---.. ----. ----- ..-.. .-.-.- ..--.. -.-.- ---... -..-. .-.-. -....- -...- -.--.- -.--.-"
        );
    }
}
