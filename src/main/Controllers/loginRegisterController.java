package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Accounts;
import main.Admin;
import main.Customer;
import main.User;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginRegisterController implements Initializable{

    @FXML private Pane loginPoster;

    @FXML private TextField loginEmailTF;
    @FXML private PasswordField loginPasswordTF;
    @FXML private Text loginErrorTF;

    @FXML private TextField forgotPassword;

    @FXML private TextField registerFirstNameTF;
    @FXML private TextField registerLastNameTF;
    @FXML private TextField registerEmailTF;
    @FXML private PasswordField registerPasswordTF;
    @FXML private PasswordField registerConfirmPasswordTF;
    @FXML private DatePicker registerDateOfBirthPicker;
    @FXML private Text registerErrorTF;

    private Accounts accounts = baseController.getAccounts();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createDummyAccounts();
        if (baseController.getLoggedInUser() == null) {
            loginPoster.toBack();
        } else {
            loginPoster.toFront();
        }
    }

    @FXML void login(ActionEvent event) {

        if (baseController.getLoggedInUser() != null) {
            System.out.println("Account already logged in. Do you wish to logout.");

        } else if (validateLoginDetails()) {

            // Validate Login
            String email = loginEmailTF.getText();
            String pass = loginPasswordTF.getText();

            User currentUser = accounts.userLogin(email, pass);
            if (currentUser instanceof User && currentUser != null) {
                loginErrorTF.setText("");
                loginPoster.toFront();
                baseController.setLoggedInUser(currentUser);
            } else {
                loginErrorTF.setText("Incorrect login details. Please try again.");;
            }
        }
    }

    private boolean validateLoginDetails() {
        return true;
    }

    @FXML void forgotPassword() {
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

    @FXML void register(ActionEvent event) {
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
            registerErrorTF.setText("Date of Birth is invalid.\n You must be at least 18 Years old to register.");

        } else {
            accounts.addUser(newAccount);
            baseController.setLoggedInUser(newAccount);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cinema Booking System");
            alert.setHeaderText("Registration completed successfully.");
            alert.setContentText("Now please login with provided email and password.");
            alert.showAndWait();
            accounts.printCustomers();
            //((Stage)((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    private Integer generateUserId() {
        Accounts accounts = baseController.getAccounts();
        return accounts.generateUserId();
    }

    private void createDummyAccounts() {
        Admin admin = new Admin(generateUserId());
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("john@email.com");
        admin.updatePassword("superadmin", "superadmin");
        admin.setDateOfBirth(LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("01"), Integer.parseInt("01")));

        baseController.getAccounts().addUser(admin);
        System.out.println("Admin account created.");
    }

    @FXML void logout(ActionEvent event) {
        User loggedInUser = baseController.getLoggedInUser();

        if (loggedInUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Account Login Warning");
            alert.setHeaderText("Looks like you are already logged in.");
            alert.setContentText("Do you wish to logout?");

            ButtonType logoutBtn = new ButtonType("Logout");
            ButtonType noLogoutBtn = new ButtonType("Stay Logged In");

            alert.getButtonTypes().setAll(logoutBtn, noLogoutBtn);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == logoutBtn){
                System.out.println("User logged out");
                baseController.setLoggedInUser(null);
                loginPoster.toBack();
            }
        } // END IF
    }
}
