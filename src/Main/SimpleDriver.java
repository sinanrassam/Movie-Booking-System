package Main;

import Session.MovieSession;
import static Session.MovieSession.convertIndexToRow;
import Session.Time;
import Reservation.AdultReservation;
import Reservation.SeatReservation;
import Reservation.ElderlyReservation;
import Reservation.ChildReservation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sinan
 */
public class SimpleDriver {

    public static void main(String[] args) {
        List<MovieSession> movieSessions = new ArrayList();
        movieSessions.add(new MovieSession("Rambo 1", 'G', new Time())); // 1
        movieSessions.add(new MovieSession("Rambo 2", 'G', new Time(5, 4))); // 3
        movieSessions.add(new MovieSession("Rambo 3", 'M', new Time(12))); // 4
        movieSessions.add(new MovieSession("Rambo 4", 'G', new Time(0, 15))); // 2
        movieSessions.add(new MovieSession("Rambo 5", 'G', new Time(12, 00))); // 5

        Collections.sort(movieSessions);
        for (MovieSession movieSession : movieSessions) {
            System.out.println(movieSession);
        }

        ArrayList<SeatReservation> reservations = new ArrayList<>();
        reservations.add(new AdultReservation(convertIndexToRow(0), 5));
        reservations.add(new ChildReservation(convertIndexToRow(3), 5));
        reservations.add(new ElderlyReservation(convertIndexToRow(3), 6));
        
        reservations.get(0).setComplementary(true);

        movieSessions.get(0).applyBookings(reservations);

        movieSessions.get(0).printSeats();
        
//        ArrayList<SeatReservation> reservations2 = new ArrayList<>();
//        reservations2.add(new AdultReservation(convertIndexToRow(0), 5));
//        reservations2.add(new ChildReservation(convertIndexToRow(2), 5));
//        reservations2.add(new ChildReservation(convertIndexToRow(1), 5));
//
//        movieSessions.get(2).applyBookings(reservations);
//        movieSessions.get(2).printSeats();
    }
}
