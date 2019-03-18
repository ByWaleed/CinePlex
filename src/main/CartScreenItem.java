package main;

import javafx.beans.property.SimpleStringProperty;

import java.awt.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

import static main.Controllers.CartController.reduceQuantity;

public class CartScreenItem {

    private Integer id;
    private Button remove;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private Double total;

    public CartScreenItem(Integer id, String name, Integer quantity, Double unitPrice, Double total) {
        this.id = id;
        this.remove = new Button("-");
        this.remove.setId(""+id);
        this.remove.setOnAction((event) -> {
             reduceQuantity(event);
        });
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartScreenItem{" +
                "id=" + id +
                ", remove=" + remove +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
