package pt.ist.standards.geographic;

public class Parish {

    private static final long serialVersionUID = 1L;

    public final Municipality municipality;
    public final String name;
    public final String code;

    public Parish(final Municipality municipality, final String name, final String code) {
        this.municipality = municipality;
        this.name = name;
        this.code = code;
        municipality.addParish(this);
    }

    private static final char PLACE_SEPERATOR = ';';

    public String exportAsString() {
        final StringBuilder result = new StringBuilder();
        if (municipality != null) {
            municipality.exportAsString(result);
        }
        result.append(PLACE_SEPERATOR);
        result.append(code);
        return result.toString();
    }

    public static Parish importParishFromString(final String string) {
        final int i = string.lastIndexOf(PLACE_SEPERATOR);
        final String municipalityKey = string.substring(0, i);
        final Municipality municipality = (Municipality) Planet.importPlaceFromString(municipalityKey);
        final String parishKey = string.substring(i + 1);
        return municipality.getParish(parishKey);
    }

}
