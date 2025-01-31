package by.epam.training.validator;

import by.epam.training.handler.DepositErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

class ValidatorXsd {
    private static final Logger log = LogManager.getLogger(ValidatorXsd.class);
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    boolean isValidXsd(String xsdPath, String xmlPath) {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        try {
            File schemaLocation = new File(ClassLoader.getSystemResource(xsdPath).toURI());
            // Schema creation
            Schema schema = factory.newSchema(schemaLocation);
            // create validator on base of schema
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new DepositErrorHandler());
            // check is document correct
            Source source = new StreamSource(ClassLoader.getSystemResourceAsStream(xmlPath));
            validator.validate(source);
            log.info("\"" + xmlPath + "\"" + " is valid.");
        } catch (SAXException | IOException | URISyntaxException e ) {
            log.error("Can't get info from xml/xsd file!");
            return false;
        }
        return true;
    }
}
