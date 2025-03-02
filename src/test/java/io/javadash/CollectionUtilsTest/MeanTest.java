package io.javadash.CollectionUtilsTest;
import static io.javadash.CollectionUtils.mean;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MeanTest {
    @Test
    void testMeanWithNonEmptyCollection() {
        Collection<Number> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble result = mean(numbers);
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals(3.0, result.getAsDouble(), "The mean of the numbers should be 3.0.");
    }

    @Test
    void testMeanWithEmptyCollection() {
        Collection<Number> numbers = Collections.emptyList();
        OptionalDouble result = mean(numbers);
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMeanWithSingleElementCollection() {
        Collection<Number> numbers = Collections.singletonList(42);
        OptionalDouble result = mean(numbers);
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals(42.0, result.getAsDouble(), "The mean of a single element should be the element itself.");
    }

    @Test
    void testMeanWithNullValues() {
        Collection<Number> numbers = Arrays.asList(1, 2, 3, null, 4, 5);
        OptionalDouble result = mean(numbers);
        assertTrue(result.isPresent(), "The result should be present when the collection contains null values.");
        assertEquals(3.0, result.getAsDouble(), "The mean should ignore null values and be 3.0.");
    }

    @Test
    void testMeanWithAllNullValues() {
        Collection<Number> numbers = Arrays.asList(null, null, null);
        OptionalDouble result = mean(numbers);
        assertFalse(result.isPresent(), "The result should be empty when all elements are null.");
    }

    @Test
    void testMeanWithAllSameValues() {
        Collection<Number> numbers = Arrays.asList(5, 5, 5, 5, 5);
        OptionalDouble result = mean(numbers);
        assertTrue(result.isPresent(), "The result should be present when all elements are the same.");
        assertEquals(5.0, result.getAsDouble(), "The mean of all the same values should be that value.");
    }

    @Test
    void testMeanWithNullCollection() {
        Collection<Number> numbers = null;
        OptionalDouble result = mean(numbers);
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }
}
