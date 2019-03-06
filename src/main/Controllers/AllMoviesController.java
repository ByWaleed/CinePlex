package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.Accounts;
import main.Admin;
import main.Customer;
import main.User;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AllMoviesController implements Initializable {

    Accounts accounts;

    User loggedInUser;

    // Navigation Buttons
    @FXML private Button moviesBtn;
    @FXML private Button snacksBtn;
    @FXML private Button theatresBtn;
    @FXML private Button adminBtn;
    @FXML private Button loginRegisterBtn;

    // Content Panes
    @FXML private Pane moviesPane;
    @FXML private Pane loginRegisterPane;

    // Login Objects
    @FXML private TextField loginEmailTF;
    @FXML private TextField loginPasswordTF;
    @FXML private Text loginErrorTF;

    // Register Objects
    @FXML private TextField registerFirstNameTF;
    @FXML private TextField registerLastNameTF;
    @FXML private TextField registerEmailTF;
    @FXML private PasswordField registerPasswordTF;
    @FXML private PasswordField registerConfirmPasswordTF;
    @FXML private DatePicker registerDateOfBirthPicker;
    @FXML private Text registerErrorTF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = null;
        accounts = new Accounts();

        dummyAccounts();
    }

    private void dummyAccounts() {
        Admin admin = new Admin(accounts.generateUserId());
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("john@email.com");
        admin.updatePassword("superadmin", "superadmin");
        admin.setDateOfBirth(LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("01"), Integer.parseInt("01")));

        Customer customer = new Customer(accounts.generateUserId());
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setEmail("jane@email.com");
        customer.updatePassword("secret", "secret123");
        customer.setDateOfBirth(LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("01"), Integer.parseInt("01")));

        accounts.addUser(admin);
        accounts.addUser(customer);
    }

    public void register(ActionEvent event) {

        registerErrorTF.setText("");
        Customer newAccount = new Customer(accounts.generateUserId());

        if (!newAccount.setFirstName(registerFirstNameTF.getText())){
            registerErrorTF.setText("First Name is invalid.");

        } else if (!newAccount.setLastName(registerLastNameTF.getText())) {
            registerErrorTF.setText("Last Name is invalid.");

        } else if (accounts.isUsedEmail(registerEmailTF.getText())) {
            registerErrorTF.setText("Email address is already is use.");

        } else if( !newAccount.setEmail(registerEmailTF.getText())) {
            registerErrorTF.setText("Email address is invalid.");

        } else if (!newAccount.updatePassword(registerPasswordTF.getText(), registerConfirmPasswordTF.getText())) {
            registerErrorTF.setText("Password/Confirm Password is invalid.");

        } else if (!newAccount.setDateOfBirth(registerDateOfBirthPicker.getValue())) {
            registerErrorTF.setText("Date of is invalid.");

        } else {
            accounts.addUser(newAccount);
            System.out.println("Registration completed successfully.");
            accounts.printCustomers();
        }
    }

    public void login(ActionEvent event) {
        if (loggedInUser == null) {
            loginErrorTF.setText("");

            String email = loginEmailTF.getText();
            String pass = loginPasswordTF.getText();

            loggedInUser = accounts.userLogin(email, pass);

            if (loggedInUser instanceof User && loggedInUser != null) {
                moviesPane.toFront();
            } else {
                loginErrorTF.setText("Incorrect login details. Please try again.");;
            }
        } else {
            /* TODO Make exception where user is already logged in? */
            System.out.println("An account is already is use. Would you like to logout?");
        }
    }

    public void forgotPassword() {
        // Create new window
        // Get email
        // Validate Email
        // Generate new random password (for email)
        // close window
    }

    private void resetLoginRegisterFields() {
        loginEmailTF.setText(null);
        loginPasswordTF.setText(null);
        loginErrorTF.setText(null);

        registerFirstNameTF.setText(null);
        registerLastNameTF.setText(null);
        loginEmailTF.setText(null);
        registerPasswordTF.setText(null);
        registerConfirmPasswordTF.setText(null);
        registerDateOfBirthPicker.setValue(null);
    }

    /* Consider creating separate controllers and FXML files, rather than using one controller file. */

    public void navigation(ActionEvent actionEvent) {
        if (actionEvent.getSource() == moviesBtn) {
            moviesPane.toFront();
        } else if (actionEvent.getSource() == loginRegisterBtn) {
            if (loggedInUser == null){
                loginRegisterPane.toFront();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Account Login Warning");
                alert.setHeaderText("Looks like you are already logged in.");
                alert.setContentText("Do you wish to logout?");

                ButtonType logoutBtn = new ButtonType("Logout");
                ButtonType noLogoutBtn = new ButtonType("Stay Logged In");

                alert.getButtonTypes().setAll(logoutBtn, noLogoutBtn);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == logoutBtn){
                    System.out.println("Logout...");
                    loggedInUser = null;
                    resetLoginRegisterFields();
                    loginRegisterPane.toFront();
                } else {
                    System.out.println("Ok, np.");
                }
            }
        }
    }
    

}
