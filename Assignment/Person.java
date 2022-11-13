/**
 * Person Interface
 * Will be used to ensure that all persons will have a name attribute
 * @author Marc Chern
 * @version 1.0.0 Nov 12, 2022
 */
interface Person {
    /**
     * Get the name of the person
     * @return name of the person in String datatype
     */
    String getName();
    /**
     * Set the name of the person
     * @param name - name of the person to be set in String datatype
     */
    void setName(String name);
}
