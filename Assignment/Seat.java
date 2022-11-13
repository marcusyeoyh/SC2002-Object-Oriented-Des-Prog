/**
 * This represents a seat in a particular layout in a particular Cinema
 * This Seat can be set to available or unavailable depending on if the seat has been purchased
 * @author Marc Chern
 * @version 1.0.0 Nov 13, 2022
 */
public class Seat {
    /**
     * The Seat ID given to each Seat object
     */
    private String seatID;  // eg.A1, [row][column]
    /**
     * indicates if the seat is available.
     * True = available
     * False = unabailable
     */
    private boolean isAvail;

    /**
     * Creates a new seat with given SeatID
     * @param seatID is the given SeatID of the Seat object in String datatype
     */
    public Seat(String seatID) {
        this.seatID = seatID;
        this.isAvail = true;
    }

    /**
     * Changes Availability of a Seat depending on Admin input
     * @param avail True = sets seat to available, False = sets seat to unavailable
     */
    public void setAvail(boolean avail){
        this.isAvail = avail; 
    }

    /**
     * Gets availability of this Seat
     * @return True = Seat available, False = Seat unavailable
     */
    public boolean getAvail(){
        return this.isAvail;
    }
    
    /**
     * Gets this Seat's ID
     * @return this Seat's ID in String datatype
     */
    public String getSeatID(){
        return this.seatID;
    }

}
