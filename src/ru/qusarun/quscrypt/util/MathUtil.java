package ru.qusarun.quscrypt.util;

public class MathUtil {
    public static int[] range(final int start, final int end) {
        final int[] arr = new int[end - start + 1];
        for (int i = start; i <= end; i++)
            arr[i - start] = i;
        return arr;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }

        return a;
    }
}
