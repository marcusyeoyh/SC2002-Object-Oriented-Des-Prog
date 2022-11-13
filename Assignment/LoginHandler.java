import java.util.Scanner;

/**
 * This represents a Login handler that checks with individual Credentials to make sure that the Cinema Staff is a verfied user before allowing further editing options for the Admin.
 * @author Marc Chern
 * @version 1.0.0 Nov 13, 2022
 */
public class LoginHandler {
    /**
     * Creates a Scanner object to recieve user input
     */
    Scanner sc = new Scanner(System.in);
    CinemaStaff cinemaStaff;
    /**
     * Main login function which prompts user for username and password before verifying the password before allowing further user access.
     * @return CinemaStaff object if login is successfull, returns NULL if unsucessful
     */
    public CinemaStaff login(){
        //user input
        int option;

        do {
            System.out.println("\n----------------------------");
            System.out.println("Please select a choice below");
            System.out.println("1 - Cinema staff login");
            System.out.println("2 - Exit");
            System.out.println("----------------------------");
            System.out.print("\nChoice: ");
            try{
            option = sc.nextInt();
            } catch (Exception err){
                System.out.println("Error: Please input a valid number (1 - 2).\n");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (option) {
                case 1 -> {
                    try{
                        System.out.print("\nEnter Username: ");
                        String username = sc.nextLine();
                        cinemaStaff = new CinemaStaff(username);
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                    

                        if (!cinemaStaff.validate(password)){
                            System.out.println("\nError: Invalid Credential");
                            return null;
                        } else {
                            System.out.println("\nLogged in. Welcome " + username + ".");
                            return cinemaStaff;
                        }
                    } catch (Exception err){
                        System.out.println(err.getMessage());
                    }
                }
                case 2 -> {
                    return null;
                }
                default -> System.out.println("Error: Incorrect option. Please try again.");
            }
        } while (true);
    }
}