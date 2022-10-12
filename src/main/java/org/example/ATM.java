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
            ATM.printUserMenu(currentUser,scan);
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

        switch(userChoice) {
            case 1:
                ATM.showTransactionHistory(currUser, scan);
                break;
            case 2:
                ATM.withdrawFunds(currUser, scan);
                break;
            case 3:
                ATM.depositFunds(currUser, scan);
                break;
            case 4:
                ATM.transferFunds(currUser, scan);
                break;
            case 5:
                scan.nextLine();
                break;
        }

        //redisplay this menu unless the user wants to quit
        if (userChoice != 5) {
            ATM.printUserMenu(currUser, scan);
        }
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

    public static void depositFunds(User currUser, Scanner scan) {
        int toAccount;
        double amount;
        double accountBalance;
        String memo;

        do {
            System.out.printf("Enter the number (1-d%) of the accounts\n to deposit in: ", currUser.numAccounts());
            toAccount = scan.nextInt();
            if (toAccount < 0 || toAccount >= currUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while(toAccount < 0 || toAccount >= currUser.numAccounts());

        accountBalance = currUser.getAccountBalance(toAccount);

        do {
            System.out.printf("Enter the amount to deposit in (maximum $%.02f)", accountBalance);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Something went wrong. Amount must be greater than zero.");
            }
        } while (amount < 0);

        scan.nextLine();
        System.out.print("Enter a memo for this transaction: ");
        memo = scan.nextLine();

        currUser.addAccountTransaction(toAccount, amount, memo);
    }

    public static void withdrawFunds(User currUser, Scanner scan) {
        int fromAccount;
        double amount;
        double accountBalance;
        String memo;

        do {
            System.out.printf("Enter the number (1-d%) of the accounts\n to deposit in: ", currUser.numAccounts());
            fromAccount = scan.nextInt();
            if (fromAccount < 0 || fromAccount >= currUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while(fromAccount < 0 || fromAccount >= currUser.numAccounts());

        accountBalance = currUser.getAccountBalance(fromAccount);

        do {
            System.out.printf("Enter the amount to withdraw from: (maximum $%.02f)", accountBalance);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Something went wrong. Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n " +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0);

        scan.nextLine();
        System.out.print("Enter a memo for this transaction: ");
        memo = scan.nextLine();

        currUser.addAccountTransaction(fromAccount, -1*amount, memo);
    }

    public static void transferFunds(User currUser, Scanner scan) {
        int fromAccount;
        int toAccount;
        double amount;
        double accountBal;

        //get account to transfer from
        do {
            System.out.printf("Enter the number (1- %d) of the account\n to transfer from :", currUser.numAccounts());
            fromAccount = scan.nextInt()-1;
            if (fromAccount < 0 || fromAccount >= currUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while(fromAccount < 0 || fromAccount >= currUser.numAccounts());

        accountBal = currUser.getAccountBalance(fromAccount);

        //get the account to transfer to
        do {
            System.out.printf("Enter the number (1- %d) of the account\n to transfer to :", currUser.numAccounts());
            toAccount = scan.nextInt()-1;
            if (toAccount < 0 || toAccount >= currUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAccount < 0 || toAccount >= currUser.numAccounts());

        //get the amount transfer
        do {
            System.out.printf("Enter the amount to transfer " +
                    "(maximum $%.02f): $", accountBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBal) {
                System.out.printf("Amount must not be greater than\n " +
                        "balance of $%.02f.\n", accountBal);
            }
        } while (amount < 0 || amount > accountBal);

        //transfer
        currUser.addAccountTransaction(fromAccount, -1*amount, String.format(
                "Transfer to account %s", currUser.getAccountUUID(toAccount)));
        currUser.addAccountTransaction(toAccount, amount, String.format(
                "Transferred from account %s", currUser.getAccountUUID(fromAccount)));
    }
}