package pt.ist.standards.telecommunications;

import pt.ist.standards.util.ResourceReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DailingCode {

    private static final String RESOURCE_PATH = "/telecommunications/";

    private static final Map<String, Boolean> allowAlphaMap = new HashMap<>();
    private static final Map<String, String> dailingMap = new HashMap<>();
    private static final Map<String, String> countryMap = new HashMap<>();

    static {
        final String[] content = ResourceReader.readLines(RESOURCE_PATH + "dailingcode.txt");
        for (final String line : content) {
            final String[] ss = line.split("\t");
            final String alpha2 = ss[0];
            final String code = ss[1];
            final Boolean allowAphaSender = Boolean.valueOf(ss[2]);
            allowAlphaMap.put(code, allowAphaSender);
            dailingMap.put(alpha2, code);
            countryMap.put(code, alpha2);
        }
    }

    public static boolean allowAlphaSender(final String dailingCode) {
        final Boolean result = allowAlphaMap.get(dailingCode);
        return result != null && result.booleanValue();
    }

    public static String dailingPrefixFor(final String alpha2) {
        return dailingMap.get(alpha2);
    }

    public static String countryFor(final String dailingCode) {
        return countryMap.get(dailingCode);
    }

    public static Stream<String> allowAlphaSenders() {
        return allowAlphaMap.entrySet().stream()
                .filter(e -> e.getValue().booleanValue())
                .map(e -> e.getKey());
    }

}
