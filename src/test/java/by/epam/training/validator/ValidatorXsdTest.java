package by.epam.training.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValidatorXsdTest {
    private static final String VALID_PATH = "data/deposits.xml";
    private static final String INVALID_PATH = "data/invalidFile.xml";
    private static final String XSD_PATH = "src/test/resources/data/deposits.xsd";
    private ValidatorXsd validatorXsd;

    @BeforeClass
    public void setUp() {
        validatorXsd = new ValidatorXsd();
    }

    @Test
    public void xsdValidatorPositiveTest() {
        boolean actualValue = validatorXsd.isValidXsd(XSD_PATH, VALID_PATH);
        assertTrue(actualValue);
    }

    @Test
    public void xsdValidatorNegativeTest() {
        boolean actualValue = validatorXsd.isValidXsd(XSD_PATH, INVALID_PATH);
        assertFalse(actualValue);
    }
}