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
}
