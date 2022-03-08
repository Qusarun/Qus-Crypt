package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.OtherT;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class ASCII extends OtherT { public ASCII() { super(Integer::toString, s -> (char) Integer.parseInt(s)); } }