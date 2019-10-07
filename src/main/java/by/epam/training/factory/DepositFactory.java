package by.epam.training.factory;

import by.epam.training.parser.*;

public class DepositFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM,
        JAXB
    }
    public Parser createDeposit(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM  : return DomParser.getInstance();
            case STAX : return StaxParser.getInstance();
            case SAX  : return SaxParser.getInstance();
            case JAXB : return JaxbParser.getInstance();
            default   : throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
