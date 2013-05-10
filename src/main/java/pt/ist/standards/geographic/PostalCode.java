package pt.ist.standards.geographic;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class PostalCode extends Place<Locality, Place<PostalCode, ?>> {

    private static final long serialVersionUID = 1L;

    final SortedSet<String> streets = new TreeSet<String>();

    public PostalCode(final Locality locality, final String name, final String code) {
        super(locality, name, code);
        locality.parent.parent.parent.postalCodeMap.put(code, this);
    }

    public SortedSet<String> getStreets() {
        return Collections.unmodifiableSortedSet(streets);
    }

}
