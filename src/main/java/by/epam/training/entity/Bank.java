package by.epam.training.entity;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "deposit"
})
@XmlRootElement(name = "bank", namespace = "http://www.training.by/bank")
public class Bank {

    @XmlElement(required = true)
    protected Set<Deposit> deposits;

    Bank() {}

    public Set<Deposit> getDeposits() {
        if (deposits == null) {
            deposits = new HashSet<>();
        }
        return this.deposits;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "bankName",
            "registrationCountry",
            "depositType",
            "depositor",
            "accountId",
            "amountOfDeposit",
            "profitability",
            "timeConstraints"
    })
    public static class Deposit {

        @XmlElement(name = "bank_name", required = true)
        protected String bankName;

        @XmlElement(name = "registration_country", required = true)
        protected String registrationCountry;

        @XmlElement(name = "deposit_type", required = true)
        @XmlSchemaType(name = "string")
        protected DepositType depositType;

        @XmlElement(required = true)
        protected String depositor;

        @XmlAttribute(name = "depositor_status", required = true)
        protected String depositorStatus;

        @XmlElement(name = "account_id")
        protected int accountId;

        @XmlElement(name = "amount_of_deposit", required = true)
        protected Double amountOfDeposit;

        @XmlElement(required = true)
        protected Double profitability;

        @XmlElement(name = "time_constraints", required = true)
        @XmlSchemaType(name = "date")
        protected String timeConstraints;

        @XmlAttribute(name = "deposit_id", required = true)
        protected String depositId;

        public Deposit() {}

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getRegistrationCountry() {
            return registrationCountry;
        }

        public void setRegistrationCountry(String registrationCountry) {
            this.registrationCountry = registrationCountry;
        }

        public DepositType getDepositType() {
            return depositType;
        }

        public void setDepositType(DepositType depositType) {
            this.depositType = depositType;
        }

        public String getDepositor() {
            return depositor;
        }

        public void setDepositor(String depositor) {
            this.depositor = depositor;
        }

        public String getDepositorStatus() {
            if (depositorStatus == null || depositorStatus.equals("")) {
                return "regular";
            }
            return depositorStatus;
        }

        public void setDepositorStatus(String depositorStatus) {
            this.depositorStatus = depositorStatus;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public Double getAmountOfDeposit() {
            return amountOfDeposit;
        }

        public void setAmountOfDeposit(Double amountOfDeposit) {
            this.amountOfDeposit = amountOfDeposit;
        }

        public Double getProfitability() {
            return profitability;
        }

        public void setProfitability(Double profitability) {
            this.profitability = profitability;
        }

        public String getTimeConstraints() {
            return timeConstraints;
        }

        public void setTimeConstraints(String timeConstraints) {
            this.timeConstraints = timeConstraints;
        }

        public String getDepositId() {
            return depositId;
        }

        public void setDepositId(String depositId) {
            this.depositId = depositId;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;

            Deposit otherDeposit = (Deposit) other;

            if (accountId != otherDeposit.accountId) {
                return false;
            }
            if (amountOfDeposit != null ? !amountOfDeposit.equals(otherDeposit.amountOfDeposit)
                                        : otherDeposit.amountOfDeposit != null) {
                return false;
            }
            if (bankName != null ? !bankName.equals(otherDeposit.bankName) : otherDeposit.bankName != null) {
                return false;
            }
            if (depositId != null ? !depositId.equals(otherDeposit.depositId) : otherDeposit.depositId != null) {
                return false;
            }
            if (depositType != otherDeposit.depositType) {
                return false;
            }
            if (depositor != null ? !depositor.equals(otherDeposit.depositor) : otherDeposit.depositor != null) {
                return false;
            }
            if (depositorStatus != null ? !depositorStatus.equals(otherDeposit.depositorStatus)
                                        : otherDeposit.depositorStatus != null) {
                return false;
            }
            if (profitability != null ? !profitability.equals(otherDeposit.profitability) : otherDeposit.profitability != null) {
                return false;
            }
            if (registrationCountry != null ? !registrationCountry.equals(otherDeposit.registrationCountry)
                                            : otherDeposit.registrationCountry != null) {
                return false;
            }
            return timeConstraints != null ? timeConstraints.equals(otherDeposit.timeConstraints)
                                           : otherDeposit.timeConstraints == null;
        }

        @Override
        public int hashCode() {
            int result = bankName != null ? bankName.hashCode() : 0;
            result = 31 * result + (registrationCountry != null ? registrationCountry.hashCode() : 0);
            result = 31 * result + (depositType != null ? depositType.hashCode() : 0);
            result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
            result = 31 * result + (depositorStatus != null ? depositorStatus.hashCode() : 0);
            result = 31 * result + accountId;
            result = 31 * result + (amountOfDeposit != null ? amountOfDeposit.hashCode() : 0);
            result = 31 * result + (profitability != null ? profitability.hashCode() : 0);
            result = 31 * result + (timeConstraints != null ? timeConstraints.hashCode() : 0);
            result = 31 * result + (depositId != null ? depositId.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Deposit{");
            sb.append("bankName='").append(bankName).append('\'');
            sb.append(", registrationCountry='").append(registrationCountry).append('\'');
            sb.append(", depositType=").append(depositType);
            sb.append(", depositor='").append(depositor).append('\'');
            sb.append(", depositorStatus='").append(depositorStatus).append('\'');
            sb.append(", accountId=").append(accountId);
            sb.append(", amountOfDeposit=").append(amountOfDeposit);
            sb.append(", profitability=").append(profitability);
            sb.append(", timeConstraints=").append(timeConstraints);
            sb.append(", depositId='").append(depositId).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
