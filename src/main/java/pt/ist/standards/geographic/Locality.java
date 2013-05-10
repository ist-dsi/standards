package pt.ist.standards.geographic;

public class Locality extends Place<Municipality, PostalCode> {

    private static final long serialVersionUID = 1L;

    public Locality(final Municipality municipality, final String name, final String code) {
        super(municipality, name, code);
    }

}
