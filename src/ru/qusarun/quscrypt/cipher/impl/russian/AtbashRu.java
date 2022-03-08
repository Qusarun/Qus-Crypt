package ru.qusarun.quscrypt.cipher.impl.russian;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.NONE)
public class AtbashRu extends MonoAlphabeticTN { public AtbashRu() { super(StringUtil.reverse(CharUtil.RU_ALPHABET)); } }
