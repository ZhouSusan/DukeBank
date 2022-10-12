package org.example;
import java.util.Date;

public class Transaction {
    private Date timestamp;
    private double amount;
    private String memo;
    private Account thisAccount;

    public Transaction (double amount, Account thisAccount) {
        this.amount = amount;
        this.memo = "";
        this.thisAccount = thisAccount;
        this.timestamp = new Date();
    }

    public Transaction(double amount, Account thisAccount, String memo) {
        this(amount, thisAccount);
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s",
                    this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s",
                    this.timestamp.toString(), -this.amount, this.memo);
        }
    }
}
