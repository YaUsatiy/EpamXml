package by.epam.training.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "deposits")
@XmlAccessorType(XmlAccessType.FIELD)
public class Deposits
{
    @XmlElement(name = "deposit", namespace = "http://www.training.by/bank", type = JAXBElement.class)
    private List<Bank.Deposit> deposits = null;

    public Deposits() {
        super();
    }

    public List<Bank.Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Bank.Deposit> deposits) {
        this.deposits = deposits;
    }
}