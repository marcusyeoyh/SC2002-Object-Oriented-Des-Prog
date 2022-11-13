import java.io.*;
import java.util.ArrayList;
/**
* Purpose of this class:
* 1. Read from .txt into raw ArrayList<String[]> using readFile(String fileSource) --> taken care of automatically
* 2. Write from raw ArrayList<String[]> into .txt file using writeFile(String fileSource) --> taken care of automatically

* Notes:
* ArrayList should only be read and written once by respective store class.
* Updates to stores should update store class only and not ArrayList
* Header file will be automatically taken care of by TxtReaderWriter
* @author Koh Mingyang
* @version 1.0.0 Nov 13, 2022
*/
public class TxtReaderWriter {
    /**
     * Raw string for each element of txt file
     */
    private ArrayList<String[]> rawStringFromFile = new ArrayList<>();
    /**
     * String variable to contain txt header line
     */
    private String header;
    /**
     * File path for txt file
     */
    private final String FILE_SOURCE;

    /**
     * Allows for the reading of the txt file
     * @param fileSource location for the file path of the text
     */
    public TxtReaderWriter(String fileSource) {
        this.FILE_SOURCE = fileSource;
        readFile();
    }

    /**
     * Gets Raw string from txt file
     * @return an ArrayList with String Array elements containing attributes of the txt file
     */
    public ArrayList<String[]> getRawStringFromFile() {
        return rawStringFromFile;
    }

    /**
     * Changes the Raw string read from the txt file
     * @param rawStringFromFile new ArrayList of String Array elements to replace current rawStringFromFile attribute
     */
    public void setRawStringFromFile(ArrayList<String[]> rawStringFromFile) {
        this.rawStringFromFile = rawStringFromFile;
        writeFile();
    }

    // Call before terminating program
    /**
     * Writes to txt file to save before terminating program
     */
    private void writeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_SOURCE));
            writer.write(header);

            for (String[] line: rawStringFromFile) {
                writer.write("\n" + String.join("|", line));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    /**
     * Reads txt file and parses through each line, splitting the String line into individual String elements, which are then passed on to rawStringFromFile.
     */
    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
            this.header = reader.readLine();    // Header

            String line = reader.readLine();
            while (line != null) {
                rawStringFromFile.add(line.split("\\|"));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
