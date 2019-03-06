package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Accounts extends User{
    private ArrayList<User> users = new ArrayList<User>(0);

    /* Hash map created for log ins with email and password, without going through all users array list. */
    private HashMap<String, String> passwords = new HashMap<String, String>();

    public Accounts() { }

    public boolean loadDatabaseUsers() {
        return true;
    }

    public Integer numberOfUsers() {
        return users.size();
    }

    public Integer generateUserId(){
        return users.size() + 1;
    }

    public boolean isUsedEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }

    public boolean addUser(User newUser) {
        users.add(newUser);
        passwords.put(newUser.getEmail(), newUser.getPassword());
        return true;
    }

    public User userLogin(String email, String pass) {
        System.out.println("Email: " + email);
        System.out.println("Password: " + pass);

        String validPassword = passwords.get(email);
        if ((validPassword != null) && validPassword.matches(pass)) {
            for (User user : users) {
                if (user.getEmail().matches(email)) {
                    return user;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public void printCustomers(){
        for (User user : users){
            System.out.println(user);
            /*if (user instanceof Customer){
                System.out.println(user.getFirstName() + " " + user.getLastName());
            }*/
        }
    }

}
