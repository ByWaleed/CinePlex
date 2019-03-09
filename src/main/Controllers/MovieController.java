package main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieController {

    @FXML private ImageView poster;

    public void setPoster(String address) {
        poster.setImage(new Image(address));
    }

}
