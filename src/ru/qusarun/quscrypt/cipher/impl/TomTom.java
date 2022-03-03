package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SEQUENCE, keyType = KeyType.NONE)
public class TomTom extends MonoAlphabeticTN {
    public TomTom() {
        super(
                CharUtil.EN_ALPHABET,
                "( (( ((( (((( () (() ((() ()) ())) )( ))( )))( )(( )((( ()( (()( ())( ()(( )() ))() )(() )()) (()) ))(( )()( ()()".replace("(", "/").replace(")", "\\")
        );
    }
}
