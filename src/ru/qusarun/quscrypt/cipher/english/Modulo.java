package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.OtherT;
import ru.qusarun.quscrypt.util.CharUtil;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Modulo extends OtherT { public Modulo() { super(c -> String.valueOf((int) (Math.random() * 100) * 27 + (CharUtil.toCase(c, 0) >= 'a'? CharUtil.toCase(c, 0) - 'a' + 1 : 0)), s -> (char) Integer.parseInt(s) % 27 == 0? ' ' : (char) (Integer.parseInt(s) % 27 + 'a' - 1)); } }