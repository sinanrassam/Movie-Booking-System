/*
 * Subclass of SeatReservation for allowing the seat to be filled by an elderly
 */
package Reservation;

/**
 *
 * @author sinan
 */
public class ElderlyReservation extends AdultReservation {
    
    public ElderlyReservation(char row, int col) {
        super(row, col);
    }
    
    @Override
    public float getTicketPrice() {
        return (super.getTicketPrice() - (super.getTicketPrice() * 30/100));
    }
    
}
