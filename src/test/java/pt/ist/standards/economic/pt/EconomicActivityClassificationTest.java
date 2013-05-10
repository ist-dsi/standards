package pt.ist.standards.economic.pt;

import junit.framework.Assert;

import org.junit.Test;


public class EconomicActivityClassificationTest {

    @Test
    public void testClassificationsAreLoaded() {
        Assert.assertTrue(EconomicActivityClassificationGroup.getCassificationGroups().size() > 0);
    }

}
