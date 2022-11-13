import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * This represents the Store of all the user accounts, known as Credentials, which are stored in a HashMap for easy access.
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class CredentialStore {
    //Attributes
    /**
     * HashMap containing all the Credential user accounts
     */
    private HashMap<String, Credential> credentialHashMap = new HashMap<>();
    /**
     * File path for credentials.txt
     */
    private String path = "Classes/src/credentials.txt";
    /**
     * ArrayList containing the String format of each Credential as read from credentials.txt
     */
    private ArrayList<Credential> credentials = new ArrayList<Credential>();
    /**
     * Singleton Instance of CredentialStore
     */
    private static CredentialStore single_instance = null;
    /**
     * Used to read credential.txt and convert the contents into String datatype
     */
    private TxtReaderWriter credentialReaderWriter = new TxtReaderWriter(path);
    
    //Constructor 
    /**
     * Creates a new CredentialStore and loads credentialHashMap with Credential objects obtained from credential.txt
     */
    private CredentialStore(){
        loadCredentialHashMap(credentialReaderWriter.getRawStringFromFile());
    }


    // parse HashMap to ArrayList<String[]>
    /**
     * Parses HashMap into ArrayList containing String Array elements
     * @return ArrayList containing String Array elements
     */
    private ArrayList<String[]> parseHashMap() {
        List<String[]> arrayListOut = new ArrayList<>();
        Set<String> keys = credentialHashMap.keySet();

        // Iterate over each Credential item
        for (String key: keys) {
            Credential credential = credentialHashMap.get(key);
            ArrayList<String> line = new ArrayList<>();

            line.add(credential.getUsername());
            line.add(credential.getPassword());
            line.add(String.valueOf(credential.getRole()));

            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return (ArrayList<String[]>) arrayListOut;
    }

    // Destructor
    /**
     * Destructor for ShowtTimeStore, writes all information in HashMap to the txt file to be saved
     * To be called before closing of program
     */
    public void closeShowTimeStore() {
        credentialReaderWriter.setRawStringFromFile(parseHashMap());
    }


    /**
    * Loads Credential HashMap with Credential information from txt file
    * @param credentialRawStore contains ArrayList of String Array elements to be loaded into the HashMap
    */
    private void loadCredentialHashMap(ArrayList<String[]> credentialRawStore) {
        for (String[] line : credentialRawStore) {
            AdminRole admRole = switch (line[2]) {
                case "CinemaStaff" -> AdminRole.CINEMA_STAFF;
                default -> AdminRole.CINEMA_STAFF; // lowest priority
            };
            Credential credential = new Credential(line[0], line[1], admRole);
            credentialHashMap.put(line[0], credential);
        }
    }

    //Return instance of store
    /**
    * Calls a Singleton Isntance of CredentialStore and initializes CredientialStore if it has not yet been done
    * @return unique CredentialStore object is returned
    */
    public static CredentialStore getInstance() {
            if (single_instance == null)
                single_instance = new CredentialStore();

            return single_instance;
    }

   
    // Return password given String
    /**
    * Parses through HashMap to check if the input username matches any keys in the HashMap
    * @param username input to be checked against the keys in the HashMap
    * @return the password of the key if there is a match, returns NULL if no match
    */
    public String getPassword(String username) {
        if (credentialHashMap.containsKey(username))
            return credentialHashMap.get(username).getPassword();
        else
            return null;
    }

}
