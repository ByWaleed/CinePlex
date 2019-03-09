package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/views/base.fxml"));

        /* Title & Icon */
        primaryStage.setTitle("Cinema Booking System");
        primaryStage.getIcons().add(new Image("main/resources/images/icon.png"));

        /* baseController Window Properties */
        Scene allMovies = new Scene(root, 1200, 600);
        
        primaryStage.setScene(allMovies);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
