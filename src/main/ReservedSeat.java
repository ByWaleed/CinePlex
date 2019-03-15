package main;

public class ReservedSeat {

    private Integer seatId;
    private String type;
    private String location;
    private String price;

    public ReservedSeat(Integer seatId, String type, String location, String price) {
        this.seatId = seatId;
        this.type = type;
        this.location = location;
        this.price = price;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
