package main.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Booking;
import main.BookingItem;
import main.CartScreenItem;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingsController implements Initializable {
    @FXML private TableView<BookingItem> ordersTable;
    @FXML private TableColumn<Booking, LocalDate> dateCol;
    @FXML private TableColumn<Booking, Integer> orderIdCol;
    @FXML private TableColumn<BookingItem, String> nameCol;
    @FXML private TableColumn<BookingItem, Integer> quantityCol;
    @FXML private TableColumn<BookingItem, Double> priceCol;
    @FXML private TableColumn<BookingItem, Double> totalCol;

    private ArrayList<Booking> bookings = baseController.getBookings();
    private ArrayList<BookingItem> bookingItems = baseController.getBookingItems();
    private ObservableList<BookingItem> bookingItemsOL = FXCollections.observableArrayList(bookingItems);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (BookingItem item : baseController.getBookingItems()) {
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

        ordersTable.setItems(bookingItemsOL);
    }
}
