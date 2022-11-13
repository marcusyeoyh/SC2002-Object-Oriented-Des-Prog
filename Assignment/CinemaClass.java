/**
 * This is a enum class which represents the possible classes allocated to each individual Cinema object.
 * The CinemaClass is a contributing factor for the price of tickets purchased
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public enum CinemaClass {
        /**
         * Standard class of Cinema, the default class if not otherwise specified
         */
        STANDARD,
        /**
         * Gold class of Cinema
         */
        GOLD,
        /**
         * Platinum class of cinema
         */
        PLATINUM;

        /**
         * Static method isValid allows us to check if the input class of Cinema matches with the existing classes
         * @param s is the class of cinema input by the user
         * @return a boolean true or false to signify if the input String is a valid Cinema class
         */
        public static boolean isValid(String s) {
                for (CinemaClass c: values()) {
                        if (s.equals(c.toString()))
                                return true;
                }
                return false;
        }
}