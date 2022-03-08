package ru.qusarun.quscrypt.cipher.impl.toki_pona;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

@CipherInfo(cipherType = CipherType.POLYALPHABETIC, keyType = KeyType.STR)
public class VigenereTp extends Cipher {
    public VigenereTp() { super(); }

    @Override
    public String encrypt(String message) {
        return transform(message, false);
    }

    @Override
    public String decrypt(String message) {
        return transform(message, true);
    }

    public String transform(final String message, final boolean inv) {
        final String key = keyToLen(message);
        final StringBuilder result = new StringBuilder();
        int i = 0;
        for (final char c: message.toLowerCase().toCharArray()) {
            if (CharUtil.TP_ALPHABET.contains(Character.toString(c)))
                result.append(map(inv? getAlphabetPos(c) - getAlphabetPos(key.charAt(i++)) : getAlphabetPos(c) + getAlphabetPos(key.charAt(i++))));
            else
                result.append(c);
        }

        return StringUtil.matchCase(result.toString(), message);
    }

    public char map(final int i) { return CharUtil.TP_ALPHABET.charAt(i >= 0 ? i % CharUtil.TP_ALPHABET.length() : (14 + i % CharUtil.TP_ALPHABET.length()) % CharUtil.TP_ALPHABET.length()); }

    public String keyToLen(final String msg) { return strKey.repeat(msg.length() / strKey.length() + 1); }

    public int getAlphabetPos(final char c) { return CharUtil.TP_ALPHABET.indexOf(Character.toLowerCase(c)); }
}
