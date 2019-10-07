package by.epam.training.entity;

import javax.xml.bind.annotation.XmlEnumValue;

public enum  DepositType {
    @XmlEnumValue("demand")
    DEMAND("demand"),

    @XmlEnumValue("fixed")
    FIXED("fixed"),

    @XmlEnumValue("estimated")
    ESTIMATED("estimated"),

    @XmlEnumValue("accumulation")
    ACCUMULATION("accumulation"),

    @XmlEnumValue("savings")
    SAVINGS("savings");

    private String value;

    DepositType(String val) {
        value = val;
    }

    public static DepositType getFromValue(String val) {
        for (DepositType deposit : DepositType.values()) {
            if (deposit.value.equals(val)) {
                return deposit;
            }
        }
        throw new IllegalArgumentException(val);
    }
}
