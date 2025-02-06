package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.orderBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class OrderByTest {

    @Test
    void testOrderBy_validCollection() {
        List<Integer> collection = Arrays.asList(5, 3, 8, 1, 7);

        // Comparator: Sort numbers in ascending order
        Comparator<Integer> ascending = Integer::compareTo;

        List<Integer> result = orderBy(collection, ascending);

        List<Integer> expected = Arrays.asList(1, 3, 5, 7, 8);
        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_reverseOrder() {
        List<Integer> collection = Arrays.asList(5, 3, 8, 1, 7);

        // Comparator: Sort numbers in descending order
        Comparator<Integer> descending = (a, b) -> Integer.compare(b, a);

        List<Integer> result = orderBy(collection, descending);

        List<Integer> expected = Arrays.asList(8, 7, 5, 3, 1);
        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_emptyCollection() {
        List<Integer> collection = Collections.emptyList();

        Comparator<Integer> ascending = Integer::compareTo;

        List<Integer> result = orderBy(collection, ascending);

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testOrderBy_nullCollection() {
        List<Integer> collection = null;

        Comparator<Integer> ascending = Integer::compareTo;

        List<Integer> result = orderBy(collection, ascending);

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testOrderBy_customComparator() {
        List<String> collection = Arrays.asList("banana", "apple", "cherry", "date");

        // Comparator: Sort strings by length in descending order
        Comparator<String> byLengthDescending = (a, b) -> Integer.compare(b.length(), a.length());

        List<String> result = orderBy(collection, byLengthDescending);

        List<String> expected = Arrays.asList("banana", "cherry", "apple", "date");
        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_studentList_sortByAge() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20),
            new Student("David", 23)
        );

        // Comparator: Sort by age in ascending order
        Comparator<Student> sortByAgeAsc = Comparator.comparingInt(Student::getAge);

        List<Student> result = orderBy(students, sortByAgeAsc);

        List<Student> expected = Arrays.asList(
            new Student("Charlie", 20),
            new Student("Alice", 22),
            new Student("David", 23),
            new Student("Bob", 25)
        );

        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_studentList_sortByName() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20),
            new Student("David", 23)
        );

        // Comparator: Sort by name alphabetically
        Comparator<Student> sortByNameAsc = Comparator.comparing(Student::getName);

        List<Student> result = orderBy(students, sortByNameAsc);

        List<Student> expected = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20),
            new Student("David", 23)
        );

        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_studentList_sortByNameAndAge() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20),
            new Student("David", 23)
        );

        // Comparator: Sort first by name, then by age
        Comparator<Student> sortByNameThenAge = Comparator
            .comparing(Student::getName)
            .thenComparingInt(Student::getAge);

        List<Student> result = orderBy(students, sortByNameThenAge);

        List<Student> expected = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20),
            new Student("David", 23)
        );

        assertEquals(expected, result);
    }

    @Test
    void testOrderBy_nullComparator() {
        List<Integer> collection = Arrays.asList(5, 3, 8, 1, 7);

        // Pass null comparator
        assertThrows(NullPointerException.class, () -> {
            orderBy(collection, null);
        });
    }

    static class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
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
            return age == student.age && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + "}";
        }
    }
}

