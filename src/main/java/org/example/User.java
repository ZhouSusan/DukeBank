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

        //ToDo list: make this function
        this.uuid = dukeBank.getNewUserUUID();

        this.accounts = new ArrayList<Account>();

        System.out.printf("A new user has been created successfully.\n%s, %s with ID# %s");
    }
}
