package io.javadash.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class BaseValidation {
    @SafeVarargs
    public static <T> boolean hasNullOrEmptyList(Collection<? extends T>... values) {
        return Arrays.stream(values).anyMatch(list -> list == null || list.isEmpty());
    }

    /**
     * Checks if a value is "falsey".
     *
     * @param value The value to check.
     * @param <T>   The type of the value.
     * @return True if the value is falsey, false otherwise.
     */
    public static <T> boolean isFalsy(T value) {
        if (value == null) {
            return true;
        }

        if (value instanceof Boolean) {
            return !(Boolean) value;
        }

        if (value instanceof Number) {
            double num = ((Number) value).doubleValue();
            return num == 0 || Double.isNaN(num);
        }

        if (value instanceof String) {
            return ((String) value).isEmpty();
        }

        return false;
    }

    public static boolean isValidList(Object collection) {
        if (collection == null) {
            return false;
        }
        if (collection instanceof Collection<?>) {
            return !((Collection<?>) collection).isEmpty();
        }
        if (collection instanceof Map<?, ?>) {
            return !((Map<?, ?>) collection).isEmpty();
        }
        return false;
    }

    @SafeVarargs
    public static <T> boolean isValidRestList(Collection<? extends T>... values) {
        return values != null && values.length > 0;
    }

    @SafeVarargs
    public static <T> boolean isValidRestValues(T... values) {
        return values != null && values.length > 0;
    }

    @SafeVarargs
    public static <T> boolean isValidRestZip(Collection<? extends T>... values) {
        return values != null && Arrays.stream(values).filter(Objects::nonNull).count() > 1;
    }

    public static boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isValidNumber(Number num) {
        return num != null;
    }
}