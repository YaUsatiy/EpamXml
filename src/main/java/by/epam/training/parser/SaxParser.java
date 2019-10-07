package by.epam.training.parser;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.exception.ParserException;
import by.epam.training.handler.DepositHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser{
    private static Logger log = LogManager.getLogger(SaxParser.class);
    private static SaxParser instance = new SaxParser();

    private SaxParser() {}

    public static SaxParser getInstance() {
        return instance;
    }

    public List<Deposit> parse(String fileName) throws ParserException {
        return buildListDeposits(fileName);
    }

    private List<Deposit> buildListDeposits(String fileName) throws ParserException {
        DepositHandler depositHandler = new DepositHandler();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(depositHandler);
            reader.parse(fileName);
        } catch (IOException e) {
            log.error("SAX : Wrong filepath!");
            throw new ParserException("SAX : Wrong filepath!", e);
        } catch (SAXException | ParserConfigurationException e) {
            log.error("SAX Parsing failure!");
            throw new ParserException("SAX : Parsing failure!", e);
        }
        log.info("SAX : parsed successfully");
        return depositHandler.getDeposits();
    }
}
