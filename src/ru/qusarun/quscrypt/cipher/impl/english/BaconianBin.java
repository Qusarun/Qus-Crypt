package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class BaconianBin extends MonoAlphabeticTN {
    public BaconianBin() {
        super(
                CharUtil.EN_ALPHABET,
                "aaaaa aaaab aaaba aaabb aabaa aabab aabba aabbb abaaa abaab ababa ababb abbaa abbab abbba abbbb baaaa baaab baaba baabb babaa babab babba babbb".replace("a", "0").replace("b", "1"),
                5
        );
    }
}
