package io.javadash;

public class DateUtils {
    /**
     * Returns the current time in milliseconds since the Unix epoch (January 1, 1970).
     * This is equivalent to `System.currentTimeMillis()`.
     *
     * <pre>{@code
     *      long currentTime = DateUtils.now();
     *      System.out.println("Current time in milliseconds: " + currentTime);
     * }</pre>
     *
     * @return The current time in milliseconds.
     */

    public static long now() {
        return System.currentTimeMillis();
    }
}
