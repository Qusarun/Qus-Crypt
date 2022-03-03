package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.AlphabetBuilderII;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

public class MonoAlphabeticTII extends Cipher {
    private final AlphabetBuilderII builder;

    public MonoAlphabeticTII(final AlphabetBuilderII builder) {
        super();
        this.builder = builder;
    }

    @Override public String encrypt(final String message) { return StringUtil.map(message, CharUtil.EN_ALPHABET, builder.build((int) intKeyA, (int) intKeyB)); }

    @Override public String decrypt(final String message) { return StringUtil.map(message, builder.build((int) intKeyA, (int) intKeyB), CharUtil.EN_ALPHABET); }
}
