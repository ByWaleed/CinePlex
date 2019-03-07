package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.Accounts;
import main.Admin;
import main.Customer;
import main.User;

import java.awt.*;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

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
        // TODO Code Small... Create methods for each type of alert/diaglog with appropriate parameters

        TextInputDialog forgotPasswordDialog = new TextInputDialog();
        forgotPasswordDialog.setTitle("Reset Account Password");
        forgotPasswordDialog.setHeaderText("Please input below the email address associated with your account.");
        forgotPasswordDialog.setContentText("Email:");

        Optional<String> email = forgotPasswordDialog.showAndWait();

        if (email.isPresent()){
            if (email.get().isEmpty() ) {
                forgotPasswordError(
                        "Invalid Email Provided",
                        "Provided email was not valid.",
                        "Please input a valid email.");
            } else if (!accounts.isUsedEmail(email.get())) {
                forgotPasswordError(
                        "Account Not Found",
                        "Email not linked with any account.",
                        "Please provide email linked to your account.");
            } else {
                String newPassword = accounts.resetPassword(email.get());

                TextInputDialog passwordUpdatedDialog = new TextInputDialog(newPassword);
                passwordUpdatedDialog.setTitle("Email: Reset Account Password");
                passwordUpdatedDialog.setHeaderText("Your password has been successfully reset.");
                passwordUpdatedDialog.setContentText("New Password: ");
                passwordUpdatedDialog.showAndWait();
            }
        }
    }

    private void forgotPasswordError(String title, String headerText, String contentText){
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle(title);
        errorDialog.setHeaderText(headerText);
        errorDialog.setContentText(contentText);
        Optional<ButtonType> errorDialogBtn = errorDialog.showAndWait();
        if (errorDialogBtn.get().equals(ButtonType.OK)) {
            forgotPassword();
        }
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
        if (actionEvent.getSource().equals(moviesBtn)) {
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
