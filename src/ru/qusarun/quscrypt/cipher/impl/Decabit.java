package ru.qusarun.quscrypt.cipher.impl;

import ru.qusarun.quscrypt.cipher.Cipher;
import ru.qusarun.quscrypt.cipher.CipherInfo;
import ru.qusarun.quscrypt.cipher.CipherType;
import ru.qusarun.quscrypt.cipher.KeyType;

import java.util.HashMap;
import java.util.Map;

@CipherInfo(cipherType = CipherType.BINARY, keyType = KeyType.NONE)
public class Decabit extends Cipher {
    private static final Map<Integer, String> table = new HashMap<>();

    static {
        table.put(0,   "--+-+++-+-");
        table.put(1,   "+--+++--+-");
        table.put(2,   "+--++-+-+-");
        table.put(3,   "+--+-++-+-");
        table.put(4,   "----+++-++");
        table.put(5,   "++--+++---");
        table.put(6,   "++--++--+-");
        table.put(7,   "++--+-+-+-");
        table.put(8,   "++---++-+-");
        table.put(9,   "---++++-+-");
        table.put(10,  "+-+-+++---");
        table.put(11,  "+-+-+-+-+-");
        table.put(12,  "+-+--++-+-");
        table.put(13,  "+---++-++-");
        table.put(14,  "+---++--++");
        table.put(15,  "--+++-++--");
        table.put(16,  "---++-+++-");
        table.put(17,  "+---+-++-+");
        table.put(18,  "+--++--+-+");
        table.put(19,  "+--++-+--+");
        table.put(20,  "+-+++--+--");
        table.put(21,  "+--+++-+--");
        table.put(22,  "++--+-++--");
        table.put(23,  "-+-++-++--");
        table.put(24,  "+--++--++-");
        table.put(25,  "+-+++-+---");
        table.put(26,  "++-+--++--");
        table.put(27,  "+-+-+-++--");
        table.put(28,  "+--+-+++--");
        table.put(29,  "+--+--++-+");
        table.put(30,  "+-++-++---");
        table.put(31,  "+-++-+-+--");
        table.put(32,  "+-+-++-+--");
        table.put(33,  "+---++++--");
        table.put(34,  "+-+--+-++-");
        table.put(35,  "+++--++---");
        table.put(36,  "+++--+-+--");
        table.put(37,  "+++---++--");
        table.put(38,  "++---+++--");
        table.put(39,  "--+-++++--");
        table.put(40,  "++--++-+--");
        table.put(41,  "-+-+-+-++-");
        table.put(42,  "++----+++-");
        table.put(43,  "+----+-+++");
        table.put(44,  "++---+-+-+");
        table.put(45,  "++-+-+-+--");
        table.put(46,  "++-+-+--+-");
        table.put(47,  "+++----++-");
        table.put(48,  "++--+--++-");
        table.put(49,  "+--+-+-++-");
        table.put(50,  "++++----+-");
        table.put(51,  "++-++---+-");
        table.put(52,  "+-+++---+-");
        table.put(53,  "-++++---+-");
        table.put(54,  "+-+-+---++");
        table.put(55,  "+++-++----");
        table.put(56,  "+++-+-+---");
        table.put(57,  "+-+-+--++-");
        table.put(58,  "-++-+--++-");
        table.put(59,  "+++-+----+");
        table.put(60,  "++++-+----");
        table.put(61,  "-+++-++---");
        table.put(62,  "-+-+-++-+-");
        table.put(63,  "++---++--+");
        table.put(64,  "++-+--+--+");
        table.put(65,  "++-+++----");
        table.put(66,  "++++--+---");
        table.put(67,  "+--++++---");
        table.put(68,  "-+-++++---");
        table.put(69,  "++-+--+-+-");
        table.put(70,  "-++---+++-");
        table.put(71,  "+---+-+++-");
        table.put(72,  "--+-+-+++-");
        table.put(73,  "+----++++-");
        table.put(74,  "--+--++++-");
        table.put(75,  "+++---+-+-");
        table.put(76,  "+-++---++-");
        table.put(77,  "+--+--+++-");
        table.put(78,  "--++--+++-");
        table.put(79,  "+-+---+-++");
        table.put(80,  "-+++--+-+-");
        table.put(81,  "-+-++-+-+-");
        table.put(82,  "-+++---++-");
        table.put(83,  "-+-++--++-");
        table.put(84,  "-+---++++-");
        table.put(85,  "-++++--+--");
        table.put(86,  "-++-++-+--");
        table.put(87,  "--++++-+--");
        table.put(88,  "--++-+++--");
        table.put(89,  "--++-+-++-");
        table.put(90,  "+-++++----");
        table.put(91,  "--++++--+-");
        table.put(92,  "--++-++-+-");
        table.put(93,  "+--+-+--++");
        table.put(94,  "+-++----++");
        table.put(95,  "-+-+++--+-");
        table.put(96,  "-++-+-+-+-");
        table.put(97,  "-+--++-++-");
        table.put(98,  "---+++-++-");
        table.put(99,  "-+--+-+++-");
        table.put(100, "+---+++-+-");
        table.put(101, "-+--+++-+-");
        table.put(102, "+-+-++--+-");
        table.put(103, "+--++-++--");
        table.put(104, "++-++--+--");
        table.put(105, "+-++--++--");
        table.put(106, "+-+--+++--");
        table.put(107, "-++--+++--");
        table.put(108, "++---+-++-");
        table.put(109, "++-+---++-");
        table.put(110, "+++-+---+-");
        table.put(111, "+++-+--+--");
        table.put(112, "++-+-++---");
        table.put(113, "++-++-+---");
        table.put(114, "+-+---+++-");
        table.put(115, "+-++--+-+-");
        table.put(116, "-+-+--+++-");
        table.put(117, "-+++-+-+--");
        table.put(118, "+-++-+--+-");
        table.put(119, "-++-+++---");
        table.put(120, "+++--+--+-");
        table.put(121, "+++++-----");
        table.put(122, "-+++++----");
        table.put(123, "--+++++---");
        table.put(124, "---+++++--");
        table.put(125, "----+++++-");
        table.put(126, "++++++++++");
    }

    @Override
    public String encrypt(String message) {
        final StringBuilder result = new StringBuilder();
        for (final char c: message.toCharArray()) result.append(table.get((int) c)).append(" ");
        return result.toString();
    }

    @Override
    public String decrypt(String message) {
        final StringBuilder result = new StringBuilder();

        loop:
        for (final String word: message.replace("0", "-").replace("1", "+").split(" ")) {
            for (final int key: table.keySet()) {
                final String value = table.get(key);
                if (value.equals(word)) {
                    result.append((char) key);
                    continue loop;
                }
            }

            return "\0";
        }

        return result.toString();
    }
}
