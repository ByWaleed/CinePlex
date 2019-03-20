package main.Controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.sun.org.apache.bcel.internal.generic.InstructionConstants.POP;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static javafx.scene.input.KeyCode.N;

public class SelectTheatreSeatsController implements Initializable {

    @FXML private AnchorPane selectTheatreSeatsAP;

    @FXML private ComboBox<Theatre> availableTheatreCB;
    @FXML private ListView<String> availableTimesLV;
    @FXML private GridPane seatsGrid;

    @FXML private Pane seatsGridContainer;

    @FXML private TableView<ReservedSeat> selectedSeatsTV;
    @FXML private TableColumn<ReservedSeat, String> selectedSeatType;
    @FXML private TableColumn<ReservedSeat, Point> selectedSeatLocation;
    @FXML private TableColumn<ReservedSeat, Double> selectedSeatPrice;

    @FXML private Text totalSeatsPrice;

    private ArrayList<ReservedSeat> reservedSeats = new ArrayList<>(0);
    private ArrayList<Seat> selectedSeats = new ArrayList<>(0);
    private ArrayList<Session> timeForSessions = new ArrayList<>(0);

    private ArrayList<Session> availableSessions = new ArrayList<>(0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Hide Seats Grid
        seatsGridContainer.setVisible(false);

        // Reset Display Objects
        totalSeatsPrice.setText("£0.00");
        reservedSeats.clear();
        selectedSeats.clear();
        timeForSessions.clear();

        Movie selectedMovie = baseController.getSelectedMovie();

        ArrayList<Session> sessions = baseController.getSessions();
        ArrayList<Theatre> theatres = baseController.getTheatres();

        // All theatres in which selected movie is showing
        ArrayList<Theatre> movieInTheatres = new ArrayList<>(0);
        for (Session session : sessions) {
            if (session.getMovieId().equals(selectedMovie.getId())) {
                availableSessions.add(session);
                movieInTheatres.add(theatres.get(session.getTheatreId() - 1));
            }
        }

        // Remove duplicated theatre listing: https://stackoverflow.com/a/40419515
        movieInTheatres = new ArrayList<>(new LinkedHashSet<>(movieInTheatres));

        // Add theatres in combobox
        ObservableList<Theatre> theatresOL = FXCollections.observableArrayList(movieInTheatres);
        availableTheatreCB.setItems(theatresOL);

        StringConverter<Theatre> converter = new StringConverter<Theatre>() {
            @Override
            public String toString(Theatre theatre) {
                return theatre.getName();
            }

            @Override
            public Theatre fromString(String id) {
                return theatres.get(Integer.parseInt(id));
            }
        };
        availableTheatreCB.setConverter(converter);

        // Add event handler to the combobox items
        availableTheatreCB.getSelectionModel().selectedItemProperty().addListener((ov, ol, nw) -> {
            ObservableList<String> availableTimesOL = FXCollections.observableArrayList();

            timeForSessions.clear();
            seatsGridContainer.setVisible(false);
            selectedSeatsTV.getItems().clear();

            for (Session session : sessions) {

                Integer selectedTheatreIndex = ov.getValue().getId() - 1;
                Theatre selectedTheatre = baseController.getTheatres().get(selectedTheatreIndex);

                // Session with same movieId and theatreId
                if (session.getTheatreId().equals(selectedTheatre.getId()) && session.getMovieId().equals(selectedMovie.getId()) )  {
                    System.out.println("Confirmed: " + session);
                    availableTimesOL.add(session.getStartTime() + " - " + session.getEndtime());
                    timeForSessions.add(session);
                }
            }

            availableTimesLV.setItems(availableTimesOL);
            ov.getValue().getName();
        });

        selectedSeatsTV.setPlaceholder(new Label("Please select some seats."));
    }

