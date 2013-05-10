package pt.ist.standards.geographic;


public class Planet extends Place<Place<?, Planet>, Country> {

    private static final long serialVersionUID = 1L;

    private static Planet earth = DataLoader.init();

    public static Planet getEarth() {
        return earth;
    }

    Planet(final String name) {
        super(null, name);
    }

    public Country getByAlfa2(final String alfa2) {
        return getPlace(alfa2);
    }

    public Country getByAlfa3(final String alfa3) {
        return getPlace(alfa3);
    }

    public Country getByNumber(final String number) {
        return getPlace(number);
    }

    @Override
    void exportAsString(final StringBuilder result) {
        // do nothing ...
    }

    public static Place importPlaceFromString(final String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        final Planet earth = Planet.getEarth();
        return earth.importFrom(string);
    }

    public static Parish importParishFromString(final String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        return Parish.importParishFromString(string);
    }

}
