package main.Controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.*;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML private TableView<CartScreenItem> cartTable;
    @FXML private TableColumn<CartScreenItem, String> cartColumnName;
    @FXML private TableColumn<CartScreenItem, Integer> cartColumnQuantity;
    @FXML private TableColumn<CartScreenItem, Double> cartColumnPrice;
    @FXML private TableColumn<CartScreenItem, Double> cartColumnTotal;
    @FXML private TableColumn<CartScreenItem, Button> cartColumnRemove;

    private static ArrayList<CartItem> userCart = baseController.getCart().getCart();
    private static ArrayList<CartScreenItem> tableRows = new ArrayList<>(0);

    private static ObservableList<CartScreenItem> tableRowsOL;

    private static baseController baseControllerObject = null;

    private static ArrayList<BookingItem> quantityItems = new ArrayList<>(0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //dummyCartItems();

        generateCartScreenItems();

        showCartTable();
    }

    private void dummyCartItems() {
        userCart.add(new CartItem(1, 1));
    }

    private void generateCartScreenItems() {
        // add cart items to cartScreenItems
        if (userCart.size() > 0) {
            tableRows.clear();
            for (CartItem item : userCart) {

                // If item already displaying, then increase quantity
                Integer existingItem = itemAdded(item);
                if (existingItem != null) {
                    // Increase item quantity
                    CartScreenItem cartItem = tableRows.get(existingItem);

                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cartItem.setTotal(cartItem.getQuantity() * cartItem.getUnitPrice());

                    tableRows.set(existingItem, cartItem); // Update table row

                // Otherwise, add new row table row
                } else {
                    if (item.getItemType().equals("Movie")) {
                        tableRows.add(new CartScreenItem(
                                tableRows.size(),
                                item.getItemName(),
                                1,
                                item.getItemPrice(),
                                item.getItemPrice()
                        ));
                    } else {
                        tableRows.add(new CartScreenItem(
                                tableRows.size(),
                                item.getItemName(),
                                1,
                                item.getItemPrice(),
                                item.getItemPrice()
                        ));
                    }
                }
            }
        } else {
            System.out.println("Error: No items in the cart");
        }
    }

    private void showCartTable() {
        cartTable.setPlaceholder(new Label("There are no items in your cart."));

        tableRowsOL = FXCollections.observableArrayList(tableRows);

        // Setup Columns
        cartColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cartColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cartColumnPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        cartColumnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        cartColumnRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));

        // Set Table Data
        cartTable.setItems(tableRowsOL);
    }

    private Integer itemAdded(CartItem item) {
        Integer counter = 0;
        for (CartScreenItem addedItem : tableRows) {
            if (addedItem.getName().equals(item.getItemName())) {
                return counter;
            }
            counter++;
        }
        return null;
    }

    public static void reduceQuantity(ActionEvent event) {
        Integer rowItemId = Integer.parseInt(((Control)event.getSource()).getId());

        // Reduce Quantity or Remove Row
        CartScreenItem rowItem = tableRows.get(rowItemId);
        if (rowItem.getQuantity() > 1) {
            rowItem.setQuantity(rowItem.getQuantity() - 1);
            tableRows.set(itemInTableIndex(rowItemId), rowItem);
        } else {
            tableRows.remove(rowItem);
        }

        // Remove item from Cart
        CartItem cartRowItem = itemInCart(rowItem.getName());
        userCart.remove(cartRowItem);
        baseController.getCart().setCart(userCart);

        // Update ObservableArrayList
        tableRowsOL = FXCollections.observableArrayList(tableRows);

        baseControllerObject.reloadCart();
    }

    private static CartItem itemInCart(String name) {
        for (CartItem item : userCart) {
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    private static CartScreenItem itemInTable(Integer itemID) {
        for (CartScreenItem item : tableRows) {
            if (item.getId().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    private static Integer itemInTableIndex(Integer itemID) {
        Integer count = 0;
        for (CartScreenItem item : tableRows) {
            if (item.getId().equals(itemID)) {
                return count;
            }
            count++;
        }
        return null;
    }

    @FXML void cashPayment() {
        if (tableRows.size() > 0) {
            Integer userId = null;
            if (baseController.getLoggedInUser() != null) {
                userId = baseController.getLoggedInUser().getId();
            }

            // Create Booking
            Booking order = new Booking(
                    userId,
                    baseController.generateBookingId(),
                    null,
                    LocalDate.now(),
                    "Cash"
            );

            // Create Booking Items (linked by bookingId)
            ArrayList<CartItem> addedItems = new ArrayList<>(0);
            for (CartItem item : userCart) {
                if (!arrayContainsItem(addedItems, item)) {
                    addedItems.add(item);
                    Integer quantity = getItemQuantity(addedItems, item);
                    baseController.addBookingItem(new BookingItem(
                            order.getBookingId(),
                            item.getItemId(),
                            item.getItemType(),
                            item.getSeatid(),
                            item.getItemName(),
                            item.getItemPrice(),
                            quantity
                    ));
                } else {
                    quantityItems.add(new BookingItem(
                            order.getBookingId(),
                            item.getItemId(),
                            item.getItemType(),
                            item.getSeatid(),
                            item.getItemName(),
                            item.getItemPrice(),
                            1
                    ));
                    System.out.println("Add more quantity to  " + item);
                }
            }

            cashOrderConfirmation(order.getBookingId());
            emptyCart();
        } else {
            emptyCartError();
        }
    }

    private Integer getItemQuantity(ArrayList<CartItem> addedItems, CartItem item) {
        Integer quantity = 1;
        for (CartItem i : addedItems) {
            if (i.getItemName() == item.getItemName()) {
                quantity++;
            }
        }
        return quantity;
    }

    @FXML void cardPayment(ActionEvent event) {
        if (tableRows.size() == 0) {
            emptyCartError();
        } else if (baseController.getLoggedInUser() == null) {
            baseController.notLoggedInError();
        } else {
            // Open snack quantity window
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/views/cardPayment.fxml"));
            try {
                Parent selectSeatsView = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(selectSeatsView, 450, 350));
                CardPaymentController.setCartController(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Cinema Booking System");
            stage.getIcons().add(new Image("main/resources/images/icon.png"));
            stage.setResizable(false);
            stage.show();
        }
    }

    private void emptyCartError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cinema Booking System");
        alert.setHeaderText("Empty Cart");
        alert.setContentText("Please add some items in your cart first.");
        alert.showAndWait();
    }

    public void emptyCart() {
        userCart.clear();
        tableRows.clear();
        tableRowsOL.clear();
    }

    private void cashOrderConfirmation(Integer orderId) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cinema Booking System");
        alert.setHeaderText("Your Number is #" + orderId);
        alert.setContentText("Your order is reserved & will be printed. \nPlease pay before entrance to Cinema Theatre.");
        alert.showAndWait();
    }

    private Boolean arrayContainsItem(ArrayList<CartItem> list, CartItem item) {
        for (CartItem i : list) {
            if (i.getItemName() == item.getItemName()) {
                return true;
            }
        }
        return false;
    }

    public static void setBaseControllerObject(baseController object) {
        CartController.baseControllerObject = object;
    }

    public static ArrayList<BookingItem> getQuantityItems() {
        return quantityItems;
    }
}
