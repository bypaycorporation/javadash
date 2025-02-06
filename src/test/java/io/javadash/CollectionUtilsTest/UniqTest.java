package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.uniq;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class UniqTest {

    @Test
    void testUniqWithDuplicates() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);

        List<Integer> result = uniq(list);

        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3)), result);  // Should return [1, 2, 3] without duplicates
    }

    @Test
    void testUniqWithNoDuplicates() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> result = uniq(list);

        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3)), result);  // Should return [1, 2, 3]
    }

    @Test
    void testUniqWithEmptyList() {
        List<Integer> list = new ArrayList<>();

        List<Integer> result = uniq(list);

        assertEquals(new ArrayList<>(), result);  // Should return an empty list
    }

    @Test
    void testUniqWithNullList() {
        List<Integer> list = null;

        List<Integer> result = uniq(list);

        assertEquals(new ArrayList<>(), result);  // Should return an empty list
    }

    @Test
    void testUniqWithNullAndNonNullValues() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(null);
        list.add(2);
        list.add(null);

        List<Integer> result = uniq(list);

        assertEquals(Arrays.asList(1, null, 2), result);  // Should return [1, null, 2] without duplicate nulls
    }

    @Test
    void testUniqWithCustomObjects() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 25));
        list.add(new Person("Alice", 30));  // Duplicate Person object

        List<Person> result = uniq(list);

        assertEquals(new ArrayList<>(Arrays.asList(new Person("Alice", 30), new Person("Bob", 25))), result);
    }

    // Custom class for testing with unique functionality
    static class Person {
        private String name;
        private int age;

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



