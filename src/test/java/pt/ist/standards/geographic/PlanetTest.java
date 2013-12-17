package pt.ist.standards.geographic;

import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class PlanetTest {

    final static Locale PT = new Locale("pt");
    final static Locale EN = new Locale("en");

    @Test
    public void testEarthExists() {
        final Planet earth = Planet.getEarth();
        Assert.assertNotNull(earth);
        Assert.assertTrue(earth.getPlaces().size() > 0);
    }

    @Test
    public void testCountryRead() {
        final Planet earth = Planet.getEarth();

        final Country byAlfa2 = earth.getByAlfa2("PT");
        Assert.assertNotNull(byAlfa2);

        final Country byAlfa3 = earth.getByAlfa3("PRT");
        Assert.assertNotNull(byAlfa3);

        final Country byNumber = earth.getByNumber("620");
        Assert.assertNotNull(byNumber);

        Assert.assertSame(byAlfa2, byAlfa3);
        Assert.assertSame(byAlfa3, byNumber);

        final PostalCode pc1 = byAlfa2.getPostalCode("2775-151");
        Assert.assertTrue(pc1.streets.contains("Avenida Amadeu Duarte"));

        final PostalCode pc2 = byAlfa2.getPostalCode("2720-111");
        Assert.assertTrue(pc2.streets.contains("Avenida Madame Curie"));
    }

    @Test
    public void testDuplicatedCountries() {
        Set<String> alpha2s = new HashSet<String>();
        Set<String> alpha3s = new HashSet<String>();
        Set<String> numbers = new HashSet<String>();
        Set<String> names = new HashSet<String>();

        for (Country country : Planet.getEarth().getPlaces()) {
            Assert.assertFalse("Duplicated Country alpha2 for " + country.alpha2, alpha2s.contains(country.alpha2));
            Assert.assertFalse("Duplicated Country alpha3 for " + country.alpha3, alpha3s.contains(country.alpha3));
            Assert.assertFalse("Duplicated Country number for " + country.number, numbers.contains(country.number));
            Assert.assertFalse("Duplicated Country name for " + country.name, names.contains(country.name));

            alpha2s.add(country.alpha2);
            alpha3s.add(country.alpha3);
            numbers.add(country.number);
            names.add(country.name);
        }
    }

    @Test
    public void testAtLeastOneLocalizedNameResource() {
        for (Country country : Planet.getEarth().getPlaces()) {
            Assert.assertTrue("The country '" + country.alpha3 + "' - '" + country.name
                    + "' must have at least one name resource", hasLocalizedName(country, EN) || hasLocalizedName(country, PT));
        }
    }

    private static boolean hasLocalizedName(Country country, Locale locale) {
        boolean hasLocalizedName;
        try {
            String enName = country.getLocalizedName(locale);
            hasLocalizedName = enName != null && !enName.isEmpty();
        } catch (MissingResourceException e) {
            hasLocalizedName = false;
        }
        return hasLocalizedName;
    }

}