    @FXML void showSelectedSeats(ActionEvent event) {
        if (availableTimesLV.getSelectionModel().getSelectedIndex() == -1) {
            seatsGridContainer.setVisible(false);
            errorMessage("Opps... an error occurred", "Please select a theatre and available time.");
        } else {
            // generate grid in gridContainer
            GridPane seatsGrid = new GridPane();

            // Selected Session, to get selected theatre id
            Integer selectedSessionIndex = availableTimesLV.getSelectionModel().getSelectedIndex();
            Session selectedSession = availableSessions.get(selectedSessionIndex);

            // Get seats of the selected theatre
            System.out.println("Session:" + selectedSession);
            ArrayList<Seat> seats = baseController.getTheatreSeats(selectedSession.getTheatreId());

            // Add seats as buttons (plus event hanlers)
            for (Seat seat : seats) {
                if (seat.getTheatreId().equals(selectedSession.getTheatreId())) {
                    Integer x = (int) seat.getLocation().getX();
                    Integer y = (int) seat.getLocation().getY();

                    /* Coordinates flipped to keep consistent with (x,y) as
                     *  JavaFx flips the coordinates of the Grid. */
                    Button button = new Button("Available");
                    if (seatReserved(seat)) {
                        //button.setText("Reserved");
                        button.setDisable(true);
                    }
                    button.setId("" + seat.getId());
                    button.setOnAction(this::seatSelected);
                    seatsGrid.add(button, x, y);
                }
            }

            // attach seatsGrid to seatsGridContainer
            seatsGridContainer.getChildren().clear();
            seatsGridContainer.getChildren().add(seatsGrid);

            seatsGridContainer.setVisible(true);

            selectedSeatsTV.getItems().clear(); // Clear Selected Seats Table
        }
    }

    private Boolean seatReserved(Seat seat) {
        ArrayList<BookingItem> bookingItems = new ArrayList<>(0);
        bookingItems.addAll(baseController.getBookingItems());
        bookingItems.addAll(CartController.getQuantityItems());

        for (BookingItem item : bookingItems) {
            // If not Snack
            if (item.getSeatId() != null) {
                if (item.getSeatId().equals(seat.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    @FXML void seatSelected(ActionEvent event){
        ArrayList<Seat> seats = baseController.getSeats();
        Button selectedButton = (Button)event.getSource();
        String selectedSeatId = ((Control)event.getSource()).getId();

        Seat selectedSeat = seats.get(Integer.parseInt(selectedSeatId) - 1);
        System.out.println("Selected Seat: " + selectedSeat.getId());

        if (selectedSeats.contains(selectedSeat)) {
            selectedButton.setText("Available");
            selectedSeats.remove(selectedSeat);

            ReservedSeat toRemove = null;

            for (ReservedSeat seat : reservedSeats) {
                if (seat.getSeatId().equals(selectedSeat.getId())) {
                    toRemove= seat;
                    break;
                }
            }
            reservedSeats.remove(toRemove);
        } else {
            selectedButton.setText("Selected");
            selectedSeats.add(selectedSeat);

            reservedSeats.add(
                    new ReservedSeat(
                            selectedSeat.getId(),
                            selectedSeat.getType(),
                            "" + (int)selectedSeat.getLocation().getX() + "," + (int)selectedSeat.getLocation().getY(),
                            "£" + baseController.getSelectedMovie().getPrice()
                    )
            );
        }

        // Show selected seats in ListView
        ObservableList<ReservedSeat> reservedSeatsOL = FXCollections.observableArrayList(reservedSeats);
        //selectedSeatsLV.setItems(reservedSeatsOL);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        selectedSeatType.setCellValueFactory(new PropertyValueFactory<>("type"));
        selectedSeatLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        selectedSeatPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        //add your data to the table here.
        selectedSeatsTV.setItems(reservedSeatsOL);

        // Total Cost
        Double total = selectedSeats.size() * baseController.getSelectedMovie().getPrice();
        totalSeatsPrice.setText("£" + total);
    }

    @FXML void addSeatsToCart(ActionEvent event) {
        if (reservedSeats.size() > 0) {
            Integer selectedSessionIndex = availableTimesLV.getSelectionModel().getSelectedIndex();
            Session selectedSession = timeForSessions.get(selectedSessionIndex);

            for (ReservedSeat seat : reservedSeats) {
                CartItem item = new CartItem(
                        selectedSession.getId(),
                        seat.getSeatId()
                );
                baseController.getCart().addItem(item);
            }

            // Show alert for confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cinema Booking System");
            alert.setHeaderText(null);
            alert.setContentText("\nYour cart has been updated successfully.");
            alert.showAndWait();

            // Take user back to the movie/home screen
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            errorMessage("Opps... an error occurred", "Please select at least one seat.");
        }
    }

    private void errorMessage(String header, String body) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cinema Booking System");
        alert.setHeaderText(header);
        alert.setContentText(body);
        alert.showAndWait();
    }
}
