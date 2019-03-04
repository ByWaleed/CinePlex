package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.Accounts;
import main.Admin;
import main.Customer;
import main.User;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AllMoviesController implements Initializable {

    Accounts accounts;

    User loggedInUser;

    @FXML private TextField loginEmailTF;
    @FXML private TextField loginPasswordTF;
    @FXML private Text loginErrorTF;

    @FXML private TextField registerFirstNameTF;
    @FXML private TextField registerLastNameTF;
    @FXML private TextField registerEmailTF;
    @FXML private PasswordField registerPasswordTF;
    @FXML private PasswordField registerConfirmPasswordTF;
    @FXML private DatePicker registerDateOfBirthPicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = null;
        accounts = new Accounts();

        dummyAccounts();
    }

    public void dummyAccounts() {
        accounts.addUser(
                new Admin(accounts.generateUserId(),"John", "Doe", "john@email.com", "superadmin", new Date("01/01/2019"))
        );
        accounts.addUser(
                new Customer(accounts.generateUserId(),"Jane", "Doe", "jane@email.com", "secret123", new Date("01/01/2019"))
        );
    }

    public void register(ActionEvent event) {
        accounts.addUser(new Customer(
                accounts.generateUserId(),
                registerFirstNameTF.getText(),
                registerLastNameTF.getText(),
                registerEmailTF.getText(),
                registerPasswordTF.getText(),
                new Date(registerDateOfBirthPicker.getValue().toEpochDay())
        ));

        accounts.printCustomers();
    }

    public void login(ActionEvent event) {
        if (loggedInUser == null) {
            loginErrorTF.setText("");

            String email = loginEmailTF.getText().toString();
            String pass = loginPasswordTF.getText();

            loggedInUser = accounts.userLogin(email, pass);

            if (loggedInUser instanceof User ) {
                System.out.println("Logged in successfully");
            } else {
                loginErrorTF.setText("Incorrect login details. Please try again.");;
            }
        } else {
            /* TODO Make exception where user is already logged in? */
            System.out.println("An account is already is use. Would you like to logout?");
        }
    }

}
