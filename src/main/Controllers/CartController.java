package main.Controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import main.Cart;
import main.CartItem;
import main.CartScreenItem;
import main.ReservedSeat;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dummyCartItems();

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
                                item.getItemId(),
                                "N/A",
                                "N/A",
                                item.getItemName(),
                                1,
                                item.getItemPrice(),
                                item.getItemPrice()
                        ));
                    } else {
                        System.out.println("Item is not a movie" + item.getItemName());
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
            if (addedItem.getId().equals(item.getItemId())) {
                return counter;
            }
            counter++;
        }
        return null;
    }

    public static void reduceQuantity(ActionEvent event) {
        Integer rowItemId = Integer.parseInt(((Control)event.getSource()).getId());

        // Remove item from Cart
        CartItem cartRowItem = itemInCart(rowItemId);
        userCart.remove(cartRowItem);
        baseController.getCart().setCart(userCart);

        // Reduce Quantity or Remove Row
        CartScreenItem rowItem = itemInTable(rowItemId);
        if (rowItem.getQuantity() > 1) {
            rowItem.setQuantity(rowItem.getQuantity() - 1);
            tableRows.set(itemInTableIndex(rowItemId), rowItem);
        } else {
            tableRows.remove(rowItem);
        }

        // Update ObservableArrayList
        tableRowsOL = FXCollections.observableArrayList(tableRows);

        // Refresh table ???

        /*for (CartScreenItem item : tableRows) {
            System.out.println(item);
        }

        for (CartScreenItem item : tableRowsOL) {
            System.out.println(item);
        }*/
    }

    private static CartItem itemInCart(Integer itemID) {
        for (CartItem item : userCart) {
            if (item.getItemId().equals(itemID)) {
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
}
