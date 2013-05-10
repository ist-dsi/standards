package pt.ist.standards.geographic;

import java.io.Serializable;
import java.util.Arrays;

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    public final Place<?, ?> place;
    private final String[] lines;

    public Address(final Place<?, ?> place, final String[] lines) {
        this.place = place;
        this.lines = Arrays.copyOf(lines, lines.length);
    }

    public String[] lines() {
        return Arrays.copyOf(lines, lines.length);
    }

    private static final char LINE_SEPERATOR = '_';
    private static final char LINE_SIZE_SEPERATOR = '_';

    public String exportAsString() {
        final StringBuilder result = new StringBuilder();
        if (place != null) {
            place.exportAsString(result);
        }
        result.append(LINE_SEPERATOR);
        for (final String line : lines) {
            final int length = line.length();
            result.append(length);
            result.append(LINE_SIZE_SEPERATOR);
            result.append(line);
        }
        return result.toString();
    }

}
