/**
 * Closes all stores. Activated just before the program is terminated, this class will close all stores and write to their respective .txt files, saving the relevant information
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public class StoreManager {
    /**
     * Closes all stores and writes their information into their respective txt files
     */
    static void closeAllStores() {
        CineplexesReaderWriter.getInstance().writeFile();
        CredentialStore.getInstance().closeShowTimeStore();
        MovieStore.getInstance().closeMovieStore();
        SeatStore.getInstance().writeFile();
        SettingStore.getInstance().closeSettingStore();
        ShowTimeStore.getInstance().closeShowTimeStore();
//        // To-do: write file for pricing store (DZ)
        ShowTimeStore.getInstance().closeShowTimeStore();
        SpecialOccasionStore.getInstance().writeToSpecialOccasionFile(); //working
        TicketStore.getInstance().closeTicketStore();
        PricingStore.getInstance().closePricingStore();
    }
}
