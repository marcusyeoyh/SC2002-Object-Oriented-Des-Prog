/**
 * Represents an enum class of Role, where a role will be assigned to each MoviePersonnel object, indicating what role that person had in the film
 * Potential further expansion could include more roles, which can be easily done by creating a new enum value
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public enum Role {
    /** 
     * The first role to appear in MoviePersonnel ArrayList
     */
    DIRECTOR, 
    /**
     * The second and subsequent role in MoviePersonnel Arraylist.
     * Every movie must have at least two MoviePersonnel with CAST Role.
     */
    CAST;
}
