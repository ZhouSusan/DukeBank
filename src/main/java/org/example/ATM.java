package org.example;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank dukeBank = new Bank();

        //test user
        User aUser = dukeBank.addUser("Steven", "Riggs", "1234", "10/31/2000", "1234567890");
    }
}