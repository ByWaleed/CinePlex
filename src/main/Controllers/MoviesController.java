package main.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Movie;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoviesController implements Initializable {

    @FXML private javafx.scene.control.ScrollPane allMoviesSP;
    @FXML private AnchorPane allMoviesPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadMovies();
        displayMovies();
    }

    public void displayMovies() {

        allMoviesSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        allMoviesSP.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ArrayList<Movie> movies = baseController.getMovies();

        int rows = 0;
        int columns = 4;
        rows = movies.size() / columns;
        if (movies.size() % columns > 0) {
            rows++;
        }

        int moviesAdded = 0;
        for (int r = 0; r < rows; r++) {
            Pane row = new Pane();

            row.setPrefWidth(950);
            row.setPrefHeight(300);
            row.setLayoutX(30);

            if (r == 0) {
                row.setLayoutY(30);
            } else {
                row.setLayoutY(15 + (340 * r));
            }

            for (int c = 0; (c < columns) && (moviesAdded < movies.size()); c++) {
                // Add Movies to r HBox
                Image img = new Image(movies.get(moviesAdded).getPoster());
                ImageView posterIV = new ImageView(img);
                posterIV.setEffect(new DropShadow(20, Color.BLACK));
                posterIV.setX(c * 230);
                posterIV.setCursor(Cursor.HAND);
                posterIV.setId("" + moviesAdded);

                row.getChildren().add(posterIV);
                moviesAdded++;

                // Add event handler for each movie
                posterIV.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ImageView posterIV = (ImageView) mouseEvent.getSource();
                        Integer id = Integer.parseInt(posterIV.getId());
                        baseController.setSelectedMovie(movies.get(id));
                        try {
                            Pane movie = FXMLLoader.load(getClass().getResource("/main/views/movie.fxml"));
                            allMoviesPane.getChildren().clear();
                            allMoviesPane.getChildren().add(movie);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            allMoviesPane.getChildren().add(row);
        }
    }

    private void loadMovies() {
        // Use online OMDb API

    }
}
