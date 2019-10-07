package by.epam.training.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DepositErrorHandler extends DefaultHandler {
    private static Logger log = LogManager.getLogger(DepositHandler.class);

    public DepositErrorHandler() {
    }

    public void warning(SAXParseException e) {
        log.warn(getLineAddress(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        log.error(getLineAddress(e) + "-" + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        log.fatal(getLineAddress(e) + "-" + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}