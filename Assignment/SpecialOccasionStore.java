import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Store represents a Singleton Class where a unique object SpecialOccasionStore is created, which is then referenced throughout MOBLIMA
 * The data is obtained from specialOccasions.txt which is then passed into an ArrayList of special occasion objects for use elsewhere
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class SpecialOccasionStore {
    //Attributes
    /**
     * String header to store the header for specialOccasions.txt
     */
    private String specialOccasionHeader;
    /**
     * ArrayList of SpecialOccasion objects
     */
    private ArrayList<SpecialOccasion> specialOccasions = new ArrayList<SpecialOccasion>(); 
    /**
     * File path for specialOccasions.txt
     */
    private String path = ("Classes/src/specialOccasions.txt");
    /**
     * Singleton instance of SpecialOccasionStore
     */
    private static SpecialOccasionStore instance = new SpecialOccasionStore();

    //Contstuctor
    /**
     * Creates a new SpecialOccasionStore if it does not exist already
     */
    private SpecialOccasionStore(){
        //reading special occasion list file path
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            this.specialOccasionHeader = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                specialOccasions.add(createSpecialOccasionObj(line)); //Add pricing object to special occasion array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            //System.out.println(err.getStackTrace());
            System.out.println("Error: Special Occasion list not found");
        }

    }

    //Operations
    /**
     * Calls the Singleton isntance of SpecialOccasionStore, initializing it if it does not exist yet
     * @return SpecialOccasionStore object is returned
     */
    public static SpecialOccasionStore getInstance(){
        return instance;
    }

    //Creates a new special occasion object based on information retreived from one line of the txt file
    /**
     * Creates a new special occasion object based on information retreived from one line of the txt file
     * @param info a lne of String from the txt file to be seperated and create a SpecialOccasion object
     * @return SpecialOccasion object created from the parameters obtained from the line from the txt file
     */
    private SpecialOccasion createSpecialOccasionObj(String info){
        String[] infoArr =  info.split("\\|"); 
        return new SpecialOccasion(infoArr[1], infoArr[0]);
    }

    // Add new special occasion
    /**
     * Adding a new Speccial occasion
     * @param specialOccasion the new Special Occasion to be added
     * @return True = Occasion has been added, False=Occasion has not been added due to error
     */
    public boolean add(SpecialOccasion specialOccasion){
        for (int i = 0; i < specialOccasions.size(); i++){
            if(specialOccasions.get(i).isSameOccasion(specialOccasion)){
                System.out.println("Error: Special occasion already declared");
                return false;
            }
        } 
        this.specialOccasions.add(specialOccasion);
        return true;     
    }


    // Remove a special occasion 
    /**
     * Removes a Special Occasion as identified by user
     * @param targetSpecialOccasion SpecialOccasion object to be removed from the ArrayList
     * @return True = Special Occasion successfully removed,  False = Special Occasion failed to be removed (error)
     */
    public boolean remove(SpecialOccasion targetSpecialOccasion){
        for (int i = 0; i < specialOccasions.size(); i++){
            if(specialOccasions.get(i).isSameOccasion(targetSpecialOccasion)){
                this.specialOccasions.remove(i);     
                System.out.println("\nSpecial Occasion successfully removed"); 
                return true;
            }
        } 
        System.out.println("Error: Special occasion not found");
        return false;
    }

    //List all special occasions
    /**
     * Prints all special occasions stored in the ArrayList
     */
    public void printAll(){
        System.out.println("\n-------All Special Occassions-------");
        for (SpecialOccasion occasion : specialOccasions) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MM");
            
            System.out.println("Public holiday: " + occasion.getName().toUpperCase());
            System.out.println("Date (DD - MM): " + occasion.getDate().format(formatter));
            System.out.println("\n");
        }
        System.out.println("------------------------------------");
    }

    //query if date provided is a special occasion or eve of special occasion 
    /**
     * query if date provided is a special occasion or eve of special occasion 
     * @param date date input by user for checking
     * @return True = date is a special occasion, False = date is not a special occasion
     */
    public boolean isSpecialOccasion(LocalDate date){
        for (int i = 0; i < this.specialOccasions.size(); i++){
            if(this.specialOccasions.get(i).isSpecialOccasion(date)) return true;
        }
        return false;
    }

    // Overwrite old specialOccasionsList with a new set of Special Occasions
    /**
     * Overwrite old specialOccasionsList with a new set of Special Occasions
     */
    public void writeToSpecialOccasionFile(){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(this.specialOccasionHeader);

            for (SpecialOccasion occasion: specialOccasions) {
                ArrayList<String> line = new ArrayList<>();
                line.add(occasion.getName().toLowerCase());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
                line.add(occasion.getDate().format(formatter));
                writer.write("\n" + String.join("|", line));
            }
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
