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

    private static ArrayList<Snack> snacks;
    private static Snack selectedSnack;

    private static Cart cart = new Cart();

    private static ArrayList<Payment> payments = new ArrayList<>(0);

    private static ArrayList<Booking> bookings = new ArrayList<>(0);
    private static ArrayList<BookingItem> bookingItems= new ArrayList<>(0);

    // Navigation Buttons
    @FXML private Button moviesBtn;
    @FXML private Button snacksBtn;
    @FXML private Button bookingsBtn;
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

        if (source.equals(moviesBtn)) {
            loadUI("/main/views/movies.fxml");

        } else if (source.equals(snacksBtn)) {
            loadUI("/main/views/snacks.fxml");

        } else if (source.equals(bookingsBtn)) {
            if (loggedInUser != null) {
                loadUI("/main/views/bookings.fxml");
            } else {
                notLoggedInError();
            }

        } else if (actionEvent.getSource().equals(cartBtn)){
            loadUI("/main/views/cart.fxml");

        } else if (source.equals(loginRegisterBtn)) {
            loadUI("/main/views/loginRegister.fxml");
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

    public static ArrayList<Snack> getSnacks() {
        return snacks;
    }

    public static void setSnacks(ArrayList<Snack> snacks) {
        baseController.snacks = snacks;
    }

    public static Snack getSelectedSnack() {
        return selectedSnack;
    }

    public static void setSelectedSnack(Snack selectedSnack) {
        baseController.selectedSnack = selectedSnack;
    }

    public static Cart getCart() {
        return cart;
    }

    public static void setCart(Cart cart) {
        baseController.cart = cart;
    }

    public static ArrayList<Booking> getBookings() {
        return bookings;
    }

    public static void setBookings(ArrayList<Booking> bookings) {
        baseController.bookings = bookings;
    }

    public static Integer generateBookingId() {
        return bookings.size() + 1;
    }

    public static ArrayList<Payment> getPayments() {
        return payments;
    }

    public static void setPayments(ArrayList<Payment> payments) {
        baseController.payments = payments;
    }

    public static Integer generatePaymentId() {
        return payments.size() + 1;
    }

    public static ArrayList<BookingItem> getBookingItems() {
        return bookingItems;
    }

    public static void setBookingItems(ArrayList<BookingItem> bookingItems) {
        baseController.bookingItems = bookingItems;
    }

    public static void addBookingItem(BookingItem item) {
        bookingItems.add(item);
    }

    public static void notLoggedInError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account Login Warning");
        alert.setHeaderText(null);
        alert.setContentText("\nPlease login first to proceed any further.");
        alert.showAndWait();
    }
}
