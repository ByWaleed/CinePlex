package main.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Movie;
import main.Snack;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SnacksController implements Initializable {

    @FXML private ScrollPane allSnacksSP;
    @FXML private AnchorPane allSnacksPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createDummySnacks();
        displaySnacks();
    }

    private void createDummySnacks() {
        ArrayList<Snack> snacks = new ArrayList<> (0);
        snacks.add(new Snack(snacks.size() + 1, "Popcorn Bucket", 2.50, 25, "https://i.imgur.com/dV5qHyG.jpg"));
        snacks.add(new Snack(snacks.size() + 1, "Pepsi Can", 0.75, 25, "https://i.imgur.com/vgycWNe.jpg"));

        baseController.setSnacks(snacks);
    }

    public void displaySnacks() {

        allSnacksSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ArrayList<Snack> snacks = baseController.getSnacks();

        int rows = 0;
        int columns = 4;
        rows = snacks.size() / columns;
        if (snacks.size() % columns > 0) {
            rows++;
        }

        int snacksAdded = 0;
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

            for (int c = 0; (c < columns) && (snacksAdded < snacks.size()); c++) {
                // Add Movies to r HBox
                Image img = new Image(snacks.get(snacksAdded).getPoster());
                ImageView posterIV = new ImageView(img);
                posterIV.setX(c * 230);
                posterIV.setCursor(Cursor.HAND);
                posterIV.setId("" + snacksAdded);

                row.getChildren().add(posterIV);
                snacksAdded++;

                // Add event handler for each movie
                posterIV.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // Set selectedSnack
                        ImageView posterIV = (ImageView) mouseEvent.getSource();
                        Integer id = Integer.parseInt(posterIV.getId());
                        Snack selectedSnack = snacks.get(id);
                        baseController.setSelectedSnack(selectedSnack);

                        // Open snack quantity window
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/views/snack.fxml"));
                        try {
                            Parent selectSeatsView = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(selectSeatsView, 600, 250));
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("Cinema Booking System");
                            stage.getIcons().add(new Image("main/resources/images/icon.png"));
                            stage.setResizable(false);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
            allSnacksPane.getChildren().add(row);
        }
    }
}
