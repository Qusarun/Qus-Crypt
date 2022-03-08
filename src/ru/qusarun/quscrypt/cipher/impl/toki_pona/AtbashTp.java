package ru.qusarun.quscrypt.cipher.impl.toki_pona;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.MonoAlphabeticTN;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

@CipherInfo(cipherType = CipherType.MONOALPHABETIC_SINGLE, keyType = KeyType.NONE)
public class AtbashTp extends MonoAlphabeticTN { public AtbashTp() { super(StringUtil.reverse(CharUtil.TP_ALPHABET)); } }
