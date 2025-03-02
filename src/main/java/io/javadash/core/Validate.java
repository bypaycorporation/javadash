package io.javadash.core;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Validate {
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

    /**
     * Tests whether the given object is an Object array or a primitive array in a null-safe manner.
     *
     * <p>
     * A {@code null} {@code object} Object will return {@code false}.
     * </p>
     *
     * <pre>
     * ObjectUtils.isArray(null)             = false
     * ObjectUtils.isArray("")               = false
     * ObjectUtils.isArray("ab")             = false
     * ObjectUtils.isArray(new int[]{})      = true
     * ObjectUtils.isArray(new int[]{1,2,3}) = true
     * ObjectUtils.isArray(1234)             = false
     * </pre>
     *
     * @param object the object to check, may be {@code null}
     * @return {@code true} if the object is an {@code array}, {@code false} otherwise
     */
    public static boolean isArray(final Object object) {
        return object != null && object.getClass().isArray();
    }

    /**
     * Tests if an Object is empty or null.
     *
     * The following types are supported:
     * <ul>
     * <li>{@link CharSequence}: Considered empty if its length is zero.</li>
     * <li>{@link Array}: Considered empty if its length is zero.</li>
     * <li>{@link Collection}: Considered empty if it has zero elements.</li>
     * <li>{@link Map}: Considered empty if it has zero key-value mappings.</li>
     * <li>{@link Optional}: Considered empty if {@link Optional#isPresent} returns false, regardless of the "emptiness" of the contents.</li>
     * </ul>
     *
     * <pre>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty("")               = true
     * ObjectUtils.isEmpty("ab")             = false
     * ObjectUtils.isEmpty(new int[]{})      = true
     * ObjectUtils.isEmpty(new int[]{1,2,3}) = false
     * ObjectUtils.isEmpty(1234)             = false
     * ObjectUtils.isEmpty(1234)             = false
     * ObjectUtils.isEmpty(Optional.of(""))  = false
     * ObjectUtils.isEmpty(Optional.empty()) = true
     * </pre>
     *
     * @param object  the {@link Object} to test, may be {@code null}
     * @return {@code true} if the object has a supported type and is empty or null,
     * {@code false} otherwise
     */
    public static boolean isEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (isArray(object)) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }
        if (object instanceof Optional<?>) {
            // TODO Java 11 Use Optional#isEmpty()
            return !((Optional<?>) object).isPresent();
        }
        return false;
    }

    @SafeVarargs
    public static <T> boolean isValidRestList(Collection<? extends T>... values) {
        return values != null && values.length > 0;
    }

    @SafeVarargs
    public static <T> boolean isValidArray(T... values) {
        return values != null && Array.getLength(values) != 0;
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