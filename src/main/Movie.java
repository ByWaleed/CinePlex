package main;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private String description;
    private String genera;
    private Double rating;
    private int minAge;
    private Double price;
    private String languauge;
    private Boolean subTitles;
    private String poster;
    private String imbdId;
    private String writer;
    private String actors;
    private String rated;
    private String awards;
    private String trailer;

    public Movie(int id, String title, String director, String writer, String actors, String rated, LocalDate releaseDate, String description, String genera, Double rating, int minAge, Double price, String languauge, Boolean subTitles, String poster, String imbdId, String awards, String trailer) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.description = description;
        this.genera = genera;
        this.rating = rating;
        this.minAge = minAge;
        this.price = price;
        this.languauge = languauge;
        this.subTitles = subTitles;
        this.poster = poster;
        this.imbdId = imbdId;
        this.writer = writer;
        this.actors = actors;
        this.rated = rated;
        this.awards = awards;
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenera() {
        return genera;
    }

    public void setGenera(String genera) {
        this.genera = genera;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLanguauge() {
        return languauge;
    }

    public void setLanguauge(String languauge) {
        this.languauge = languauge;
    }

    public Boolean getSubTitles() {
        return subTitles;
    }

    public void setSubTitles(Boolean subTitles) {
        this.subTitles = subTitles;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImbdId() {
        return imbdId;
    }

    public void setImbdId(String imbdId) {
        this.imbdId = imbdId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", genera='" + genera + '\'' +
                ", rating=" + rating +
                ", minAge=" + minAge +
                ", price=" + price +
                ", languauge='" + languauge + '\'' +
                ", subTitles=" + subTitles +
                ", poster='" + poster + '\'' +
                '}';
    }
}
