/*
 * Subclass of SeatReservation for allowing the seat to be filled by an adult
 */
package Reservation;

/**
 *
 * @author sinan
 */
public class AdultReservation extends SeatReservation {
    
    private final float ticketPrice = 12.50f;
    
    public AdultReservation(char row, int col) {
        super(row, col);
    }

    @Override
    public float getTicketPrice() {
        if (super.complementary) return 0.0f;
        return ticketPrice;
    }
    
}
