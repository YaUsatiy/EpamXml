package by.epam.training.parser;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.entity.DepositType;
import by.epam.training.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements Parser{
    private static Logger log = LogManager.getLogger(StaxParser.class);
    private static StaxParser instance = new StaxParser();
    private String depositId;
    private String bankName;
    private String registrationCountry;
    private DepositType depositType;
    private String depositor;
    private String depositorStatus;
    private Integer accountId;
    private Double amountOfDeposit;
    private Double profitability;
    private String timeConstraints;

    private StaxParser() {}

    public static StaxParser getInstance() {
        return instance;
    }

    @Override
    public List<Deposit> parse(String fileName) throws ParserException {
        List<Deposit> deposits = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader =
                    xmlInputFactory.createXMLEventReader(ClassLoader.getSystemResourceAsStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "deposit":
                            Attribute idAttr = startElement.getAttributeByName(new QName("deposit_id"));
                            if (idAttr != null) {
                                depositId = idAttr.getValue();
                            }
                            break;
                        case "bank_name":
                            xmlEvent = xmlEventReader.nextEvent();
                            bankName = String.valueOf(xmlEvent.asCharacters().getData());
                            break;
                        case "registration_country":
                            xmlEvent = xmlEventReader.nextEvent();
                            registrationCountry = String.valueOf(xmlEvent.asCharacters().getData());
                            break;
                        case "deposit_type":
                            xmlEvent = xmlEventReader.nextEvent();
                            depositType = DepositType.valueOf(xmlEvent.asCharacters().getData().toUpperCase());
                            break;
                        case "depositor":
                            xmlEvent = xmlEventReader.nextEvent();
                            depositor = String.valueOf(xmlEvent.asCharacters().getData());
                            Attribute statusAttribute = startElement.getAttributeByName(new QName("depositor_status"));
                            if (statusAttribute != null) {
                                depositorStatus = statusAttribute.getValue();
                            }
                            break;
                        case "account_id":
                            xmlEvent = xmlEventReader.nextEvent();
                            accountId = Integer.parseInt(xmlEvent.asCharacters().getData());
                            break;
                        case "amount_of_deposit":
                            xmlEvent = xmlEventReader.nextEvent();
                            amountOfDeposit = Double.valueOf(xmlEvent.asCharacters().getData());
                            break;
                        case "profitability":
                            xmlEvent = xmlEventReader.nextEvent();
                            profitability = Double.valueOf(xmlEvent.asCharacters().getData());
                            break;
                        case "time_constraints":
                            xmlEvent = xmlEventReader.nextEvent();
                            timeConstraints = String.valueOf(xmlEvent.asCharacters().getData());
                            break;
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("deposit")) {
                        Deposit deposit = new Deposit();
                        deposit.setDepositId(depositId);
                        deposit.setBankName(bankName);
                        deposit.setRegistrationCountry(registrationCountry);
                        deposit.setDepositType(depositType);
                        deposit.setDepositor(depositor);
                        deposit.setDepositorStatus(depositorStatus);
                        deposit.setAccountId(accountId);
                        deposit.setAmountOfDeposit(amountOfDeposit);
                        deposit.setProfitability(profitability);
                        deposit.setTimeConstraints(timeConstraints);
                        deposits.add(deposit);
                    }
                }
            }
        } catch (XMLStreamException e) {
            log.error("Stax : stream problem!");
            throw new ParserException("Stax : stream problem!", e);
        }
        log.info("StAX : parsed successfully");
        return deposits;
    }
}
