package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private Integer userId;
    private Integer bookingId;
    private Integer paymentId;
    private LocalDate date;
    private String bookingType;

    public Booking(Integer userId, Integer bookingId, Integer paymentId, LocalDate date, String bookingType) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.paymentId = paymentId;
        this.date = date;
        this.bookingType = bookingType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "userId=" + userId +
                ", bookingId=" + bookingId +
                ", paymentId=" + paymentId +
                ", date=" + date +
                ", bookingType='" + bookingType + '\'' +
                '}';
    }
}
