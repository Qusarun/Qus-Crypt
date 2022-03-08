package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.AlphabetBuilderI;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

public class MonoAlphabeticTI extends Cipher {
    private final AlphabetBuilderI builder;
    private final String alphabet = this.getClass().getSimpleName().endsWith("Tp")? CharUtil.TP_ALPHABET : this.getClass().getSimpleName().endsWith("Ru")? CharUtil.RU_ALPHABET : this.getClass().getSimpleName().endsWith("De")? CharUtil.DE_ALPHABET : CharUtil.EN_ALPHABET;

    public MonoAlphabeticTI(final AlphabetBuilderI builder) {
        super();
        this.builder = builder;
    }

    @Override public String encrypt(final String message) { return StringUtil.map(message, alphabet, builder.build((int) intKeyA)); }

    @Override public String decrypt(final String message) { return StringUtil.map(message, builder.build((int) intKeyA), alphabet); }
}
