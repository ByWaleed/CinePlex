package main;

import java.util.Date;

public class Customer extends User{

    public Customer(Integer id, String firstName, String lastName, String email, String password, Date dateOfBirth) {
        super(id, firstName, lastName, email, password, dateOfBirth, "Customer");
    }

}