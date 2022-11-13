import java.time.LocalDate;

/**
 * This represents a Special Occasion date, which will affect ticket pricing
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class SpecialOccasion {
    /**
     * The date of the special occasion in LocalData format
     */
    private LocalDate date;
    /**
     * The name of the special occasion in String format
     */
    private String name;

    /**
     * Creates a new Special Occasion based on provided dates and name of the occasion
     * @param cDate the date of the new special occasion in String format
     * @param cName the name of the new Special occasion in String format
     */
    public SpecialOccasion(String cDate, String cName){
        
        String[] dateArr = cDate.split("-");
        int year = LocalDate.now().getYear();
        this.date = LocalDate.of(year, Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[0])); 
        this.name = cName;
    }

    /**
     * Checks is two special occasions are the same occasion
     * @param otherSpecialOccasion A special occasion object to be input by user to conduct a check
     * @return True = the two occasions are the same. False = the two occasions are not the same
     */
    public boolean isSameOccasion(SpecialOccasion otherSpecialOccasion){
        return (
            this.date.equals(otherSpecialOccasion.date)
            && this.name.equals(otherSpecialOccasion.name)
        );
    }

    /**
     * Checks if the provided date is a special occasion or close to a special occasion
     * @param date user provided date in LocalDate format
     * @return True = the date is a special occasion, False = date is not a special occasion
     */
    public boolean isSpecialOccasion(LocalDate date){
        return this.date.equals(date) || this.date.equals(date.plusDays(1)); //checks if its a special occasion or eve of a special occasion
    }

    /**
     * Gets the date of this special occasion
     * @return the date of this special occasion in LocalDate format
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the name of this special occasion
     * @return the name of this special occasion in String format
     */
    public String getName() {
        return name;
    }
}
