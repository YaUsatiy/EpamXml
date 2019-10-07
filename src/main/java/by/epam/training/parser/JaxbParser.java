package by.epam.training.parser;

import by.epam.training.entity.Bank;
import by.epam.training.entity.Bank.Deposit;
import by.epam.training.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements Parser {
    private static Logger log = LogManager.getLogger(JaxbParser.class);
    private static JaxbParser instance = new JaxbParser();
    private static final String XSD_PATH = "src/main/resources/data/deposits.xsd";

    private JaxbParser() {}

    public static JaxbParser getInstance() {
        return instance;
    }

    @Override
    public List<Deposit> parse(String fileName) throws ParserException {
        List<Deposit> deposits = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.Deposit.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory =SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(XSD_PATH));
            jaxbUnmarshaller.setSchema(schema);
            //Collection<Deposit> deposits1 = (Collection<Deposit>) jaxbUnmarshaller.unmarshal(new File(fileName));
            deposits = (List<Deposit>) jaxbUnmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException | SAXException e) {
            log.error("JAXB parser problem!");
            throw new ParserException(e.getMessage(), e);
        }
        log.info("JAXB parsed successfully");
        return deposits;
    }
}
