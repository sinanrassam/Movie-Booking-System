/*
 * An abstract class for describing an abstract seat booking. It has an abstract
 * method getTicketPrice intended to be overridden by subclasses. It holds a row
 * (char) and column (int) of where the seat is positioned in the movie theatre
 * and a boolean to indicate whether the seat is complementary (free).
 */
package Reservation;

/**
 *
 * @author sinan
 */
public abstract class SeatReservation {
    
    private char row;
    private int col;
    protected boolean complementary;
    private boolean toggled;
    
    public SeatReservation(char row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public abstract float getTicketPrice();
    
    public void setComplementary (boolean complementary) {
        this.complementary = complementary;
    }
    
    public char getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
}
