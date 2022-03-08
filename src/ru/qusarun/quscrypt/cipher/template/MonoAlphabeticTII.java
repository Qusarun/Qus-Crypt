package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.AlphabetBuilderII;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

public class MonoAlphabeticTII extends Cipher {
    private final AlphabetBuilderII builder;
    private final String alphabet = this.getClass().getSimpleName().endsWith("Tp")? CharUtil.TP_ALPHABET : this.getClass().getSimpleName().endsWith("Ru")? CharUtil.RU_ALPHABET : this.getClass().getSimpleName().endsWith("De")? CharUtil.DE_ALPHABET : CharUtil.EN_ALPHABET;

    public MonoAlphabeticTII(final AlphabetBuilderII builder) {
        super();
        this.builder = builder;
    }

    @Override public String encrypt(final String message) { return StringUtil.map(message, alphabet, builder.build((int) intKeyA, (int) intKeyB)); }

    @Override public String decrypt(final String message) { return StringUtil.map(message, builder.build((int) intKeyA, (int) intKeyB), alphabet); }
}
