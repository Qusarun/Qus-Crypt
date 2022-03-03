package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.template.OtherT;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Hex extends OtherT { public Hex() { super(Integer::toHexString, s -> (char) Integer.parseInt(s, 16)); } }