package ru.qusarun.quscrypt.util;

import ru.qusarun.flogger.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ClassUtil {
    @SuppressWarnings("unchecked")
    public static <T> List<T> createInstances(final List<Class<?>> classes) {
        final List<T> result = new ArrayList<>();

        for (final Class<?> clazz: classes) {
            try {
                result.add((T) clazz.getConstructors()[0].newInstance());
            } catch (final Exception ignored) {
                Logger.log("Failed to instantiate %b" + clazz.getSimpleName());
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getField(final String name, final Object object, final Class<?> clazz) {
        try {
            final Field f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            return (T) f.get(object);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void execute(final T object, final LOL<T> lol) { lol.lol(object); }

    public static <T, U> T transform(final U object, final Transformer<T, U> transformer) { return transformer.transform(object); }
}
