import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Store of Singleton class containing the pricing conventions of a ticket
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class PricingStore {
    /**
     * String variable to store the header of pricings,txt
     */
    private String pricingHeader;
    /**
     * The base price of a ticket
     */
    private Float base;
    /**
     * HashMap storing the price charged according to age group (Child, Adult, Senior)
     */
    private Map<AgeGroup, Float> ageGroupChange =  new HashMap<AgeGroup, Float>();
    /**
     * HashMap storing the price charged according to movie category (Blockbuster, Regular)
     */
    private Map<Hype, Float> hypeAdd = new HashMap<Hype, Float>();
    /**
     * HashMap storing the price charged according to age group
     */
    private Map<CinemaClass,  Float> cinemaClassAdd = new HashMap<CinemaClass, Float>();
    /**
     * HashMap storing the price charged according to day
     */
    private Map<Integer, Float> dayOfWeekAdd = new HashMap<Integer, Float>();
    /**
     * Cutoff time on Fridays where pricing changes
     */
    private LocalTime cutOff;
    /**
     * HashMap storing the price charged according to Friday Rule
     */
    private Map<LocalTime, Float> fridayRuleAdd = new HashMap<LocalTime, Float>();
    /**
     * HashMap storing the price charged according to Viewing Mode (2D, 3D)
     */
    private Map<View, Float> viewAdd = new HashMap<View, Float>();
    /**
     * File path of pricings.txt
     */
    private String path = ("Classes/src/pricings.txt");
    /**
     * Singleton instance of PricingStore
     */
    private static PricingStore instance = new PricingStore();

    /**
     * Creates a new PricingStore object if not already available
     */
    private PricingStore(){
        // Reads a file line by line
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            this.pricingHeader = reader.readLine(); //Header row
            String line = reader.readLine();
            while (line != null){
                createPricingRule(line); //Add pricing object to pricings array list
                line = reader.readLine(); // Reading next line in txt file
            }
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
            System.out.println("Error: Pricing list not found");
        }
    }

    /**
     * Creates a Singleton Instance of PricingStore to be used throughout MOBLIMA.
     * @return A PricingStore object which contains a HashMap with pricing rules and details
     */
    public static PricingStore getInstance(){
        return instance;
    }

    /**
     * Reads the princing info from pricing.txt
     * @param info the string of pricing information from pricing.txt
     */
    private void createPricingRule(String info){
        String[] infoArr = info.split("\\|");

        switch(infoArr[0]){
            case "base":
                this.base = Float.parseFloat(infoArr[2]);
                break;
                
            case "ageGroup":
                this.ageGroupChange.put(AgeGroup.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));  
                break;

            case "hype":
                this.hypeAdd.put(Hype.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "cinemaClass":
                this.cinemaClassAdd.put(CinemaClass.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "dayOfWeek":
                this.dayOfWeekAdd.put(Integer.parseInt(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

            case "fridayRule":
                //infoArr[1] is in the (hhmm) format
                int hour = Integer.parseInt(infoArr[1].substring(0,2));
                int minute = Integer.parseInt(infoArr[1].substring(2));
                this.cutOff = LocalTime.of(hour, minute);

                this.fridayRuleAdd.put(this.cutOff,Float.parseFloat(infoArr[2]));
                break;

            case "view":
                this.viewAdd.put(View.valueOf(infoArr[1]), Float.parseFloat(infoArr[2]));
                break;

        }
    }

    //Checks for price based on pricing rules set 
    /**
     * Checks for pricing based on pricing rules set
     * @param ageGroup age group of ticket chosen in AgeGroup datatype
     * @param hype movie category of ticket chosen in Hype datatype
     * @param cinemaClass class of cinema chosen in CinemaClass datatype
     * @param isPH boolean datatype where true if the date chosen is a public holiday, else false
     * @param dayOfWeek day of week of ticket chosen in Integer datatype
     * @param startTime start time of movie chosen in LocalTime datatype
     * @param view viewing format of movie chosen in View datatype
     * @return a Float value that contains the price queried.
     */
    public Float queryPrice(AgeGroup ageGroup, Hype hype, CinemaClass cinemaClass, boolean isPH,  Integer dayOfWeek, LocalTime startTime, View view){
        Float price = base; 

        if (this.dayOfWeekAdd.containsKey(dayOfWeek)){
            price += dayOfWeekAdd.get(dayOfWeek);

            if (dayOfWeek == 5 && startTime.isAfter(cutOff)){
                price += fridayRuleAdd.get(cutOff);
            }
        }

        if (this.ageGroupChange.containsKey(ageGroup) && !isPH && startTime.isBefore(cutOff) && (dayOfWeek != 6 ) && (dayOfWeek != 7)){
            price = this.ageGroupChange.get(ageGroup); //resets price if ticket is a child of senior ticket for a show before cut off timing and not on a public holiday or weekend
        }
        
        if (this.hypeAdd.containsKey(hype)){
            price += hypeAdd.get(hype);
        }

        if (this.cinemaClassAdd.containsKey(cinemaClass)){
            price += cinemaClassAdd.get(cinemaClass);
        }

        if(this.viewAdd.containsKey(view)){
            price += this.viewAdd.get(view);
        }
        return price;
    }


    //Add pricing rule 
    /**
     * Adds a particular pricing rule if desired by the admin user
     * @param ruleClass denotes which rule type is to have a rule added in Integer datatype
     * @param type the new value of rule to be added in String datatype
     * @param val how much would the new ticket cost in Float datatype
     */
    public void addPricingRule(Integer ruleClass, String type, Float val){
        switch(ruleClass){
            case 1: //ageGroup
                try{
                    AgeGroup.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(ageGroupChange.put(AgeGroup.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 2: //hype
                try{
                    Hype.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(hypeAdd.put(Hype.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 3: //cinemaClass
                try{
                    CinemaClass.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(cinemaClassAdd.put(CinemaClass.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 4: //dayOfWeek
                try{
                    Integer.parseInt(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(dayOfWeekAdd.put(Integer.parseInt(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 5: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if (hour < 0 || hour > 23 || minute < 0 || minute > 59){
                    System.out.println("Error: Invalid rule type entered");
                    break;
                } else if (fridayRuleAdd.put(LocalTime.of(hour,minute), val) == null){
                    System.out.println("Rule successfully added.");                               
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 6: //view
                try{
                    View.valueOf(type);
                } catch (IllegalArgumentException err) {
                    System.out.println("Error: Invalid rule type entered");
                    break;
                }
                if(viewAdd.put(View.valueOf(type), val) == null){
                    System.out.println("Rule successfully added.");
                } else {
                    System.out.println("Successfully overwrote exisiting rule..");
                }
                
                break;

            case 7: //quit
                System.out.println("Exiting pricing rule removal menu");
                break;

            default:
                System.out.println("Invalid choice, exiting pricing rule removal menu");
        }
    }
    //Update pricing rule
    /**
     * Updating a pricing rule if desired by an Admin user
     * @param ruleClass the rule class to be edited in Integer datatype
     * @param type the type of rule to be edited (eg Child, Adult or Senior for AgeClass rule) in String datatype
     * @param val the new ticket price in Float datatype
     */
    public void updatePricingRule(Integer ruleClass, String type, Float val){
        switch(ruleClass){
            case 1: //base
                this.base = val;
                break;
            case 2: //ageGroup
                if (this.ageGroupChange.containsKey(AgeGroup.valueOf(type))){
                    hypeAdd.replace(Hype.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                }
                else{
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 3: //hype
                if (this.hypeAdd.containsKey(Hype.valueOf(type))){
                    hypeAdd.replace(Hype.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                }
                else{
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 4: //cinemaClass
                if (this.cinemaClassAdd.containsKey(CinemaClass.valueOf(type))){
                    cinemaClassAdd.replace(CinemaClass.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 5: //dayOfWeek
                if (this.dayOfWeekAdd.containsKey(Integer.parseInt(type))){
                    dayOfWeekAdd.replace(Integer.parseInt(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 6: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if (fridayRuleAdd.containsKey(LocalTime.of(hour,minute))){
                    fridayRuleAdd.replace(LocalTime.of(hour,minute), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            case 7: //view
                if (viewAdd.containsKey(View.valueOf(type))){
                    viewAdd.replace(View.valueOf(type), val);
                    System.out.println("\nRule successfully added.");
                } else {
                    System.out.println("\nFailed to replace rule. Please enter a exisiting rule type.");
                }
                break;

            default:
                System.out.println("\nInvalid choice, exiting pricing rule removal menu");
        }
    }


    
    //Remove Pricing Rule 
    /**
     * Removing a pricing rule if desired by Admin
     * @param ruleClass the rule class desired to be accessed in Integer datatype
     * @param type the type of rule to be removed (eg Child, Adult or Senior for AgeClass rule) in String datatype
     */
    public void removePricingRule(Integer ruleClass, String type){
        switch(ruleClass){
                
            case 1: //ageGroup
                if(ageGroupChange.remove(AgeGroup.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 2: //hype
                if(hypeAdd.remove(Hype.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 3: //cinemaClass
                if(cinemaClassAdd.remove(CinemaClass.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 4: //dayOfWeek
                if(dayOfWeekAdd.remove(Integer.parseInt(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 5: //fridayRule
                Integer hour = Integer.parseInt(type.substring(0,2));
                Integer minute = Integer.parseInt(type.substring(2,4));
                if(fridayRuleAdd.remove(LocalTime.of(hour,minute)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");                               
                break;

            case 6: //view
                if(viewAdd.remove(View.valueOf(type)) == null){
                    System.out.println("Failed to remove rule. Please enter a valid rule type.");
                }
                System.out.println("Rule successfully removed.");
                break;

            case 7: //quit
                System.out.println("Exiting pricing rule removal menu");
                break;

            default:
                System.out.println("Invalid choice, exiting pricing rule removal menu");
        }
    }

    //Print all pricing rules 
    /**
     * Prints out all currently established pricing rules
     */
    public void printAllPricings(){

        System.out.println("\n-------------------Pricing Rules-------------------");
        
        //Base
        System.out.println("Rule Class: Base");
        System.out.println("Type: Base");
        System.out.println("Base price for tickets: $" + String.valueOf(this.base));
        System.out.println("---------------------------------------------------");
        
        
        //Age group rule
        for (Map.Entry<AgeGroup, Float> entry : ageGroupChange.entrySet()){
            System.out.println("Rule Class: Age Group");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Discounted base price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Movie Hype rule
        for (Map.Entry<Hype, Float> entry : hypeAdd.entrySet()){
            System.out.println("Rule Class: Hype");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Cinema class rule
        for (Map.Entry<CinemaClass,  Float> entry : cinemaClassAdd.entrySet()){
            System.out.println("Rule Class: Cinema Class");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Day of week rule
        for (Map.Entry<Integer, Float> entry : dayOfWeekAdd.entrySet()){
            System.out.println("Rule Class: Day of Week");
            System.out.println("Type: "+ String.valueOf(entry.getKey()));
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //Friday rules (Matches weekend pricing after a certain cutoff timing)
        for (Map.Entry<LocalTime, Float> entry : fridayRuleAdd.entrySet()){
            LocalTime time = entry.getKey();
            int hour = time.getHour();
            String hourStr = hour < 10 ? "0" : "" + String.valueOf(hour);
            int minute  = time.getMinute();
            String minuteStr = minute < 10 ? "0" : "" + String.valueOf(minute);

            System.out.println("Rule Class: Friday evening surcharges");
            System.out.println("Type : "+ hourStr + minuteStr + "h");
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
        //View rules
        for (Map.Entry<View, Float> entry : viewAdd.entrySet()){
            System.out.println("Rule Class: View");
            System.out.println("Type: "+ entry.getKey().name());
            System.out.println("Price added to total price: $" + String.valueOf(entry.getValue()));
            System.out.println("---------------------------------------------------");
        }
    }

    //Save all changes made
    /**
     * Saves all changes made to pricing rules and writes to pricings.txt
     */
    public void closePricingStore(){
        try(FileWriter writer = new FileWriter(path)){
            
            writer.write(this.pricingHeader);
            
            //Write Rules
            writer.write("\nbase|base|" + String.valueOf(this.base)); 

            //Age group rule
            for (Map.Entry<AgeGroup, Float> entry : ageGroupChange.entrySet()){
               writer.write("\nageGroup|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Movie Hype rule
            for (Map.Entry<Hype, Float> entry : hypeAdd.entrySet()){
               writer.write("\nhype|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Cinema class rule
            for (Map.Entry<CinemaClass,  Float> entry : cinemaClassAdd.entrySet()){
               writer.write("\ncinemaClass|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            //Day of week rule
            for (Map.Entry<Integer, Float> entry : dayOfWeekAdd.entrySet()){
               writer.write("\ndayOfWeek|" + String.valueOf(entry.getKey()) + "|" + String.valueOf(entry.getValue()));
            }
            //Friday rules (Matches weekend pricing after a certain cutoff timing)
            for (Map.Entry<LocalTime, Float> entry : fridayRuleAdd.entrySet()){
                LocalTime time = entry.getKey();
                int hour = time.getHour();
                String hourStr;
                if (hour < 10){
                    hourStr = "0" + String.valueOf(hour);
                } else {
                    hourStr = String.valueOf(hour);
                }
                int minute  = time.getMinute();
                String minuteStr;
                if (minute < 10){
                    minuteStr = "0" + String.valueOf(minute);
                } else {
                    minuteStr = String.valueOf(minute);
                }
                writer.write("\nfridayRule|" + hourStr + minuteStr + "|" + String.valueOf(entry.getValue()));
            }
            //View rules
            for (Map.Entry<View, Float> entry : viewAdd.entrySet()){
               writer.write("\nview|" + entry.getKey().name() + "|" + String.valueOf(entry.getValue()));
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}   
