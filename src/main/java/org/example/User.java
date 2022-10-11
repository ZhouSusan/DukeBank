package org.example;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    private String uuid;
    private String firstName;
    private String lastName;
    private byte pinEncypt[];
    private String dateOfBirth;
    private ArrayList<Account> accounts;
}
