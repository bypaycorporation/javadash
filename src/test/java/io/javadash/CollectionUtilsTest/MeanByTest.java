package io.javadash.CollectionUtilsTest;
import static io.javadash.CollectionUtils.meanBy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MeanByTest {
    @Test
    void testMeanByWithNonEmptyCollection() {
        // Collection of numbers, calculate average directly
        Collection<Number> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals(3.0, result.getAsDouble(), "The mean of the numbers should be 3.0.");
    }

    @Test
    void testMeanByWithEmptyCollection() {
        Collection<Number> numbers = Collections.emptyList();
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMeanByWithSingleElementCollection() {
        Collection<Number> numbers = Collections.singletonList(42);
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals(42.0, result.getAsDouble(), "The mean of a single element should be the element itself.");
    }

    @Test
    void testMeanByWithNullValues() {
        Collection<Number> numbers = Arrays.asList(1, 2, 3, null, 4, 5);
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertTrue(result.isPresent(), "The result should be present when the collection contains null values.");
        assertEquals(3.0, result.getAsDouble(), "The mean should ignore null values and be 3.0.");
    }

    @Test
    void testMeanByWithAllNullValues() {
        Collection<Number> numbers = Arrays.asList(null, null, null);
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertFalse(result.isPresent(), "The result should be empty when all elements are null.");
    }

    @Test
    void testMeanByWithCustomFunction() {
        // Use a custom function that squares each number
        Collection<Number> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble result = meanBy(numbers, n -> Math.pow(n.doubleValue(), 2));
        assertTrue(result.isPresent(), "The result should be present when using a custom function.");
        assertEquals(11.0, result.getAsDouble(), "The mean of squared numbers should be 11.0.");
    }

    @Test
    void testMeanByWithNullCollection() {
        Collection<Number> numbers = null;
        OptionalDouble result = meanBy(numbers, Number::doubleValue);
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }
}
