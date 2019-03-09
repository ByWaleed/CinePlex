package main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Movie;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoviesController implements Initializable {

    ArrayList<Movie> movies = new ArrayList<Movie>(0);

    @FXML private javafx.scene.control.ScrollPane allMoviesSP;
    @FXML private AnchorPane allMoviesPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMovies();
        displayMovies();
    }

    public void displayMovies() {

        allMoviesSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int rows = 0;
        int column = 4;
        rows = movies.size() / column;
        if (movies.size() % column > 0) {
            rows++;
        }

        int moviesAdded = 0;
        for (int r = 0; r <= rows; r++) {
            HBox hbox = new HBox();
            hbox.setLayoutY(r * 330);
            hbox.setLayoutX(10);

            if (r == 0) {
                hbox.setLayoutY(15);
            }

            for (int c = 0; c < column && moviesAdded < movies.size(); c++) {
                // Add Movies to r HBox
                Node[] movieItems = new Node[10];
                try {
                    movieItems[c] = FXMLLoader.load( getClass().getResource("/main/views/movie.fxml"));
                    //movies.get(c).getPoster()
                    hbox.getChildren().add(movieItems[c]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                moviesAdded++;
            }
            allMoviesPane.getChildren().add(hbox);
        }

        /*Node[] movieItems = new Node[10];
        for (int i = 0; i < movies.size(); i++) {
            try {

                final int j = i;
                movieItems[i] = FXMLLoader.load( getClass().getResource(movies.get(i).getPoster()));

                //give the items some effect

                movieItems[i].setOnMouseEntered(event -> {
                    movieItems[j].setStyle("-fx-background-color : #0A0E3F");
                });
                movieItems[i].setOnMouseExited(event -> {
                    movieItems[j].setStyle("-fx-background-color : #02030A");
                });
                moviesHBox.getChildren().add(movieItems[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    private void loadMovies() {
        // Use online OMDb API
        LocalDate random = LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("02"), Integer.parseInt("01"));
        movies.add(new Movie(generateID(), "Some Movie", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "/main/resources/images/movies/1.jpeg"));
    }

    private int generateID() {
        return movies.size() + 1;
    }

}
