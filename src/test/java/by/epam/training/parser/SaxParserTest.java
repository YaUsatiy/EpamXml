package by.epam.training.parser;

import by.epam.training.entity.Bank;
import by.epam.training.entity.DepositType;
import by.epam.training.exception.ParserException;
import by.epam.training.factory.DepositFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SaxParserTest {
    private static final String XML_PATH = "src/test/resources/data/deposits.xml";
    private static final String INVALID_PATH = "data/invalidFile.xml";
    private static Parser parser;

    @BeforeClass
    public void setUp() {
        DepositFactory depositFactory = new DepositFactory();
        parser = depositFactory.createDeposit("sax");
    }

    @Test
    public void saxParserPositiveTest() throws ParserException {
        List<Bank.Deposit> deposits = parser.parse(XML_PATH);
        Bank.Deposit deposit = deposits.get(1);
        assertEquals(deposit.getBankName(), "BelarusBank");
        assertEquals(deposit.getRegistrationCountry(), "Belarus");
        assertEquals(deposit.getDepositType(), DepositType.METAL);
        assertEquals(deposit.getDepositor(), "Depositor-2");
        assertEquals(deposit.getDepositorStatus(), "regular");
        assertEquals(deposit.getAccountId(), 2);
        assertEquals(deposit.getAmountOfDeposit(), 1000.0);
        assertEquals(deposit.getProfitability(), 12.1);
        assertEquals(deposit.getTimeConstraints(), "2021-05-01");
        assertEquals(deposit.getDepositId(), "dep-2");
    }

    @Test(expectedExceptions = ParserException.class)
    public void saxParserNegativeTest() throws ParserException {
        parser.parse(INVALID_PATH);
    }
}