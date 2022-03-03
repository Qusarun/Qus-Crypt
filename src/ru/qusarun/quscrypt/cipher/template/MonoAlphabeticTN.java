package ru.qusarun.quscrypt.cipher.template;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.StringUtil;

import java.util.*;

public class MonoAlphabeticTN extends Cipher {
    private final String alphabet;
    private final List<Character> characters = new ArrayList<>();
    private final List<String> replacements = new ArrayList<>();
    private final int seqLength;
    private final Set<Character> cipherChars = new HashSet<>();

    public MonoAlphabeticTN(final String alphabet) {
        super();

        this.alphabet = alphabet;
        this.seqLength = -1;
    }

    public MonoAlphabeticTN(final String characters, final String replacements, final int seqLength) {
        super();

        this.alphabet = null;
        for (final char c: characters.toCharArray()) this.characters.add(c);
        this.replacements.addAll(Arrays.asList(replacements.split(" ")));
        this.seqLength = seqLength;
        for (final char c: replacements.toCharArray()) {
            if (c != ' ') this.cipherChars.add(c);
        }
    }

    public MonoAlphabeticTN(final String characters, final String replacements) {
        this(characters, replacements, -1);
    }

    @Override
    public String encrypt(final String message) {
        if (alphabet != null)
            return StringUtil.map(message, alphabet.length() == 33? CharUtil.RU_ALPHABET : CharUtil.EN_ALPHABET, alphabet);
        final StringBuilder result = new StringBuilder();
        for (final char c: message.toCharArray())
            result.append(c == ' ' && !characters.contains(' ') ? ' ' : replacements.get(characters.indexOf(c))).append(seqLength == -1 ? " " : "");
        return result.toString();
    }

    @Override
    public String decrypt(final String message) {
        if (alphabet != null)
            return StringUtil.map(message, alphabet, alphabet.length() == 33? CharUtil.RU_ALPHABET : CharUtil.EN_ALPHABET);
        final StringBuilder result = new StringBuilder();
        if (seqLength == -1) {
            for (final String word: message.split(" "))
                result.append(word.length() == 0 ? result.length() > 0 && result.charAt(result.length() - 1) != ' ' ? " " : "" : characters.get(StringUtil.indexOf(word, replacements)));
        } else {
            for (int i = 0; i < message.length(); i += seqLength) {
                if (cipherChars.contains(message.charAt(i))) {
                    final StringBuilder b = new StringBuilder();
                    for (int j = i; j < i + seqLength; j++)
                        b.append(message.charAt(j));
                    result.append(characters.get(StringUtil.indexOf(b.toString(), replacements)));
                } else if (message.charAt(i) == ' ') {
                    result.append(message.charAt(i));
                    i -= seqLength;
                    i++;
                } else return "\0";
            }
        }

        return result.toString();
    }
}
