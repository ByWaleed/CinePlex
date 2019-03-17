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
import java.lang.reflect.Array;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class baseController implements Initializable {

    private static Accounts accounts;
    private static HashMap<String, String> passwords;
    private static User loggedInUser;

    private static ArrayList<Session> sessions;
    private static ArrayList<Theatre> theatres;
    private static ArrayList<Seat> seats;

    private static ArrayList<Movie> movies;

    private static Movie selectedMovie;

    private static Cart cart = new Cart();


    // Navigation Buttons
    @FXML private Button moviesBtn;
    @FXML private Button snacksBtn;
    @FXML private Button theatresBtn;
    @FXML private Button cartBtn;
    @FXML private Button loginRegisterBtn;

    @FXML private Pane mainContentPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baseController.accounts = new Accounts();
        baseController.passwords = new HashMap<>(0);
        baseController.loggedInUser = null;

        baseController.sessions = new ArrayList<>(0);
        baseController.theatres = new ArrayList<>(0);
        baseController.seats = new ArrayList<>(0);

        baseController.movies = new ArrayList<>(0);
        baseController.selectedMovie = null;

        dummyData.createMovies();
        dummyData.createTheatres();
        dummyData.createSeats();
        dummyData.createSessions();
    }

    public void navigation(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        System.out.println(source);

        if (source.equals(moviesBtn)) {
            loadUI("/main/views/movies.fxml");

        } else if (source.equals(snacksBtn)) {
            System.out.println("Snacks pane selected");

        } else if (source.equals(theatresBtn)) {
            System.out.println("Theatres pane selected");

        } else if (actionEvent.getSource() ==  cartBtn){
            loadUI("/main/views/cart.fxml");

        } else if (source.equals(loginRegisterBtn)) {
            if (loggedInUser == null){
                loadUI("/main/views/loginRegister.fxml");
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
                    loadUI("/main/views/loginRegister.fxml");
                } else {
                    System.out.println("Ok, np.");
                }
            }
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

    /* GETTERS & SETTERS for static attributes */

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        baseController.loggedInUser = loggedInUser;
    }

    public static Accounts getAccounts() {
        return accounts;
    }

    public static void setAccounts(Accounts accounts) {
        baseController.accounts = accounts;
    }

    public static HashMap<String, String> getPasswords() {
        return passwords;
    }

    public static void setPasswords(HashMap<String, String> passwords) {
        baseController.passwords = passwords;
    }

    public static Movie getSelectedMovie() {
        return selectedMovie;
    }

    public static void setSelectedMovie(Movie selectedMovie) {
        baseController.selectedMovie = selectedMovie;
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

    public static void setSessions(ArrayList<Session> sessions) {
        baseController.sessions = sessions;
    }

    public static ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    public static void setTheatres(ArrayList<Theatre> theatres) {
        baseController.theatres = theatres;
    }

    public static ArrayList<Seat> getSeats() {
        return seats;
    }

    public static void setSeats(ArrayList<Seat> seats) {
        baseController.seats = seats;
    }

    public static ArrayList<Movie> getMovies() {
        return movies;
    }

    public static void setMovies(ArrayList<Movie> movies) {
        baseController.movies = movies;
    }

    public static Cart getCart() {
        return cart;
    }

    public static void setCart(Cart cart) {
        baseController.cart = cart;
    }
}
