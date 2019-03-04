package main;

import java.util.Date;

public class Admin extends User {

    public Admin(Integer id, String firstName, String lastName, String email, String password, Date dateOfBirth) {
        super(id, firstName, lastName, email, password, dateOfBirth, "Admin");
    }
    
}
