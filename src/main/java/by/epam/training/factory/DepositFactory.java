package by.epam.training.factory;

import by.epam.training.parser.DomParser;
import by.epam.training.parser.Parser;
import by.epam.training.parser.SaxParser;
import by.epam.training.parser.StaxParser;

public class DepositFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }
    public Parser createDeposit(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM  : return DomParser.getInstance();
            case STAX : return StaxParser.getInstance();
            case SAX  : return SaxParser.getInstance();
            default   : throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
