package by.epam.training.parser;

import by.epam.training.entity.Bank.Deposit;
import by.epam.training.exception.ParserException;

import java.util.List;

public interface Parser {
    List<Deposit> parse(String fileName) throws ParserException;
}
