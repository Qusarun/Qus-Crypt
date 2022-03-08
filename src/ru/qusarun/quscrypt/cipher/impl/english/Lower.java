package ru.qusarun.quscrypt.cipher.impl.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.CASE, keyType = KeyType.NONE)
public class Lower extends Cipher {
    @Override public String encrypt(String message) { return message.toLowerCase(); }
    @Override public String decrypt(String message) { return message.toUpperCase(); }
}
