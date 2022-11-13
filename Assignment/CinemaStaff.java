/**
 * Represents a Staff member of the Cineplex.
 * This object will contain the registered name of the staff member and a method to verify if the staff has the correct password to the account
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public class CinemaStaff implements SysShowtimeHandler, SysPriceHandler, SysSpecialOccasionHandler, SysMovieHandler, SysSettings, Person {

    /**
     * Name of the staff member. Full name including first and last names
     */
    private String name;
    
    /**
     * Creates a new CinemaStaff account with the given name
     * This name should include first and last name and will be the name used when logging in by the staff member
     * @param name this CinemaStaff member's full name
     */
    public CinemaStaff(String name){
        setName(name);
    }

    /**
     * Gets this CinemaStaff's full name
     * @return this CinemaStaff's full name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this CinemaStaff
     * @param name this CinemaStaff's new name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Encapsulation: CinemaStaff responsible for own validation and persistence
    /**
     * method validate allows CinemaStaff to validate password provided by user input with the password stored in CredentialStore. This allows for logging in and other verification processes
     * This method allows for encapsulation as correct password is never passed out of CredentialStore, making CinemaStaff responsible for its own validation and persistence
     * @param password the user input password to be checked against the password stored in CredentialStore
     * @return a boolean true or false where true indicates that the password passed in is correct, and false if the password input is not similar to the password in CredentialStore.
     */
    public boolean validate(String password) {
        return password.equals(CredentialStore.getInstance().getPassword(name));
    }
}
