package ru.qusarun.quscrypt.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
    public static String read(final String file) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final BufferedReader br = new BufferedReader(new FileReader(file));
        char c;
        while ((c = (char) br.read()) != 65535) builder.append(c);
        br.close();
        return builder.toString();
    }

    public static void write(final String file, final String text) throws IOException {
        final PrintWriter pw = new PrintWriter(file);
        pw.print(text);
        pw.close();
    }
}
