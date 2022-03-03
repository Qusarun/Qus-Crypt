package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.OtherT;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class ModuloNS extends OtherT { public ModuloNS() { super(c -> String.valueOf((int) (Math.random() * 100) * 26 + CharUtil.toCase(c, 0) - 'a' + 1), s -> (char) (Integer.parseInt(s) % 26 + 'a' - 1)); } }