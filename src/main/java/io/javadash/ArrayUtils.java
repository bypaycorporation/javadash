package io.javadash;

import static io.javadash.lib.BaseValidation.isFalsy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {
    /**
     * Splits the given array into chunks of the specified size.
     * If the array cannot be split evenly, the final chunk will contain the remaining elements.
     *
     * @param array The array to process.
     * @param size  The length of each chunk. Defaults to 1 if not specified.
     * @return A list of chunks (each chunk is a list of elements).
     */
    public static <T> List<List<T>> chunk(T[] array, int size) {

        int length = array == null ? 0 : array.length;
        if (length == 0 || size < 1) {
            return Collections.emptyList();
        }

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < length; i += size) {
            result.add(new ArrayList<>(List.of(array).subList(i, Math.min(length, i + size))));
        }

        return result;
    }

    /**
     * Removes all falsey values (`false`, `null`, `0`, `""`, `NaN`) from the array.
     *
     * @param array The input array.
     * @param <T>   The type of elements in the array.
     * @return A new list with all falsey values removed.
     * @example compact(new Object[] { 0, 1, false, 2, " ", 3 }); // => [1, 2, 3]
     */
    public static <T> List<T> compact(T[] array) {

        int length = array == null ? 0 : array.length;
        if (length == 0) {
            return Collections.emptyList();
        }

        List<T> result = new ArrayList<>();

        for (T value : array) {
            if (!isFalsy(value)) {
                result.add(value);
            }
        }

        return result;
    }
}
