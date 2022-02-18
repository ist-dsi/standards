package pt.ist.standards.geographic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pt.ist.standards.util.ResourceReader;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class PostalCode extends Place<Locality, Place<PostalCode, ?>> {

    private static final long serialVersionUID = 1L;

    public final String code;
    final SortedSet<String> streets = new TreeSet<String>();

    public PostalCode(final Locality locality, final String name, final String code) {
        super(locality, name, code);
        this.code = code;
        locality.parent.parent.parent.postalCodeMap.put(code, this);
    }

    public SortedSet<String> getStreets() {
        return Collections.unmodifiableSortedSet(streets);
    }

    public JsonObject getDetails() {
        final String path = "/geographic/" + parent.parent.parent.parent.alpha3.toLowerCase() + "/postalCode/" + code + ".json";
        try {
            final JsonElement json = ResourceReader.readJson(path);
            return json == null || json.isJsonNull() ? null : json.isJsonObject() ? json.getAsJsonObject()
                    : json.getAsJsonArray().iterator().next().getAsJsonObject();
        } catch (final Throwable t) {
            return null;
        }
    }

}
