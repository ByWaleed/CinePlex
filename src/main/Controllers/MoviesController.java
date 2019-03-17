package main.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;
import javafx.scene.input.MouseEvent;
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
<<<<<<< Updated upstream

                        baseController.setSelectedMovie(movies.get(id));
=======
                        System.out.println(movies.get(id));
>>>>>>> Stashed changes

                        // select the mainContentPane and update the mainContentPane
                        try {
                            Pane movie = FXMLLoader.load(getClass().getResource("/main/views/movie.fxml"));
                            /*Parent root = (Parent)fxmlLoader.load();
                            MainController controller = fxmlLoader.<MainController>getController();
                            controller.setUser(user_id);*/

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
        movies.add(new Movie(generateID(), "Batman vs Superman", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "Avengers End Game", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BNGZiMzBkZjMtNjE3Mi00MWNlLWIyYjItYTk3MjY0Yjg5ODZkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "Blade Runner 2049", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "Star Wars the Last Jedi", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BMjQ1MzcxNjg4N15BMl5BanBnXkFtZTgwNzgwMjY4MzI@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "Matrix", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "The Dark Night", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX200.jpg"));
        movies.add(new Movie(generateID(), "Terminator 2", "John Doe", random, "Some random movie.", "Unnknown", 5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BMGU2NzRmZjUtOGUxYS00ZjdjLWEwZWItY2NlM2JhNjkxNTFmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX200.jpg"));
    }

    private int generateID() {
        return movies.size() + 1;
    }

}
