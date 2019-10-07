package by.epam.training.parser;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.entity.DepositType;
import by.epam.training.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    private static Logger log = LogManager.getLogger(DomParser.class);
    private static DomParser instance = new DomParser();

    private DomParser() {}

    public static DomParser getInstance() {
        return instance;
    }

    public List<Deposit> parse(String fileName) throws ParserException {
        List<Deposit> deposits = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
            if (stream != null) {
                doc = dBuilder.parse(stream);
            }
            else {
                log.error("DOM : Path to file can't be null!");
                throw new ParserException("DOM : Path to file can't be null!");
            }
        } catch (ParserConfigurationException e) {
            log.error("DOM : Parser configuration problem!");
            throw new ParserException("DOM : Parser configuration problem!", e);
        } catch (SAXException e) {
            log.error("DOM : parser problem!");
            throw new ParserException("DOM : parser problem!", e);
        } catch (IOException e) {
            log.error("DOM : io problems!");
            throw new ParserException("DOM : io problems!", e);
        }
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("deposit");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                deposits.add(parseSingleDeposit((Element) nNode));
            }
        }
        log.info("DOM : parsed successfully");
        return deposits;
    }

    public Deposit parseSingleDeposit(Element element) {
        Deposit result = new Deposit();
        String depositId = element.getAttribute("deposit_id");
        result.setDepositId(depositId);
        String bankName = String.valueOf(element.getElementsByTagName("bank_name").item(0).getTextContent());
        result.setBankName(bankName);
        String registrationCountry = String.valueOf(element.getElementsByTagName("registration_country").item(0).getTextContent());
        result.setRegistrationCountry(registrationCountry);
        DepositType depositType = DepositType.valueOf(element.getElementsByTagName("deposit_type").item(0).getTextContent().toUpperCase());
        result.setDepositType(depositType);
        String depositor = String.valueOf(element.getElementsByTagName("depositor").item(0).getTextContent());
        result.setDepositor(depositor);
        Integer accountId = Integer.parseInt(element.getElementsByTagName("account_id").item(0).getTextContent());
        result.setAccountId(accountId);
        Double amountOfDeposit = Double.valueOf(element.getElementsByTagName("amount_of_deposit").item(0).getTextContent());
        result.setAmountOfDeposit(amountOfDeposit);
        Double profitability = Double.valueOf(element.getElementsByTagName("profitability").item(0).getTextContent());
        result.setProfitability(profitability);
        String timeConstraints = String.valueOf(element.getElementsByTagName("time_constraints").item(0).getTextContent());
        result.setTimeConstraints(timeConstraints);
        return result;
    }
}
