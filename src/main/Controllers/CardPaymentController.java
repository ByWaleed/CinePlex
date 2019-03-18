package main.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CardPaymentController implements Initializable {
    @FXML private TextField cardNumberTF;
    @FXML private TextField expiryTF;
    @FXML private TextField cvvTF;

    private Boolean validaCard = false;
    private Boolean validExpiry = false;
    private Boolean validCVV = false;

    private static CartController cartController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardNumberTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    cardNumberTF.setText(newValue.replaceAll("[^\\d]", ""));
                    validaCard=false;
                } else if (newValue.length() != 16) {
                    validaCard=false;
                }else {
                    validaCard=true;
                }
            }
        });

        expiryTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    expiryTF.setText(newValue.replaceAll("[^\\d]", ""));
                    validExpiry=false;
                } else if (newValue.length() != 6) {
                    validExpiry=false;
                }else {
                    validExpiry=true;
                }
            }
        });

        cvvTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    cvvTF.setText(newValue.replaceAll("[^\\d]", ""));
                    validCVV=false;
                } else if (newValue.length() != 3) {
                    validCVV=false;
                }else {
                    validCVV=true;
                }
            }
        });
    }

    @FXML void confirmPayment(ActionEvent event) {
        if (!validaCard) {
            fieldError("Incorrect Card Number given. Please try again.");
        } else if (!validExpiry) {
            fieldError("Incorrect Expiry Date given. Please try again.");
        } else if (!validCVV) {
            fieldError("Incorrect CVV Number given. Please try again.");
        } else {
            orderConfirmation();
            cartController.emptyCart();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void fieldError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cinema Booking System");
        alert.setHeaderText("Opps... incorrect details given.");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void orderConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cinema Booking System");
        alert.setHeaderText("Thank you for your order");
        alert.setContentText("Your payment has been accepted. \nPlease collect order receipt from the printer.");
        alert.showAndWait();
    }

    public static void setCartController(CartController controller) {
        cartController = controller;
    }
}
