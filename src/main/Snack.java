package main;

public class Snack {

    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private String poster;

    public Snack(Integer id, String name, Double price, Integer stock, String poster) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.poster = poster;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
