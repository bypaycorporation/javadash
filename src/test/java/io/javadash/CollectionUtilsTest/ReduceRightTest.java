package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.reduceRight;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.Test;

public class ReduceRightTest {
    @Test
    void testReduceRight_withIdentity() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Accumulator: sum the numbers
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + b;

        // Reduce right with identity value (0)
        Integer result = reduceRight(numbers, 0, sumAccumulator);

        // Expected result: sum = 5 + 4 + 3 + 2 + 1 = 15
        Integer expected = 15;

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withEmptyCollection() {
        List<Integer> numbers = new ArrayList<>();

        // Accumulator: sum the numbers
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + b;

        // Reduce right with identity value (0)
        Integer result = reduceRight(numbers, 0, sumAccumulator);

        // Expected result: since the collection is empty, it should return the identity value
        Integer expected = 0;

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withNullValues() {
        List<Integer> numbers = Arrays.asList(1, 2, null, 3, 4);

        // Accumulator: sum the numbers, ignoring nulls
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + (b != null ? b : 0);

        // Reduce right with identity value (0)
        Integer result = reduceRight(numbers, 0, sumAccumulator);

        // Expected result: sum = 4 + 3 + 2 + 1 = 10 (ignoring null)
        Integer expected = 10;

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withoutIdentity() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Accumulator: sum the numbers
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + b;

        // Reduce right without identity value
        Optional<Integer> result = reduceRight(numbers, sumAccumulator);

        // Expected result: sum = 5 + 4 + 3 + 2 + 1 = 15
        Optional<Integer> expected = Optional.of(15);

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withoutIdentity_emptyCollection() {
        List<Integer> numbers = new ArrayList<>();

        // Accumulator: sum the numbers
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + b;

        // Reduce right without identity value
        Optional<Integer> result = reduceRight(numbers, sumAccumulator);

        // Expected result: since the collection is empty, it should return Optional.empty()
        Optional<Integer> expected = Optional.empty();

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withoutIdentity_withNullValues() {
        List<Integer> numbers = Arrays.asList(1, 2, null, 3, 4);

        // Accumulator: sum the numbers, ignoring nulls
        BinaryOperator<Integer> sumAccumulator = (a, b) -> a + (b != null ? b : 0);

        // Reduce right without identity value
        Optional<Integer> result = reduceRight(numbers, sumAccumulator);

        // Expected result: sum = 4 + 3 + 2 + 1 = 10 (ignoring null)
        Optional<Integer> expected = Optional.of(10);

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRight_withStudent_optional() {
        // Create a list of students
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 80),
            new Student("Charlie", 85),
            new Student("David", 92)
        );

        // Accumulator: find the student with the highest score
        BinaryOperator<Student> highestScoreAccumulator = (student1, student2) -> {
            return student1.getScore() > student2.getScore() ? student1 : student2;
        };

        // Reduce right without identity value
        Optional<Student> result = reduceRight(students, highestScoreAccumulator);

        // Expected result: the student with the highest score, David (92)
        Optional<Student> expected = Optional.of(new Student("David", 92));

        // Assert that the result is correct
        assertEquals(expected, result);
    }

    @Test
    void testReduceRightWithIdentity() {
        // Create a list of students
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 80),
            new Student("Charlie", 85),
            new Student("David", 92)
        );

        // Define the identity student (default value for reduction)
        Student identity = new Student("Default", 0);

        // Accumulator: Sum the scores of students
        BinaryOperator<Student> scoreAccumulator = (student1, student2) -> {
            return new Student(student1.getName() + "+" + student2.getName(),
                student1.getScore() + student2.getScore());
        };

        // Reduce right with identity value
        Student result = reduceRight(students, identity, scoreAccumulator);

        // Expected result: Start with identity, and reduce the students from right to left
        String expectedName = "Default+David+Charlie+Bob+Alice";
        int expectedScore = 347;

        // Assert the final result is correct
        assertEquals(expectedName, result.getName());
        assertEquals(expectedScore, result.getScore());
    }

    @Test
    void testReduceRightWithIdentity_emptyCollection() {
        // Create an empty list of students
        List<Student> students = new ArrayList<>();

        // Define the identity student (default value for reduction)
        Student identity = new Student("Default", 0);

        // Accumulator: Sum the scores of students
        BinaryOperator<Student> scoreAccumulator = (student1, student2) -> {
            return new Student(student1.getName() + "+" + student2.getName(),
                student1.getScore() + student2.getScore());
        };

        // Reduce right with identity value
        Student result = reduceRight(students, identity, scoreAccumulator);

        // Since the list is empty, the result should be the identity value
        String expectedName = "Default";
        int expectedScore = 0;

        // Assert that the result is the identity student
        assertEquals(expectedName, result.getName());
        assertEquals(expectedScore, result.getScore());
    }

    static class Student {
        private String name;
        private int score;

        // Constructor
        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        // Getter methods
        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return score == student.score && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + "}";
        }
    }
}


