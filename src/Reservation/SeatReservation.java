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
