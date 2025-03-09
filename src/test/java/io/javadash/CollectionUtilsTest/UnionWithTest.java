package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.unionWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.Test;

public class UnionWithTest {

    @Test
    void testUnionWithComparator() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        // Comparator to consider equality of numbers
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        List<Integer> result = unionWith(comparator, list1, list2);  // Using the unionWith method directly

        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)), result);  // Should return [1, 2, 3, 4, 5] without duplicates
    }

    @Test
    void testUnionWithEmptyLists() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Comparator to consider equality of numbers
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        List<Integer> result = unionWith(comparator, list1, list2);  // Using the unionWith method directly

        assertEquals(new ArrayList<>(), result);  // Should return an empty list
    }

    @Test
    void testUnionWithNullLists() {
        List<Integer> list1 = null;
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        // Comparator to consider equality of numbers
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        List<Integer> result = unionWith(comparator, list1, list2);  // Using the unionWith method directly

        assertEquals(new ArrayList<>(Arrays.asList(1, 2)), result);  // Should return [1, 2]
    }

    @Test
    void testUnionWithComparatorForCustomObject() {
        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("Alice", 30));
        list1.add(new Person("Bob", 25));

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Bob", 25));
        list2.add(new Person("Charlie", 35));

        // Comparator to consider equality based on name and age
        BiPredicate<Person, Person> comparator = (a, b) -> a.getName().equals(b.getName()) && a.getAge() == b.getAge();

        List<Person> result = unionWith(comparator, list1, list2);  // Using the unionWith method directly

        assertEquals(new ArrayList<>(Arrays.asList(new Person("Alice", 30), new Person("Bob", 25), new Person("Charlie", 35))), result);
    }

    @Test
    void testUnionWithNullAndEmptyLists() {
        List<Integer> list1 = null;
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        // Comparator to consider equality of numbers
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        List<Integer> result = unionWith(comparator, list1, list2);  // Using the unionWith method directly

        assertEquals(new ArrayList<>(Arrays.asList(3, 4)), result);  // Should return [3, 4] from list2
    }

    // Custom class for testing with comparator
    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
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
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + ", " + age;
        }
    }
}



