/*
 * A class which represents a movie with a name, a rating (R, M or G) and a
 * screening time using the Time class.
 */
package Session;

import Reservation.AdultReservation;
import Reservation.SeatReservation;
import Reservation.ElderlyReservation;
import Reservation.ChildReservation;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sinan
 */
public class MovieSession implements Comparable<MovieSession> {
    
    private String movieName;
    private char rating;
    private Time sessionTime;
    private SeatReservation[][] sessionSeats;
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLS = 6;
    
    public MovieSession(String movieName, char rating, Time sessionTime) {
        this.movieName = movieName;
        this.rating = (Character.toUpperCase(rating)); // convert to uppercase as a safety net for user error
        this.sessionTime = sessionTime;
        sessionSeats = new SeatReservation[NUM_ROWS][NUM_COLS];
    }
    
    public static int convertRowsIndex(char rowLetter) {
        return (int) (Character.toUpperCase(rowLetter) - 65);
    }
    
    public static char convertIndexToRow(int rowIndex) {
        return (char) (Character.toUpperCase(rowIndex) + 65);
    }
    
    public char getRating() {
        return rating;
    }
    
    public String getMovieName() {
        return movieName;
    }
    
    public Time getSessionTime() {
        return sessionTime;
    }
    
    public SeatReservation getSeat(char row, int col) {
        return sessionSeats[convertRowsIndex(row)][col];
    }
    
    public boolean isSeatAvailable(char row, int col) {
        return (getSeat(row, col) == null);
    }
    
    public boolean applyBookings(List<SeatReservation> reservations) {
        int child = 0, adult = 0;
        for (SeatReservation seat : reservations) {
            if (!isSeatAvailable(seat.getRow(), seat.getCol())) {
                JOptionPane.showMessageDialog(null, "Over booking");
                return isSeatAvailable(seat.getRow(), seat.getCol());
            } else {
                if (seat instanceof ChildReservation) {
                    child++;
                }
                if (seat instanceof AdultReservation) {
                    adult++;
                }
            }
        }
        int total = child + adult;
        String message;
        if (total > 1) message = "tickets";
        else message = "ticket";
        if ((((child > 0) && (adult <= 0) && (getRating() == 'M'))) || ((child > 0) && (getRating() == 'R'))) {
                JOptionPane.showMessageDialog(null, "CANNOT BOOK A CHILD IN 'R' MOVIE OR UNSUPERVISED IN 'M' MOVIES", "Booking Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            float cost = 0.0f;
            for (SeatReservation seat : reservations) {
                sessionSeats[convertRowsIndex(seat.getRow())][seat.getCol()] = seat;
                cost += seat.getTicketPrice();
            }
            JOptionPane.showMessageDialog(null, "TICKET COST IS: $" + cost, + total + " " + message + " " + "booked", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }
    
    public void printSeats() {
        String seats = "";
        for (int i = 0; i < NUM_ROWS; i++) {
            char row = convertIndexToRow(i);
            for (int col = 0; col < NUM_COLS; col++) {
                seats += "|";
                if (isSeatAvailable(row, col)) {
                    seats += "_";
                } else {
                    if (sessionSeats[i][col] instanceof ElderlyReservation) {
                        seats += "E";
                    } else if (sessionSeats[i][col] instanceof AdultReservation) {
                        seats += "A";
                    } else {
                        seats += "C";
                    }
                }
                seats += "|";
            }
            seats += "\n";
        }
        System.out.println(seats);
    }
    
    @Override
    public int compareTo(MovieSession o) {
        int check = this.getSessionTime().compareTo(o.getSessionTime());
        if (check == 0) {
            check = this.getMovieName().compareTo(o.getMovieName());
        }
        return check;
    }
    
    @Override
    public String toString() {
        return getMovieName() + "(" + getRating() + ") - " + getSessionTime();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MovieSession s1 = new MovieSession("Rambo 4", 'G', new Time(12));
        MovieSession s2 = new MovieSession("Rambo 5", 'G', new Time(12));
        System.out.println(s1.compareTo(s2));
    }
}
