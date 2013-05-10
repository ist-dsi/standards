package pt.ist.standards.geographic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Municipality extends Place<District, Locality> {

    private static final long serialVersionUID = 1L;

    private final Map<String, Parish> parishes = new HashMap<String, Parish>();

    public Municipality(final District district, final String name, final String code) {
        super(district, name, code);
    }

    void addParish(final Parish parish) {
        parishes.put(parish.code, parish);
    }

    public Parish getParish(final String key) {
        return parishes.get(key);
    }

    public Set<Parish> getParishes() {
        final Collection<Parish> values = parishes.values();
        return new HashSet<Parish>(values);
    }

}
