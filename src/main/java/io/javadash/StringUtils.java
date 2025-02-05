package io.javadash;

import static io.javadash.lib.BaseLibrary.charsEndIndex;
import static io.javadash.lib.BaseLibrary.charsStartIndex;
import static io.javadash.lib.BaseLibrary.createPadding;
import static io.javadash.lib.BaseLibrary.deburrLetter;
import static io.javadash.lib.BaseLibrary.getEscapeReplacement;
import static io.javadash.lib.BaseLibrary.reComboMark;
import static io.javadash.lib.BaseLibrary.reLatin;
import static io.javadash.lib.BaseLibrary.splitByNonAlphabets;
import static io.javadash.lib.BaseValidation.isValidString;
import static io.javadash.lib.Constant.REGEXP_CHARS;
import static io.javadash.lib.Constant.RE_TRIM_END;
import static io.javadash.lib.Constant.RE_TRIM_START;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    /**
     * Checks if the given value is in the string (substring match).
     *
     * <pre>{@code
     *      String sentence = "Hello, world!";
     *      boolean contains = StringUtils.includes(sentence, "world");  // Output: true
     * }</pre>
     *
     * @param str The string to search within.
     * @param value The value to search for in the string.
     * @return true if the string contains the specified value; otherwise, false.
     */
    public static boolean includes(String str, String value) {
        if (!isValidString(str)) {
            return false;
        }
        return str.contains(value);
    }

    /**
     * Converts a given string to camel case.
     *
     * <pre>{@code
     *      String sentence = "hello world example";
     *      String camelCaseStr = StringUtils.camelCase(sentence);  // Output: helloWorldExample
     * }</pre>
     *
     * @param str The string to convert to camelCase.
     * @return The string in camelCase format, or an empty string if the input is invalid.
     */
    public static String camelCase(String str) {
        if (!isValidString(str)) {
            return "";
        }
        String[] words = str.split("[^a-zA-Z0-9]+");
        StringBuilder result = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            result.append(capitalize(words[i]));
        }
        return result.toString();
    }

    /**
     * Capitalizes the first letter of a given string and makes the rest lowercase.
     *
     * <pre>{@code
     *      String word = "hello";
     *      String capitalizedWord = StringUtils.capitalize(word);  // Output: Hello
     * }</pre>
     *
     * @param word The word to capitalize.
     * @return The capitalized word, or an empty string if the input is invalid.
     */
    public static String capitalize(String word) {
        if (!isValidString(word)) {
            return "";
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    /**
     * Deburrs a string by converting Latin-1 Supplement and Latin Extended-A letters
     * to basic Latin letters and removing combining diacritical marks.
     *
     * <pre>{@code
     *      String word = "résumé";
     *      String deburredWord = StringUtils.deburr(word);  // Output: resume
     * }</pre>
     *
     * @param string The string from which to remove accents.
     * @return The deburred string, or an empty string if the input is invalid.
     */
    public static String deburr(String string) {
        if (!isValidString(string)) {
            return "";
        }
        String normalized = Normalizer.normalize(string, Normalizer.Form.NFD);
        normalized = reComboMark.matcher(normalized).replaceAll("");
        normalized = reLatin.matcher(normalized).replaceAll(match -> deburrLetter(match.group()));
        return normalized;
    }

    /**
     * Checks if `string` ends with the given target string.
     *
     * <pre>{@code
     *      String sentence = "Hello, world!";
     *      boolean result = StringUtils.endsWith(sentence, "world!");  // Output: true
     * }</pre>
     *
     * @param string The string to check.
     * @param target The substring to check for at the end of the string.
     * @return true if the string ends with the target, false otherwise.
     */
    public static boolean endsWith(String string, String target) {
        if (!isValidString(string) || !isValidString(target)) {
            return false;
        }
        int end = string.length();
        int position = string.length() - target.length();
        return position >= 0 && string.substring(position, end).equals(target);
    }

    /**
     * Converts the characters "&", "<", ">", '"', and "'" in `string` to their
     * corresponding HTML entities.
     *
     * <pre>{@code
     *      String rawString = "Hello & welcome!";
     *      String escapedString = StringUtils.escape(rawString);  // Output: "Hello &amp; welcome!"
     * }</pre>
     *
     * @param string The string to escape.
     * @return A new string with special characters replaced by their escape sequences, or an empty string if the input is invalid.
     */
    public static String escape(String string) {
        if (!isValidString(string)) {
            return "";
        }
        StringBuilder escapedString = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            String replacement = getEscapeReplacement(c);
            if (replacement != null) {
                escapedString.append(replacement);
            } else {
                escapedString.append(c);
            }
        }
        return escapedString.toString();
    }

    /**
     * Escapes the `RegExp` special characters "^", "$", "\", ".", "*", "+",
     * "?", "(", ")", "[", "]", "{", "}", and "|" in `string`.
     *
     * <pre>{@code
     *      String rawString = "Hello (world)!";
     *      String escapedString = StringUtils.escapeRegExp(rawString);  // Output: "Hello \\(world\\)!"
     * }</pre>
     *
     * @param string The string to escape for regular expressions.
     * @return A new string where special characters are escaped with a backslash, or an empty string if the input is invalid.
     */
    public static String escapeRegExp(String string) {
        if (!isValidString(string)) {
            return "";
        }
        StringBuilder escapedString = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (REGEXP_CHARS.indexOf(c) >= 0) {
                escapedString.append("\\").append(c);
            } else {
                escapedString.append(c);
            }
        }
        return escapedString.toString();
    }

    /**
     * Converts the input string to kebab case.
     *
     * <pre>{@code
     *      String input = "HelloWorld Example";
     *      String kebab = StringUtils.kebabCase(input);  // Output: "hello-world-example"
     * }</pre>
     *
     * @param string The string to convert into kebab case.
     * @return A new string in kebab case format, or an empty string if the input is invalid.
     */
    public static String kebabCase(String string) {
        if (!isValidString(string)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean isFirstWord = true;
        String[] words = string.split("(?=[A-Z])|[^a-zA-Z0-9]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                if (!isFirstWord) {
                    result.append("-");
                }
                result.append(word.toLowerCase());
                isFirstWord = false;
            }
        }
        return result.toString();
    }

    /**
     * Converts the input string to lowercase with words separated by spaces.
     *
     * <pre>{@code
     *      String input = "HelloWorld Example";
     *      String lower = StringUtils.lowerCase(input);  // Output: "hello world example"
     * }</pre>
     *
     * @param string The string to convert into lowercase and space-separated words.
     * @return A new string with all words in lowercase, or an empty string if the input is invalid.
     */
    public static String lowerCase(String string) {
        if (!isValidString(string)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean isFirstWord = true;
        String[] words = string.split("(?=[A-Z])|[^a-zA-Z0-9]+");
        for (String word : words) {
            if (!word.isEmpty()) {
                if (!isFirstWord) {
                    result.append(" ");
                }
                result.append(word.toLowerCase());
                isFirstWord = false;
            }
        }
        return result.toString();
    }

    /**
     * Converts the first character of the input string to lowercase.
     *
     * <pre>{@code
     *      String input = "Hello";
     *      String result = StringUtils.lowerFirst(input);  // Output: "hello"
     * }</pre>
     *
     * @param string The string whose first character will be converted to lowercase.
     * @return A new string with the first character in lowercase, or an empty string if the input is invalid.
     */
    public static String lowerFirst(String string) {
        if (!isValidString(string)) {
            return "";
        }
        char firstChar = Character.toLowerCase(string.charAt(0));
        return firstChar + string.substring(1);
    }

    /**
     * Pads the input string on the left and right sides if it's shorter than the specified length.
     * Padding characters are truncated if they can't be evenly divided by the length.
     *
     * <pre>{@code
     *      String result = StringUtils.pad("hello", 10, "*");
     *      System.out.println(result);  // Output: "**hello***"
     * }</pre>
     *
     * @param string The string to pad.
     * @param length The target length of the resulting string.
     * @param chars The characters to use for padding.
     * @return A new string padded to the specified length, or the original string if no padding is needed.
     */
    public static String pad(String string, int length, String chars) {
        if (string == null) {
            string = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = string.length();
        if (length <= 0 || strLength >= length) {
            return string;
        }
        int paddingLength = length - strLength;
        int leftPadding = paddingLength / 2;
        int rightPadding = (paddingLength % 2 == 0) ? leftPadding : leftPadding + 1;
        String leftPad = createPadding(leftPadding, chars);
        String rightPad = createPadding(rightPadding, chars);
        return leftPad + string + rightPad;
    }

    /**
     * Pads the input string on the left side if it's shorter than the specified length.
     * Padding characters are truncated if they exceed the remaining length.
     *
     * <pre>{@code
     *      String result = StringUtils.padStart("hello", 10, "*");
     *      System.out.println(result);  // Output: "*****hello"
     * }</pre>
     *
     * @param string The string to pad.
     * @param length The target length of the resulting string.
     * @param chars The characters to use for padding.
     * @return A new string padded to the specified length from the start, or the original string if no padding is needed.
     */
    public static String padStart(String string, int length, String chars) {
        if (string == null) {
            string = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = string.length();
        if (length <= 0 || strLength >= length) {
            return string;
        }
        int paddingLength = length - strLength;
        String leftPad = createPadding(paddingLength, chars);
        return leftPad + string;
    }

    /**
     * Pads the input string on the right side if it's shorter than the specified length.
     * Padding characters are truncated if they exceed the remaining length.
     *
     * <pre>{@code
     *      String result = StringUtils.padEnd("hello", 10, "*");
     *      System.out.println(result);  // Output: "hello*****"
     * }</pre>
     *
     * @param string The string to pad.
     * @param length The target length of the resulting string.
     * @param chars The characters to use for padding.
     * @return A new string padded to the specified length from the end, or the original string if no padding is needed.
     */
    public static String padEnd(String string, int length, String chars) {
        if (string == null) {
            string = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = string.length();
        if (length <= 0 || strLength >= length) {
            return string;
        }
        int paddingLength = length - strLength;
        String rightPad = createPadding(paddingLength, chars);
        return string + rightPad;
    }

    /**
     * Repeats the given string `n` times.
     *
     * <pre>{@code
     *      String result = StringUtils.repeat("abc", 3);
     *      System.out.println(result);  // Output: "abcabcabc"
     * }</pre>
     *
     * @param string The string to repeat.
     * @param n The number of times to repeat the string.
     * @return A new string consisting of the original string repeated the specified number of times,
     *         or an empty string if the repetition count is zero or negative.
     */
    public static String repeat(String string, int n) {
        if (string == null) {
            string = "";
        }
        int nStep = Math.max(0, n);
        if (nStep == 0) {
            return "";
        }
        return string.repeat(nStep);
    }

    /**
     * Replaces matches for `pattern` in `string` with `replacement`.
     *
     * <pre>{@code
     *      String result = StringUtils.replace("Hello World", "World", "Java");
     *      System.out.println(result);  // Output: "Hello Java"
     * }</pre>
     *
     * @param string The original string where replacement will be made.
     * @param pattern The regular expression pattern to search for in the string.
     * @param replacement The string to replace the first occurrence of the pattern with.
     * @return A new string with the first occurrence of the pattern replaced by the replacement,
     *         or the original string if any parameter is null.
     */
    public static String replace(String string, String pattern, String replacement) {
        if (string == null) {
            return "";
        }
        if (pattern == null || replacement == null) {
            return string;
        }
        return string.replaceFirst(pattern, replacement);
    }

    /**
     * Converts `string` to snake case.
     *
     * <pre>{@code
     *      String result = StringUtils.snakeCase("HelloWorld Example");
     *      System.out.println(result);  // Output: "hello_world_example"
     * }</pre>
     *
     * @param string The string to be converted to snake_case.
     * @return The string in snake_case format, or an empty string if the input is invalid.
     */
    public static String snakeCase(String string) {
        if (!isValidString(string)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            char c = characters[i];
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    result.append('_');
                }
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Splits `string` by `separator`.
     *
     * <pre>{@code
     *      List<String> result = StringUtils.split("apple,banana,orange", ",");
     *      System.out.println(result);  // Output: ["apple", "banana", "orange"]
     * }</pre>
     *
     * @param string The string to be split into substrings.
     * @param separator The delimiter used to split the string. If null, the entire string is treated as a single element.
     * @return A list containing the substrings obtained by splitting the input string.
     */
    public static List<String> split(String string, String separator) {
        List<String> result = new ArrayList<>();
        if (!isValidString(string)) {
            return result;
        }
        if (separator == null) {
            result.add(string);
        } else {
            String[] segments = string.split(Pattern.quote(separator));
            Collections.addAll(result, segments);
        }
        return result;
    }

    /**
     * Converts a string to start case, where the first letter of each word is capitalized and the rest are in lowercase.
     * The string is split by non-alphabetical characters, and the first letter of each word is capitalized.
     * If the string is null or empty, an empty string is returned.
     *
     * <pre>{@code
     *      String result = StringUtils.startCase("hello world!");
     *      System.out.println(result);  // Output: "Hello World"
     * }</pre>
     *
     * @param string The string to be converted to start case.
     * @return A string where each word's first letter is capitalized and the rest are lowercase.
     */

    public static String startCase(String string) {
        if (!isValidString(string)) {
            return "";
        }
        List<String> words = splitByNonAlphabets(string);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (i > 0) {
                result.append(" ");
            }
            result.append(upperFirst(word));
        }
        return result.toString();
    }

    /**
     * Checks if a string starts with the specified prefix.
     * If either the string or the target prefix is null, it returns false.
     *
     * <pre>{@code
     *      boolean result = StringUtils.startsWith("hello world", "hello");
     *      System.out.println(result);  // Output: true
     * }</pre>
     *
     * @param string The string to check.
     * @param target The prefix to check for.
     * @return true if the string starts with the target prefix, otherwise false.
     */

    public static boolean startsWith(String string, String target) {
        if (string == null || target == null) {
            return false;
        }
        return string.startsWith(target);
    }

    /**
     * Converts the given string to lowercase.
     * If the input string is null or invalid, it returns an empty string.
     *
     * <pre>{@code
     *      String lowerValue = StringUtils.toLower("HELLO");
     *      System.out.println(lowerValue);  // Output: hello
     * }</pre>
     *
     * @param value The string to convert to lowercase.
     * @return The lowercase version of the string, or an empty string if the input is null or invalid.
     */

    public static String toLower(String value) {
        if (!isValidString(value)) {
            return "";
        }
        return value.toLowerCase();
    }

    /**
     * Converts the given string to uppercase.
     * If the input string is null or invalid, it returns an empty string.
     *
     * <pre>{@code
     *      String upperValue = StringUtils.toUpper("hello");
     *      System.out.println(upperValue);  // Output: HELLO
     * }</pre>
     *
     * @param value The string to convert to uppercase.
     * @return The uppercase version of the string, or an empty string if the input is null or invalid.
     */

    public static String toUpper(String value) {
        if (!isValidString(value)) {
            return "";
        }
        return value.toUpperCase();
    }

    /**
     * Trims characters from both ends of a string based on the specified character set.
     * If the character set is not provided or is invalid, it performs a standard trim.
     * If the input string is null, it returns an empty string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trim("  hello world  ", " ");
     *      System.out.println(trimmed);  // Output: "hello world"
     * }</pre>
     *
     * @param string The string to trim.
     * @param chars The characters to trim from both ends of the string.
     * @return A new string with the specified characters removed from both ends, or the original string if no trimming is needed.
     */

    public static String trim(String string, String chars) {
        if (string == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return string.trim();
        }
        char[] strSymbols = string.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int start = charsStartIndex(strSymbols, chrSymbols);
        int end = charsEndIndex(strSymbols, chrSymbols);
        if (start >= end) {
            return "";
        }
        return new String(Arrays.copyOfRange(strSymbols, start, end));
    }

    /**
     * Trims characters from the beginning of a string based on the specified character set.
     * If the character set is not provided or is invalid, it performs a standard trim from the start.
     * If the input string is null, it returns an empty string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trimStart("  hello world  ", " ");
     *      System.out.println(trimmed);  // Output: "hello world  "
     * }</pre>
     *
     * @param string The string to trim.
     * @param chars The characters to trim from the beginning of the string.
     * @return A new string with the specified characters removed from the beginning, or the original string if no trimming is needed.
     */

    public static String trimStart(String string, String chars) {
        if (string == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return string.replaceFirst(RE_TRIM_START, "");
        }
        char[] strSymbols = string.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int start = charsStartIndex(strSymbols, chrSymbols);
        if (start == strSymbols.length) {
            return "";
        }
        return new String(Arrays.copyOfRange(strSymbols, start, strSymbols.length));
    }

    /**
     * Trims characters from the end of a string based on the specified character set.
     * If the character set is not provided or is invalid, it performs a standard trim from the end.
     * If the input string is null, it returns an empty string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trimEnd("  hello world  ", " ");
     *      System.out.println(trimmed);  // Output: "  hello world"
     * }</pre>
     *
     * @param string The string to trim.
     * @param chars The characters to trim from the end of the string.
     * @return A new string with the specified characters removed from the end, or the original string if no trimming is needed.
     */

    public static String trimEnd(String string, String chars) {
        if (string == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return string.replaceAll(RE_TRIM_END, "");
        }
        char[] strSymbols = string.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int end = charsEndIndex(strSymbols, chrSymbols);
        return new String(Arrays.copyOfRange(strSymbols, 0, end));
    }

    /**
     * Converts all alphanumeric words in the input string to uppercase, while preserving other characters.
     * It matches sequences of alphanumeric characters and applies uppercase transformation to them,
     * leaving any non-alphanumeric characters unchanged.
     * If the input string is invalid or null, it returns an empty string.
     *
     * <pre>{@code
     *      String transformed = StringUtils.upperCase("hello world 123");
     *      System.out.println(transformed);  // Output: "HELLO WORLD 123"
     * }</pre>
     *
     * @param string The string to process.
     * @return A new string with all alphanumeric words converted to uppercase.
     */

    public static String upperCase(String string) {
        if (!isValidString(string)) {
            return "";
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(string);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String word = matcher.group();
            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(word.toUpperCase());
        }
        return result.toString();
    }

    /**
     * Capitalizes the first character of the input string and returns the modified string.
     * If the string is null or empty, it returns an empty string.
     *
     * <pre>{@code
     *      String transformed = StringUtils.upperFirst("hello");
     *      System.out.println(transformed);  // Output: "Hello"
     * }</pre>
     *
     * @param string The string whose first character will be capitalized.
     * @return The string with the first character capitalized, or an empty string if the input is invalid.
     */

    public static String upperFirst(String string) {
        if (!isValidString(string)) {
            return "";
        }
        char firstChar = Character.toUpperCase(string.charAt(0));
        return firstChar + string.substring(1);
    }
}
