package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.reduce;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.Test;

public class ReduceTest {

    @Test
    void testReduce_withIdentityValue() {
        // Create a collection of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection with an identity value of 0
        Integer result = reduce(numbers, 0, sumAccumulator);

        // Expected result
        Integer expected = 15; // 1 + 2 + 3 + 4 + 5

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withIdentityValue_emptyCollection() {
        // Create an empty collection
        List<Integer> numbers = new ArrayList<>();

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection with an identity value of 0
        Integer result = reduce(numbers, 0, sumAccumulator);

        // Expected result
        Integer expected = 0; // Identity value when collection is empty

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withIdentityValue_withNull() {
        // Create a collection with a null value
        List<Integer> numbers = Arrays.asList(1, null, 3, 4);

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection with an identity value of 0
        Integer result = reduce(numbers, 0, sumAccumulator);

        // Expected result
        Integer expected = 8; // 1 + 3 + 4 (null is ignored)

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withoutIdentityValue() {
        // Create a collection of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection
        Optional<Integer> result = reduce(numbers, sumAccumulator);

        // Expected result
        Optional<Integer> expected = Optional.of(15); // 1 + 2 + 3 + 4 + 5

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withoutIdentityValue_emptyCollection() {
        // Create an empty collection
        List<Integer> numbers = new ArrayList<>();

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection
        Optional<Integer> result = reduce(numbers, sumAccumulator);

        // Expected result
        Optional<Integer> expected = Optional.empty(); // Empty collection returns Optional.empty()

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withoutIdentityValue_withNull() {
        // Create a collection with a null value
        List<Integer> numbers = Arrays.asList(1, null, 3, 4);

        // Accumulator: sum the values
        BinaryOperator<Integer> sumAccumulator = Integer::sum;

        // Reduce the collection
        Optional<Integer> result = reduce(numbers, sumAccumulator);

        // Expected result
        Optional<Integer> expected = Optional.of(8); // 1 + 3 + 4 (null is ignored)

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduce_withStudent_withoutIdentityValue() {
        // Create a collection of students
        List<Student> students = Arrays.asList(
            new Student("Alice", 85),
            new Student("Bob", 90),
            new Student("Charlie", 95)
        );

        // Accumulator: sum the scores of the students
        BinaryOperator<Student> scoreAccumulator = (student1, student2) ->
            new Student("Total", student1.getScore() + student2.getScore());

        // Reduce the collection without an identity value
        Optional<Student> result = reduce(students, scoreAccumulator);

        // Expected result: total score = 85 + 90 + 95 = 270
        Optional<Student> expected = Optional.of(new Student("Total", 270));

        // Assert that the result is correct
        assertEquals(expected.get().getScore(), result.get().getScore());
    }

    @Test
    void testReduce_withStudent_withoutIdentityValue_emptyCollection() {
        // Create an empty collection of students
        List<Student> students = new ArrayList<>();

        // Accumulator: sum the scores of the students
        BinaryOperator<Student> scoreAccumulator = (student1, student2) ->
            new Student("Total", student1.getScore() + student2.getScore());

        // Reduce the collection without an identity value
        Optional<Student> result = reduce(students, scoreAccumulator);

        // Expected result: since collection is empty, it should return Optional.empty
        Optional<Student> expected = Optional.empty();

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + "}";
        }
    }
}

