package by.epam.training.handler;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.entity.DepositType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DepositHandler extends DefaultHandler {
    private String depositId;
    private String bankName;
    private String registrationCountry;
    private DepositType depositType;
    private String depositor;
    private Integer accountId;
    private Double amountOfDeposit;
    private Double profitability;
    private String timeConstraints;
    private List<Deposit> deposits;
    private String currentElement;

    public DepositHandler() {
        deposits = new ArrayList<>();
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        currentElement = qName;
        if (qName.equals("deposit")){
            depositId = atts.getValue("deposit_id");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentElement.equals("bank_name")) {
            bankName = new String(ch, start, length);
        }
        if (currentElement.equals("registration_country")) {
            registrationCountry = new String(ch, start, length);
        }
        if (currentElement.equals("deposit_type")) {
            depositType = DepositType.valueOf(new String(ch, start, length).toUpperCase());
        }
        if (currentElement.equals("depositor")) {
            depositor = new String(ch, start, length);
        }
        if (currentElement.equals("account_id")) {
            accountId = Integer.parseInt(new String(ch, start, length));
        }
        if (currentElement.equals("amount_of_deposit")) {
            amountOfDeposit = Double.valueOf(new String(ch, start, length));
        }
        if (currentElement.equals("profitability")) {
            profitability = Double.valueOf(new String(ch, start, length));
        }
        if (currentElement.equals("time_constraints")) {
            timeConstraints = new String(ch, start, length);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        currentElement = "";
        if (qName.equals("deposit")) {
            Deposit deposit = new Deposit();
            deposit.setDepositId(depositId);
            deposit.setBankName(bankName);
            deposit.setRegistrationCountry(registrationCountry);
            deposit.setDepositType(depositType);
            deposit.setDepositor(depositor);
            deposit.setAccountId(accountId);
            deposit.setAmountOfDeposit(amountOfDeposit);
            deposit.setProfitability(profitability);
            deposit.setTimeConstraints(timeConstraints);
            deposits.add(deposit);
        }
    }
}
