package main;

import main.Controllers.baseController;

import java.time.LocalTime;

public class CartItem {

    // Generic Attributes
    private Integer itemId;
    private String itemType;
    private String itemName;
    private Double itemPrice;

    // Movie Attributes
    private Integer seatid;

    // Constructor for movies
    public CartItem(Integer sessionID, Integer seatID) {
        Session item = baseController.getSessions().get(sessionID - 1);
        this.itemId = sessionID;
        this.itemType = "Movie";
        this.itemName = baseController.getMovies().get(item.getMovieId() - 1).getTitle();
        this.itemPrice = baseController.getMovies().get(item.getMovieId() - 1).getPrice();
        this.seatid = seatID;
    }

    public CartItem(Integer itemId, String itemName, Double itemPrice) {
        this.itemId = itemId;
        this.itemType = "Snack";
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.seatid = null;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", seatid=" + seatid +
                '}';
    }
}
