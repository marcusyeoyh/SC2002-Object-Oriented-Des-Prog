/**
 * This is a enum class representing the different Age Classifications available to a particular movie
 * Movies will have one of the following AgeEnum associated with them: G, PG, PG13, NC16, M18, R21
 * @author Marcus, Low Zhe Kai
 * @version 1.0.0 Nov 12, 2022
 */
public enum AgeEnum {
    /**
     * General rating, safe to view for all
     */
    G, 
    /**
     * Parental Guidance advised when watching with children.
     */
    PG, 
    /**
     * Parental Guidance advised when watching with children, Children below 13 not advised to watch
     */
    PG13, 
    /**
     * No children under 16 advised to watch
     */
    NC16, 
    /**
     * Mature 18 rating, no children under 18
     */
    M18, 
    /**
     * Restricted to Adults above 21 years
     */
    R21;
}
