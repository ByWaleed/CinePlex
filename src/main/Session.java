package main;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session {

    private Integer id;
    private Integer movieId;
    private Integer theatreId;
    private LocalTime startTime;
    private LocalTime endtime;

    public Session(Integer id, Integer movieId, Integer theatreId, LocalTime startTime, LocalTime endtime) {
        this.id = id;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.startTime = startTime;
        this.endtime = endtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", theatreId=" + theatreId +
                ", startTime=" + startTime +
                ", endtime=" + endtime +
                '}';
    }
}
