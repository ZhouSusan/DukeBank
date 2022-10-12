package org.example;
import java.util.ArrayList;

public class Account {
    private String name;
    //account id
    private String uuid;
    private User owner;
    private ArrayList<Transaction> transactions;

    public Account(String name, User owner, Bank dukeBank) {
        this.name = name;
        this.owner = owner;
        this.uuid = dukeBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return this.uuid;
    }

    private double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }
    public String getSummaryLine() {
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s: $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s: $%(.02f) : %s", this.uuid, balance, this.name);
        }
    }

}
