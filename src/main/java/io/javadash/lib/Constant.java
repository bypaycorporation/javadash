package io.javadash.lib;

public class Constant {
    public static final String LATIN_1_A = "\u00C0";
    public static final String LATIN_1_A_LOW = "\u00E0";
    public static final String LATIN_1_C = "\u00C7";
    public static final String LATIN_1_C_LOW = "\u00E7";
    public static final String LATIN_1_D = "\u00D0";
    public static final String LATIN_1_D_LOW = "\u00F0";
    public static final String LATIN_1_E = "\u00C8";
    public static final String LATIN_1_E_LOW = "\u00E8";
    public static final String LATIN_1_I = "\u00CC";
    public static final String LATIN_1_I_LOW = "\u00EC";
    public static final String LATIN_1_O = "\u00D2";
    public static final String LATIN_1_O_LOW = "\u00F2";
    public static final String LATIN_1_U = "\u00D9";
    public static final String LATIN_1_U_LOW = "\u00F9";
    public static final String LATIN_1_Y = "\u00DD";
    public static final String LATIN_1_Y_LOW = "\u00FD";
    public static final String LATIN_1_SS = "\u00DF";

    // Latin Extended-A block constants
    public static final String LATIN_EXT_A_A = "\u0100";
    public static final String LATIN_EXT_A_A_LOW = "\u0101";
    public static final String LATIN_EXT_A_C = "\u0106";
    public static final String LATIN_EXT_A_C_LOW = "\u0107";

    public static final String[][] ESCAPE_HTML_CHARS = {
        {"&", "&amp;"},
        {"<", "&lt;"},
        {">", "&gt;"},
        {"\"", "&quot;"},
        {"'", "&#39;"}
    };

    public static final String REGEXP_CHARS = "\\^$.*+?()[\\]{}|";

    public static final String RE_TRIM_START = "^\\s+";
    public static final String RE_TRIM_END = "\\s+$";
}
