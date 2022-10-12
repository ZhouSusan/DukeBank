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

    public static void printUserMenu(User currUser, Scanner scan) {
        currUser.printAccountsSummary();
        int userChoice;

        do {
            System.out.printf("Welcome %s, what would you like to do?\n", currUser.getFullName());
            System.out.println("    1- Show account transaction history");
            System.out.println("    2- Deposit");
            System.out.println("    3- Withdraw");
            System.out.println("    4- Transfer");
            System.out.println("    5- Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            userChoice = scan.nextInt();

            if(userChoice <1 || userChoice > 5) {
                System.out.println("Invalid choice. Please choose a number between 1 - 5");
            }
        } while (userChoice <1 || userChoice > 5);
    }

    public static void showTransactionHistory(User currUser, Scanner scan) {
        int thisAccount;

        do {
            System.out.printf("Enter the number (1-%d) of the account whose transactions you want to see: ", currUser.numAccounts());
            //Since we start at a zero based index, subtract one to whatever the user is enters
            thisAccount = scan.nextInt() - 1;
            if (thisAccount < 0 || thisAccount >= currUser.numAccounts()) {
                System.out.println("Invalid account.Please try again.");
            }
        } while (thisAccount < 0 || thisAccount >= currUser.numAccounts());

        currUser.printAccountTransactionHistory(thisAccount);
    }
}