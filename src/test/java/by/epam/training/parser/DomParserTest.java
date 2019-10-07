package by.epam.training.parser;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.entity.DepositType;
import by.epam.training.exception.ParserException;
import by.epam.training.factory.DepositFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DomParserTest {
    private static final String XML_PATH = "data/deposits.xml";
    private static final String INVALID_PATH = "data/invalidFile.xml";
    private static Parser parser;

    @BeforeClass
    public void setUp() {
        DepositFactory depositFactory = new DepositFactory();
        parser = depositFactory.createDeposit("dom");
    }

    @Test
    public void domParserPositiveTest() throws ParserException {
        List<Deposit> deposits = parser.parse(XML_PATH);
        Deposit deposit = deposits.get(0);
        assertEquals(deposit.getBankName(), "NationalBankRepublicOfBelarus");
        assertEquals(deposit.getRegistrationCountry(), "Belarus");
        assertEquals(deposit.getDepositType(), DepositType.DEMAND);
        assertEquals(deposit.getDepositor(), "Depositor-1");
        assertEquals(deposit.getAccountId(), 1);
        assertEquals(deposit.getAmountOfDeposit(), 2500.0);
        assertEquals(deposit.getProfitability(), 11.0);
        assertEquals(deposit.getTimeConstraints(), "2020-01-01");
        assertEquals(deposit.getDepositId(), "dep-1");
    }

    @Test(expectedExceptions = ParserException.class)
    public void domParserNegativeTest() throws ParserException {
        parser.parse(INVALID_PATH);
    }
}