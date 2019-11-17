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
public class ElderlyReservation extends AdultReservation {
    
    public ElderlyReservation(char row, int col) {
        super(row, col);
    }
    
    @Override
    public float getTicketPrice() {
        return (super.getTicketPrice() - (super.getTicketPrice() * 30/100));
    }
    
}
