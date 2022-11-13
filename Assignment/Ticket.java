/**
 * A Ticket object
 * Every Movie-Goer will have many tickets
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
public class Ticket {
    /**
     * Transaction ID of the Ticket
     */
    private String transactionID;
    /**
     * price of the Ticket
     */
    private double price;
    /**
     * username of the owner of the Ticket
     */
    private String username;
    /**
     * email of the owner of the Ticket
     */
    private String email;
    /**
     * mobile of the owner of the Ticket
     */
    private String mobile;
    /**
     * seat ID of the Ticket
     */
    private String seatID;
    /**
     * Age Group of the Ticket
     */
    private AgeGroup ageGroup;
    
    /**
     * Default constructor for the Ticket
     * @param transactionID - Used as unique key identifier
     */
    Ticket(String transactionID){
        this.transactionID = transactionID;
    }
    /**
     * Gets the transaction ID of this Ticket
     * @return the trasaction ID of this Ticket in String format
     */
    public String getTransactionID() {
        return transactionID;
    }
    /**
     * Gets the price of this Ticket
     * @return the price of this Ticket in double format
     */
    public double getPrice() {
        return price;
    }
    /**
     * Gets the username of the purchaser of this Ticket
     * @return the username of this Ticket in String format
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the email of the purchaser of this Ticket
     * @return the email of this Ticket in String format
     */
    public String getEmail() {
        return email;
    }
    /**
     * Gets the mobile of the purchaser of this Ticket
     * @return the mobile of this Ticket in String format
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * Gets the Seat ID of this Ticket
     * @return the Seat ID of this Ticket in String format
     */
    public String getSeatID() {
        return seatID;
    }
    /**
     * Gets the age group of this Ticket
     * @return the age group of this Ticket in AgeGroup format
     */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }
    /**
     * Changes the Transaction ID of this Ticket based on parameter input
     * @param transactionID the new Transaction ID of this Ticket
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    /**
     * Changes the Price of this Ticket based on parameter input
     * @param price the new Price of this Ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Changes the username of this Ticket based on parameter input
     * @param username the new username of this Ticket
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Changes the email of this Ticket based on parameter input
     * @param email the new email of this Ticket
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Changes the mobile of this Ticket based on parameter input
     * @param mobile the new mobile of this Ticket
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * Changes the Seat ID of this Ticket based on parameter input
     * @param seatID the new Seat ID of this Ticket
     */
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }
    /**
     * Changes the age group of this Ticket based on parameter input
     * @param ageGroup the new age group of this Ticket
     */
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
}
