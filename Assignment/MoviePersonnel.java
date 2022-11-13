/**
 * This represents a person involved in a particular movie. The person will have his full name stored as a String and their Role in the movie (Director or Cast)
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public class MoviePersonnel implements Person {
    /**
     * Name of the personnel in String datatype
     */
    private String name;
    /**
     * Role of the personnel in Role datatype (DIRECTOR or CAST)
     */
    private Role role;

    /**
     * Creates a new Movie Personnel for a particular Movie
     * @param name the full name of this Movie Personnel (first and last name) in String datatype
     * @param role the Role of this Movie Personnel (Director or Cast)
     */
    public MoviePersonnel(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    /**
     * Gets this Movie Personnel's name
     * @return this movie Personnel's name in String datatype
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes this Movie Personnel's name
     * @param name this Movie Personnel's new Name (first and last name) in String datatype
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets this Movie Personnel's role
     * @return this movie Personnel's role in Role datatype
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Changes this Movie Personnel's role
     * @param role this Movie Personnel's new role in Role Datatype
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
