package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Movie;
import main.Session;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.json.*;

public class MovieController implements Initializable{

    private final String USER_AGENT = "Mozilla/5.0";

    @FXML private ImageView moviePoster;
    @FXML private Text movieTitle;
    @FXML private Text movieYear;
    @FXML private Text movieGenere;
    @FXML private Text movieDirector;
    @FXML private Text movieWriters;
    @FXML private Text movieActors;
    @FXML private Text moviePlot;
    @FXML private Text movieRating;
    @FXML private Text movieRated;
    @FXML private Text movieAwards;
    @FXML private Text movieSeatsAvailable;
    @FXML private Text moviePrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Movie movie = baseController.getSelectedMovie();

        try {
            String movieDetail = movieDetailsAPI(movie.getImbdId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Getting higher resolution poster
        String posterLocation = movie.getPoster().split("_")[0];
        posterLocation = posterLocation + "_V1_SX400.jpg";

        moviePoster.setImage(new Image(posterLocation));
        movieTitle.setText(movie.getTitle());
        movieYear.setText("" + movie.getReleaseDate());
        movieGenere.setText(movie.getGenera());
        movieDirector.setText(movie.getDirector());
        movieWriters.setText(movie.getWriter());
        movieActors.setText(movie.getActors());
        moviePlot.setText(movie.getDescription());
        movieRating.setText("" + movie.getRating());
        movieRated.setText(movie.getRated());
        movieAwards.setText(movie.getAwards());
        movieSeatsAvailable.setText("");
        moviePrice.setText("£" + movie.getPrice());
    }

    /* SOURCE https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/ */
    private String movieDetailsAPI(String imbdId) throws Exception {

        String url = "http://www.omdbapi.com/?i=" + imbdId + "&apikey=19e921e7";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // return in JSON
        System.out.println(response);
        JSONObject jsonResponse = new JSONObject(response);

        System.out.println(jsonResponse);
        /*for (int i = 0; i < arr.length(); i++)
            System.out.println(arr.getInt(i));*/
        return response.toString();

    }

    @FXML void makeBooking(ActionEvent event) throws IOException {
        Movie selected = baseController.getSelectedMovie();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/views/selectTheatreSeats.fxml"));
        Parent selectSeatsView = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(selectSeatsView, 1175, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Cinema Booking System");
        stage.getIcons().add(new Image("main/resources/images/icon.png"));
        stage.setResizable(false);
        /* baseController Window Properties */
        stage.show();

        /*try {
            Pane movie = FXMLLoader.load(getClass().getResource("/main/views/movie.fxml"));
            allMoviesPane.getChildren().clear();
            allMoviesPane.getChildren().add(movie);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML void watchTrailer(ActionEvent event) {
        // Ref: https://stackoverflow.com/a/18761332
        WebView webview = new WebView();
        webview.getEngine().load(
                "http://www.youtube.com/embed/" + baseController.getSelectedMovie().getTrailer() + "?autoplay=1"
        );
        webview.setPrefSize(1200, 600);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Cinema Booking System");
        stage.getIcons().add(new Image("main/resources/images/icon.png"));
        stage.setResizable(false);

        stage.setScene(new Scene(webview));
        stage.show();
    }
}