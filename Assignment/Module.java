/**
 * Represents main Module for all Module classes (Admin, Guest and MovieGoer)
 * Allows all Modules to execute when running method run()
 * @author Koh Mingyang
 * @version 1.0.0 Nov 13, 2022
 */
public interface Module {
    /**
     * Main function for all Modules, allows all modules to execute their respective functions when run() is called.
     */
    void run();
}
