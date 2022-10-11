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
}
