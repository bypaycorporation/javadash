package io.javadash;

import static io.javadash.lib.BaseLibrary.baseDifference;
import static io.javadash.lib.BaseLibrary.baseFlatten;
import static io.javadash.lib.BaseLibrary.baseIntersection;
import static io.javadash.lib.BaseLibrary.baseList;
import static io.javadash.lib.BaseLibrary.baseSlice;
import static io.javadash.lib.BaseLibrary.baseUniq;
import static io.javadash.lib.BaseLibrary.baseWhile;
import static io.javadash.lib.BaseLibrary.baseXor;
import static io.javadash.lib.BaseLibrary.baseXorBy;
import static io.javadash.lib.BaseLibrary.baseXorWith;
import static io.javadash.lib.BaseValidation.hasNullOrEmptyList;
import static io.javadash.lib.BaseValidation.isFalsy;
import static io.javadash.lib.BaseValidation.isValidList;
import static io.javadash.lib.BaseValidation.isValidRestList;
import static io.javadash.lib.BaseValidation.isValidRestValues;
import static io.javadash.lib.BaseValidation.isValidRestZip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionUtils {

    /**
     * Creates an array of elements split into groups the length of size.
     * If array can't be split evenly, the final chunk will be the remaining elements.<p>
     *
     * <pre>{@code
     *      List<String> input = List.of("a", "b", "c", "d");
     *      CollectionUtils.chunk(input, 2);
     *      // => [["a", "b"], ["c", "d"]]
     *  }</pre>
     *
     * @param collection The collection to process.
     * @param size  The length of each chunk.
     * @param <T>   The type of elements in the list.
     * @return Returns the new collection of chunks.
     */
    public static <T> List<List<T>> chunk(Collection<? extends T> collection, int size) {
        if (!isValidList(collection) || size < 1) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        int length = list.size();
        int chunkCount = (int) Math.ceil((double) length / size);
        return IntStream.range(0, chunkCount)
            .mapToObj(i -> list.subList(i * size, Math.min((i + 1) * size, length)))
            .collect(Collectors.toList());
    }

    /**
     * Creates an array with all falsey values removed. The values (`false`, `null`, `0`, `""`, `NaN`) are falsey.
     *
     * <pre>{@code
     *      List<String> input = Arrays.asList("a", null, "", "d");
     *      CollectionUtils.compact(input);
     *      // => ["a", "d"]
     *  }</pre>
     *
     * @param collection The collection to compact.
     * @param <T>   The type of elements in the array.
     * @return Returns the new array of filtered values.
     */
    public static <T> List<T> compact(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        return list.stream()
            .filter(value -> !isFalsy(value))
            .toList();
    }

    /**
     * Creates a new collection concatenating collection with any additional collection.
     *
     * <pre>{@code
     *      List<Integer> input = Arrays.asList(1);
     *      List<Integer> values1 = Arrays.asList(2, 3, 4);
     *      List<Integer> values2 = Arrays.asList(5, 6);
     *      CollectionUtils.concat(input,values1,values2);
     *      // => [1, 2, 3, 4, 5, 6]
     *  }</pre>
     *
     * @param collection  The collection to concatenate.
     * @param values The collection to concatenate.
     * @param <T>    The type of elements in the array.
     * @return Returns the new concatenated array.
     */
    @SafeVarargs
    public static <T> List<T> concat(Collection<? extends T> collection, Collection<? extends T>... values) {
        List<T> result = isValidList(collection) ? new ArrayList<>(collection) : new ArrayList<>();
        for (Collection<? extends T> value : values) {
            if (value == null) {
                result.add(null);
            } else {
                result.addAll(value);
            }
        }
        return result;
    }

    /**
     * Creates a new list with elements from the first list that are not in any of the other given lists.
     *
     * <pre>{@code
     *      List<Integer> input = Arrays.asList(2, 1);
     *      List<Integer> values = Arrays.asList(2, 3);
     *      CollectionUtils.difference(input,values);
     *      // => [1]
     *  }</pre>
     *
     * @param collection  The list to inspect.
     * @param values The lists to exclude.
     * @param <T>    The type of elements in the list.
     * @return Returns the new array of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> difference(Collection<? extends T> collection, Collection<? extends T>... values) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        if (!isValidRestList(values)) {
            return new ArrayList<>(collection);
        }
        Set<T> resultSet = new LinkedHashSet<>(collection);
        for (Collection<? extends T> value : values) {
            if (value != null) {
                resultSet.retainAll(baseDifference(collection, value));
            }
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Returns a new list containing elements from the first collection
     * that are not present in any of the other collections.
     * The comparison is performed based on a criterion derived using the provided predicate function.
     *
     * <pre>{@code
     *      List<Double> input = Arrays.asList(2.1, 1.2);
     *      List<Double> exclude = Arrays.asList(2.3, 3.4);
     *      List<Double> result = CollectionUtils.differenceBy(input, Math::floor, exclude);
     *      // => [1.2]
     *  }</pre>
     *
     * @param collection The collection to inspect.
     * @param values     The collection to exclude.
     * @param predicate  A function used to derive the criterion for comparison between elements.
     * @param <T>        The type of elements in the collections.
     * @return Returns the new collection of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> differenceBy(Collection<? extends T> collection, Predicate<T> predicate, Collection<? extends T>... values) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        if (!isValidRestList(values)) {
            return new ArrayList<>(collection);
        }
        List<T> source = baseList(collection, predicate);
        Set<T> resultSet = new LinkedHashSet<>(source);
        for (Collection<? extends T> value : values) {
            if (value != null) {
                List<T> target = baseList(value, predicate);
                resultSet.retainAll(baseDifference(source, target));
            }
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Finds the difference between a list and multiple other lists, based on a custom comparator.
     *
     * <pre>{@code
     *      List<Map<String, Integer>> objects = new ArrayList<>();
     *      Map<String, Integer> obj1 = new HashMap<>();
     *      obj1.put("x", 1);
     *      obj1.put("y", 2);
     *      Map<String, Integer> obj2 = new HashMap<>();
     *      obj2.put("x", 2);
     *      obj2.put("y", 1);
     *      objects.add(obj1);
     *      objects.add(obj2);
     *
     *      List<Map<String, Integer>> values = new ArrayList<>();
     *      Map<String, Integer> objToExclude = new HashMap<>();
     *      objToExclude.put("x", 1);
     *      objToExclude.put("y", 2);
     *      values.add(objToExclude);
     *
     *      List<Map<String, Integer>> result = CollectionUtils.differenceWith(objects, (a, b) -> a.equals(b), values);
     *      // => [{ 'x': 2, 'y': 1 }]
     *  }</pre>
     *
     * @param collection The collection to inspect.
     * @param values     The collection to exclude.
     * @param comparator the comparator to compare elements
     * @param <T>        The type of elements in the collections.
     * @return a list of elements from `array` that do not exist in any of `values`, based on the comparator
     */
    @SafeVarargs
    public static <T> List<T> differenceWith(Collection<? extends T> collection, BiPredicate<T, T> comparator, Collection<? extends T>... values) {
        Objects.requireNonNull(comparator);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        if (!isValidRestList(values)) {
            return new ArrayList<>(collection);
        }
        Set<T> resultSet = new LinkedHashSet<>(collection);
        for (Collection<? extends T> valueList : values) {
            if (valueList != null) {
                resultSet.retainAll(baseDifference(collection, valueList, comparator));
            }
        }
        return new ArrayList<>(resultSet);
    }


    /**
     * Creates a slice of collection with n elements dropped from the beginning.
     *
     * <pre>{@code
     *      List<Integer> list = List.of(1, 2, 3);
     *      List<Integer> result = CollectionUtils.drop(input, 2);
     *      // => [3]
     *  }</pre>
     *
     * @param collection The list to collection.
     * @param n     The number of elements to drop.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> drop(Collection<? extends T> collection, int n) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        int size = list.size();
        int nStep = Math.max(0, n);
        if (nStep >= size) {
            return Collections.emptyList();
        }
        return new ArrayList<>(list.subList(nStep, size));
    }

    /**
     * Creates a slice of collection with n elements dropped from the end.
     *
     * <pre>{@code
     *      List<Integer> list = List.of(1, 2, 3);
     *      List<Integer> result = CollectionUtils.dropRight(input, 2);
     *      // => [1]
     *  }</pre>
     *
     * @param collection The list to query.
     * @param n     The number of elements to drop.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> dropRight(Collection<? extends T> collection, int n) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        int length = list.size();
        if (n <= 0) {
            return list;
        }
        int nStep = Math.min(n, length);
        return new ArrayList<>(list.subList(0, length - nStep));
    }

    /**
     * Creates a slice of the collection with elements dropped from the beginning until
     * the predicate returns `false`. Once the predicate returns `false`, the remaining
     * elements are included in the result.
     *
     * <pre>{@code
     *      List<Integer> collection = List.of(1, 2, 3, 4);
     *      List<Integer> result = CollectionUtils.dropWhile(collection, x -> x < 3);
     *      // => [3, 4]
     * }</pre>
     *
     * @param collection The collection to query.
     * @param predicate  The predicate used to determine which elements to drop.
     * @param <T>        The type of elements in the collection.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> dropWhile(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        int index = 0;
        while (index < list.size() && predicate.test(list.get(index))) {
            index++;
        }
        return new ArrayList<>(list.subList(index, list.size()));
    }

    /**
     * Creates a slice of the collection with elements dropped from the end until
     * the predicate returns `false`. Once the predicate returns `false`, the remaining
     * elements are included in the result.
     *
     * <pre>{@code
     *      List<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.dropRightWhile(collection, x -> x > 3);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param collection The collection to query.
     * @param predicate  The predicate used to determine which elements to drop from the end.
     * @param <T>        The type of elements in the collection.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> dropRightWhile(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        int index = list.size() - 1;
        while (index >= 0 && predicate.test(list.get(index))) {
            index--;
        }
        return new ArrayList<>(list.subList(0, index + 1));
    }

    /**
     * Fills elements of list with value from start to end.
     *
     * <pre>{@code
     *      List<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.fill(collection, 0, 1, 4);
     *      // => [1, 0, 0, 0, 5]
     * }</pre>
     *
     * @param list       The list to modify.
     * @param value      The value to fill into the collection.
     * @param start      The start position.
     * @param end        The end position.
     * @param <T>        The type of elements in the collection.
     * @return Returns collection.
     */
    public static <T> List<T> fill(List<T> list, T value, int start, int end) {
        if (!isValidList(list)) {
            return Collections.emptyList();
        }
        int startN = Math.max(0, start);
        int endN = Math.min(list.size(), end);
        for (int i = startN; i < endN; i++) {
            list.set(i, value);
        }
        return list;
    }

    /**
     * Finds the index of the first element in the collection that satisfies the provided predicate,
     * starting from the specified index. If no element satisfies the predicate, returns -1.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      int result = CollectionUtils.findIndex(collection, x -> x == 3, 0);
     *      // => 2
     * }</pre>
     *
     * @param collection The collection to inspect.
     * @param predicate  The predicate used to test each element.
     * @param fromIndex  The index to search from.
     * @param <T>        The type of elements in the collection.
     * @return Returns the index of the found element, else -1.
     */
    public static <T> int findIndex(Collection<? extends T> collection, Predicate<T> predicate, int fromIndex) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection) || fromIndex > collection.size() - 1) {
            return -1;
        }
        List<T> list = new ArrayList<>(collection);
        int index = Math.max(fromIndex, 0);
        for (int i = index; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the last element in the collection that satisfies the provided predicate,
     * starting from the specified index and searching backwards. If no element satisfies the predicate, returns -1.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      int result = CollectionUtils.findLastIndex(collection, x -> x == 3, 4);
     *      // => 2
     * }</pre>
     *
     * @param collection The collection to search.
     * @param predicate  The predicate used to test each element.
     * @param fromIndex  The index to search from.
     * @param <T>        The type of elements in the collection.
     * @return Returns the index of the found element, else -1.
     */
    public static <T> int findLastIndex(Collection<? extends T> collection, Predicate<T> predicate, int fromIndex) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection) || fromIndex < 0) {
            return -1;
        }
        List<T> list = new ArrayList<>(collection);
        int length = list.size() - 1;
        int index = Math.min(fromIndex, length);
        for (int i = index; i >= 0; i--) {
            if (predicate.test(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Flattens collection a single level deep.
     *
     * <pre>{@code
     *      Collection<List<Integer>> collection = List.of(List.of(1, 2), List.of(3, 4));
     *      List<Integer> result = CollectionUtils.flatten(collection);
     *      // => [1, 2, 3, 4]
     * }</pre>
     *
     * @param collection The collection to flatten.
     * @param <T>        The type of elements in the collection.
     * @return Returns the new flattened collection.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> flatten(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
            .flatMap(element -> element instanceof Collection<?>
                ? ((Collection<?>) element).stream()
                : Stream.of(element))
            .map(e -> (T) e)
            .toList();
    }

    /**
     * Recursively flattens collection.
     *
     * <pre>{@code
     *      Collection<Collection<Integer>> collection = List.of(List.of(1, 2), List.of(3, List.of(4, 5)));
     *      List<Integer> result = CollectionUtils.flattenDeep(collection);
     *      // => [1, 2, 3, 4, 5]
     * }</pre>
     *
     * @param collection The collection to flatten.
     * @param <T>        The type of elements in the collection.
     * @return Returns the new flattened collection.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> flattenDeep(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .flatMap(element -> element instanceof Collection<?>
                ? flattenDeep((Collection<? extends T>) element).stream()
                : Stream.of(element))
            .collect(Collectors.toList());
    }

    /**
     * Recursively flatten collection up to depth times.
     *
     * <pre>{@code
     *      Collection<Collection<Integer>> collection = List.of(List.of(1, 2), List.of(3, List.of(4, 5)));
     *      List<Integer> result = CollectionUtils.flattenDepth(collection, 1);
     *      // => [1, 2, 3, List.of(4, 5)]
     * }</pre>
     *
     * @param collection The collection to flatten.
     * @param depth      The maximum recursion depth.
     * @param <T>        The type of elements in the collection.
     * @return Returns the new flattened collection.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> flattenDepth(Collection<? extends T> collection, int depth) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        if (depth <= 0) {
            return new ArrayList<>(collection);
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .flatMap(element -> element instanceof Collection<?>
                ? flattenDepth((Collection<? extends T>) element, depth - 1).stream()
                : Stream.of(element))
            .collect(Collectors.toList());
    }

    /**
     * This method returns an Map composed from key-value pairs.
     *
     * <pre>{@code
     *      List<List<Object>> pairs = List.of(
     *          List.of("key1", "value1"),
     *          List.of("key2"),
     *          List.of("key3", "value3")
     *      );
     *      Map<String, Object> result = CollectionUtils.fromPairs(pairs);
     *      // => { "key1"="value1", "key2"=null, "key3"="value3" }
     * }</pre>
     *
     * @param pairs The list of key-value pairs to convert into a map.
     * @return Returns the new map.
     */
    public static Map<String, Object> fromPairs(List<List<Object>> pairs) {
        if (!isValidList(pairs)) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<>();
        for (List<Object> pair : pairs) {
            if (pair == null) {
                continue;
            }
            if (pair.size() == 1) {
                result.put(pair.get(0).toString(), null);
            }
            if (pair.size() >= 2) {
                result.put(pair.get(0).toString(), pair.get(1));
            }
        }
        return result;
    }


    /**
     * Gets the first element of collection.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      Optional<Integer> result = CollectionUtils.head(collection);
     *      // => Optional[1]
     * }</pre>
     *
     * @param collection The collection to query.
     * @param <T>        The type of elements in the collection.
     * @return Returns the first element of the collection.
     */
    public static <T> Optional<T> head(Collection<? extends T> collection) {
        return isValidList(collection) && !collection.isEmpty()
            ? Optional.ofNullable(collection.iterator().next())
            : Optional.empty();
    }

    /**
     * Gets the index at which the first occurrence of value is found in collection.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      int result = CollectionUtils.indexOf(collection, 3, 0);
     *      // => 2
     * }</pre>
     *
     * @param collection The collection to inspect.
     * @param value      The value to search for.
     * @param fromIndex  The index to search from.
     * @param <T>        The type of elements in the collection.
     * @return Returns the index of the matched value, else -1.
     */
    public static <T> int indexOf(Collection<? extends T> collection, T value, int fromIndex) {
        if (!isValidList(collection) || fromIndex > collection.size() - 1) {
            return -1;
        }
        int index = Math.max(fromIndex, 0);
        return IntStream.range(index, collection.size())
            .filter(i -> Objects.equals(new ArrayList<>(collection).get(i), value))
            .findFirst()
            .orElse(-1);
    }

    /**
     * Gets all but the last element of collection.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4);
     *      List<Integer> result = CollectionUtils.initial(collection);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param collection The collection to query.
     * @param <T>        The type of elements in the collection.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> initial(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        return list.subList(0, list.size() - 1);
    }

    /**
     * Creates an collection of unique values that are included in all given collections.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3, 4);
     *      Collection<Integer> collection2 = List.of(2, 3, 4, 5);
     *      Collection<Integer> collection3 = List.of(3, 4, 6);
     *      List<Integer> result = CollectionUtils.intersection(collection1, collection2, collection3);
     *      // => [3, 4]
     * }</pre>
     *
     * @param collection The first collection to inspect.
     * @param values The other collections to compare against.
     * @param <T> The type of elements in the collections.
     * @return Returns the new collection of intersecting values.
     */
    @SafeVarargs
    public static <T> List<T> intersection(Collection<? extends T> collection, Collection<? extends T>... values) {
        if (!isValidList(collection) || !isValidRestList(values) || hasNullOrEmptyList(values)) {
            return Collections.emptyList();
        }
        Set<T> resultSet = new LinkedHashSet<>(collection);
        for (Collection<? extends T> value : values) {
            resultSet.retainAll(baseIntersection(collection, value));
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Returns a new list containing the elements that are present in all the provided collections,
     * based on the result of applying the predicate function to generate the criterion for comparison.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3, 4);
     *      Collection<Integer> collection2 = List.of(2, 3, 4, 5);
     *      Collection<Integer> collection3 = List.of(3, 4, 6);
     *      List<Integer> result = CollectionUtils.intersectionBy(collection1, x -> x % 2 == 0, collection2, collection3);
     *      // => [2, 4]
     * }</pre>
     *
     * @param collection The first collection to inspect.
     * @param predicate  The function to generate the criterion for comparison.
     * @param values     The other collections to compare against.
     * @param <T>        The type of elements in the collections.
     * @return Returns the new collection of intersecting values.
     */
    @SafeVarargs
    public static <T> List<T> intersectionBy(Collection<? extends T> collection, Predicate<T> predicate, Collection<? extends T>... values) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection) || !isValidRestList(values) || hasNullOrEmptyList(values)) {
            return Collections.emptyList();
        }
        List<T> source = baseList(collection, predicate);
        Set<T> resultSet = new LinkedHashSet<>(source);
        for (Collection<? extends T> value : values) {
            List<T> target = baseList(value, predicate);
            resultSet.retainAll(baseIntersection(source, target));
        }
        return new ArrayList<>(resultSet);
    }


    /**
     * Computes the intersection of multiple collections using a custom comparator.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3, 4);
     *      Collection<Integer> collection2 = List.of(2, 3, 4, 5);
     *      Collection<Integer> collection3 = List.of(3, 4, 6);
     *      List<Integer> result = CollectionUtils.intersectionWith(collection1, (a, b) -> a == b, collection2, collection3);
     *      // => [3, 4]
     * }</pre>
     *
     * @param collection The first collection to inspect.
     * @param comparator The function to compare elements from each collection.
     * @param values     The other collections to compare against.
     * @param <T>        The type of elements in the collections.
     * @return Returns the new collection of intersecting values.
     */
    @SafeVarargs
    public static <T> List<T> intersectionWith(Collection<? extends T> collection, BiPredicate<T, T> comparator, Collection<? extends T>... values) {
        Objects.requireNonNull(comparator);
        if (!isValidList(collection) || !isValidRestList(values) || hasNullOrEmptyList(values)) {
            return Collections.emptyList();
        }
        Set<T> resultSet = new LinkedHashSet<>(collection);
        for (Collection<? extends T> valueList : values) {
            resultSet.retainAll(baseIntersection(collection, valueList, comparator));
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Converts all elements in array into a string separated by separator.
     *
     * <pre>{@code
     *      Collection<String> collection = List.of("apple", "banana", "cherry");
     *      String result = CollectionUtils.join(collection, ", ");
     *      // => "apple, banana, cherry"
     * }</pre>
     *
     * @param collection    The collection to convert.
     * @param separator     The element separator.
     * @return Returns the joined string.
     */
    public static <T> String join(Collection<? extends T> collection, String separator) {
        if (!isValidList(collection)) {
            return "";
        }
        String actualSeparator = (separator == null || separator.isEmpty()) ? "," : separator;
        return collection.stream()
            .filter(Objects::nonNull)
            .map(String::valueOf)
            .collect(Collectors.joining(actualSeparator));
    }

    /**
     * Gets the last element of list.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      Optional<Integer> result = CollectionUtils.last(collection);
     *      // => Optional[3]
     * }</pre>
     *
     * @param collection The list to query.
     * @param <T>   The type of elements in the list.
     * @return Returns the last element of list.
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> last(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        return (Optional<T>) collection.stream()
            .skip(collection.size() - 1)
            .findFirst();
    }

    /**
     * Finds the last index of a value in a collection, searching from right to left.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 2);
     *      int index = CollectionUtils.lastIndexOf(collection, 2, 3);
     *      // => 3
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to inspect.
     * @param value      The value to search for.
     * @param fromIndex  The index to search from.
     * @return Returns the index of the matched value, else -1.
     */
    public static <T> int lastIndexOf(Collection<? extends T> collection, T value, int fromIndex) {
        if (!isValidList(collection)) {
            return -1;
        }
        int length = collection.size();
        int index = fromIndex < 0 ? Math.max(length + fromIndex, 0) : Math.min(fromIndex, length - 1);
        int i = 0;
        for (T element : collection) {
            if (i == index && Objects.equals(element, value)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Gets the element at index n of collection. If n is negative, the nth element from the end is returned.
     *
     * <pre>{@code
     *      Collection<String> collection = List.of("apple", "banana", "cherry");
     *      Optional<String> result = CollectionUtils.nth(collection, 1);
     *      // => Optional[banana]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to query.
     * @param n          The index of the element to return.
     * @return Returns the nth element of collection.
     */
    public static <T> Optional<T> nth(Collection<? extends T> collection, int n) {
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        int size = collection.size();
        int index = n < 0 ? size + n : n;
        if (index >= 0 && index < size) {
            return Optional.ofNullable(((List<? extends T>) collection).get(index));  // Cast for List-based access
        }
        return Optional.empty();
    }

    /**
     * Removes all elements from the collection that are present in the specified values.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry"));
     *      Collection<String> result = CollectionUtils.pull(collection, "banana", "cherry");
     *      // => ["apple"]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to modify.
     * @param values     The values to remove.
     * @return Returns collection.
     */
    @SafeVarargs
    public static <T> Collection<? extends T> pull(Collection<? extends T> collection, T... values) {
        if (!isValidList(collection) || !isValidRestValues(values)) {
            return collection;
        }
        Set<T> valuesSet = new HashSet<>(Arrays.asList(values));
        collection.removeIf(valuesSet::contains);
        return collection;
    }

    /**
     * Removes all elements from the collection that are present in any of the specified values.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry"));
     *      Collection<String> result = CollectionUtils.pullAll(collection, List.of("banana", "cherry"));
     *      // => ["apple"]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to modify.
     * @param values     The values to remove.
     * @return Returns collection.
     */
    public static <T> Collection<? extends T> pullAll(Collection<? extends T> collection, Collection<? extends T> values) {
        if (!isValidList(collection) || !isValidRestValues(values)) {
            return collection;
        }
        Set<T> valuesSet = new HashSet<>(values);
        collection.removeIf(valuesSet::contains);
        return collection;
    }

    /**
     * Removes elements from the collection based on the result of applying an iteratee function to each element and value.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry"));
     *      Collection<String> result = CollectionUtils.pullAllBy(collection, List.of("banana", "cherry"), String::toUpperCase);
     *      // => ["apple"]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to modify.
     * @param values     The values to remove.
     * @param iteratee   The function to apply to each element for comparison.
     * @return Returns collection.
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<? extends T> pullAllBy(Collection<? extends T> collection, Collection<? extends T> values, Function<T, Object> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection) || !isValidList(values)) {
            return collection;
        }
        Set<Object> transformedValuesSet = new HashSet<>();
        for (T value : values) {
            transformedValuesSet.add(iteratee.apply(value));
        }
        Iterator<T> iterator = (Iterator<T>) collection.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            Object transformedElement = iteratee.apply(element);
            if (transformedValuesSet.contains(transformedElement)) {
                iterator.remove();
            }
        }
        return collection;
    }

    /**
     * Removes elements from the collection that match any element in the values collection based on the provided comparator.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry"));
     *      Collection<String> result = CollectionUtils.pullAllWith(collection, List.of("banana", "cherry"), String::equalsIgnoreCase);
     *      // => ["apple"]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to modify.
     * @param values     The values to remove.
     * @param comparator The function to compare elements.
     * @return Returns collection.
     */
    public static <T> Collection<? extends T> pullAllWith(Collection<? extends T> collection, Collection<? extends T> values, BiPredicate<T, T> comparator) {
        Objects.requireNonNull(comparator);
        if (!isValidList(collection) || !isValidList(values)) {
            return collection;
        }
        Set<T> valuesSet = new HashSet<>(values);
        collection.removeIf(element -> valuesSet.stream().anyMatch(value -> comparator.test(element, value)));
        return collection;
    }

    /**
     * Removes and returns the elements from the collection at the specified indexes.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry", "date"));
     *      List<Integer> indexes = List.of(1, 3);
     *      List<String> result = CollectionUtils.pullAt(collection, indexes);
     *      // => ["banana", "date"]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to modify.
     * @param indexes    The indexes of the elements to remove.
     * @return Returns the new collection of removed elements.
     */
    public static <T> List<T> pullAt(Collection<? extends T> collection, List<Integer> indexes) {
        if (!isValidList(collection) || !isValidList(indexes)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        Set<Integer> indexSet = new HashSet<>(indexes);
        List<T> removedElements = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (indexSet.contains(i)) {
                removedElements.add(list.remove(i));
            }
        }
        Collections.reverse(removedElements);
        return removedElements;
    }

    /**
     * Removes and returns all elements from the collection that match the given predicate.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry", "date"));
     *      Predicate<String> predicate = s -> s.startsWith("b");
     *      List<String> result = CollectionUtils.remove(collection, predicate);
     *      // => ["banana"]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to modify.
     * @param predicate  The predicate to test each element.
     * @return Returns the new collection of removed elements.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> remove(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        Iterator<T> iterator = (Iterator<T>) collection.iterator();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (predicate.test(value)) {
                result.add(value);
                iterator.remove();
            }
        }
        return result;
    }

    /**
     * Reverses collection so that the first element becomes the last, the second element becomes the second to last, and so on.
     *
     * <pre>{@code
     *      Collection<String> collection = new ArrayList<>(List.of("apple", "banana", "cherry"));
     *      List<String> result = CollectionUtils.reverse(collection);
     *      // => ["cherry", "banana", "apple"]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to modify.
     * @return Returns collection.
     */
    public static <T> List<T> reverse(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        Collections.reverse(list);
        return list;
    }

    /**
     * Creates a slice of the collection from the specified start index to the end index.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.slice(collection, 1, 4);
     *      // => [2, 3, 4]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to slice.
     * @param start      The start position.
     * @param end        The end position.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> slice(Collection<? extends T> collection, int start, int end) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        int size = collection.size();
        int startN = Math.max(start, 0);
        int endN = Math.min(end, size);
        List<T> result = new ArrayList<>();
        Iterator<? extends T> iterator = collection instanceof List ? ((List<? extends T>) collection).listIterator(startN) : collection.iterator();
        int index = 0;
        while (iterator.hasNext() && index < endN - startN) {
            result.add(iterator.next());
            index++;
        }
        return result;
    }

    /**
     * Gets all but the first element of collection.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.tail(collection);
     *      // => [2, 3, 4, 5]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to query.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> tail(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return baseSlice(collection, 1, collection.size());
    }

    /**
     * Creates a slice of collection with n elements taken from the beginning.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.take(collection, 3);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to query.
     * @param n          The number of elements to take.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> take(Collection<? extends T> collection, int n) {
        if (!isValidList(collection) || n < 0) {
            return Collections.emptyList();
        }
        return baseSlice(collection, 0, n);
    }

    /**
     * Creates a slice of collection with n elements taken from the end.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.takeRight(collection, 3);
     *      // => [3, 4, 5]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to query.
     * @param n          The number of elements to take.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> takeRight(Collection<? extends T> collection, int n) {
        if (!isValidList(collection) || n < 0) {
            return Collections.emptyList();
        }
        int start = Math.max(0, collection.size() - n);
        return baseSlice(collection, start, collection.size());
    }

    /**
     * Creates a slice of collection with elements taken from the end. Elements are taken until predicate returns falsey.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.takeRightWhile(collection, x -> x > 2);
     *      // => [3, 4, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to query.
     * @param predicate  The function invoked per iteration.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> takeRightWhile(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return baseWhile(collection, predicate, true);
    }

    /**
     * Creates a slice of collection with elements taken from the beginning. Elements are taken until predicate returns falsey.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.takeWhile(collection, x -> x < 4);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to query.
     * @param predicate  The condition to match.
     * @return Returns the slice of collection.
     */
    public static <T> List<T> takeWhile(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return baseWhile(collection, predicate, false);
    }

    /**
     * Creates an collection of unique values
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(3, 4, 5);
     *      List<Integer> result = CollectionUtils.union(collection1, collection2);
     *      // => [1, 2, 3, 4, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param collections The collections to combine.
     * @return An Optional containing the combined list, or an empty Optional if the collections are invalid.
     */
    @SafeVarargs
    public static <T> List<T> union(Collection<? extends T>... collections) {
        if (!isValidRestList(collections)) {
            return Collections.emptyList();
        }
        Set<T> resultSet = new LinkedHashSet<>();
        for (Collection<? extends T> collection : collections) {
            if (collection != null) {
                resultSet.addAll(collection);
            }
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Combines multiple collections into a single collection, applying a function to each element
     * to determine its uniqueness and removing duplicates.
     *
     * <pre>{@code
     *      List<Integer> collection1 = List.of(1, 2, 3);
     *      List<Integer> collection2 = List.of(3, 4, 5);
     *      Function<Integer, Integer> iteratee = Function.identity();
     *      List<Integer> result = CollectionUtils.unionBy(iteratee, collection1, collection2);
     *      // => [1, 2, 3, 4, 5]
     * }</pre>
     *
     * @param <T>        The type of elements in the collections.
     * @param iteratee   The iteratee invoked per element.
     * @param collections     The collections to inspect.
     * @return Returns the new collection of combined values.
     */
    @SafeVarargs
    public static <T> List<T> unionBy(Function<T, ?> iteratee, Collection<? extends T>... collections) {
        Objects.requireNonNull(iteratee);
        if (!isValidRestList(collections)) {
            return Collections.emptyList();
        }
        List<T> flattenedList = baseFlatten(collections);
        return baseUniq(flattenedList, iteratee);
    }

    /**
     * Combines multiple collections into a single collection, using a comparator to determine
     * the uniqueness of elements, removing duplicates based on the comparison.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(3, 4, 5);
     *      BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);
     *      List<Integer> result = CollectionUtils.unionWith(comparator, collection1, collection2);
     *      // => [1, 2, 3, 4, 5]
     * }</pre>
     *
     * @param <T>        The type of elements in the collections.
     * @param comparator A comparator function to apply for determining uniqueness of elements.
     * @param collections     The collections to inspect.
     * @return Returns the new collection of combined values.
     */
    @SafeVarargs
    public static <T> List<T> unionWith(BiPredicate<T, T> comparator, Collection<? extends T>... collections) {
        Objects.requireNonNull(comparator);
        if (!isValidRestList(collections)) {
            return Collections.emptyList();
        }
        List<T> flattenedList = baseFlatten(collections);
        return baseUniq(flattenedList, comparator);
    }

    /**
     * Returns a collection with unique elements, removing duplicates.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 2, 3, 3, 3);
     *      List<Integer> result = CollectionUtils.uniq(collection);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param <T>   The type of elements in the collection.
     * @param collection  The collection to inspect.
     * @return Returns the new duplicate free collection.
     */
    public static <T> List<T> uniq(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        Set<T> seen = new HashSet<>();
        List<T> result = new ArrayList<>();
        for (T value : collection) {
            if (seen.add(value)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Returns a collection with unique elements based on a specific criterion, removing duplicates.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 2, 3, 3, 3);
     *      List<Integer> result = CollectionUtils.uniqBy(collection, value -> value);
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to inspect.
     * @param iteratee  The iteratee invoked per element.
     * @return Returns the new duplicate free collection.
     */
    public static <T> List<T> uniqBy(Collection<? extends T> collection, Function<T, ?> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return baseUniq(collection, iteratee);
    }

    /**
     * Returns a collection with unique elements based on a comparator function, removing duplicates.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 2, 3, 3, 3);
     *      List<Integer> result = CollectionUtils.uniqWith(collection, (a, b) -> a.equals(b));
     *      // => [1, 2, 3]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to inspect.
     * @param comparator  The comparator invoked per element.
     * @return Returns the new duplicate free collection.
     */
    public static <T> List<T> uniqWith(Collection<? extends T> collection, BiPredicate<T, T> comparator) {
        Objects.requireNonNull(comparator);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return baseUniq(collection, comparator);
    }

    /**
     * Unzips a collection of collections into a list of lists by grouping elements at the same index.
     *
     * <pre>{@code
     *      Collection<List<Integer>> grouped = List.of(
     *          List.of(1, 2, 3),
     *          List.of(4, 5, 6),
     *          List.of(7, 8, 9)
     *      );
     *      List<List<Integer>> result = CollectionUtils.unzip(grouped);
     *      // => [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param grouped    The collection of grouped elements to process.
     * @return Returns the new array of regrouped elements.
     */
    public static <T> List<List<T>> unzip(Collection<? extends Collection<? extends T>> grouped) {
        if (!isValidList(grouped)) {
            return Collections.emptyList();
        }
        int maxLength = 0;
        for (Collection<? extends T> group : grouped) {
            if (group != null) {
                maxLength = Math.max(group.size(), maxLength);
            }
        }
        List<List<T>> result = new ArrayList<>(maxLength);
        for (int i = 0; i < maxLength; i++) {
            List<T> regrouped = new ArrayList<>(grouped.size());
            for (Collection<? extends T> group : grouped) {
                regrouped.add(group != null && i < group.size() ? (T) group.toArray()[i] : null);
            }
            result.add(regrouped);
        }
        return result;
    }

    /**
     * Unzips a collection of collections into a list of lists, and applies a combine function to each list of unzipped elements.
     *
     * <pre>{@code
     *      Collection<List<Integer>> grouped = List.of(
     *          List.of(1, 2, 3),
     *          List.of(4, 5, 6),
     *          List.of(7, 8, 9)
     *      );
     *      List<Integer> result = CollectionUtils.unzipWith(grouped, group -> group.stream().mapToInt(Integer::intValue).sum());
     *      // => [12, 15, 18]
     * }</pre>
     *
     * @param <T>            The type of elements in the collection.
     * @param grouped        The collection of collections to unzip.
     * @param combineFunction The function to combine regrouped values.
     * @return Returns the new collection of regrouped elements.
     */
    public static <T> List<T> unzipWith(Collection<? extends Collection<? extends T>> grouped, Function<List<T>, T> combineFunction) {
        Objects.requireNonNull(combineFunction);
        if (!isValidList(grouped)) {
            return Collections.emptyList();
        }
        List<List<T>> unzipped = unzip(grouped);
        List<T> result = new ArrayList<>();
        for (List<T> group : unzipped) {
            result.add(combineFunction.apply(group.stream().filter(Objects::nonNull).toList()));
        }
        return result;
    }

    /**
     * Creates an collection excluding all given values
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.without(collection, 2, 4);
     *      // => [1, 3, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection     The collection to inspect.
     * @param values    The values to exclude.
     * @return Returns the new collection of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> without(Collection<? extends T> collection, T... values) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        if (!isValidRestValues(values)) {
            return new ArrayList<>(collection);
        }
        Set<T> valuesSet = new HashSet<>(Arrays.asList(values));
        List<T> result = new ArrayList<>();
        for (T element : collection) {
            if (!valuesSet.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Creates an collection of unique values that is the symmetric difference of the given collections.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(3, 4, 5);
     *      List<Integer> result = CollectionUtils.xor(collection1, collection2);
     *      // => [1, 2, 4, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param collections The collections to inspect.
     * @return Returns the new collection of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> xor(Collection<? extends T>... collections) {
        if (!isValidRestValues(collections)) {
            return Collections.emptyList();
        }
        List<Collection<? extends T>> collectionList = new ArrayList<>();
        for (Collection<? extends T> array : collections) {
            if (array != null) {
                collectionList.add(array);
            }
        }
        return baseXor(collectionList);
    }

    /**
     * This method is like xor except that it accepts iteratee which is invoked for each element of each collections to generate the criterion by which by which they're compared.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(3, 4, 5);
     *      List<Integer> result = CollectionUtils.xorBy(Integer::doubleValue, collection1, collection2);
     *      // => [1, 2, 4, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param collections    The collections to inspect.
     * @param iteratee  The iteratee invoked per element.
     * @return Returns the new collection of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> xorBy(Function<T, ?> iteratee, Collection<? extends T>... collections) {
        Objects.requireNonNull(iteratee);
        if (!isValidRestValues(collections)) {
            return Collections.emptyList();
        }
        List<Collection<? extends T>> collectionList = new ArrayList<>();
        for (Collection<? extends T> collection : collections) {
            if (collection != null) {
                collectionList.add(collection);
            }
        }
        return baseXorBy(collectionList, iteratee);
    }

    /**
     * This method is like xor except that it accepts comparator which is invoked to compare elements of collections.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(3, 4, 5);
     *      List<Integer> result = CollectionUtils.xorWith((a, b) -> a.equals(b), collection1, collection2);
     *      // => [1, 2, 4, 5]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param collections    The collections to inspect.
     * @param comparator The comparator invoked per element.
     * @return Returns the new collection of filtered values.
     */
    @SafeVarargs
    public static <T> List<T> xorWith(BiPredicate<T, T> comparator, Collection<? extends T>... collections) {
        Objects.requireNonNull(comparator);
        if (!isValidRestList(collections)) {
            return Collections.emptyList();
        }
        List<Collection<? extends T>> collectionList = new ArrayList<>();
        for (Collection<? extends T> collection : collections) {
            if (collection != null) {
                collectionList.add(collection);
            }
        }
        return baseXorWith(collectionList, comparator);
    }

    /**
     * Creates an collection of grouped elements, the first of which contains the first elements of the given collections, the second of which contains the second elements of the given collections, and so on.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(4, 5, 6, 7);
     *      List<List<Integer>> result = CollectionUtils.zip(collection1, collection2);
     *      // => [[1, 4], [2, 5], [3, 6], [null, 7]]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param collections     The collections to process.
     * @return Returns the new collection of grouped elements.
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T> List<List<T>> zip(Collection<? extends T>... collections) {
        if (!isValidRestZip(collections)) {
            return Collections.emptyList();
        }
        int maxSize = Arrays.stream(collections)
            .filter(Objects::nonNull)
            .mapToInt(Collection::size)
            .max()
            .orElse(0);
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            List<T> group = new ArrayList<>();
            for (Collection<? extends T> collection : collections) {
                if (collection != null && i < collection.size()) {
                    group.add(((List<T>) collection).get(i));
                } else {
                    group.add(null);
                }
            }
            result.add(group);
        }
        return result;
    }

    /**
     * This method is like zip except that it accepts iteratee to specify how grouped values should be combined.
     *
     * <pre>{@code
     *      Collection<Integer> collection1 = List.of(1, 2, 3);
     *      Collection<Integer> collection2 = List.of(4, 5, 6);
     *      Function<List<Integer>, String> combiner = group -> group.stream().map(String::valueOf).collect(Collectors.joining("-"));
     *      List<String> result = CollectionUtils.zipWith(combiner, collection1, collection2);
     *      // => ["1-4", "2-5", "3-6"]
     * }</pre>
     *
     * @param <T>       The type of elements in the collections.
     * @param <R>       The result type after applying the combiner function.
     * @param combiner  The function to combine grouped values.
     * @param collections     The collections to process.
     * @return Returns the new collection of grouped elements.
     */
    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T, R> List<R> zipWith(Function<List<T>, R> combiner, Collection<? extends T>... collections) {
        Objects.requireNonNull(combiner);
        if (!isValidRestZip(collections)) {
            return Collections.emptyList();
        }
        int minSize = Arrays.stream(collections)
            .filter(Objects::nonNull)
            .mapToInt(Collection::size)
            .min()
            .orElse(0);

        List<R> result = new ArrayList<>();
        for (int i = 0; i < minSize; i++) {
            List<T> group = new ArrayList<>();
            for (Collection<? extends T> collection : collections) {
                group.add(((List<T>) collection).get(i));
            }
            result.add(combiner.apply(group));
        }
        return result;
    }

    /**
     * Combines two collections into a map where the first collection provides the keys and the second provides the values.
     * If the second collection has fewer elements than the first, `null` is used for missing values.
     *
     * <pre>{@code
     *      Collection<String> keys = List.of("a", "b", "c");
     *      Collection<Integer> values = List.of(1, 2);
     *      Map<String, Integer> result = CollectionUtils.zipObject(keys, values);
     *      // => {"a": 1, "b": 2, "c": null}
     * }</pre>
     *
     * @param <K>     The type of keys in the map.
     * @param <V>     The type of values in the map.
     * @param props   The collection of keys.
     * @param values  The collection of values.
     * @return Returns the new map.
     */
    public static <K, V> Map<K, V> zipObject(Collection<? extends K> props, Collection<? extends V> values) {
        if (!isValidList(props) || values == null) {
            return Collections.emptyMap();
        }
        Map<K, V> result = new HashMap<>();
        int length = props.size();
        int valsLength = values.size();
        Iterator<? extends K> propIterator = props.iterator();
        Iterator<? extends V> valueIterator = values.iterator();
        for (int i = 0; i < length; i++) {
            K key = propIterator.next();
            V value = i < valsLength ? valueIterator.next() : null;
            result.put(key, value);
        }
        return result;
    }

    /**
     * Counts the occurrences of each unique key in a collection based on the provided key mapper.
     * Returns a map where the keys are the result of applying the keyMapper to each element of the collection,
     * and the values are the count of occurrences of each key.
     *
     * <pre>{@code
     *      Collection<String> collection = List.of("apple", "banana", "apple", "apple", "orange");
     *      Map<String, Long> result = CollectionUtils.countBy(collection, String::toUpperCase);
     *      // => {"APPLE": 3, "BANANA": 1, "ORANGE": 1}
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param <K>        The type of keys in the map.
     * @param collection The collection to count items from.
     * @param keyMapper  The function to map each element to a key.
     * @return Returns the composed aggregate object.
     */
    public static <T, K> Map<K, Long> countBy(Collection<? extends T> collection, Function<? super T, ? extends K> keyMapper) {
        Objects.requireNonNull(keyMapper);
        if (!isValidList(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream()
            .collect(Collectors.groupingBy(
                keyMapper, Collectors.counting()
            ));
    }

    /**
     * Checks if predicate returns truthy for all elements of collection
     *
     * <pre>{@code
     *      Collection<Integer> numbers = List.of(1, 2, 3, 4);
     *      boolean result = CollectionUtils.every(numbers, num -> num > 0);
     *      // => true
     * }</pre>
     *
     * @param <T>      The type of elements in the collection.
     * @param collection The collection to iterate over.
     * @param predicate The predicate to apply to each element.
     * @return Returns {@code true} if all elements pass the predicate check, else {@code false}.
     */
    public static <T> boolean every(Collection<? extends T> collection, Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return false;
        }
        for (T element : collection) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if every entry in the map satisfies the given biPredicate.
     *
     * <pre>{@code
     *      Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
     *      boolean result = CollectionUtils.every(map, (key, value) -> value > 0);
     *      // => true
     * }</pre>
     *
     * @param <K>          The type of keys in the map.
     * @param <V>          The type of values in the map.
     * @param map          The map to check.
     * @param biPredicate  The biPredicate to apply to each entry (key, value).
     * @return {@code true} if every entry in the map satisfies the biPredicate, {@code false} otherwise.
     */
    public static <K, V> boolean every(Map<K, V> map, BiPredicate<K, V> biPredicate) {
        Objects.requireNonNull(biPredicate);
        if (!isValidList(map)) {
            return false;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!biPredicate.test(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterates over elements of collection, returning an array of all elements predicate returns truthy for.
     *
     * <pre>{@code
     *      List<Integer> list = List.of(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.filter(list, x -> x % 2 == 0);
     *      // => [2, 4]
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param collection The collection to iterate over.
     * @param predicate  The predicate to apply to each element.
     * @return Returns the new filtered array.
     */
    public static <T> List<T> filter(Collection<? extends T> collection, Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .filter(predicate)
            .collect(Collectors.toList());
    }

    /**
     * Filters elements of a map based on a BiPredicate applied to entries (key-value pairs).
     *
     * <pre>{@code
     *      Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
     *      Map<String, Integer> result = CollectionUtils.filter(map, (key, value) -> value > 1);
     *      // => {b=2, c=3}
     * }</pre>
     *
     * @param <K>        The type of keys in the map.
     * @param <V>        The type of values in the map.
     * @param map        The map to filter.
     * @param biPredicate The BiPredicate to apply to each key-value entry.
     * @return A new map containing only the entries that satisfy the predicate.
     */
    public static <K, V> Map<K, V> filter(Map<K, V> map, BiPredicate<K, V> biPredicate) {
        Objects.requireNonNull(biPredicate);
        if (!isValidList(map)) {
            return Collections.emptyMap();
        }
        return map.entrySet().stream()
            .filter(entry -> entry.getKey() != null && entry.getValue() != null &&
                biPredicate.test(entry.getKey(), entry.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Finds the first element in a collection that satisfies the predicate.
     *
     * <pre>{@code
     *      Collection<String> collection = List.of("apple", "banana", "cherry");
     *      Optional<String> result = CollectionUtils.find(collection, s -> s.startsWith("b"));
     *      // => Optional[banana]
     * }</pre>
     *
     * @param <T>         The type of elements in the collection.
     * @param collection  The collection to search through.
     * @param predicate   The predicate to test each element against.
     * @return An Optional containing the first element that satisfies the predicate, or an empty Optional if none are found.
     */
    public static <T> Optional<T> find(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        for (T element : collection) {
            if (element != null && predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    /**
     * Finds the last element in a collection that satisfies the predicate,
     * iterating from right to left.
     *
     * <pre>{@code
     *      Collection<String> collection = List.of("apple", "banana", "cherry");
     *      Optional<String> result = CollectionUtils.findLast(collection, s -> s.startsWith("b"));
     *      // => Optional[banana]
     * }</pre>
     *
     * @param <T>         The type of elements in the collection.
     * @param collection  The collection to search through.
     * @param predicate   The predicate to test each element against.
     * @return An Optional containing the last element that satisfies the predicate, or an empty Optional if none are found.
     */
    public static <T> Optional<T> findLast(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        List<? extends T> list = new ArrayList<>(collection);
        for (int i = list.size() - 1; i >= 0; i--) {
            T element = list.get(i);
            if (element != null && predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    /**
     * Flattens a collection of collections by applying the given function to each element,
     * and collecting the results into a single list.
     * The function transforms each element into a collection, and the results of these collections
     * are then flattened into a single list.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      Function<Integer, Collection<String>> iteratee = num -> List.of("Item " + num);
     *      List<String> result = CollectionUtils.flatMap(collection, iteratee);
     *      // => ["Item 1", "Item 2", "Item 3"]
     * }</pre>
     *
     * @param <T>         The type of elements in the input collection.
     * @param <R>         The type of elements in the resulting collection after transformation.
     * @param collection  The collection to iterate over.
     * @param iteratee    The function to transform each element of the input collection into a collection of type R.
     * @return A list containing all the elements from the collections returned by the iteratee.
     */
    public static <T, R> List<R> flatMap(Collection<? extends T> collection,
                                         Function<? super T, ? extends Collection<? extends R>> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<R> result = new ArrayList<>();
        for (T element : collection) {
            Collection<? extends R> mapped = iteratee.apply(element);
            if (mapped != null) {
                result.addAll(mapped);
            }
        }
        return result;
    }

    /**
     * This method is like flatMap except that it recursively flattens the mapped results.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      Function<Integer, Collection<Object>> iteratee = num -> List.of(List.of("Item " + num));
     *      List<Object> result = CollectionUtils.flatMapDeep(collection, iteratee);
     *      // => ["Item 1", "Item 2", "Item 3"]
     * }</pre>
     *
     * @param <T>         The type of elements in the input collection.
     * @param <R>         The type of elements in the resulting collection after transformation.
     * @param collection  The collection to iterate over.
     * @param iteratee    The function to transform each element of the input collection into a collection of type R.
     * @return A list containing all the elements from the collections returned by the iteratee, deeply flattened.
     */
    public static <T, R> List<R> flatMapDeep(Collection<? extends T> collection,
                                             Function<? super T, ? extends Collection<? extends R>> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<R> result = new ArrayList<>();
        for (T element : collection) {
            Collection<? extends R> mapped = iteratee.apply(element);
            if (mapped != null) {
                result.addAll(flattenDeep(mapped));
            }
        }
        return result;
    }

    /**
     * This method is like flatMap except that it recursively flattens the mapped results
     * up to a specified depth.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      Function<Integer, Collection<Object>> iteratee = num -> List.of(List.of("Item " + num));
     *      List<Object> result = CollectionUtils.flatMapDepth(collection, iteratee, 2);
     *      // => ["Item 1", "Item 2", "Item 3"]
     * }</pre>
     *
     * @param <T>         The type of elements in the input collection.
     * @param <R>         The type of elements in the resulting collection after transformation.
     * @param collection  The collection to iterate over.
     * @param iteratee    The function to transform each element of the input collection into a collection of type R.
     * @param depth       The depth to which the resulting collections should be flattened.
     *                   A depth of 1 flattens one level, while greater values flatten deeper.
     * @return A list containing all the elements from the collections returned by the iteratee, flattened to the specified depth.
     */
    public static <T, R> List<R> flatMapDepth(Collection<? extends T> collection,
                                              Function<? super T, ? extends Collection<? extends R>> iteratee,
                                              int depth) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        int minDepth = Math.max(1, depth);
        List<R> result = new ArrayList<>();
        for (T element : collection) {
            Collection<? extends R> mapped = iteratee.apply(element);
            if (mapped != null) {
                result.addAll(flattenDepth(mapped, minDepth));
            }
        }
        return result;
    }

    /**
     * Iterates over elements of a collection and invokes the given iteratee for each element.
     *
     * <pre>{@code
     *      Collection<Integer> collection = List.of(1, 2, 3);
     *      CollectionUtils.forEach(collection, num -> System.out.println("Element: " + num));
     *      // Prints:
     *      // Element: 1
     *      // Element: 2
     *      // Element: 3
     * }</pre>
     *
     * @param <T>         The type of elements in the collection.
     * @param collection  The collection of elements to iterate over.
     * @param iteratee    The action to be performed on each element of the collection.
     */
    public static <T> void forEach(Collection<? extends T> collection, Consumer<? super T> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return;
        }
        for (T element : collection) {
            if (element != null) {
                iteratee.accept(element);
            }
        }
    }

    /**
     * Iterates over elements of a map (key-value pairs) and invokes the given iteratee for each entry.
     *
     * <pre>{@code
     *      Map<String, Integer> map = Map.of("a", 1, "b", 2);
     *      CollectionUtils.forEach(map, (key, value) -> System.out.println(key + " = " + value));
     *      // Prints:
     *      // a = 1
     *      // b = 2
     * }</pre>
     *
     * @param <K>         The type of keys in the map.
     * @param <V>         The type of values in the map.
     * @param map         The map of entries to iterate over.
     * @param iteratee    The action to be performed on each entry of the map.
     */
    public static <K, V> void forEach(Map<? extends K, ? extends V> map, BiConsumer<? super K, ? super V> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(map)) {
            return;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                iteratee.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Iterates over elements of a collection from right to left and invokes the given iteratee for each element.
     *
     * <pre>{@code
     *      List<String> list = List.of("a", "b", "c");
     *      CollectionUtils.forEachRight(list, System.out::println);
     *      // Prints:
     *      // c
     *      // b
     *      // a
     * }</pre>
     *
     * @param <T>         The type of elements in the collection.
     * @param collection  The collection to iterate over.
     * @param iteratee    The action to be performed on each element of the collection.
     */
    public static <T> void forEachRight(Collection<? extends T> collection, Consumer<? super T> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) collection;
        ListIterator<T> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            T element = iterator.previous();
            if (element != null) {
                iteratee.accept(element);
            }
        }
    }

    /**
     * Iterates over elements of a map (key-value pairs) from right to left and invokes the given iteratee for each entry.
     *
     * <pre>{@code
     *      Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
     *      CollectionUtils.forEachRight(map, (key, value) -> System.out.println(key + ": " + value));
     *      // Prints:
     *      // c: 3
     *      // b: 2
     *      // a: 1
     * }</pre>
     *
     * @param <K>        The type of keys in the map.
     * @param <V>        The type of values in the map.
     * @param map        The map to iterate over.
     * @param iteratee   The action to be performed on each entry of the map.
     */
    public static <K, V> void forEachRight(Map<? extends K, ? extends V> map,
                                           BiConsumer<? super K, ? super V> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(map)) {
            return;
        }
        List<Map.Entry<? extends K, ? extends V>> entries = new ArrayList<>(map.entrySet());
        Collections.reverse(entries);
        for (Map.Entry<? extends K, ? extends V> entry : entries) {
            if (entry.getKey() != null && entry.getValue() != null) {
                iteratee.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Groups elements of the given collection by the result of applying the given
     * function (iteratee) to each element.
     *
     * <pre>{@code
     *      List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry");
     *      Map<Character, List<String>> grouped = CollectionUtils.groupBy(words, word -> word.charAt(0));
     *      // Returns: {a=[apple, apricot], b=[banana, blueberry]}
     * }</pre>
     *
     * @param <T>        The type of elements in the collection.
     * @param <K>        The type of the key generated by the iteratee function.
     * @param collection The collection of elements to group.
     * @param iteratee   The function used to extract the key for each element.
     * @return A map where keys are generated by the iteratee and values are lists of elements corresponding to each key.
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<? extends T> collection,
                                                 Function<? super T, ? extends K> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(iteratee));
    }

    /**
     * Checks if the given value is in the collection. If the collection is a string, it checks for a substring of the value,
     * otherwise, it checks for equality using the `equals` method.
     *
     * <pre>{@code
     *      List<String> words = Arrays.asList("apple", "banana", "apricot");
     *      boolean result = CollectionUtils.includes(words, "banana");
     *      // Returns: true
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection to search through.
     * @param value      The value to check for in the collection.
     * @return `true` if the collection contains the specified value, otherwise `false`.
     */
    public static <T> boolean includes(Collection<? extends T> collection, T value) {
        if (!isValidList(collection)) {
            return false;
        }
        for (T element : collection) {
            if (Objects.equals(element, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a Map composed of keys generated from the results of running each element
     * of the collection through the given iteratee function. The corresponding value of each key
     * is the last element responsible for generating that key.
     *
     * <pre>{@code
     *      List<String> words = Arrays.asList("apple", "banana", "apricot");
     *      Map<Integer, String> result = CollectionUtils.keyBy(words, String::length);
     *      // Returns: {5=apple, 6=banana, 7=apricot}
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param <R>       The type of the generated key.
     * @param collection The collection of elements to be processed.
     * @param iteratee   A function that generates the key from each element in the collection.
     * @return A map where each key is generated from the iteratee and the value is the original element.
     */
    public static <T, R> Map<R, T> keyBy(Collection<? extends T> collection, Function<T, R> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyMap();
        }
        Map<R, T> result = new LinkedHashMap<>();
        for (T value : collection) {
            if (value != null) {
                R key = iteratee.apply(value);
                result.put(key, value);
            }
        }
        return result;
    }

    /**
     * Creates a list of values by running each element in the collection through the given iteratee.
     *
     * <pre>{@code
     *      List<String> words = Arrays.asList("apple", "banana", "apricot");
     *      List<Integer> lengths = CollectionUtils.map(words, String::length);
     *      // Returns: [5, 6, 7]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param <R>       The type of elements in the returned list.
     * @param collection The collection of elements to be processed.
     * @param iteratee   A function that processes each element and produces a new result.
     * @return A new list containing the results of applying the iteratee function to each element.
     */
    public static <T, R> List<R> map(Collection<? extends T> collection, Function<T, R> iteratee) {
        Objects.requireNonNull(iteratee);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<R> result = new ArrayList<>();
        for (T value : collection) {
            if (value != null) {
                result.add(iteratee.apply(value));
            }
        }
        return result;
    }

    /**
     * Sorts the collection based on a given predicate that determines the sorting order.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(5, 2, 8, 3);
     *      List<Integer> sortedNumbers = CollectionUtils.orderBy(numbers, Integer::compareTo);
     *      // Returns: [2, 3, 5, 8]
     * }</pre>
     *
     * @param <T>       The type of elements in the collection.
     * @param collection The collection of elements to be sorted.
     * @param comparator A comparator used to compare the elements of the collection.
     * @return A new list containing the elements of the collection sorted according to the comparator.
     */
    public static <T> List<T> orderBy(Collection<? extends T> collection, Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        return list.stream().sorted(comparator).toList();
    }

    /**
     * Partitions the given collection into two groups based on the predicate:
     * one group for elements where the predicate returns true, and the other for false.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      List<List<Integer>> partitioned = CollectionUtils.partition(numbers, n -> n % 2 == 0);
     *      // Returns: [[2, 4], [1, 3, 5]]
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to partition.
     * @param predicate The condition used to partition the elements.
     * @return A list containing two sublists: the first with elements that match the predicate, and the second with those that do not.
     */
    public static <T> List<List<T>> partition(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> trueGroup = new ArrayList<>();
        List<T> falseGroup = new ArrayList<>();
        for (T item : collection) {
            if (item == null) {
                continue;
            }
            if (predicate.test(item)) {
                trueGroup.add(item);
            } else {
                falseGroup.add(item);
            }
        }
        List<List<T>> result = new ArrayList<>();
        result.add(trueGroup);
        result.add(falseGroup);
        return result;
    }

    /**
     * Reduces the collection to a single value by applying the provided accumulator function
     * to each element, where each successive invocation is supplied the return value of the previous.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      int sum = CollectionUtils.reduce(numbers, 0, Integer::sum);
     *      // Returns: 15 (sum of numbers)
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to reduce.
     * @param identity The initial value to start the reduction.
     * @param accumulator A function that combines two elements into one result.
     * @return The result of applying the accumulator to all elements in the collection.
     */
    public static <T> T reduce(Collection<? extends T> collection, T identity, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(accumulator);
        if (!isValidList(collection)) {
            return identity;
        }
        T result = identity;
        for (T item : collection) {
            if (item != null) {
                result = accumulator.apply(result, item);
            }
        }
        return result;
    }

    /**
     * Reduces a collection to a single value without an identity.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      Optional<Integer> sum = CollectionUtils.reduce(numbers, Integer::sum);
     *      sum.ifPresent(s -> System.out.println("Sum: " + s));  // Output: Sum: 15
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to reduce.
     * @param accumulator A function that combines two elements into one result.
     * @return An Optional containing the result of applying the accumulator to all elements in the collection,
     *         or an empty Optional if the collection is empty.
     */
    public static <T> Optional<T> reduce(Collection<? extends T> collection, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(accumulator);
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        Iterator<? extends T> iterator = collection.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next != null) {
                result = accumulator.apply(result, next);
            }
        }
        return Optional.ofNullable(result);
    }

    /**
     * Reduces the collection from right to left to a single value by applying the provided accumulator function
     * to each element, where each successive invocation is supplied the return value of the previous.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      Integer sumRight = CollectionUtils.reduceRight(numbers, 0, Integer::sum);
     *      System.out.println("Sum from right: " + sumRight);  // Output: Sum from right: 15
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to reduce.
     * @param identity The initial value for the reduction.
     * @param accumulator A function that combines two elements into one result.
     * @return The result of applying the accumulator to all elements in the collection,
     *         or the identity if the collection is empty.
     */
    public static <T> T reduceRight(Collection<? extends T> collection, T identity, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(accumulator);
        if (!isValidList(collection)) {
            return identity;
        }
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) collection;
        ListIterator<T> iterator = list.listIterator(list.size());
        T result = identity;
        while (iterator.hasPrevious()) {
            T element = iterator.previous();
            if (element != null) {
                result = accumulator.apply(result, element);
            }
        }
        return result;
    }

    /**
     * Performs a reduction on the elements of a collection in reverse order,
     * using an accumulator function. The reduction starts with the last element
     * of the collection and combines it with preceding elements using the provided accumulator.
     * The result is returned as an Optional to handle the case when the collection is empty.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      Optional<Integer> sumRight = CollectionUtils.reduceRight(numbers, Integer::sum);
     *      sumRight.ifPresent(sum -> System.out.println("Sum from right: " + sum));  // Output: Sum from right: 15
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to reduce.
     * @param accumulator A function that combines two elements into one result.
     * @return An Optional containing the result of applying the accumulator to all elements
     *         in the collection, or Optional.empty() if the collection is empty.
     */
    public static <T> Optional<T> reduceRight(Collection<? extends T> collection, BinaryOperator<T> accumulator) {
        Objects.requireNonNull(accumulator);
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) collection;
        ListIterator<T> iterator = list.listIterator(list.size());
        T result = iterator.previous();
        while (iterator.hasPrevious()) {
            T element = iterator.previous();
            if (element != null) {
                result = accumulator.apply(result, element);
            }
        }
        return Optional.ofNullable(result);
    }

    /**
     * Filters out elements from the collection that do **not** satisfy the given predicate.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      List<Integer> result = CollectionUtils.reject(numbers, n -> n % 2 == 0);
     *      result.forEach(System.out::println);  // Output: 1 3 5
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to filter.
     * @param predicate A function that tests each element to determine whether it should be excluded.
     * @return A list containing the elements of the collection that do not match the predicate.
     */
    public static <T> List<T> reject(Collection<? extends T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .filter(predicate.negate())
            .collect(Collectors.toList());
    }

    /**
     * Gets a random element from the collection.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      Optional<Integer> randomElement = CollectionUtils.sample(numbers);
     *      randomElement.ifPresent(System.out::println);  // Output: A random element from the list (e.g., 3)
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection from which to sample an element.
     * @return An Optional containing the randomly selected element, or Optional.empty() if the collection is empty.
     */
    public static <T> Optional<T> sample(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Optional.empty();
        }
        List<? extends T> list = new ArrayList<>(collection);
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return Optional.ofNullable(list.get(randomIndex));
    }

    /**
     * Randomly selects a specified number of elements from the given collection.
     * If the requested number exceeds the size of the collection, it returns as many elements as possible.
     * If the collection is empty or null, an empty list is returned.
     * The selection is done randomly using shuffle, and the result contains exactly 'n' elements.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      List<Integer> randomElements = CollectionUtils.sampleSize(numbers, 3);
     *      System.out.println(randomElements);  // Output: A random list of 3 elements, e.g., [2, 5, 1]
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection from which to sample elements.
     * @param n The number of elements to select.
     * @return A list containing 'n' randomly selected elements from the collection, or an empty list if the collection is empty.
     */

    public static <T> List<T> sampleSize(Collection<? extends T> collection, int n) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        int size = collection.size();
        n = Math.min(Math.max(1, n), size);
        List<T> list = new ArrayList<>(collection);
        Collections.shuffle(list, new Random());
        return list.subList(0, n);
    }

    /**
     * Creates a shuffled version of the given collection using the Fisher-Yates shuffle algorithm.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
     *      List<Integer> shuffled = CollectionUtils.shuffle(numbers);
     *      System.out.println(shuffled);  // Output: A randomly shuffled list, e.g., [3, 1, 4, 2, 5]
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection to shuffle.
     * @return A list containing the elements of the collection in random order, or an empty list if the collection is empty.
     */
    public static <T> List<T> shuffle(Collection<? extends T> collection) {
        if (!isValidList(collection)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(collection);
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
        return list;
    }

    /**
     * Gets the size of the given collection. For arrays and strings, it returns the length.
     * For objects, it returns the number of own properties. For Maps and Sets, it returns their size.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
     *      int size = CollectionUtils.size(numbers);  // Output: 4
     *      String str = "Hello";
     *      int strSize = CollectionUtils.size(str);  // Output: 5
     * }</pre>
     *
     * @param collection The object whose size is to be determined. Can be a collection, map, or string.
     * @return The size of the collection, map, or string, or 0 if the object is null or of an unsupported type.
     */
    public static int size(Object collection) {
        if (collection == null) {
            return 0;
        }
        if (collection instanceof Collection) {
            return ((Collection<?>) collection).size();
        }
        if (collection instanceof Map) {
            return ((Map<?, ?>) collection).size();
        }
        if (collection instanceof String) {
            return ((String) collection).length();
        }
        return 0;
    }

    /**
     * Checks if the predicate returns true for **any** element of the collection.
     * Iteration is stopped once the predicate returns true.
     *
     * <pre>{@code
     *      List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
     *      boolean hasEven = CollectionUtils.some(numbers, num -> num % 2 == 0);  // Output: true
     * }</pre>
     *
     * @param <T> The type of elements in the collection.
     * @param collection The collection of elements to test.
     * @param predicate A predicate that tests each element.
     * @return true if any element in the collection satisfies the predicate, otherwise false.
     */
    public static <T> boolean some(Collection<T> collection, Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isValidList(collection)) {
            return false;
        }
        for (T element : collection) {
            if (element != null && predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

}
