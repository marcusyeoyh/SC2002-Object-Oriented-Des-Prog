import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles the changing of Pricing Rules
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public interface SysPriceHandler {
    /**
     * Scanner object created to allow for user inputs
     */
    public Scanner sc = new Scanner(System.in);
    /**
     * Singleton Class PricingStore variable introduced to facillitate the editing of the pricing rules
     */
    static PricingStore pStore =  PricingStore.getInstance();

    /**
     * Adds a pricing rule
     */
    default void addPricingRule(){
        Integer ruleClass;
        System.out.println("------Add pricing rule------");
        System.out.println("\n--------------Pricing Rule Classes--------------");
        System.out.println("(1) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(2) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(3) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(4) Day of Week (1-7)");
        System.out.println("(5) Friday evening surcharges (hhmm)");
        System.out.println("(6) View (_2D / _3D)");
        System.out.println("(7) Quit");
        System.out.println("------------------------------------------------\n");
        System.out.print("Enter choice: ");
        
        //Option input handling
        try{
            ruleClass = sc.nextInt();
        } catch (Exception err) {
            System.out.println("Error: Invalid input, exiting ...");
            sc.nextLine();
            return;
        }

        if (ruleClass == 7){
            System.out.println("Exiting system...");
            return;
        }
        else if (ruleClass < 0 || ruleClass > 7 ){
            System.out.println("Error: Invalid input, exiting...");
            return;
        }


        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        System.out.print("Enter value of new rule: ");

        Float value = null;
        try {
            value = sc.nextFloat();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid value! Exiting module");
            sc.nextLine();
            return;
        }
        pStore.addPricingRule(ruleClass, type, value);

    }

    /**
     * Updates a pricing rule
     */
    default void updatePricingRule(){
        Integer ruleClass;
        System.out.println("------Update pricing rule------");
        System.out.println("\n--------------Pricing Rule Classes--------------");
        System.out.println("(1) Base Price (base)");
        System.out.println("(2) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(3) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(4) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(5) Day of Week (1-7)");
        System.out.println("(6) Friday evening surcharges (hhmm)");
        System.out.println("(7) View (_2D / _3D)");
        System.out.println("(8) Quit");
        System.out.println("------------------------------------------------\n");
        System.out.print("Select a pricing rule: ");

        //Option input handling
        try{
            ruleClass = sc.nextInt();
        } catch (InputMismatchException err) {
            System.out.println("Error: Invalid input, exiting ...");
            sc.nextLine();
            return;
        }
        if (ruleClass == 8){
            System.out.println("Exiting system...");
            return;
        } else if (ruleClass < 0 || ruleClass > 7 ){
            System.out.println("Error: Invalid input, exiting...");
            return;
        }

        //Updated pricing rule values
        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        System.out.print("Enter new value for existing rule: ");
        Float value = null;
        try {
            value = sc.nextFloat();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid value! Exiting module");
            sc.nextLine();
            return;
        }
        pStore.updatePricingRule(ruleClass, type, value);

    }

    /**
     * Removes a pricing rule
     */
    default void removePricingRule() {
        System.out.println("------Remove pricing rule------");
        System.out.println("\n------------Pricing Rule Classes------------");
        System.out.println("(1) Age group (CHILD / ADULT / SENIOR)");
        System.out.println("(2) Hype (REGULAR / BLOCKBUSTER)");
        System.out.println("(3) Cinema class (STANDARD / GOLD / PLATINUM)");
        System.out.println("(4) Day of Week (1-7)");
        System.out.println("(5) Friday evening surcharges (hhmm)");
        System.out.println("(6) View (_2D / _3D)");
        System.out.println("(7) Quit");
        System.out.println("--------------------------------------------\n");
        System.out.print("Enter choice: ");

        //Option input handling
        Integer ruleClass;
        try {
            ruleClass = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Exiting module");
            sc.nextLine();
            return;
        }
        if (ruleClass == 7){
            System.out.println("Exiting system...");
            return;
        }
        else if (ruleClass < 0 || ruleClass > 7 ){
            System.out.println("Error: Invalid input, exiting...");
            return;
        }

        //Pricing rule input 
        System.out.print("Enter Type (Refer to pricing rule classes list): ");
        String type = sc.next();
        pStore.removePricingRule(ruleClass, type);

    }

    /**
     * Prints all pricing rules
     */
    default void printAllPricingRules() {
        pStore.printAllPricings();
    }
}
