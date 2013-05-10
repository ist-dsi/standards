package pt.ist.standards.geographic;

public class District extends Place<Country, Municipality> {

    private static final long serialVersionUID = 1L;

    public District(final Country country, final String name, final String code) {
        super(country, name, code);
    }

}
