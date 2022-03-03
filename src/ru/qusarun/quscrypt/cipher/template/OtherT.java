package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.Transformer;

public class OtherT extends Cipher {
    private final Transformer<String, Character> encryptor;
    private final Transformer<Character, String> decryptor;

    public OtherT(final Transformer<String, Character> encryptor, final Transformer<Character, String> decryptor) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
    }

    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();
        for (final char c: message.toCharArray())
            result.append(encryptor.transform(c)).append(" ");
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final StringBuilder result = new StringBuilder();
        for (final String word: message.split(" "))
            result.append(decryptor.transform(word));
        return result.toString();
    }
}
