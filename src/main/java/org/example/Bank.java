package org.example;
import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank() {
        this.name = "Duke Bank";
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
}
