package main;

import java.awt.*;

public class Seat {

    private Integer id;
    private String type;
    private Integer theatreId;
    private Point location;

    public Seat(Integer id, String type, Integer theatreId, Point location) {
        this.id = id;
        this.type = type;
        this.theatreId = theatreId;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
