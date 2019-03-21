package main.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Booking;
import main.BookingItem;
import main.CartScreenItem;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class BookingsController implements Initializable {
    @FXML private TableView<BookingItem> ordersTable;
    @FXML private TableColumn<Booking, LocalDate> dateCol;
    @FXML private TableColumn<Booking, Integer> orderIdCol;
    @FXML private TableColumn<BookingItem, String> nameCol;
    @FXML private TableColumn<BookingItem, Integer> quantityCol;
    @FXML private TableColumn<BookingItem, Double> priceCol;
    @FXML private TableColumn<BookingItem, Double> totalCol;

    private ArrayList<BookingItem> bookingItems = new ArrayList<>(0);
    private ObservableList<BookingItem> bookingItemsOL = FXCollections.observableArrayList(bookingItems);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Number of Bookings: " + baseController.getBookings().size());
        for (Booking item : baseController.getBookings()) {
            System.out.println(item);
        }
        setupTable();
    }

    private void setupTable() {
        ordersTable.setPlaceholder(new Label("You have not placed any order. "));

        // Setup Columns
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));


        // Get all bookings & current user->id
        ArrayList<Booking> bookings = baseController.getBookings();
        int userId = baseController.getLoggedInUser().getId();

        // Get user bookings
        if (baseController.getLoggedInUser() != null) {
            for (Booking booking : bookings) {

                if (booking.getUserId() != 0) {
                    if (booking.getUserId().equals(userId)) {
                        showBookingItems(booking.getBookingId());
                    }
                }

            }
        }

        bookingItemsOL = FXCollections.observableArrayList(bookingItems);
        ordersTable.setItems(bookingItemsOL);
    }

    private void showBookingItems(Integer bookingId) {
        ArrayList<BookingItem> bookingItems = baseController.getBookingItems();
        for (BookingItem item : bookingItems) {
            if (item.getBookingId() == bookingId) {
                this.bookingItems.add(item);
            }
        }
    }
}
