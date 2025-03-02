package io.javadash;

import static io.javadash.core.BaseLibrary.createMathOperation;
import static io.javadash.core.Validate.isValidArray;
import static io.javadash.core.Validate.isValidNumber;

import io.javadash.core.Validate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class NumberUtils {
    private static final Random RANDOM = new Random();

    /**
     * Tests if a Number is empty or null.
     *
     * <pre>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty(1234)             = false
     * </pre>
     *
     * @param number the {@link Number} to test, may be {@code null}
     * @return {@code true} if the number has a supported type and is empty or null,
     * {@code false} otherwise
     */
    public static boolean isEmpty(final Object number) {
        return Validate.isEmpty(number);
    }

    /**
     * Adds two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.add(5, 4);
     *      System.out.println(result1);
     *      // Output: 9
     *
     *      int result2 = NumberUtils.add(5, null);
     *      System.out.println(result2);
     *      // Output: 0
     *
     * }</pre>
     *
     * @param a The first number in the addition.
     * @param b The second number in the addition.
     * @return The result of the addition.
     */
    public static int add(Integer a, Integer b) {
        return createMathOperation(Integer::sum, 0).apply(a, b);
    }

    /**
     * Adds two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.add(5F, 4F);
     *      System.out.println(result1);
     *      // Output: 9F
     *
     *      float result2 = NumberUtils.add(5F, null);
     *      System.out.println(result2);
     *      // Output: 0F
     *
     * }</pre>
     *
     * @param a The first number in the addition.
     * @param b The second number in the addition.
     * @return The result of the addition.
     */
    public static float add(Float a, Float b) {
        return createMathOperation(Float::sum, 0F).apply(a, b);
    }

    /**
     * Adds two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.add(5.0, 4.0);
     *      System.out.println(result1);
     *      // Output: 9.0
     *
     *      double result2 = NumberUtils.add(5.0, null);
     *      System.out.println(result2);
     *      // Output: 0.0
     *
     * }</pre>
     *
     * @param a The first number in the addition.
     * @param b The second number in the addition.
     * @return The result of the addition.
     */
    public static double add(Double a, Double b) {
        return createMathOperation(Double::sum, 0.0).apply(a, b);
    }

    /**
     * Adds two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.add(5L, 4L);
     *      System.out.println(result1);
     *      // Output: 9L
     *
     *      long result2 = NumberUtils.add(5L, null);
     *      System.out.println(result2);
     *      // Output: 0L
     *
     * }</pre>
     *
     * @param a The first number in the addition.
     * @param b The second number in the addition.
     * @return The result of the addition.
     */
    public static long add(Long a, Long b) {
        return createMathOperation(Long::sum, 0L).apply(a, b);
    }

    /**
     * Divides two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     * If the divisor is zero, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.divide(10, 2);
     *      System.out.println(result1);
     *      // Output: 5
     *
     *      int result2 = NumberUtils.divide(10, null);
     *      System.out.println(result2);
     *      // Output: 0
     *
     *      int result3 = NumberUtils.divide(10, 0);
     *      System.out.println(result3);
     *      // Output: 0
     *
     * }</pre>
     *
     * @param dividend The first number in the division.
     * @param divisor  The second number in the division.
     * @return The result of the division.
     */
    public static int divide(Integer dividend, Integer divisor) {
        return createMathOperation((a, b) -> b == 0 ? 0 : a / b, 0).apply(dividend, divisor);
    }

    /**
     * Divides two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     * If the divisor is zero, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.divide(10F, 2F);
     *      System.out.println(result1);
     *      // Output: 5F
     *
     *      float result2 = NumberUtils.divide(10F, null);
     *      System.out.println(result2);
     *      // Output: 0F
     *
     *      float result3 = NumberUtils.divide(10F, 0F);
     *      System.out.println(result3);
     *      // Output: 0F
     *
     * }</pre>
     *
     * @param dividend The first number in the division.
     * @param divisor  The second number in the division.
     * @return The result of the division.
     */
    public static float divide(Float dividend, Float divisor) {
        return createMathOperation((a, b) -> b == 0 ? 0F : a / b, 0F).apply(dividend, divisor);
    }

    /**
     * Divides two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     * If the divisor is zero, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.divide(10.0, 2.0);
     *      System.out.println(result1);
     *      // Output: 5.0
     *
     *      double result2 = NumberUtils.divide(10.0, null);
     *      System.out.println(result2);
     *      // Output: 0.0
     *
     *      double result3 = NumberUtils.divide(10.0, 0.0);
     *      System.out.println(result3);
     *      // Output: 0.0
     *
     * }</pre>
     *
     * @param dividend The first number in the division.
     * @param divisor  The second number in the division.
     * @return The result of the division.
     */
    public static double divide(Double dividend, Double divisor) {
        return createMathOperation((a, b) -> b == 0 ? 0.0 : a / b, 0.0).apply(dividend, divisor);
    }

    /**
     * Divides two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     * If the divisor is zero, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.divide(10L, 2L);
     *      System.out.println(result1);
     *      // Output: 5L
     *
     *      long result2 = NumberUtils.divide(10L, null);
     *      System.out.println(result2);
     *      // Output: 0L
     *
     *      long result3 = NumberUtils.divide(10L, 0L);
     *      System.out.println(result3);
     *      // Output: 0L
     *
     * }</pre>
     *
     * @param dividend The first number in the division.
     * @param divisor  The second number in the division.
     * @return The result of the division.
     */
    public static long divide(Long dividend, Long divisor) {
        return createMathOperation((a, b) -> b == 0 ? 0L : a / b, 0L).apply(dividend, divisor);
    }

    /**
     * Finds the maximum value in an array of numbers.
     *
     * <pre>{@code
     *
     *      Optional<Double> result1 = MaxUtils.max(null);
     *      System.out.println(result1);
     *      // Output: Optional.empty
     *
     *      Optional<Integer> result2 = MaxUtils.max(1, 5, null, 3, 7);
     *      System.out.println(result2);
     *      // Output: Optional[7]
     *
     *
     * }</pre>
     *
     * @param values The values to iterate over.
     * @return An Optional containing the maximum value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T extends Comparable<? super T>> Optional<T> max(T... values) {
        if (!isValidArray(values)) {
            return Optional.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).max(Comparator.naturalOrder());
    }

    /**
     * Finds the maximum value in an array using a custom comparator.
     *
     * <pre>{@code
     *
     *      Optional<String> result = MaxUtils.maxBy(String::length, "apple", "banana", "cherry");
     *      System.out.println(result.orElse(null));
     *      // Output: "banana"
     *
     *      Optional<Integer> result2 = MaxUtils.maxBy(Integer::intValue, 1, 5, 3, 7);
     *      System.out.println(result2.orElse(null));
     *      // Output: 7
     *
     * }</pre>
     *
     * @param iteratee The iteratee invoked per element.
     * @param values   The array of values.
     * @return An Optional containing the maximum value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T, U extends Comparable<? super U>> Optional<T> maxBy(final Function<T, U> iteratee,
                                                                         final T... values) {
        Objects.requireNonNull(iteratee);
        if (!isValidArray(values)) {
            return Optional.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).max(Comparator.comparing(iteratee));
    }

    /**
     * Finds the minimum value in an array of numbers.
     *
     * <pre>{@code
     *
     *      // Example: Find the minimum value in an array of integers
     *      Integer[] values = {1, 5, 3, 7};
     *      Optional<Integer> result = MinUtils.min(values);
     *      System.out.println(result.orElse(null));
     *      // Output: 1
     *
     *      // Example: Find the minimum value in an array of doubles
     *      Double[] doubleValues = {1.5, 5.5, 3.5, 7.5};
     *      Optional<Double> result2 = MinUtils.min(doubleValues);
     *      System.out.println(result2.orElse(null));
     *      // Output: 1.5
     *
     * }</pre>
     *
     * @param values The array of numbers.
     * @return An Optional containing the minimum value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T extends Comparable<T>> Optional<T> min(T... values) {
        if (!isValidArray(values)) {
            return Optional.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).min(Comparator.naturalOrder());
    }

    /**
     * Finds the minimum value in an array using a custom iteratee function.
     *
     * <pre>{@code
     *
     *      // Example: Find the minimum string by length
     *      Function<String, Integer> lengthIteratee = String::length;
     *      Optional<String> result = MinUtils.minBy(lengthIteratee, "apple", "banana", "cherry");
     *      System.out.println(result.orElse(null));
     *      // Output: "apple"
     *
     *      // Example: Find the minimum integer using natural order
     *      Optional<Integer> result2 = MinUtils.minBy(Function.identity(), 1, 5, 3, 7);
     *      System.out.println(result2.orElse(null));
     *      // Output: 1
     *
     * }</pre>
     *
     * @param iteratee The iteratee function to extract the comparison key from each element.
     * @param values   The array of values.
     * @return An Optional containing the minimum value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T, U extends Comparable<? super U>> Optional<T> minBy(final Function<T, U> iteratee,
                                                                         final T... values) {
        Objects.requireNonNull(iteratee);
        if (!isValidArray(values)) {
            return Optional.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).min(Comparator.comparing(iteratee));
    }

    /**
     * Calculates the mean (average) of an array of numbers.
     *
     * <pre>{@code
     *
     *      // Example: Calculate the mean of an array of integers
     *      Integer[] values = {1, 5, 3, 7};
     *      Optional<Double> result = MeanUtils.mean(values);
     *      System.out.println(result.orElse(null));
     *      // Output: 4.0
     *
     *      // Example: Calculate the mean of an array of doubles
     *      Double[] doubleValues = {1.5, 5.5, 3.5, 7.5};
     *      Optional<Double> result2 = MeanUtils.mean(doubleValues);
     *      System.out.println(result2.orElse(null));
     *      // Output: 4.5
     *
     * }</pre>
     *
     * @param values The array of numbers.
     * @return An Optional containing the mean value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T extends Number> OptionalDouble mean(T... values) {
        if (!isValidArray(values)) {
            return OptionalDouble.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).mapToDouble(Number::doubleValue).average();
    }

    /**
     * Calculates the mean (average) of an array of values based on a custom iteratee function.
     *
     * <pre>{@code
     *
     *      // Example: Calculate the mean of string lengths
     *      Function<String, Integer> lengthIteratee = String::length;
     *      Optional<Double> result = MeanUtils.meanBy(lengthIteratee, "apple", "banana", "cherry");
     *      System.out.println(result.orElse(null));
     *      // Output: 6.0
     *
     *      // Example: Calculate the mean of custom numeric values
     *      Function<MyObject, Double> valueIteratee = MyObject::getValue;
     *      Optional<Double> result2 = MeanUtils.meanBy(valueIteratee, obj1, obj2, obj3);
     *      System.out.println(result2.orElse(null));
     *      // Output: Mean of extracted values
     *
     * }</pre>
     *
     * @param iteratee The iteratee function to extract the numeric value from each element.
     * @param values   The array of values.
     * @return An Optional containing the mean value, or empty if the array is null or empty.
     */
    @SafeVarargs
    public static <T, U extends Number> OptionalDouble meanBy(final Function<T, U> iteratee, final T... values) {
        Objects.requireNonNull(iteratee);
        if (!isValidArray(values)) {
            return OptionalDouble.empty();
        }
        return Stream.of(values).filter(Objects::nonNull).mapToDouble(value -> iteratee.apply(value).doubleValue())
            .average();
    }

    /**
     * Multiplies two numbers. If either number is {@code null}, it returns the default value {@code 1}.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.multiply(5, 4);
     *      System.out.println(result1);
     *      // Output: 20
     *
     *      int result2 = NumberUtils.multiply(5, null);
     *      System.out.println(result2);
     *      // Output: 1
     *
     * }</pre>
     *
     * @param multiplier   The first number in the multiplication.
     * @param multiplicand The second number in the multiplication.
     * @return The product of the two numbers.
     */
    public static int multiply(Integer multiplier, Integer multiplicand) {
        return createMathOperation((a, b) -> a * b, 1).apply(multiplier, multiplicand);
    }

    /**
     * Multiplies two numbers. If either number is {@code null}, it returns the default value {@code 1}.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.multiply(5F, 4F);
     *      System.out.println(result1);
     *      // Output: 20F
     *
     *      float result2 = NumberUtils.multiply(5F, null);
     *      System.out.println(result2);
     *      // Output: 1F
     *
     * }</pre>
     *
     * @param multiplier   The first number in the multiplication.
     * @param multiplicand The second number in the multiplication.
     * @return The product of the two numbers.
     */
    public static float multiply(Float multiplier, Float multiplicand) {
        return createMathOperation((a, b) -> a * b, 1F).apply(multiplier, multiplicand);
    }

    /**
     * Multiplies two numbers. If either number is {@code null}, it returns the default value {@code 1}.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.multiply(5.0, 4.0);
     *      System.out.println(result1);
     *      // Output: 20.0
     *
     *      double result2 = NumberUtils.multiply(5.0, null);
     *      System.out.println(result2);
     *      // Output: 1.0
     *
     * }</pre>
     *
     * @param multiplier   The first number in the multiplication.
     * @param multiplicand The second number in the multiplication.
     * @return The product of the two numbers.
     */
    public static double multiply(Double multiplier, Double multiplicand) {
        return createMathOperation((a, b) -> a * b, 1.0).apply(multiplier, multiplicand);
    }

    /**
     * Multiplies two numbers. If either number is {@code null}, it returns the default value {@code 1}.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.multiply(5L, 4L);
     *      System.out.println(result1);
     *      // Output: 20L
     *
     *      long result2 = NumberUtils.multiply(5L, null);
     *      System.out.println(result2);
     *      // Output: 1L
     *
     * }</pre>
     *
     * @param multiplier   The first number in the multiplication.
     * @param multiplicand The second number in the multiplication.
     * @return The product of the two numbers.
     */
    public static long multiply(Long multiplier, Long multiplicand) {
        return createMathOperation((a, b) -> a * b, 1L).apply(multiplier, multiplicand);
    }

    /**
     * Rounds a number to the specified precision.
     *
     * <pre>{@code
     *
     *      double result0 = NumberUtils.round(null, 2);
     *      System.out.println(result0);
     *      // Output: 0L
     *
     *      double result1 = NumberUtils.round(123.4567, 2);
     *      System.out.println(result1);
     *      // Output: 123.46
     *
     * }</pre>
     *
     * @param number    The number to round.
     * @param precision The precision to round to (number of decimal places).
     *                  If precision is negative, it rounds to the nearest 10^precision.
     * @return The rounded number.
     */
    public static double round(Double number, int precision) {
        if (!isValidNumber(number)) {
            return 0L;
        }
        if (precision == 0) {
            return Math.round(number);
        }
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Rounds a float to the specified precision.
     *
     * <pre>{@code
     *
     *      float result0 = NumberUtils.round(null, 2);
     *      System.out.println(result0);
     *      // Output: 0F
     *
     *      float result1 = NumberUtils.round(123.4567F, 2);
     *      System.out.println(result1);
     *      // Output: 123.46
     *
     * }</pre>
     *
     * @param number    The number to round.
     * @param precision The precision to round to (number of decimal places).
     *                  If precision is negative, it rounds to the nearest 10^precision.
     * @return The rounded number as a float.
     */
    public static float round(Float number, int precision) {
        if (!isValidNumber(number)) {
            return 0F;
        }
        if (precision == 0) {
            return Math.round(number);
        }
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    /**
     * Rounds a number to the nearest integer (precision = 0).
     *
     * <pre>{@code
     *
     *      double result0 = NumberUtils.round(null);
     *      System.out.println(result0);
     *      // Output: 0L
     *
     *      double result = NumberUtils.round(123.4567);
     *      System.out.println(result);
     *      // Output: 123
     *
     * }</pre>
     *
     * @param number The number to round.
     * @return The rounded number.
     */
    public static double round(Double number) {
        if (!isValidNumber(number)) {
            return 0L;
        }
        return round(number, 0);
    }

    /**
     * Rounds a number to the nearest integer (precision = 0).
     *
     * <pre>{@code
     *
     *      float result0 = NumberUtils.round(null);
     *      System.out.println(result0);
     *      // Output: 0L
     *
     *      float result = NumberUtils.round(123.4567F);
     *      System.out.println(result);
     *      // Output: 123.0
     *
     * }</pre>
     *
     * @param number The number to round.
     * @return The rounded number.
     */
    public static float round(Float number) {
        if (!isValidNumber(number)) {
            return 0F;
        }
        return round(number, 0);
    }

    /**
     * Subtracts two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.subtract(5, 4);
     *      System.out.println(result1);
     *      // Output: 1
     *
     *      int result2 = NumberUtils.subtract(5, null);
     *      System.out.println(result2);
     *      // Output: 0
     *
     * }</pre>
     *
     * @param minuend    The first number in the subtraction.
     * @param subtrahend The second number in the subtraction.
     * @return The result of the subtraction.
     */
    public static int subtract(Integer minuend, Integer subtrahend) {
        return createMathOperation((a, b) -> a - b, 0).apply(minuend, subtrahend);
    }

    /**
     * Subtracts two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.subtract(5F, 4F);
     *      System.out.println(result1);
     *      // Output: 1F
     *
     *      float result2 = NumberUtils.subtract(5F, null);
     *      System.out.println(result2);
     *      // Output: 0F
     *
     * }</pre>
     *
     * @param minuend    The first number in the subtraction.
     * @param subtrahend The second number in the subtraction.
     * @return The result of the subtraction.
     */
    public static float subtract(Float minuend, Float subtrahend) {
        return createMathOperation((a, b) -> a - b, 0F).apply(minuend, subtrahend);
    }

    /**
     * Subtracts two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.subtract(5.0, 4.0);
     *      System.out.println(result1);
     *      // Output: 1.0
     *
     *      double result2 = NumberUtils.subtract(5.0, null);
     *      System.out.println(result2);
     *      // Output: 0.0
     *
     * }</pre>
     *
     * @param minuend    The first number in the subtraction.
     * @param subtrahend The second number in the subtraction.
     * @return The result of the subtraction.
     */
    public static double subtract(Double minuend, Double subtrahend) {
        return createMathOperation((a, b) -> a - b, 0.0).apply(minuend, subtrahend);
    }

    /**
     * Subtracts two numbers. If either number is {@code null}, it returns the default value {@code 0}.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.subtract(5L, 4L);
     *      System.out.println(result1);
     *      // Output: 1L
     *
     *      long result2 = NumberUtils.subtract(5L, null);
     *      System.out.println(result2);
     *      // Output: 0L
     *
     * }</pre>
     *
     * @param minuend    The first number in the subtraction.
     * @param subtrahend The second number in the subtraction.
     * @return The result of the subtraction.
     */
    public static long subtract(Long minuend, Long subtrahend) {
        return createMathOperation((a, b) -> a - b, 0L).apply(minuend, subtrahend);
    }

    /**
     * Computes the sum of the values in the collection.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.sumInt(null);
     *      System.out.println(result1);
     *      // Output: 0
     *
     *      int result2 = NumberUtils.sumInt(Arrays.asList(1, null, 3, null));
     *      System.out.println(result2);
     *      // Output: 4
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Integer> int sumInt(Collection<? extends T> collection) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
    }

    /**
     * Computes the sum of the values in the collection.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.sumLong(null);
     *      System.out.println(result1);
     *      // Output: 0L
     *
     *      long result2 = NumberUtils.sumLong(Arrays.asList(1L, null, 3L, null));
     *      System.out.println(result2);
     *      // Output: 4L
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Long> long sumLong(Collection<? extends T> collection) {
        if (isEmpty(collection)) {
            return 0L;
        }
        return collection.stream().filter(Objects::nonNull).mapToLong(Long::longValue).sum();
    }

    /**
     * Computes the sum of the values in the collection.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.sumFloat(null);
     *      System.out.println(result1);
     *      // Output: 0F
     *
     *      float result2 = NumberUtils.sumFloat(Arrays.asList(1.5F, null, 3.5F, null));
     *      System.out.println(result2);
     *      // Output: 5.0F
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Float> float sumFloat(Collection<? extends T> collection) {
        if (isEmpty(collection)) {
            return 0F;
        }
        return (float) collection.stream().filter(Objects::nonNull).mapToDouble(Float::doubleValue)
            .reduce(0.0, Double::sum);
    }

    /**
     * Computes the sum of the values in the collection.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.sumDouble(null);
     *      System.out.println(result1);
     *      // Output: 0.0
     *
     *      double result2 = NumberUtils.sumDouble(Arrays.asList(1.5, null, 3.5, null));
     *      System.out.println(result2);
     *      // Output: 5.0
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Double> double sumDouble(Collection<? extends T> collection) {
        if (isEmpty(collection)) {
            return 0.0;
        }
        return collection.stream().filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Computes the sum of the integer values in the collection after applying the iteratee function.
     *
     * <pre>{@code
     *
     *      int result1 = NumberUtils.sumByInt(null, obj -> obj.getValue());
     *      System.out.println(result1);
     *      // Output: 0
     *
     *      int result2 = NumberUtils.sumByInt(Arrays.asList(obj1, obj2, obj3), obj -> obj.getValue());
     *      System.out.println(result2);
     *      // Output: Sum of values extracted by the iteratee
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @param iteratee   The function to extract the integer value from each element.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Integer> int sumByInt(Collection<? extends T> collection,
                                                   Function<? super T, Integer> iteratee) {
        Objects.requireNonNull(iteratee);
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.stream().filter(Objects::nonNull).mapToInt(iteratee::apply).sum();
    }

    /**
     * Computes the sum of the long values in the collection after applying the iteratee function.
     *
     * <pre>{@code
     *
     *      long result1 = NumberUtils.sumByLong(null, obj -> obj.getValue());
     *      System.out.println(result1);
     *      // Output: 0L
     *
     *      long result2 = NumberUtils.sumByLong(Arrays.asList(obj1, obj2, obj3), obj -> obj.getValue());
     *      System.out.println(result2);
     *      // Output: Sum of values extracted by the iteratee
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @param iteratee   The function to extract the long value from each element.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Long> long sumByLong(Collection<? extends T> collection,
                                                  Function<? super T, Long> iteratee) {
        Objects.requireNonNull(iteratee);
        if (isEmpty(collection)) {
            return 0L;
        }
        return collection.stream().filter(Objects::nonNull).mapToLong(iteratee::apply).sum();
    }

    /**
     * Computes the sum of the float values in the collection after applying the iteratee function.
     *
     * <pre>{@code
     *
     *      float result1 = NumberUtils.sumByFloat(null, obj -> obj.getValue());
     *      System.out.println(result1);
     *      // Output: 0F
     *
     *      float result2 = NumberUtils.sumByFloat(Arrays.asList(obj1, obj2, obj3), obj -> obj.getValue());
     *      System.out.println(result2);
     *      // Output: Sum of values extracted by the iteratee
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @param iteratee   The function to extract the float value from each element.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Float> float sumByFloat(Collection<? extends T> collection,
                                                     Function<? super T, Float> iteratee) {
        Objects.requireNonNull(iteratee);
        if (isEmpty(collection)) {
            return 0F;
        }
        return (float) collection.stream().filter(Objects::nonNull).mapToDouble(t -> iteratee.apply(t).doubleValue())
            .sum();
    }

    /**
     * Computes the sum of the double values in the collection after applying the iteratee function.
     *
     * <pre>{@code
     *
     *      double result1 = NumberUtils.sumByDouble(null, obj -> obj.getValue());
     *      System.out.println(result1);
     *      // Output: 0.0
     *
     *      double result2 = NumberUtils.sumByDouble(Arrays.asList(obj1, obj2, obj3), obj -> obj.getValue());
     *      System.out.println(result2);
     *      // Output: Sum of values extracted by the iteratee
     *
     * }</pre>
     *
     * @param collection The collection to iterate over.
     * @param iteratee   The function to extract the double value from each element.
     * @return The sum of the values, or 0 if the collection is null or empty.
     */
    public static <T extends Double> double sumByDouble(Collection<? extends T> collection,
                                                        Function<? super T, Double> iteratee) {
        Objects.requireNonNull(iteratee);
        if (isEmpty(collection)) {
            return 0.0;
        }
        return collection.stream().filter(Objects::nonNull).mapToDouble(iteratee::apply).sum();
    }

    /**
     * Produces a random integer between the inclusive `lower` and `upper` bounds.
     *
     * <pre>{@code
     *
     *      // Example 1: Random integer between 0 and 5
     *      int result1 = RandomUtils.random(0, 5);
     *      System.out.println(result1);
     *      // Output: e.g., 3
     *
     *      // Example 2: Random integer between 10 and 20
     *      int result2 = RandomUtils.random(10, 20);
     *      System.out.println(result2);
     *      // Output: e.g., 15
     *
     * }</pre>
     *
     * @param lower The lower bound (inclusive).
     * @param upper The upper bound (inclusive).
     * @return Returns the random integer.
     */
    public static int random(int lower, int upper) {
        if (lower > upper) {
            int temp = lower;
            lower = upper;
            upper = temp;
        }
        return RANDOM.nextInt((upper - lower) + 1) + lower;
    }

    /**
     * Produces a random long between the inclusive `lower` and `upper` bounds.
     *
     * <pre>{@code
     *
     *      // Example: Random long between 100L and 200L
     *      long result = RandomUtils.random(100L, 200L);
     *      System.out.println(result);
     *      // Output: e.g., 150L
     *
     * }</pre>
     *
     * @param lower The lower bound (inclusive).
     * @param upper The upper bound (inclusive).
     * @return Returns the random long.
     */
    public static long random(long lower, long upper) {
        if (lower > upper) {
            long temp = lower;
            lower = upper;
            upper = temp;
        }
        return lower + (long) (RANDOM.nextDouble() * (upper - lower + 1));
    }

    /**
     * Produces a random float between the inclusive `lower` and `upper` bounds.
     *
     * <pre>{@code
     *
     *      // Example: Random float between 1.2F and 5.2F
     *      float result = RandomUtils.random(1.2F, 5.2F);
     *      System.out.println(result);
     *      // Output: e.g., 3.7F
     *
     * }</pre>
     *
     * @param lower The lower bound (inclusive).
     * @param upper The upper bound (inclusive).
     * @return Returns the random float.
     */
    public static float random(float lower, float upper) {
        if (lower > upper) {
            float temp = lower;
            lower = upper;
            upper = temp;
        }
        return lower + RANDOM.nextFloat() * (upper - lower);
    }

    /**
     * Produces a random double between the inclusive `lower` and `upper` bounds.
     *
     * <pre>{@code
     *
     *      // Example 1: Random double between 0.0 and 1.0
     *      double result1 = RandomUtils.random(0.0, 1.0);
     *      System.out.println(result1);
     *      // Output: e.g., 0.12345
     *
     *      // Example 2: Random double between 1.5 and 5.5
     *      double result2 = RandomUtils.random(1.5, 5.5);
     *      System.out.println(result2);
     *      // Output: e.g., 3.14159
     *
     * }</pre>
     *
     * @param lower The lower bound (inclusive).
     * @param upper The upper bound (inclusive).
     * @return Returns the random double.
     */
    public static double random(double lower, double upper) {
        if (lower > upper) {
            double temp = lower;
            lower = upper;
            upper = temp;
        }
        return lower + RANDOM.nextDouble() * (upper - lower);
    }

    /**
     * Produces a random integer between 0 (inclusive) and the given `upper` bound (inclusive).
     *
     * <pre>{@code
     *
     *      // Example: Random integer between 0 and 10
     *      int result = RandomUtils.random(10);
     *      System.out.println(result);
     *      // Output: e.g., 7
     *
     * }</pre>
     *
     * @param upper The upper bound (inclusive).
     * @return Returns the random integer.
     */
    public static int random(int upper) {
        return random(0, upper);
    }

    /**
     * Produces a random long between 0 (inclusive) and the given `upper` bound (inclusive).
     *
     * <pre>{@code
     *
     *      // Example: Random long between 0 and 100L
     *      long result = RandomUtils.random(100L);
     *      System.out.println(result);
     *      // Output: e.g., 75L
     *
     * }</pre>
     *
     * @param upper The upper bound (inclusive).
     * @return Returns the random long.
     */
    public static long random(long upper) {
        return random(0L, upper);
    }

    /**
     * Produces a random float between 0 (inclusive) and the given `upper` bound (inclusive).
     *
     * <pre>{@code
     *
     *      // Example: Random float between 0 and 5.5F
     *      float result = RandomUtils.random(5.5F);
     *      System.out.println(result);
     *      // Output: e.g., 3.7F
     *
     * }</pre>
     *
     * @param upper The upper bound (inclusive).
     * @return Returns the random float.
     */
    public static float random(float upper) {
        return random(0F, upper);
    }

    /**
     * Produces a random double between 0 (inclusive) and the given `upper` bound (inclusive).
     *
     * <pre>{@code
     *
     *      // Example: Random double between 0 and 1.0
     *      double result = RandomUtils.random(1.0);
     *      System.out.println(result);
     *      // Output: e.g., 0.12345
     *
     * }</pre>
     *
     * @param upper The upper bound (inclusive).
     * @return Returns the random double.
     */
    public static double random(double upper) {
        return random(0.0, upper);
    }

    /**
     * Checks if {@code number} is between {@code start} (inclusive) and {@code end} (exclusive).
     * If {@code end} is not specified, it defaults to {@code start}, and {@code start} defaults to {@code 0}.
     * If {@code start} is greater than {@code end}, the values are swapped to support negative ranges.
     *
     * <pre>{@code
     *
     *      // Example 1: Check if 3 is between 2 (inclusive) and 4 (exclusive)
     *      boolean result1 = RangeUtils.inRange(3, 2, 4);
     *      System.out.println(result1);
     *      // Output: true
     *
     *      // Example 2: Check if 4 is between 0 (inclusive) and 8 (exclusive)
     *      boolean result2 = RangeUtils.inRange(4, 8);
     *      System.out.println(result2);
     *      // Output: true
     *
     *      // Example 3: Check if 4 is between 0 (inclusive) and 2 (exclusive)
     *      boolean result3 = RangeUtils.inRange(4, 2);
     *      System.out.println(result3);
     *      // Output: false
     *
     *      // Example 4: Check if 1.2 is between 0 (inclusive) and 2 (exclusive)
     *      boolean result4 = RangeUtils.inRange(1.2, 2);
     *      System.out.println(result4);
     *      // Output: true
     *
     *      // Example 5: Check if -3 is between -2 (inclusive) and -6 (exclusive)
     *      boolean result5 = RangeUtils.inRange(-3, -2, -6);
     *      System.out.println(result5);
     *      // Output: true
     *
     * }</pre>
     *
     * @param number The number to check.
     * @param start  The start of the range (inclusive).
     * @param end    The end of the range (exclusive).
     * @return Returns {@code true} if {@code number} is in the range, else {@code false}.
     */
    public static boolean inRange(double number, double start, double end) {
        if (start > end) {
            double temp = start;
            start = end;
            end = temp;
        }
        return number >= start && number < end;
    }

    /**
     * Overloaded method for checking if {@code number} is between {@code 0} (inclusive) and {@code end} (exclusive).
     *
     * <pre>{@code
     *      boolean result5 = RangeUtils.inRange(-3, -6);
     *      System.out.println(result5);
     *      // Output: true
     * }</pre>
     *
     * @param number The number to check.
     * @param end    The end of the range (exclusive).
     * @return Returns {@code true} if {@code number} is in the range, else {@code false}.
     */
    public static boolean inRange(double number, double end) {
        return inRange(number, 0, end);
    }
}
