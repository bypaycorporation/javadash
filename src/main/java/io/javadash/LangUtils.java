package io.javadash;

import java.util.Collection;
import java.util.Map;


public class LangUtils {
    /**
     * Checks if the given value is considered empty. The method handles various types of objects, including
     * Strings, Collections, Maps, and Arrays, and returns true if the value is empty, null, or has no elements.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList();
     *      boolean isEmpty = LangUtils.isEmpty(numbers);  // Output: true
     * }</pre>
     *
     * @param value The value to check for emptiness.
     * @return true if the value is null, an empty String, an empty Collection, an empty Map, or an empty array;
     *         otherwise, false.
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            return ((String) value).isEmpty();
        }
        if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        }
        if (value instanceof Map) {
            return ((Map<?, ?>) value).isEmpty();
        }
        if (value instanceof Object[]) {
            return ((Object[]) value).length == 0;
        }
        return false;
    }

}
