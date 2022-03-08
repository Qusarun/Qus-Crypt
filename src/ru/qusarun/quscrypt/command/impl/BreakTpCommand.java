package ru.qusarun.quscrypt.command.impl;

import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.QusCrypt;
import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.KeyType;
import ru.qusarun.quscrypt.cipher.impl.english.Vigenere;
import ru.qusarun.quscrypt.cipher.impl.toki_pona.VigenereTp;
import ru.qusarun.quscrypt.command.Command;
import ru.qusarun.quscrypt.command.CommandInfo;
import ru.qusarun.quscrypt.util.CharUtil;
import ru.qusarun.quscrypt.util.MathUtil;
import ru.qusarun.quscrypt.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@CommandInfo(usage = "breaktp <message>", description = "automatically decrypts Toki Pona messages")
public class BreakTpCommand extends Command {
    private static final int ITERATIONS = 5;

    @Override public boolean execute(final String[] args) { return args.length < 1? invalidUsage() : Logger.log(decrypt(StringUtil.join(args))); }

    public String decrypt(String message) {
        if (StringUtil.isValidTokiPona(message))
            return message + " is already plain text";

        final List<String> ciphers = new ArrayList<>();
        for (int i = 0; i < ITERATIONS; i++) {
            for (final Cipher cipher: QusCrypt.INSTANCE.getCipherManager().getCiphers()) {
                if (cipher.getKeyType() == KeyType.NONE) {
                    try {
                        final String result = cipher.decrypt(message);
                        if (result.equalsIgnoreCase(message))
                            continue;
                        if ((cipher.producesNonASCII() && StringUtil.isValidTokiPonaString(result)) || StringUtil.isValidTokiPona(result)) {
                            ciphers.add(cipher.getName());
                            message = result;
                        }
                    } catch (final Exception ignored) { }
                }

                if (cipher.getKeyType() == KeyType.INT && cipher.getKeysA() != null) {
                    final int[] keysA = cipher.getKeysA() == Cipher.ONE_TO_LEN? MathUtil.range(1, message.length()) : cipher.getKeysA();
                    for (int a: keysA) {
                        try {
                            cipher.setKey(String.valueOf(a));
                            final String result = cipher.decrypt(message);
                            if (result.equalsIgnoreCase(message))
                                continue;
                            if ((cipher.producesNonASCII() && StringUtil.isValidTokiPonaString(result)) || StringUtil.isValidTokiPona(result)) {
                                ciphers.add(cipher.getName() + "%R(%b" + a + "%R)");
                                message = result;
                            }
                        } catch (final Exception ignored) { }
                    }
                }

                if (cipher.getKeyType() == KeyType.INT_INT && cipher.getKeysA() != null && cipher.getKeysB() != null) {
                    final int[] keysA = cipher.getKeysA() == Cipher.ONE_TO_LEN? MathUtil.range(1, message.length()) : cipher.getKeysA();
                    final int[] keysB = cipher.getKeysB() == Cipher.ONE_TO_LEN? MathUtil.range(1, message.length()) : cipher.getKeysB();
                    for (int a: keysA) {
                        for (int b: keysB) {
                            try {
                                cipher.setKey(String.valueOf(a), String.valueOf(b));
                                final String result = cipher.decrypt(message);
                                if (result.equalsIgnoreCase(message))
                                    continue;
                                if ((cipher.producesNonASCII() && StringUtil.isValidTokiPonaString(result)) || StringUtil.isValidTokiPona(result)) {
                                    ciphers.add(cipher.getName() + "%R(%b" + a + "%R, %b" + b + "%R)");
                                    message = result;
                                }
                            } catch (final Exception ignored) { }
                        }
                    }
                }

                if (StringUtil.isValidTokiPona(message)) {
                    printPath(ciphers);
                    return message;
                }
            }
        }

        final VigenereTp vigenere = (VigenereTp) QusCrypt.INSTANCE.getCipherManager().getCipherByName("VigenereTp");
        for (final String word: StringUtil.TP_WORDS) {
            vigenere.setKey(word);
            final String result = vigenere.decrypt(message);
            if (result.equalsIgnoreCase(message))
                continue;
            if (StringUtil.isValidTokiPona(result)) {
                ciphers.add(vigenere.getName() + "%R(%b" + word + "%R)");
                message = result;
                printPath(ciphers);
                return message;
            }
        }

        printPath(ciphers);
        return "Decryption failed.";
    }

    public boolean matches(final String word, final String pattern) {
        return word.length() == pattern.length() && toPattern(word).equals(toPattern(pattern));
    }

    public String toPattern(final String s) {
        final StringBuilder   result = new StringBuilder();
        final List<Character> chars  = new ArrayList<>();
        for (final char c: s.toCharArray()) {
            if (!chars.contains(c))
                chars.add(c);
            result.append(CharUtil.TP_ALPHABET.charAt(chars.indexOf(c)));
        }

        return result.toString();
    }

    public void printPath(final List<String> ciphers) {
        final StringBuilder path = new StringBuilder();
        for (final String s: ciphers) path.append(path.length() > 0 ? "%R -> %b" + s : "%b" + s);
        if (path.length() > 0)
            Logger.log(path.toString());
    }
}
