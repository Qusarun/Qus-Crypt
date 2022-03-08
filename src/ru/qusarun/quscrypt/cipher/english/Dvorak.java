package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.NONE)
public class Dvorak extends MonoAlphabeticTN { public Dvorak() { super("pyfgcrlaoeuidhtnsqjkxbmwvz"); } }