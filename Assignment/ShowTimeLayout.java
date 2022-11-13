import java.util.HashMap;
import java.util.List;

/* Created by Mingyang
*  Purpose of class: know which seats are taken and which are not
*  Has a HashMap containing all Seats in this ShowTime */
/**
 * This represents the Layout of a Cinema for a particular showtime. This will allow users to see which seats have been taken and which seats have not, providing better user experience for customers when purchasing tickets.
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class ShowTimeLayout {
    /**
     * Unchangable variable HashMap representing the seat layout of the cinema
     */
    private final HashMap<String, Seat> seatHashMap;    // key=SEAT_ID
    /**
     * Unchangable integer representing the number of rows in the cinema
     */
    private final int rows;
    /**
     * Unchangable integer representing the number of columns in the cinema
     */
    private final int columns;
    /**
     * Unchangable integer representing the location of aisle in the cinema
     */
    private final int aisle;
    /**
     * Unchangable integer representing the location of main stairway in the cinema
     */
    private final int mainStairway;
    /**
     * Unchangable Layout representing the layout of the cinema
     */
    private final Layout layout;
    


    /**
     * Creates a new ShowTimeLayout with a provided showtimeID
     * @param showtimeID the user input parameter used to create a showtime layout
     */
    public ShowTimeLayout(String showtimeID) {
        //Generating Cinema layout from cineplexes.txt
        this.layout = LayoutStore.getInstance().getLayout(showtimeID.substring(0,4));
        this.seatHashMap = layout.createSeats();

        this.rows = layout.getRows();
        this.columns = layout.getColumns();
        this.aisle = layout.getAisle();
        this.mainStairway = layout.getMainStairway();

        //Get occupied seats from seats.txt
        List<String> occupiedSeats = SeatStore.getInstance().getSeatsForShowTime(showtimeID);
        if (occupiedSeats != null){
            for(int i = 0; i < occupiedSeats.size(); i++){
                seatHashMap.get(occupiedSeats.get(i)).setAvail(false); //Marking seats as unavailable
            }
        }

    }

    /**
     * Gets the seat HashMap of this Showtime layout
     * @return the HashMap of this Showtime Layout
     */
    public HashMap<String, Seat> getSeatHashMap() {
        return seatHashMap;
    }

    /**
     * Gets the Seat object with a particular SeatID from the Seat HashMap
     * @param seatID the ID of the seat in String datatype
     * @return the Seat object with the same seatID as input by the user
     */
    public Seat getSeat(String seatID) {
        return seatHashMap.get(seatID);
    }

    /**
     * prints the layout of the seat
     */
    public void printLayout() {
        String s1 = "-";
        String s = s1.repeat((columns * 4 - 4) / 2);
        System.out.println(s + "LEGEND" + s);
        System.out.println("[  ]: Seat available");
        System.out.println("[XX]: Seat unavailable");
        System.out.println(s + "------" + s);
        System.out.println(s + "SCREEN" + s);

        // Print column numbers
        printColumnNumbers();

        for (int r = 1; r <= rows; r++) {
            if (r == aisle) {
                System.out.println("");
                for (int c=1;c<=columns;c++){
                    String seatID = layout.generateSeatID(r, c);
                    getSeat(seatID).setAvail(false);
                }
                continue;
            }
            // Print row letters
            System.out.printf("%c", r+64);

            // Print seats, [  ] for empty [XX] for taken
            for (int c = 1; c <= columns; c++) {
                String seatID = layout.generateSeatID(r, c);
                if (c == mainStairway){
                    getSeat(seatID).setAvail(false);
                    System.out.print("    ");
                }
                else if (getSeat(seatID).getAvail()) {
                    System.out.print("[  ]");
                }
                else {
                    System.out.print("[XX]");
                }
            }
            System.out.printf("%c\n", r+64);
        }

        // Print column numbers
        printColumnNumbers();
    }

    /**
     * prints the column numbers of a particular Cinema Showtime Layout to be displayed visually as part of the overall layout to allow for customers to choose their seats better.
     */
    private void printColumnNumbers() {
        System.out.print(" ");
        for (int c = 1; c <= columns; c++) {
            if (c == mainStairway) {
                System.out.print("    ");
            }
            else
                System.out.printf("[%02d]", c);
        }
        System.out.println();
    }
}
