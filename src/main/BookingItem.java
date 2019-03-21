package main;

import java.time.LocalDateTime;

public class BookingItem {

    private Integer bookingId;
    private Integer itemId; // sessionId for movies
    private String itemType;
    private Integer seatId;
    private String itemName;
    private Double itemPrice;
    private Integer quantity;
    private Double totalPrice;
    private LocalDateTime date;

    public BookingItem(Integer bookingId, Integer itemId, String itemType, Integer seatId, String itemName, Double itemPrice, Integer quantity, LocalDateTime date) {
        this.bookingId = bookingId;
        this.itemId = itemId;
        this.itemType = itemType;
        this.seatId = seatId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalPrice = quantity * itemPrice;
        this.date = date;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
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

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "bookingId=" + bookingId +
                ", itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", seatId=" + seatId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
