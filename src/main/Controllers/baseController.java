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
import main.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class baseController implements Initializable {

    public static Accounts accounts = new Accounts();

    private static User loggedInUser;

    private static Movie selectedMovie;

    // Navigation Buttons
    @FXML private Button moviesBtn;
    @FXML private Button snacksBtn;
    @FXML private Button theatresBtn;
    @FXML private Button adminBtn;
    @FXML private Button loginRegisterBtn;

    @FXML private Pane mainContentPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedInUser = null;

        dummyAccounts();
        System.out.println(accounts);
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
        System.out.println(accounts);
    }
    /* Consider creating separate controllers and FXML files, rather than using one controller file. */

    public void navigation(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(moviesBtn)) {
            loadUI("/main/views/movies.fxml");

        } else if (actionEvent.getSource() ==  snacksBtn) {
            System.out.println("Snacks pane selected");

        } else if (actionEvent.getSource() ==  theatresBtn) {
            System.out.println("Theatres pane selected");

        } else if (actionEvent.getSource() ==  adminBtn) {
            System.out.println("Admin pane selected");

        } else if (actionEvent.getSource() == loginRegisterBtn) {
            if (loggedInUser == null){
                loadUI("/main/views/loginRegister.fxml");
            } /*else {
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
            }*/
        }
    }

    private void loadUI(String resourceName) {
        try {
            Pane content = FXMLLoader.load(getClass().getResource(resourceName));
            mainContentPane.getChildren().clear();
            mainContentPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Movie getSelectedMovie() {
        return selectedMovie;
    }

    public static void setSelectedMovie(Movie selectedMovie) {
        baseController.selectedMovie = selectedMovie;
    }
}
