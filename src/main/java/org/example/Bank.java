package org.example;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank() {
        this.name = "Duke Bank";
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID() {
        String uuid;
        Random rand = new Random();
        int len = 8;
        boolean isUnique;

        //keep looping until we get a unique Id
        do {
            uuid = "";
            for (int r = 0; r < len; r++) {
                uuid += ((Integer)rand.nextInt(10)).toString();
            }

            //check to see if the user's id is unique
            isUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    isUnique = true;
                    break;
                }
            }
        } while(isUnique);
        
        return uuid;
    }

    public String getNewAccountUUID() {
        String accountUUID;
        Random rand = new Random();
        int len = 12;
        boolean isIdUnique;

        //keep looping until we get a unique Id
        do {
            accountUUID = "";
            for (int i = 0; i < len; i++) {
                accountUUID += ((Integer)rand.nextInt(10)).toString();
            }

            //check to see if the user's id is unique
            isIdUnique = false;
            for (Account a : this.accounts) {
                if (accountUUID.compareTo(a.getUUID()) == 0) {
                    isIdUnique = true;
                    break;
                }
            }
        } while(isIdUnique);

        return accountUUID;
    }

    public User addUser(String firstName, String lastName, String pin, String dateOfBirth, String socialSecurityNumber) {
        User newUser = new User(firstName, lastName, pin, dateOfBirth, socialSecurityNumber, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        //ToDo create addAccount()git addd
        this.addAccount(newAccount);

        return newUser;
    }
}
