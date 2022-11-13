import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Store which contains a HashMap of Ticket objects created
 * Singleton Instance means that TicketStore object is unique
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class TicketStore {

    //Attributes
    /**
     * HashMap containing all ticket objects
     */
    private HashMap<String, Ticket> ticketHashMap = new HashMap<>();    // key=TRANSACTION_ID
    /**
     * File path for tickets.txt
     */
    private String path = ("Classes/src/tickets.txt");
    /**
     * Singleton instance of TicketStore
     */
    private static TicketStore single_instance = null;
    /**
     * Allows for reading of tickets.txt to obtain string information
     */
    private TxtReaderWriter ticketReaderWriter = new TxtReaderWriter(path);
    
    //Constructor
    /**
     * Creates a new TicketStore
     */
    private TicketStore(){
        loadTicketHashMap(ticketReaderWriter.getRawStringFromFile());
    }

    // Destructor
    /**
     * Destructor for TicketStore, saves HashMap data back into .txt file
     */
    public void closeTicketStore() {
        ticketReaderWriter.setRawStringFromFile(parseHashMap());
    }

    //Operations
    //Return single instance of store 
    /**
     * Returns Singleton instance of TicketStore and initializes TicketStore if NULL
     * @return TicketStore object
     */
    public static TicketStore getInstance(){
        if (single_instance == null){
            single_instance = new TicketStore();
        }
        return single_instance;
    }

    /**
     * Adds a ticket to TicketStore
     * @param ticket Ticket object to be added to the HashMap
     */
    public void addTicketToStore(Ticket ticket) {
        ticketHashMap.put(ticket.getTransactionID(), ticket);
    }

    /**
     * Gets a Ticket object from TicketStore based on provided transaction ID
     * @param transactionID String parameter used to search TicketStore HashMap for Ticket object
     * @return Ticket object returned with similar TransactionID
     */
    public Ticket getTicketFromStore(String transactionID) {
        return ticketHashMap.get(transactionID);
    }

    /**
     * Gets ArrayList of Ticket objects with similar user details
     * @param name name of person to be searched
     * @param email email of person to be searched
     * @param mobile mobile of person to be searched
     * @return the ArrayList of Ticket objects that fulfil all of the search parameters
     */
    public ArrayList<Ticket> getTicketWithUserDetails(String name, String email, String mobile){
        Set<String> keys = ticketHashMap.keySet();
        ArrayList<Ticket> correctTickets = new ArrayList<>();
        for (String key:keys){
            if (ticketHashMap.get(key).getUsername().equals(name) && ticketHashMap.get(key).getEmail().equals(email) && ticketHashMap.get(key).getMobile().equals(mobile)){
                correctTickets.add(ticketHashMap.get(key));
            }
        }
        return correctTickets;
    }

    /**
     * Loads the TicketStore HashMap from an ArrayList of String Array elements
     * @param ticketRawStore ArrayList of String Array elements
     */
    private void loadTicketHashMap(ArrayList<String []> ticketRawStore) {
        if (ticketRawStore.size() == 0)
            return;

        for (String[] line: ticketRawStore) {
            Ticket ticket = new Ticket(line[0]);
            ticket.setUsername(line[1]);
            ticket.setEmail(line[2]);
            ticket.setMobile(line[3]);
            ticket.setSeatID(line[4]);
            ticket.setAgeGroup(AgeGroup.valueOf(line[5]));
            ticket.setPrice(Double.parseDouble(line[6]));
            ticketHashMap.put(line[0], ticket);
        }
    }

    /**
     * Parses HashMap and returns contents in an ArrayList of String Array elements
     * @return an ArrayList of String Array elements
     */
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        Collection<Ticket> ticketCollection = ticketHashMap.values();

        for (Ticket ticket: ticketCollection) {
            ArrayList<String> line = new ArrayList<>();

            line.add(ticket.getTransactionID());
            line.add(ticket.getUsername());
            line.add(ticket.getEmail());
            line.add(ticket.getMobile());
            line.add(ticket.getSeatID());
            line.add(String.valueOf(ticket.getAgeGroup()));
            line.add(String.valueOf(ticket.getPrice()));

            String[] strArr = new String[line.size()];
            arrayListOut.add(line.toArray(strArr));
        }
        return arrayListOut;
    }
}