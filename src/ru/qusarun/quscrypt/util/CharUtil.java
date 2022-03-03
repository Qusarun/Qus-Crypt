package ru.qusarun.quscrypt.util;

public class CharUtil {
    public static final String EN_ALPHABET = "abcdefghijklmnopqrstuvwxyz", RU_ALPHABET = "абвгдеёжзийклмнопрстуфцчшщъыьэюя", DE_ALPHABET = "abcdefghijklmnopqrstuvwxyzäöüß", NO_ALPHABET = "aåæbcdefghijklmnøopqrstuvwxyz", SYMBOLS = "`1234567890[]',./=\\-;~!@#$%^&*(){}\"<>?+_:";

    /* I know that java already has this function, but it doesn't quite work as I'd like it to, so I made a custom one :> */
    public static boolean isLetter(final char c) { return EN_ALPHABET.contains(str(toCase(c, 0))); }

    public static boolean isSymbol(final char c) { return SYMBOLS.contains(str(c)); }

    public static boolean isWhitespace(final char c) { return c == ' ' || c == '\n' || c == '\r' || c == '\t'; }

    public static boolean isValidChar(final char c) { return isLetter(c) || isSymbol(c) || isWhitespace(c); }

    /* returns 0 if the character is lowercase or can't have a case and 1 if the character is uppercase */
    public static int getCase(final char c) { return c == toCase(c, 0)? 0 : 1; }

    public static char toCase(final char c, final int i) { return i == 0? Character.toLowerCase(c) : Character.toUpperCase(c); }

    /* I'm too lazy to type Character.toString(c), ok? */
    private static String str(final char c) { return Character.toString(c); }
}
