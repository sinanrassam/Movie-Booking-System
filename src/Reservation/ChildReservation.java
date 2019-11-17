package Reservation;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

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
