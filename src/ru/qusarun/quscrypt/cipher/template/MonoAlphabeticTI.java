package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.AlphabetBuilderI;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

public class MonoAlphabeticTI extends Cipher {
    private final AlphabetBuilderI builder;

    public MonoAlphabeticTI(final AlphabetBuilderI builder) {
        super();
        this.builder = builder;
    }

    @Override public String encrypt(final String message) { return StringUtil.map(message, CharUtil.EN_ALPHABET, builder.build((int) intKeyA)); }

    @Override public String decrypt(final String message) { return StringUtil.map(message, builder.build((int) intKeyA), CharUtil.EN_ALPHABET); }
}
