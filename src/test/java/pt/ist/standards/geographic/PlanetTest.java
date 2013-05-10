package pt.ist.standards.geographic;

import junit.framework.Assert;

import org.junit.Test;

public class PlanetTest {

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

}
