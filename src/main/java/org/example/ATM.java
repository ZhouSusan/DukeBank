package org.example;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank dukeBank = new Bank();

        //test user
        User aUser = dukeBank.addUser("Steven", "Riggs", "1234", "10/31/2000", "1234567890");
        Account aNewAccount = new Account("Checking", aUser, dukeBank);
        aUser.addAccount(aNewAccount);
        dukeBank.addAccount(aNewAccount);

        User currentUser;
        while( true) {
            currentUser = ATM.mainMenuPrompt(dukeBank, scan);
        }
    }

    public static User mainMenuPrompt(Bank dukeBank, Scanner scan) {
        String userUUID;
        String pinNumber;
        User authorizedUser;

        do {
            System.out.printf("\nWelcome to %s\n\n", dukeBank.getName());
            System.out.print("Enter userID: ");
            userUUID = scan.nextLine();
            System.out.println("Enter pin: ");
            pinNumber = scan.nextLine();

            authorizedUser = dukeBank.userLogin(userUUID, pinNumber);
            if (authorizedUser == null) {
                System.out.println("Incorrect userID/ pin combination. Please try again!");
            }
        } while (authorizedUser == null);

        return authorizedUser;
    }
}