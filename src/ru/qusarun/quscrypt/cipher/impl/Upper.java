package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.CASE, keyType = KeyType.NONE)
public class Upper extends Cipher {
    @Override public String encrypt(String message) { return message.toUpperCase(); }
    @Override public String decrypt(String message) { return message.toLowerCase(); }
}
