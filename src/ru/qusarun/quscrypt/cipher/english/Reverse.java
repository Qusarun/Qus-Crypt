package ru.qusarun.quscrypt.cipher.english;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.StringUtil;

@CipherInfo(cipherType = CipherType.TRANSPOSITION, keyType = KeyType.NONE)
public class Reverse extends Cipher {
    @Override public String encrypt(String message) { return StringUtil.reverse(message); }
    @Override public String decrypt(String message) { return StringUtil.reverse(message); }
}
