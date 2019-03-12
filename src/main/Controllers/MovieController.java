package main.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.Movie;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieController implements Initializable{

    private final String USER_AGENT = "Mozilla/5.0";
    @FXML private ImageView moviePoster;
    @FXML private Text movieTitle;
    @FXML private Text movieYear;
    @FXML private Text movieGenre;
    @FXML private Text movieDirector;
    @FXML private Text movieWriter;
    @FXML private Text movieActors;
    @FXML private Text moviePlot;
    @FXML private Text movieRating;
    @FXML private Text movieRating1;
    @FXML private Text movieSeatsAvailable;

    @FXML void buySeats(ActionEvent event) {

    }

    @FXML void watchTrailer(ActionEvent event) {

    }

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

        //print result
        return response.toString();

    }
}