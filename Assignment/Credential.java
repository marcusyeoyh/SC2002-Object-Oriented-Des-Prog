/**
 * Contains the individual credentials of each Admin account
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class Credential {
    //Attributes
    /**
     * The username of the Admin account in String datatype
     */
    private String username;
    /**
     * The password of the Admin account in String datatype
     */
    private String password;
    /**
     * The Role given to the Admin account in AdminRole datatype
     */
    private AdminRole role;

    //Constructor 
    /**
     * Creates a new Credential for a new Admin account
     * @param username this Credential's new username
     * @param password this Credential's new password
     * @param role this Credential's new role
     */
    public Credential(String username, String password, AdminRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Operations
    /**
     * Gets this Credential's username
     * @return this Credential's username in String datatype
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets this Credential's role
     * @return this Credential's role in AdminRole datatype
     */
    public AdminRole getRole() {
        return this.role;
    }

    //Change username 
    /**
     * Changes this Credential's username if password is given accurately
     * @param newUsername this Credential's new username
     * @param password User input password
     * @return Boolean true if input password matches Credential password, false if password does not match
     */
    public boolean setUsername(String newUsername, String password) {
        //User needs to provide the corrrect password to change username
        if (this.password.equals(password)) {
            this.username = newUsername;
            return true;
        } else {
            return false;
        }
    }

    //Change password
    /**
     * Changes the password of this Credential if the old password provided is correct
     * @param oldPassword the user input old password for verification
     * @param newPassword this Credential's new password
     * @return a boolean true if password is succesfully changed and false if password is not changed due to oldPassword not matching the current password of Credential
     */
    public boolean setPassword(String oldPassword, String newPassword) {
        //User needs to provide the correct old password to change password
        //To do: implement password checks to ensure that password is 8 char, alphanumeric, contains capital letters and symbols
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    //Check if correct password
    /**
     * Check function to see if user input password matches the password of Credential
     * @param password user input password to be checked against the password attribute of Credential
     * @return a boolean true if passwords match, false if they do not
     */
    public boolean check(String password) {

        return this.password.equals(password);
    }

    /**
     * Gets the password of Credential
     * @return this Credential's password in String datatype
     */
    public String getPassword() {
        return password;
    }
}


