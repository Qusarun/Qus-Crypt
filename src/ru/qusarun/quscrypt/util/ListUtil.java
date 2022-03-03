package ru.qusarun.quscrypt.util;

import java.util.List;
import java.util.Random;

public class ListUtil {
    public static <T> void swap(final List<T> list, final int a, final int b) {
        final T c = list.get(a), d = list.get(b);
        list.set(b, c);
        list.set(a, d);
    }

    public static <T> void shuffle(final List<T> list, final int times, final Random random) {
        for (int i = 0; i < times; i++)
            swap(list, random.nextInt(list.size()), random.nextInt(list.size()));
    }
}
