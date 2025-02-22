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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {
    /**
     * Checks if the given value is in the string (substring match).
     *
     * <pre>{@code
     *      String sentence = "Hello, world!";
     *      boolean contains = StringUtils.includes(sentence, "world");
     *      // Output: true
     * }</pre>
     *
     * @param str   The string to search within.
     * @param value The value to search for in the string.
     * @return Return {@code true} if the string contains the specified value; otherwise, {@code false}.
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
     *      String camelCaseStr = StringUtils.camelCase(sentence);
     *      // Output: helloWorldExample
     * }</pre>
     *
     * @param str The string to convert to camelCase.
     * @return Returns the camel cased string.
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
     *      String capitalizedWord = StringUtils.capitalize(word);
     *      // Output: Hello
     * }</pre>
     *
     * @param str The str to capitalize.
     * @return The capitalized word, or an empty string if the input is invalid.
     */
    public static String capitalize(String str) {
        if (!isValidString(str)) {
            return "";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Deburrs a string by converting Latin-1 Supplement and Latin Extended-A letters
     * to basic Latin letters and removing combining diacritical marks.
     *
     * <pre>{@code
     *      String word = "résumé";
     *      String deburredWord = StringUtils.deburr(word);
     *      // Output: resume
     * }</pre>
     *
     * @param str The string to deburr.
     * @return Returns the deburred string.
     */
    public static String deburr(String str) {
        if (!isValidString(str)) {
            return "";
        }
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        normalized = reComboMark.matcher(normalized).replaceAll("");
        Matcher matcher = reLatin.matcher(normalized);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, deburrLetter(matcher.group()));
        }
        matcher.appendTail(result);
        return result.toString();
    }


    /**
     * Checks if {@code string} ends with the given target string.
     *
     * <pre>{@code
     *      String sentence = "Hello, world!";
     *      boolean result = StringUtils.endsWith(sentence, "world!");
     *      // Output: true
     * }</pre>
     *
     * @param str    The string to inspect.
     * @param target The string to search for.
     * @return Returns {@code true} if string ends with target, else {@code false}.
     */
    public static boolean endsWith(String str, String target) {
        if (!isValidString(str) || !isValidString(target)) {
            return false;
        }
        int end = str.length();
        int position = str.length() - target.length();
        return position >= 0 && str.substring(position, end).equals(target);
    }

    /**
     * Converts the characters {@code &}, {@code <}, {@code >}, {@code "}, and {@code '} in {@code string} to their
     * corresponding HTML entities.
     *
     * <pre>{@code
     *      String rawString = "Hello & welcome!";
     *      String escapedString = StringUtils.escape(rawString);
     *      // Output: "Hello &amp; welcome!"
     * }</pre>
     *
     * @param str The string to escape.
     * @return Returns the escaped string.
     */
    public static String escape(String str) {
        if (!isValidString(str)) {
            return "";
        }
        StringBuilder escapedString = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
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
     * Escapes the {@code RegExp} special characters {@code ^}, {@code $}, {@code \}, {@code .}, {@code *}, {@code +},
     * {@code ?}, {@code (}, {@code )}, {@code [}, {@code ]}, {@code {}, {@code }}, and {@code |} in {@code string}.
     *
     * <pre>{@code
     *      String rawString = "Hello (world)!";
     *      String escapedString = StringUtils.escapeRegExp(rawString);
     *      // Output: "Hello \\(world\\)!"
     * }</pre>
     *
     * @param str The string to escape.
     * @return Returns the escaped string.
     */
    public static String escapeRegExp(String str) {
        if (!isValidString(str)) {
            return "";
        }
        StringBuilder escapedString = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (REGEXP_CHARS.indexOf(c) >= 0) {
                escapedString.append("\\").append(c);
            } else {
                escapedString.append(c);
            }
        }
        return escapedString.toString();
    }

    /**
     * Converts string to kebab case.
     *
     * <pre>{@code
     *      String input = "HelloWorld Example";
     *      String kebab = StringUtils.kebabCase(input);
     *      // Output: "hello-world-example"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the kebab cased string.
     */
    public static String kebabCase(String str) {
        if (!isValidString(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean isFirstWord = true;
        String[] words = str.split("(?=[A-Z])|[^a-zA-Z0-9]+");
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
     * Converts string to lowercase with words separated by spaces.
     *
     * <pre>{@code
     *      String input = "Hello World Example";
     *      String lower = StringUtils.lowerCase(input);
     *      // Output: "hello world example"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the lower cased string.
     */
    public static String lowerCase(String str) {
        if (!isValidString(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean isFirstWord = true;
        String[] words = str.split("(?=[A-Z])|[^a-zA-Z0-9]+");
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
     * Converts the first character of string to lower case.
     *
     * <pre>{@code
     *      String input = "Hello";
     *      String result = StringUtils.lowerFirst(input);
     *      // Output: "hello"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the converted string.
     */
    public static String lowerFirst(String str) {
        if (!isValidString(str)) {
            return "";
        }
        char firstChar = Character.toLowerCase(str.charAt(0));
        return firstChar + str.substring(1);
    }

    /**
     * Pads string on the left and right sides if it's shorter than length. Padding characters are truncated if they can't be evenly divided by length.
     *
     * <pre>{@code
     *      String result = StringUtils.pad("hello", 10, "*");
     *      System.out.println(result);
     *      // Output: "**hello***"
     * }</pre>
     *
     * @param str The string to pad.
     * @param length The padding length.
     * @param chars  The string used as padding.
     * @return Returns the padded string.
     */
    public static String pad(String str, int length, String chars) {
        if (str == null) {
            str = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = str.length();
        if (length <= 0 || strLength >= length) {
            return str;
        }
        int paddingLength = length - strLength;
        int leftPadding = paddingLength / 2;
        int rightPadding = (paddingLength % 2 == 0) ? leftPadding : leftPadding + 1;
        String leftPad = createPadding(leftPadding, chars);
        String rightPad = createPadding(rightPadding, chars);
        return leftPad + str + rightPad;
    }

    /**
     * Pads string on the left side if it's shorter than length. Padding characters are truncated if they exceed length.
     *
     * <pre>{@code
     *      String result = StringUtils.padStart("hello", 10, "*");
     *      System.out.println(result);
     *      // Output: "*****hello"
     * }</pre>
     *
     * @param str The string to pad.
     * @param length The padding length.
     * @param chars  The string used as padding.
     * @return Returns the padded string.
     */
    public static String padStart(String str, int length, String chars) {
        if (str == null) {
            str = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = str.length();
        if (length <= 0 || strLength >= length) {
            return str;
        }
        int paddingLength = length - strLength;
        String leftPad = createPadding(paddingLength, chars);
        return leftPad + str;
    }

    /**
     * Pads string on the right side if it's shorter than length. Padding characters are truncated if they exceed length.
     *
     * <pre>{@code
     *      String result = StringUtils.padEnd("hello", 10, "*");
     *      System.out.println(result);
     *      // Output: "hello*****"
     * }</pre>
     *
     * @param str The string to pad.
     * @param length The padding length.
     * @param chars  The string used as padding.
     * @return Returns the padded string.
     */
    public static String padEnd(String str, int length, String chars) {
        if (str == null) {
            str = "";
        }
        if (!isValidString(chars)) {
            chars = " ";
        }
        int strLength = str.length();
        if (length <= 0 || strLength >= length) {
            return str;
        }
        int paddingLength = length - strLength;
        String rightPad = createPadding(paddingLength, chars);
        return str + rightPad;
    }

    /**
     * Repeats the given string n times.
     *
     * <pre>{@code
     *      String result = StringUtils.repeat("abc", 3);
     *      System.out.println(result);
     *      // Output: "abcabcabc"
     * }</pre>
     *
     * @param str The string to repeat.
     * @param n   The number of times to repeat the string.
     * @return Returns the repeated string.
     */
    public static String repeat(String str, int n) {
        if (str == null) {
            return "";
        }
        int nStep = Math.max(0, n);
        if (nStep == 0) {
            return "";
        }
        return IntStream.range(0, nStep)
            .mapToObj(i -> str)
            .collect(Collectors.joining());
    }

    /**
     * Replaces matches for pattern in string with replacement.
     *
     * <pre>{@code
     *      String result = StringUtils.replace("Hello World", "World", "Java");
     *      System.out.println(result);
     *      // Output: "Hello Java"
     * }</pre>
     *
     * @param str         The string to modify.
     * @param pattern     The pattern to replace.
     * @param replacement The match replacement.
     * @return Returns the modified string.
     */
    public static String replace(String str, String pattern, String replacement) {
        if (str == null) {
            return "";
        }
        if (pattern == null || replacement == null) {
            return str;
        }
        return str.replaceFirst(pattern, replacement);
    }

    /**
     * Converts string to snake case.
     *
     * <pre>{@code
     *      String result = StringUtils.snakeCase("HelloWorld Example");
     *      System.out.println(result);
     *      // Output: "hello_world_example"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the snake cased string.
     */
    public static String snakeCase(String str) {
        if (!isValidString(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char[] characters = str.toCharArray();
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
     * Splits string by separator.
     *
     * <pre>{@code
     *      List<String> result = StringUtils.split("apple,banana,orange", ",");
     *      System.out.println(result);
     *      // Output: ["apple", "banana", "orange"]
     * }</pre>
     *
     * @param str    The string to split.
     * @param separator The separator pattern to split by.
     * @return Returns the string segments.
     */
    public static List<String> split(String str, String separator) {
        List<String> result = new ArrayList<>();
        if (!isValidString(str)) {
            return result;
        }
        if (separator == null) {
            result.add(str);
        } else {
            String[] segments = str.split(Pattern.quote(separator));
            Collections.addAll(result, segments);
        }
        return result;
    }

    /**
     * Converts string to start case.
     *
     * <pre>{@code
     *      String result = StringUtils.startCase("hello world!");
     *      System.out.println(result);
     *      // Output: "Hello World"
     * }</pre>
     *
     * @param string TThe string to convert.
     * @return Returns the start cased string.
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
     * Checks if string starts with the given target string.
     *
     * <pre>{@code
     *      boolean result = StringUtils.startsWith("hello world", "hello");
     *      System.out.println(result);
     *      // Output: true
     * }</pre>
     *
     * @param str The string to inspect.
     * @param target The string to search for.
     * @return Returns {@code true} if string starts with target, else {@code false}.
     */

    public static boolean startsWith(String str, String target) {
        if (str == null || target == null) {
            return false;
        }
        return str.startsWith(target);
    }

    /**
     * Converts string, as a whole, to lower case.
     *
     * <pre>{@code
     *      String lowerValue = StringUtils.toLower("HELLO");
     *      System.out.println(lowerValue);
     *      // Output: hello
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the lower cased string.
     */

    public static String toLower(String str) {
        if (!isValidString(str)) {
            return "";
        }
        return str.toLowerCase();
    }

    /**
     * Converts string, as a whole, to upper case.
     *
     * <pre>{@code
     *      String upperValue = StringUtils.toUpper("hello");
     *      System.out.println(upperValue);
     *      // Output: HELLO
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the upper cased string.
     */

    public static String toUpper(String str) {
        if (!isValidString(str)) {
            return "";
        }
        return str.toUpperCase();
    }

    /**
     * Removes leading and trailing whitespace or specified characters from string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trim("  hello world  ", " ");
     *      System.out.println(trimmed);
     *      // Output: "hello world"
     * }</pre>
     *
     * @param str The string to trim.
     * @param chars  The characters to trim.
     * @return Returns the trimmed string.
     */

    public static String trim(String str, String chars) {
        if (str == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return str.trim();
        }
        char[] strSymbols = str.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int start = charsStartIndex(strSymbols, chrSymbols);
        int end = charsEndIndex(strSymbols, chrSymbols);
        if (start >= end) {
            return "";
        }
        return new String(Arrays.copyOfRange(strSymbols, start, end));
    }

    /**
     * Removes leading whitespace or specified characters from string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trimStart("  hello world  ", " ");
     *      System.out.println(trimmed);
     *      // Output: "hello world  "
     * }</pre>
     *
     * @param str The string to trim.
     * @param chars  The characters to trim.
     * @return Returns the trimmed string.
     */

    public static String trimStart(String str, String chars) {
        if (str == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return str.replaceFirst(RE_TRIM_START, "");
        }
        char[] strSymbols = str.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int start = charsStartIndex(strSymbols, chrSymbols);
        if (start == strSymbols.length) {
            return "";
        }
        return new String(Arrays.copyOfRange(strSymbols, start, strSymbols.length));
    }

    /**
     * Removes leading whitespace or specified characters from string.
     *
     * <pre>{@code
     *      String trimmed = StringUtils.trimEnd("  hello world  ", " ");
     *      System.out.println(trimmed);
     *      // Output: "  hello world"
     * }</pre>
     *
     * @param str    The string to trim.
     * @param chars  The characters to trim.
     * @return Returns the trimmed string.
     */

    public static String trimEnd(String str, String chars) {
        if (str == null) {
            return "";
        }
        if (!isValidString(chars)) {
            return str.replaceAll(RE_TRIM_END, "");
        }
        char[] strSymbols = str.toCharArray();
        char[] chrSymbols = chars.toCharArray();
        int end = charsEndIndex(strSymbols, chrSymbols);
        return new String(Arrays.copyOfRange(strSymbols, 0, end));
    }

    /**
     * Converts string, as space separated words, to upper case.
     *
     * <pre>{@code
     *      String transformed = StringUtils.upperCase("hello world 123");
     *      System.out.println(transformed);
     *      // Output: "HELLO WORLD 123"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the upper cased string.
     */

    public static String upperCase(String str) {
        if (!isValidString(str)) {
            return "";
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(str);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String word = matcher.group();
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(word.toUpperCase());
        }
        return result.toString();
    }

    /**
     * Converts the first character of string to upper case.
     *
     * <pre>{@code
     *      String transformed = StringUtils.upperFirst("hello");
     *      System.out.println(transformed);
     *      // Output: "Hello"
     * }</pre>
     *
     * @param str The string to convert.
     * @return Returns the converted string.
     */

    public static String upperFirst(String str) {
        if (!isValidString(str)) {
            return "";
        }
        char firstChar = Character.toUpperCase(str.charAt(0));
        return firstChar + str.substring(1);
    }
}
