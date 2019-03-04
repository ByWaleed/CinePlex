package main;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {

    /* TODO  Generate proper exceptions if there are errors. */
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registrationDate;
    private Date dateOfBirth;
    private String accountType;

    public User(){}

    public User(Integer id, String firstName, String lastName, String email, String password, Date dateOfBirth, String accountType) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        updatePassword(password);
        this.registrationDate = new Date();
        setDateOfBirth(dateOfBirth);
        setAccountType(accountType);
    }

    // GETTERS
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    protected String getPassword() {
        return password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAccountType() {
        return this.accountType;
    }

    // SETTERS
    private boolean validateName(String name) {
        if (name.length() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean setFirstName(String firstName) {
        if (validateName(firstName)) {
            this.firstName = firstName;
            return true;
        } else {
            return false;
        }
    }

    public boolean setLastName(String lastName) {
        if (validateName(lastName)) {
            this.lastName = lastName;
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public boolean setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePassword(String password) {
        if (password.length() > 6) {
            this.password = password;
            return true;
        } else {
            return false;
        }
    }

    public boolean setAccountType(String accountType) {
        this.accountType = accountType;
        return true;
    }

    /* TODO Missing operations in class diagram */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
