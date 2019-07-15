package main;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import main.Controllers.baseController;

import java.awt.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class dummyData {

    public dummyData() {}

    public static void createTheatres() {

        ArrayList<Theatre> theatres = baseController.getTheatres();

        theatres.add(new Theatre(theatres.size() + 1, "Theatre One", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Two", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Three", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Four", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Five", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Six", "Some Random Location"));
        theatres.add(new Theatre(theatres.size() + 1, "Theatre Seven", "Some Random Location"));

        baseController.setTheatres(theatres);
    }

    public static void createSeats() {
        ArrayList<Seat> seats = baseController.getSeats();

        ArrayList<Theatre> theatres = baseController.getTheatres();

        // 8 Rows of 10 seats for each theatres
        for (Theatre theatre : theatres) {
            for (Integer row=0; row < 8; row++) {
                for (Integer column=0; column < 10; column++) {

                    if ((row == 4 || row == 5) && (column == 5 || column == 6 || column == 7)) {
                        seats.add(new Seat(seats.size() + 1, "Premium", theatre.getId(), new Point(row, column)));
                    } else {
                        seats.add(new Seat(seats.size() + 1, "Regular", theatre.getId(), new Point(row, column)));
                    }
                }
            }
        }

        baseController.setSeats(seats);
    }

    public static void createMovies() {
        LocalDate random = LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("02"), Integer.parseInt("01"));
        LocalDate batmanvsupermanDate = LocalDate.of(Integer.parseInt("2016"), Integer.parseInt("03"), Integer.parseInt("25"));
        LocalDate avengersDate = LocalDate.of(Integer.parseInt("2019"), Integer.parseInt("04"), Integer.parseInt("25"));

        ArrayList<Movie> movies = baseController.getMovies();

        movies.add(new Movie(movies.size() + 1, "Batman vs Superman", "Zack Snyder", "Chris Terrio, David S. Goyer", " Ben Affleck, Henry Cavill, Amy Adams", "12A", batmanvsupermanDate, "Fearing that the actions of Superman are left unchecked, Batman takes on the Man of Steel, while the world wrestles with what kind of a hero it really needs.", "Action, Adventure, Fantasy", 8.5, 12, 5.79, "English", true, "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX200.jpg", "tt2975590", "14 wins and 33 nominations", "0WWzgGyAH6Y"));
        movies.add(new Movie(movies.size() + 1, "Avengers End Game", "Anthony Russo, Joe Russo", "Christopher Markus, Stephen McFeely", "Brie Larson, Karen Gillan, Scarlett Johansson", "12A", avengersDate, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.", "Action, Adventure, Fantasy", 8.7, 10, 6.49, "English", true, "https://m.media-amazon.com/images/M/MV5BNGZiMzBkZjMtNjE3Mi00MWNlLWIyYjItYTk3MjY0Yjg5ODZkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX200.jpg", "tt4154796", "0 wins and 0 nominations", "TcMBFSGVi1c"));
        movies.add(new Movie(movies.size() + 1, "Blade Runner 2049", "Denis Villeneuve", "Hampton Fancher", "Harrison Ford, Ryan Gosling, Ana de Armas", "15", random, "A young blade runner's discovery of a long-buried secret leads him to track down former blade runner Rick Deckard, who's been missing for thirty years.", "Drama, Mystery, Sci-Fi", 7.5, 16, 12.00, "English", true, "https://m.media-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX200.jpg", "tt1856101", "Won 2 Oscars. Another 88 wins & 144 nominations.", "gCcx85zbxz4"));
        movies.add(new Movie(movies.size() + 1, "Star Wars the Last Jedi", "Rian Johnson",  "Rian Johnson, George Lucas", "Daisy Ridley, John Boyega, Mark Hamill", "12A", random, "Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares for battle with the First Order. ", "Action, Adventure, Fantasy ", 7.2, 12, 6.99, "English", true, "https://m.media-amazon.com/images/M/MV5BMjQ1MzcxNjg4N15BMl5BanBnXkFtZTgwNzgwMjY4MzI@._V1_SX200.jpg", "tt2527336", "Nominated for 4 Oscars. Another 22 wins & 86 nominations.", "Q0CbN8sfihY"));
        movies.add(new Movie(movies.size() + 1, "Matrix", "Lana Wachowski", "Lilly Wachowski", "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss", "15", random, "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers. ", "Action, Sci-Fi", 8.7, 15, 7.49, "English", true, "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX200.jpg", "tt0133093", "16 wins & 49 nominations.", "vKQi3bBA1y8"));
        movies.add(new Movie(movies.size() + 1, "The Dark Night", "Christopher Nolan", "Jonathan Nolan", "Christian Bale, Heath Ledger, Aaron Eckhart", "12A", random, "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "Action, Crime, Drama", 9.0, 16, 7.39, "English", true, "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX200.jpg", "tt0468569", " Won 2 Oscars. Another 152 wins & 155 nominations.", "EXeTwQWrcwY"));
        movies.add(new Movie(movies.size() + 1, "Terminator 2", "James Cameron", "James Cameron, William Wisher", "Arnold Schwarzenegger, Linda Hamilton, Edward Furlong" , "15", random, "A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her teenage son, John Connor, from a more advanced and powerful cyborg.", "Action, Sci-Fi", 8.5, 15, 6.50, "English", true, "https://m.media-amazon.com/images/M/MV5BMGU2NzRmZjUtOGUxYS00ZjdjLWEwZWItY2NlM2JhNjkxNTFmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX200.jpg", "tt0103064", "Won 3 Oscars. Another 85 wins & 130 nominations.", "lwSysg9o7wE"));

        baseController.setMovies(movies);
    }

    public static void createSessions() {
        ArrayList<Session> sessions = baseController.getSessions();
        ArrayList<Theatre> theatres = baseController.getTheatres();
        ArrayList<Movie> movies = baseController.getMovies();

        LocalTime start = LocalTime.of(18,00);
        LocalTime end = LocalTime.of(19,00);

        Integer count = 0;
        for (Theatre theatre : theatres) {
            sessions.add(new Session(sessions.size() + 1, movies.get(count).getId(), theatre.getId(), start, end));
            count++;
        }

        sessions.add(new Session(sessions.size() + 1, movies.get(6).getId(), theatres.get(5).getId(), LocalTime.of(21,00), LocalTime.of(22,00)));
        //sessions.add(new Session(sessions.size() + 1, movies.get(6).getId(), theatres.get(4).getId(), LocalTime.of(22,00), LocalTime.of(23,30)));

        baseController.setSessions(sessions);
    }

    public static void createSnacks() {
        ArrayList<Snack> snacks = new ArrayList<> (0);
        snacks.add(new Snack(snacks.size() + 1, "Popcorn Bucket", 2.50, 25, "https://target.scene7.com/is/image/Target/GUEST_2233bb5e-de0e-4669-a05e-455421d9ac47?wid=200&hei=300&fmt=pjpeg"));
        snacks.add(new Snack(snacks.size() + 1, "Pepsi Can", 0.75, 25, "https://i.imgur.com/vgycWNe.jpg"));
        snacks.add(new Snack(snacks.size() + 1, "Doritos", 1.75, 25, "http://s-tickets.com/wordpress/wp-content/uploads/2015/01/doritos-coolranch.png"));
        snacks.add(new Snack(snacks.size() + 1, "Cheetos (Pack of 4)", 4.49, 25, "https://i.ebayimg.com/images/g/cvkAAOSw-4Jb9Mzi/s-l300.png"));

        baseController.setSnacks(snacks);
    }
}
