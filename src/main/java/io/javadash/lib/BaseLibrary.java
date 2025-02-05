package io.javadash.lib;

import static io.javadash.lib.BaseValidation.isValidList;
import static io.javadash.lib.BaseValidation.isValidString;
import static io.javadash.lib.Constant.ESCAPE_HTML_CHARS;
import static io.javadash.lib.Constant.LATIN_1_A;
import static io.javadash.lib.Constant.LATIN_1_A_LOW;
import static io.javadash.lib.Constant.LATIN_1_C;
import static io.javadash.lib.Constant.LATIN_1_C_LOW;
import static io.javadash.lib.Constant.LATIN_1_D;
import static io.javadash.lib.Constant.LATIN_1_D_LOW;
import static io.javadash.lib.Constant.LATIN_1_E;
import static io.javadash.lib.Constant.LATIN_1_E_LOW;
import static io.javadash.lib.Constant.LATIN_1_I;
import static io.javadash.lib.Constant.LATIN_1_I_LOW;
import static io.javadash.lib.Constant.LATIN_1_O;
import static io.javadash.lib.Constant.LATIN_1_O_LOW;
import static io.javadash.lib.Constant.LATIN_1_SS;
import static io.javadash.lib.Constant.LATIN_1_U;
import static io.javadash.lib.Constant.LATIN_1_U_LOW;
import static io.javadash.lib.Constant.LATIN_1_Y;
import static io.javadash.lib.Constant.LATIN_1_Y_LOW;
import static io.javadash.lib.Constant.LATIN_EXT_A_A;
import static io.javadash.lib.Constant.LATIN_EXT_A_A_LOW;
import static io.javadash.lib.Constant.LATIN_EXT_A_C;
import static io.javadash.lib.Constant.LATIN_EXT_A_C_LOW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaseLibrary {
    public static final Pattern reLatin =
        Pattern.compile("[\\xc0-\\xd6\\xd8-\\xf6\\xf8-\\xff\\u0100-\\u017f]", Pattern.UNICODE_CASE);
    public static final Pattern reComboMark =
        Pattern.compile("[\\u0300-\\u036f\\ufe20-\\ufe2f\\u20d0-\\u20ff]", Pattern.UNICODE_CASE);

    public static <T> List<T> baseList(Collection<? extends T> value, Predicate<T> predicate) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(predicate);
        return value.stream().filter(Objects::nonNull).filter(predicate).collect(Collectors.toList());
    }

    public static <T> List<T> baseDifference(Collection<? extends T> source, Collection<? extends T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Set<T> resultSet = new LinkedHashSet<>(source);
        resultSet.removeAll(new HashSet<>(target));
        return new ArrayList<>(resultSet);
    }

    public static <T> List<T> baseDifference(Collection<? extends T> source, Collection<? extends T> target, BiPredicate<T, T> comparator) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Objects.requireNonNull(comparator);
        return source.stream().filter(Objects::nonNull).filter(srcElement -> target.stream().filter(Objects::nonNull)
            .noneMatch(targetElement -> comparator.test(srcElement, targetElement))).collect(Collectors.toList());
    }

    /**
     * Computes the difference between two lists using the iteratee function.
     *
     * @param <T>      The type of elements in the list.
     * @param list1    The first list.
     * @param list2    The second list.
     * @param iteratee The function to transform each element before comparison.
     * @return A list containing the difference.
     */
    private static <T> List<T> baseDifference(List<T> list1, List<T> list2, Function<T, ?> iteratee) {
        Set<Object> transformedSet = new HashSet<>();
        for (T element : list2) {
            transformedSet.add(iteratee.apply(element));
        }
        List<T> result = new ArrayList<>();
        for (T element : list1) {
            if (!transformedSet.contains(iteratee.apply(element))) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> List<T> baseIntersection(Collection<? extends T> source, Collection<? extends T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Set<T> resultSet = new LinkedHashSet<>(source);
        resultSet.retainAll(new HashSet<>(target));
        return new ArrayList<>(resultSet);
    }

    public static <T> List<T> baseIntersection(Collection<? extends T> source, Collection<? extends T> target, BiPredicate<T, T> comparator) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Objects.requireNonNull(comparator);
        return source.stream().filter(Objects::nonNull).filter(srcElement -> target.stream().filter(Objects::nonNull)
            .anyMatch(targetElement -> comparator.test(srcElement, targetElement))).collect(Collectors.toList());
    }

    /**
     * A helper method that slices the list starting from the given index to the end.
     *
     * @param collection  The list to slice.
     * @param start The start index for the slice.
     * @param end   The end index for the slice.
     * @return A sublist from start index to end index.
     */
    public static <T> List<T> baseSlice(Collection<? extends T> collection, int start, int end) {
        List<T> list = new ArrayList<>(collection);
        return new ArrayList<>(list.subList(Math.max(0, start), Math.min(end, list.size())));
    }

    /**
     * Helper method that filters elements from the end of the list until the predicate returns false.
     *
     * @param <T>       The type of elements in the list.
     * @param collection      The list to query.
     * @param predicate The function invoked per iteration.
     * @param fromEnd   If true, the elements are taken from the end of the list.
     * @return A new list containing the filtered elements.
     */
    public static <T> List<T> baseWhile(Collection<? extends T> collection, Predicate<T> predicate, boolean fromEnd) {
        List<T> result = new ArrayList<>();
        if (!isValidList(collection)) {
            return result;
        }
        List<T> list = new ArrayList<>(collection);
        int startIndex = fromEnd ? list.size() - 1 : 0;
        int endIndex = fromEnd ? -1 : list.size();
        int step = fromEnd ? -1 : 1;
        for (int i = startIndex; i != endIndex; i += step) {
            T element = list.get(i);
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        if (fromEnd) {
            Collections.reverse(result);
        }
        return result;
    }

    /**
     * Flattens a list of lists into a single list.
     *
     * @param <T>    The type of elements in the list.
     * @param arrays The arrays to flatten.
     * @return A flattened list.
     */
    @SafeVarargs
    public static <T> List<T> baseFlatten(Collection<? extends T>... arrays) {
        List<T> result = new ArrayList<>();
        for (Collection<? extends T> array : arrays) {
            if (array != null) {
                result.addAll(array);
            }
        }
        return result;
    }

    /**
     * The base implementation of uniqBy which uses the iteratee function to determine uniqueness.
     *
     * @param <T>      The type of elements in the list.
     * @param list     The list to inspect.
     * @param iteratee The iteratee function that generates the criterion for uniqueness.
     * @return A list with unique values based on the iteratee's result.
     */
    public static <T> List<T> baseUniq(Collection<? extends T> list, Function<T, ?> iteratee) {
        Set<Object> seen = new HashSet<>();  // Set to track already seen criterion
        List<T> result = new ArrayList<>();
        for (T value : list) {
            Object computed = iteratee.apply(value);
            if (seen.add(computed)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Helper method to create a list with unique values based on the comparator.
     *
     * @param <T>        The type of elements in the list.
     * @param list       The list to check.
     * @param comparator The comparator to compare two elements.
     * @return A list with unique values based on the comparator.
     */
    public static <T> List<T> baseUniq(Collection<? extends T> list, BiPredicate<T, T> comparator) {
        List<T> result = new ArrayList<>();
        for (T value : list) {
            boolean isUnique = true;
            for (T existingValue : result) {
                if (comparator.test(value, existingValue)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Helper method that calculates the symmetric difference of an array of lists.
     *
     * @param <T>    The type of elements in the list.
     * @param arrays The lists to inspect.
     * @return A list containing the symmetric difference.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> baseXor(List<Collection<? extends T>> arrays) {
        int length = arrays.size();
        if (length < 2) {
            return length > 0 ? new ArrayList<>(new HashSet<>(arrays.get(0))) : new ArrayList<>();
        }
        Set<T> resultSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            List<T> array = (List<T>) arrays.get(i);
            Set<T> arraySet = new HashSet<>(array);
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    List<T> otherArray = (List<T>) arrays.get(j);
                    Set<T> otherSet = new HashSet<>(otherArray);
                    arraySet.removeAll(otherSet); // Remove elements in 'otherSet' from 'arraySet'
                }
            }
            resultSet.addAll(arraySet); // Add the remaining unique elements to the result set
        }
        return new ArrayList<>(resultSet); // Return the result as a List
    }

    /**
     * Helper method that calculates the symmetric difference of an array of lists with an iteratee.
     *
     * @param <T>      The type of elements in the list.
     * @param arrays   The lists to inspect.
     * @param iteratee The function to transform each element before comparison.
     * @return A list containing the symmetric difference.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> baseXorBy(List<Collection<? extends T>> arrays, Function<T, ?> iteratee) {
        int length = arrays.size();
        if (length < 2) {
            return length > 0 ? baseUniq(arrays.get(0), iteratee) : new ArrayList<>();
        }
        Map<Object, Integer> frequencyMap = new HashMap<>();
        Set<Object> seenKeys = new HashSet<>();
        for (int i = 0; i < length; i++) {
            List<T> array = (List<T>) arrays.get(i);
            for (T element : array) {
                Object key = iteratee.apply(element);
                frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
                seenKeys.add(key);
            }
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<T> array = (List<T>) arrays.get(i);
            for (T element : array) {
                Object key = iteratee.apply(element);
                if (frequencyMap.get(key) == 1) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /**
     * Helper method to compute the symmetric difference of an array of lists with a comparator function.
     *
     * @param <T>        The type of elements in the list.
     * @param arrays     The lists to inspect.
     * @param comparator The comparator function to compare elements.
     * @return A list containing the symmetric difference.
     */
    public static <T> List<T> baseXorWith(List<Collection<? extends T>> arrays, BiPredicate<T, T> comparator) {
        List<T> result = new ArrayList<>();
        for (Collection<? extends T> array : arrays) {
            for (T element : array) {
                boolean foundInOtherArrays = false;
                for (Collection<? extends T> otherArray : arrays) {
                    if (otherArray != array && contains(otherArray, element, comparator)) {
                        foundInOtherArrays = true;
                        break;
                    }
                }
                if (!foundInOtherArrays) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    private static <T> boolean contains(Collection<? extends T> list, T element, BiPredicate<T, T> comparator) {
        for (T e : list) {
            if (comparator.test(e, element)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T baseClone(Object value) {
        T result = null;
        if (value == null) {
            return null;
        }
        if (value instanceof List) {
            result = (T) new ArrayList<>((List<?>) value);
        } else if (value instanceof Map) {
            result = (T) new HashMap<>((Map<?, ?>) value);
        } else if (value instanceof Set) {
            result = (T) new HashSet<>((Set<?>) value);
        } else if (value instanceof String || value instanceof Integer || value instanceof Boolean ||
            value instanceof Double) {
            return (T) value;
        } else {
            result = (T) new Object();
        }
        return result;
    }

    /**
     * Converts a letter using the deburredLetters map.
     *
     * @param letter The letter to deburr.
     * @return The deburred letter.
     */
    public static String deburrLetter(String letter) {
        Map<String, String> deburredLetters = new HashMap<>();
        deburredLetters.put(LATIN_1_A, "A");
        deburredLetters.put(LATIN_1_A_LOW, "a");
        deburredLetters.put(LATIN_1_C, "C");
        deburredLetters.put(LATIN_1_C_LOW, "c");
        deburredLetters.put(LATIN_1_D, "D");
        deburredLetters.put(LATIN_1_D_LOW, "d");
        deburredLetters.put(LATIN_1_E, "E");
        deburredLetters.put(LATIN_1_E_LOW, "e");
        deburredLetters.put(LATIN_1_I, "I");
        deburredLetters.put(LATIN_1_I_LOW, "i");
        deburredLetters.put(LATIN_1_O, "O");
        deburredLetters.put(LATIN_1_O_LOW, "o");
        deburredLetters.put(LATIN_1_U, "U");
        deburredLetters.put(LATIN_1_U_LOW, "u");
        deburredLetters.put(LATIN_1_Y, "Y");
        deburredLetters.put(LATIN_1_Y_LOW, "y");
        deburredLetters.put(LATIN_1_SS, "ss");

        // Latin Extended-A mappings
        deburredLetters.put(LATIN_EXT_A_A, "A");
        deburredLetters.put(LATIN_EXT_A_A_LOW, "a");
        deburredLetters.put(LATIN_EXT_A_C, "C");
        deburredLetters.put(LATIN_EXT_A_C_LOW, "c");
        return deburredLetters.getOrDefault(letter, letter);  // Default to the original if not found
    }

    public static String getEscapeReplacement(char c) {
        for (String[] pair : ESCAPE_HTML_CHARS) {
            if (pair[0].charAt(0) == c) {
                return pair[1];
            }
        }
        return null;
    }

    /**
     * Creates a padding string of the specified length using the given characters.
     *
     * @param length The length of the padding string.
     * @param chars  The string used for padding.
     * @return The generated padding string.
     */
    public static String createPadding(int length, String chars) {
        if (length <= 0 || !isValidString(chars)) {
            return "";
        }
        StringBuilder padding = new StringBuilder();
        int charsLength = chars.length();
        for (int i = 0; i < length; i++) {
            padding.append(chars.charAt(i % charsLength));
        }
        return padding.toString();
    }

    /**
     * Capitalizes the first letter of a string.
     *
     * @param word The string to capitalize.
     * @return The capitalized string.
     */
    public static String upperFirst(String word) {
        if (!isValidString(word)) {
            return "";
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

    /**
     * Splits the string by non-alphabet characters.
     *
     * @param string The string to split.
     * @return A list of words.
     */
    public static List<String> splitByNonAlphabets(String string) {
        List<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else if (!word.isEmpty()) {
                words.add(word.toString());
                word.setLength(0);
            }
        }
        if (!word.isEmpty()) {
            words.add(word.toString());
        }
        return words;
    }

    /**
     * Find the index of the first occurrence of characters from chars array in the input string array.
     */
    public static int charsStartIndex(char[] strSymbols, char[] chrSymbols) {
        int start = 0;
        while (start < strSymbols.length) {
            boolean match = false;
            for (char c : chrSymbols) {
                if (strSymbols[start] == c) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                break;
            }
            start++;
        }
        return start;
    }

    /**
     * Find the index of the last occurrence of characters from chars array in the input string array.
     */
    public static int charsEndIndex(char[] strSymbols, char[] chrSymbols) {
        int end = strSymbols.length;
        while (end > 0) {
            boolean match = false;
            for (char c : chrSymbols) {
                if (strSymbols[end - 1] == c) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                break;
            }
            end--;
        }
        return end;
    }
}
