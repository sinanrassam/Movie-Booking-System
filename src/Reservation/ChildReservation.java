/*
 * Subclass of SeatReservation for allowing the seat to be filled by a child
 */
package Reservation;

/**
 *
 * @author sinan
 */
public class ChildReservation extends SeatReservation {
    
    private final float ticketPrice = 8.0f;
    
    public ChildReservation(char row, int col) {
        super(row, col);
    }
    
    @Override
    public float getTicketPrice() {
        if (super.complementary) return 0.0f;
        return ticketPrice;
    }    
    
}
