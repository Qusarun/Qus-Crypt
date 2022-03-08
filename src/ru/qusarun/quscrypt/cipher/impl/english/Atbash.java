package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.NONE)
public class Atbash extends MonoAlphabeticTN { public Atbash() { super(StringUtil.reverse(CharUtil.EN_ALPHABET)); } }
