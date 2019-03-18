package main.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Cart;
import main.CartItem;
import main.Snack;

import java.net.URL;
import java.util.ResourceBundle;

public class SnackController implements Initializable {

    @FXML private Text itemNameText;
    @FXML private Text itemPriceText;
    @FXML private TextField ItemQuantityTF;
    @FXML private Text itemQuantityText;
    @FXML private Text itemTotalPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Snack selectedSnack = baseController.getSelectedSnack();

        itemNameText.setText(selectedSnack.getName());
        itemPriceText.setText("£" + selectedSnack.getPrice());
        itemQuantityText.setText("("+selectedSnack.getStock()+")");
        itemTotalPrice.setText("£" + selectedSnack.getPrice());

        ItemQuantityTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ItemQuantityTF.setText(newValue.replaceAll("[^\\d]", ""));
                } else {
                    updateTotal();
                }
            }
        });
    }

    private Boolean updateTotal() {
        Snack selectedSnack = baseController.getSelectedSnack();

        if (!ItemQuantityTF.getText().equals("") && (ItemQuantityTF.getText().length() < 4)) {
            Integer quantity = Integer.parseInt(ItemQuantityTF.getText());
            if (quantity > 0) {
                if (quantity <= selectedSnack.getStock()) {
                    itemTotalPrice.setText("£" + quantity * selectedSnack.getPrice());
                    return true;
                }
            }
        }
        return false;
    }

    @FXML void addSnackToCart(ActionEvent event) {
        if (updateTotal()) {
            if (addSnackToCart()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cinema Booking System");
                alert.setHeaderText(null);
                alert.setContentText("\nYour cart has been updated successfully.");
                alert.showAndWait();

                ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cinema Booking System");
            alert.setHeaderText(null);
            alert.setContentText("\nPlease input quantity, to add snack to cart.");
            alert.showAndWait();
        }
    }

    private boolean addSnackToCart() {
        Cart cart = baseController.getCart();
        Snack selectedSnack = baseController.getSelectedSnack();
        Integer quantity = Integer.parseInt(ItemQuantityTF.getText());

        for (Integer i=0; i < quantity; i++) {
            cart.addItem(new CartItem(selectedSnack.getId(), selectedSnack.getName(), selectedSnack.getPrice()));
        }
        return true;
    }
}
