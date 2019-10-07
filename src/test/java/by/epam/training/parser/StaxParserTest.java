package by.epam.training.parser;

import by.epam.training.entity.Bank;
import by.epam.training.entity.DepositType;
import by.epam.training.exception.ParserException;
import by.epam.training.factory.DepositFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class StaxParserTest {
    private static final String XML_PATH = "data/deposits.xml";
    private static final String INVALID_PATH = "data/invalidFile.xml";
    private static Parser parser;

    @BeforeClass
    public void setUp() {
        DepositFactory depositFactory = new DepositFactory();
        parser = depositFactory.createDeposit("stax");
    }

    @Test
    public void staxParserPositiveTest() throws ParserException {
        List<Bank.Deposit> deposits = parser.parse(XML_PATH);
        Bank.Deposit deposit = deposits.get(2);
        System.out.println(deposit);
        assertEquals(deposit.getBankName(), "RZB");
        assertEquals(deposit.getRegistrationCountry(), "Austria");
        assertEquals(deposit.getDepositType(), DepositType.SAVINGS);
        assertEquals(deposit.getDepositor(), "Depositor-3");
        assertEquals(deposit.getAccountId(), 3);
        assertEquals(deposit.getAmountOfDeposit(), 5000.0);
        assertEquals(deposit.getProfitability(), 3.4);
        assertEquals(deposit.getTimeConstraints(), "2022-01-01");
        assertEquals(deposit.getDepositId(), "dep-3");
    }

    @Test(expectedExceptions = ParserException.class)
    public void staxParserNegativeTest() throws ParserException {
        parser.parse(INVALID_PATH);
    }
}