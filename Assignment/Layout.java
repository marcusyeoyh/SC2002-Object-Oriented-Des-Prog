// Done by Mingyang
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents the Layout of each Cinema, containing information like the number of rows and columns
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class Layout {
    /**
     * Number of rows in the Cinema
     */
    private int rows;   // Num of rows
    /**
     * Number of columns in the Cinema
     */
    private int columns;    // Num of columns
    /**
     * integer number of aisle in the Cinema
     */
    private int aisle;      // Empty row (not part of rows)
    /**
     * integer number of main stairway in the Cinema
     */
    private int mainStairway;   // Empty column (not part of col)
    /**
     * 2D integer array of Cinema layout
     */
    private int[][] layout;

    /**
     * Creates a new Cinema Layout
     * @param rows the number of rows in this Cinema in int datatype
     * @param columns the number of columns in this Cinema in int datatype
     * @param aisle this Cinema's aisle in int datatype
     * @param mainStairway this Cinema's main starway in int datatype
     */
    public Layout(int rows, int columns, int aisle, int mainStairway) {
        this.aisle = aisle;
        this.columns = columns;
        this.rows = rows;
        this.mainStairway = mainStairway;

        layout = new int[rows][columns];
        // Fill array with 0s
        for (int[] row: layout) {
            Arrays.fill(row, 0);
        }
    }

    /**
     * Creates a HashMap containing all the seats of a particular Cinema
     * @return HashMap of all seats in a Cinema
     */
    public HashMap<String, Seat> createSeats() {
        HashMap<String, Seat> seats = new HashMap<>();

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                String seatID = generateSeatID(r,c);
                Seat seat = new Seat(seatID);
                // if (!SeatStore.getInstance().getSeatHashMap().get(seatID).getAvail()){
                //     seat.setAvail(false);
                // }
                seats.put(seatID, seat);
            }
        }
        return seats;
    }

    /**
     * Generates a Seat ID which is then used to Hash individual seats in createSeats()
     * @param r row number of seat in int datatype
     * @param c column number of seat in int datatype
     * @return this seat's ID in String datatype
     */
    public String generateSeatID(int r, int c) {
        return String.format("%c%d", r+64, c);
    }

    /**
     * Gets the number of rows in this Cinema's Layout
     * @return number of rows in int datatype
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in this Cinema's Layout
     * @return number of columns in int datatype
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the integer value of this in this Cinema's Aisle
     * @return location of aisle in int datatype
     */
    public int getAisle() {
        return aisle;
    }

    /**
     * Gets the integer value of this in this Cinema's main stairway
     * @return location of main stairway in int datatype
     */
    public int getMainStairway() {
        return mainStairway;
    }
}
