package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.uniqWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.Test;

public class UniqWithTest {

    @Test
    void testUniqWithComparator() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 25));
        list.add(new Person("Alice", 30)); // Duplicate based on name and age
        list.add(new Person("Charlie", 35));

        // Define a comparator that considers two persons as equal if both name and age are the same
        BiPredicate<Person, Person> comparator = (p1, p2) -> p1.getName().equals(p2.getName()) && p1.getAge() == p2.getAge();

        List<Person> result = uniqWith(list, comparator);

        // The result should contain only one "Alice" and no duplicate values
        assertEquals(new ArrayList<>(Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35))), result);
    }

    @Test
    void testUniqWithEmptyList() {
        List<Person> list = new ArrayList<>();

        BiPredicate<Person, Person> comparator = (p1, p2) -> p1.getName().equals(p2.getName()) && p1.getAge() == p2.getAge();

        List<Person> result = uniqWith(list, comparator);

        // The result should be an empty list
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testUniqWithNullList() {
        List<Person> list = null;

        BiPredicate<Person, Person> comparator = (p1, p2) -> p1.getName().equals(p2.getName()) && p1.getAge() == p2.getAge();

        List<Person> result = uniqWith(list, comparator);

        // The result should be an empty list as the list is null
        assertEquals(new ArrayList<>(), result);
    }

    // Custom class for testing with uniqWith functionality
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
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
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



