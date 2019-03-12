package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.Accounts;
import main.Admin;
import main.Customer;
import main.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginRegisterController {

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


    @FXML void login(ActionEvent event){
        System.out.println(baseController.getAccounts());
        if (baseController.getLoggedInUser() == null) {
            System.out.println("Validate login details");
        } else {
            System.out.println("Account already logged in. Do you wish to logout.");
        }
    }

    @FXML void forgotPassword(MouseEvent event) {
        System.out.println(baseController.getAccounts());
    }

    @FXML void register(ActionEvent event) {

    }

    private Integer generateUserId() {
        Accounts accounts = baseController.getAccounts();
        return accounts.generateUserId();
    }

    private void createDummyAccounts() {
        /*Admin admin = new Admin(generateUserId());
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("john@email.com");
        admin.updatePassword("superadmin", "superadmin");
        admin.setDateOfBirth(LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("01"), Integer.parseInt("01")));
*/
        /*Accounts accounts = baseController.getAccounts();
        accounts.addUser(admin);

        baseController.setAccounts(accounts);
        System.out.println("Accounts in Dummy Accounts Method: ");
        System.out.println(accounts);*/
    }
}
