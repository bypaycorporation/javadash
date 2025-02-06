package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.findLastIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FindLastIndexTest {

    @Test
    public void testFindLastIndex_withValidInputs() {
        List<String> users = Arrays.asList("barney", "fred", "pebbles");

        // Case 2: Find the last index of "barney" starting from index 1
        int result2 = findLastIndex(users, user -> "barney".equals(user), 1);
        assertEquals(0, result2);

        // Case 4: Find the last index using negative indexing
        int result4 = findLastIndex(users, user -> user.startsWith("f"), -1);
        assertEquals(-1, result4);
    }

    @Test
    public void testFindLastIndex_withEmptyList() {
        List<String> emptyList = Collections.emptyList();

        // Case 1: Empty list should always return -1
        int result = findLastIndex(emptyList, user -> "barney".equals(user), 1);
        assertEquals(-1, result);
    }

    @Test
    public void testFindLastIndex_withNullList() {
        // Case 1: Null list should always return -1
        int result = findLastIndex(null, user -> "barney".equals(user), 2);
        assertEquals(-1, result);
    }

    @Test
    public void testFindLastIndex_withFromIndexOutOfBounds() {
        List<String> users = Arrays.asList("barney", "fred", "pebbles");

        // Case 1: Start index larger than size
        int result1 = findLastIndex(users, user -> "pebbles".equals(user), 10);
        assertEquals(2, result1);

        // Case 2: Negative start index beyond the list size
        int result2 = findLastIndex(users, user -> "barney".equals(user), -10);
        assertEquals(-1, result2);
    }
}

