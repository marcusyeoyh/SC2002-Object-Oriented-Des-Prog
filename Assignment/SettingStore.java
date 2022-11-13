import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Setting Store represents a Store object with Singleton Class attributes, making it a unique instance which will be constantly referred to when running MOBLIMA
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class SettingStore {
    /**
     * HashMap of all Settings
     */
    private HashMap<String, String> settings = new HashMap<>();
    /** 
     * File path to settings.txt
     */
    private final String FILE_SOURCE = "Classes/src/settings.txt";
    /**
     * Static variable allowing there to be only one unique instance of SettingStore
     */
    private static SettingStore single_instance = null;
    /**
     * Creating a Scanner object to accept user input
     */
    Scanner sc = new Scanner(System.in);
    /**
     * Allows for reading of settings.txt and converting into String datatype
     */
    private TxtReaderWriter settingReaderWriter = new TxtReaderWriter(FILE_SOURCE);

    // Retrieve relevant settings from setting store
    /**
     * Gets relevant settings from setting store
     * @param setting indicates what setting status to be returned in String datatype
     * @return returns the setting status if it exists, else reurns NULL
     */
    public String getSetting(String setting) {
        return settings.get(setting);
    }

    // Set relevant setting into setting store
    /**
     * Changes Setting depending on what setting and option as input by user
     * @param setting Setting to be changed
     * @param option new option for the setting
     */
    public void setSetting(String setting, String option) {
        settings.put(setting, option);
    }

    /**
     * Initializes a new instance of SettingStore if it does not already exist
     */
    private SettingStore() {
        loadSettings(settingReaderWriter.getRawStringFromFile());
    }

    // Destructor
    /**
     * Destructor once the program terminates
     */
    public void closeSettingStore() {
        settingReaderWriter.setRawStringFromFile(parseHashMap());
    }

    /**
     * Calls the unique instance of SettingStore and initializes the Singleton class if it has not already been initialized
     * @return SettingStore class with HashMap of all settings.
     */
    public static SettingStore getInstance(){
        if (single_instance == null)
            single_instance = new SettingStore();

        return single_instance;
    }

    /**
     * Loading HashMap with settings from setting.txt
     * @param settingRawStore settings and their attributes in ArrayList form with String Array elements.
     */
    private void loadSettings(ArrayList<String[]> settingRawStore) {
        for (String[] line: settingRawStore) {
            settings.put(line[0], line[1]);
        }
    }

    /**
     * Parses HashMap and returns all the Settings as an ArrayList with String Array elements
     * @return an ArrayList with String Array elements containing Settings attributes
     */
    private ArrayList<String[]> parseHashMap() {
        ArrayList<String[]> arrayListOut = new ArrayList<>();
        for (String key: settings.keySet()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(key);
            line.add(settings.get(key));
            String[] out = new String[line.size()];
            arrayListOut.add(line.toArray(out));
        }
        return arrayListOut;
    }
}
