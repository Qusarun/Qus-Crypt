package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Base64 extends Cipher {
    @Override public String encrypt(String message) { return new String(java.util.Base64.getUrlEncoder().encode(message.getBytes())); }
    @Override public String decrypt(String message) { return new String(java.util.Base64.getUrlDecoder().decode(message.getBytes())); }
}
