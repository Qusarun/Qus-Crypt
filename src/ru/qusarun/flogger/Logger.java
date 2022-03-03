package ru.qusarun.flogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Logger {
    private static String prefix = "%R";
    private static final Map<String, String> COLORS = new HashMap<>();
    
    static {
        COLORS.put("R", ConsoleColors.RESET);
        COLORS.put("B", ConsoleColors.BLACK);
        COLORS.put("r", ConsoleColors.RED);
        COLORS.put("g", ConsoleColors.GREEN);
        COLORS.put("y", ConsoleColors.YELLOW);
        COLORS.put("b", ConsoleColors.BLUE);
        COLORS.put("p", ConsoleColors.PURPLE);
        COLORS.put("c", ConsoleColors.CYAN);
        COLORS.put("w", ConsoleColors.WHITE);

        COLORS.put("*B", ConsoleColors.BLACK_BOLD);
        COLORS.put("*r", ConsoleColors.RED_BOLD);
        COLORS.put("*g", ConsoleColors.GREEN_BOLD);
        COLORS.put("*y", ConsoleColors.YELLOW_BOLD);
        COLORS.put("*b", ConsoleColors.BLUE_BOLD);
        COLORS.put("*p", ConsoleColors.PURPLE_BOLD);
        COLORS.put("*c", ConsoleColors.CYAN_BOLD);
        COLORS.put("*w", ConsoleColors.WHITE_BOLD);

        COLORS.put("_B", ConsoleColors.BLACK_UNDERLINED);
        COLORS.put("_r", ConsoleColors.RED_UNDERLINED);
        COLORS.put("_g", ConsoleColors.GREEN_UNDERLINED);
        COLORS.put("_y", ConsoleColors.YELLOW_UNDERLINED);
        COLORS.put("_b", ConsoleColors.BLUE_UNDERLINED);
        COLORS.put("_p", ConsoleColors.PURPLE_UNDERLINED);
        COLORS.put("_c", ConsoleColors.CYAN_UNDERLINED);
        COLORS.put("_w", ConsoleColors.WHITE_UNDERLINED);

        COLORS.put("+B", ConsoleColors.BLACK_BRIGHT);
        COLORS.put("+r", ConsoleColors.RED_BRIGHT);
        COLORS.put("+g", ConsoleColors.GREEN_BRIGHT);
        COLORS.put("+y", ConsoleColors.YELLOW_BRIGHT);
        COLORS.put("+b", ConsoleColors.BLUE_BRIGHT);
        COLORS.put("+p", ConsoleColors.PURPLE_BRIGHT);
        COLORS.put("+c", ConsoleColors.CYAN_BRIGHT);
        COLORS.put("+w", ConsoleColors.WHITE_BRIGHT);

        COLORS.put("+*B", ConsoleColors.BLACK_BOLD_BRIGHT);
        COLORS.put("+*r", ConsoleColors.RED_BOLD_BRIGHT);
        COLORS.put("+*g", ConsoleColors.GREEN_BOLD_BRIGHT);
        COLORS.put("+*y", ConsoleColors.YELLOW_BOLD_BRIGHT);
        COLORS.put("+*b", ConsoleColors.BLUE_BOLD_BRIGHT);
        COLORS.put("+*p", ConsoleColors.PURPLE_BOLD_BRIGHT);
        COLORS.put("+*c", ConsoleColors.CYAN_BOLD_BRIGHT);
        COLORS.put("+*w", ConsoleColors.WHITE_BOLD_BRIGHT);
    }

    public static String format(String s) {
        for (final Map.Entry<String, String> entry : COLORS.entrySet())
            s = s.replace("%" + entry.getKey(), entry.getValue());
        return s;
    }

    public static boolean log(String info) { return logNoLine(info + System.lineSeparator()); }

    public static boolean log(Object object) { return log(object.toString()); }

    public static boolean logNoLine(String info) {
        System.out.print(format(getPrefix() + info));
        return true;
    }

    public static String input(final String prompt) {
        Logger.logNoLine(prompt);
        return new Scanner(System.in).nextLine();
    }

    public static boolean clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return true;
    }

    public static String getPrefix() { return prefix == null ? "" : prefix; }
    public static void setPrefix(String prefix) { Logger.prefix = prefix; }
}
