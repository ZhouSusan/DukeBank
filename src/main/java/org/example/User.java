package org.example;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    private String uuid;
    private String firstName;
    private String lastName;
    private byte pinEncypt[];
    private String dateOfBirth;
    private String socialSecurityNumber;
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pinNumber, String dateOfBirth, String socialSecurityNumber, Bank dukeBank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinEncypt = md.digest(pinNumber.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        this.uuid = dukeBank.getNewUserUUID();

        this.accounts = new ArrayList<Account>();

        System.out.printf("A new user has been created successfully.\n%s, %s with ID# %s", lastName, firstName, this.uuid);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePinNumber (String pinNumber) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pinNumber.getBytes()), this.pinEncypt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


    public void printAccountsSummary() {
        System.out.printf("\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAccountTransactionHistory(int accountIndex) {
        this.accounts.get(accountIndex).printTransactionHistory();
    }

    public double getAccountBalance(int accountIndex) {
        return this.accounts.get(accountIndex).getBalance();
    }

    public void addAccountTransaction(int accountIdx, double amount, String memo) {
        this.accounts.get(accountIdx).addTransaction(amount, memo);
    }

    public String getAccountUUID(int accountIdx) {
        return this.accounts.get(accountIdx).getUUID();
    }
}
