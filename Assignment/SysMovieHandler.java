import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.Duration;

/**
 * Handles all matters pertaining to Movies, such as adding and editing movies
 * @author Marcus Yeo, Low Zhe Kai
 * @version 1.0.0 Nov 13, 2022
 */
public interface SysMovieHandler {
    /**
     * Creating a scanner object ot accept user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Menu popup to create a new Movie
     */
    default void createNewMovie() {
        MovieStore movieStore = MovieStore.getInstance();

        System.out.println("\n--------Create Movie Module--------");


        System.out.print("Enter new movie's ID: ");

        String movieID = sc.nextLine();
        while (movieStore.getMovieHashMap().containsKey(movieID)) {
            System.out.println("\nMovie ID is already in use. Please enter a different value: ");
            movieID = sc.nextLine();
        }

        Movie movie = new Movie(movieID);

        System.out.print("Enter the movie name: ");
        String temp = sc.nextLine();
        movie.setMovieName(temp);

        while(true){
            try{
                System.out.print("Enter new movie's duration (mins): ");
                temp = sc.nextLine();
                movie.setMovieDuration(Duration.ofMinutes(Long.parseLong(temp)));
                break;
            } catch (NumberFormatException err){
                System.out.println("\nError: Please enter an integer value!");
            }
        }

        System.out.println("\nEnter the movie showing status: ");
        System.out.println("1 - Coming Soon");
        System.out.println("2 - Preview");
        System.out.println("3 - Now Showing");
        System.out.println("4 - End of Showing");
        temp = sc.nextLine();
        switch(temp){
            case "1":
                movie.setShowingStatus(Status.COMINGSOON);
                break;
            case "2":
                movie.setShowingStatus(Status.PREVIEW);
                break;
            case "3":
                movie.setShowingStatus(Status.NOWSHOWING);
                break;
            case "4":
                movie.setShowingStatus(Status.ENDOFSHOWING);
                break;
            default:
                movie.setShowingStatus(Status.COMINGSOON);
        }

        System.out.println("\nEnter the movie synopsis:");
        temp = sc.nextLine();
        movie.setSynopsis(temp);

        System.out.println("\nEnter the viewing mode:");
        System.out.println("1 - 2D");
        System.out.println("2 - 3D");
        temp = sc.nextLine();
        switch(temp) {
            case "1":
                movie.setViewingMode(View._2D);
                break;
            case "2":
                movie.setViewingMode(View._3D);
                break;
            default:
                movie.setViewingMode(View._2D);
        }

        System.out.println("\nEnter the movie status:");
        System.out.println("1 - Regular");
        System.out.println("2 - Blockbuster");
        temp = sc.nextLine();
        switch(temp) {
            case "1":
                movie.setMovieHype(Hype.REGULAR);
                break;
            case "2":
                movie.setMovieHype(Hype.BLOCKBUSTER);
                break;
            default:
                movie.setMovieHype(Hype.REGULAR);
        }

        movie.setMovieSales(0);
        
        System.out.println("\nEnter the age rating:");
        System.out.println("1 - G");
        System.out.println("2 - PG");
        System.out.println("3 - PG13");
        System.out.println("4 - NC16");
        System.out.println("5 - M18");
        System.out.println("6 - R21");
        temp = sc.nextLine();
        switch(temp) {
            case "1":
                movie.setAgeRating(AgeEnum.G);
                break;
            case "2":
                movie.setAgeRating(AgeEnum.PG);
                break;
            case "3":
                movie.setAgeRating(AgeEnum.PG13);
                break;
            case "4":
                movie.setAgeRating(AgeEnum.NC16);
                break;
            case "5":
                movie.setAgeRating(AgeEnum.M18);
                break;
            case "6":
                movie.setAgeRating(AgeEnum.R21);
                break;
            default:
                movie.setAgeRating(AgeEnum.G);
        }

        System.out.print("\nEnter the Director's name: ");
        temp = sc.nextLine();
        movie.addMoviePersonnel(temp, Role.DIRECTOR);
        
        int castCount = 0;
        for (int i = 0; i < 2; i++){
            castCount++;
            System.out.print(String.format("\nEnter cast member %d name: ", castCount));
            temp = sc.nextLine();
            movie.addMoviePersonnel(temp, Role.CAST);      
        }

        System.out.println("\nDo you want to add a cast member?");
        System.out.println("1 - No");
        System.out.println("2 - Yes");
        System.out.print("Option: ");
        int toggle = sc.nextInt();
        sc.nextLine();
        while (toggle != 1) {
            castCount++;
            System.out.print(String.format("\nEnter cast member %d name: ", castCount));
            temp = sc.nextLine();
            movie.addMoviePersonnel(temp, Role.CAST);

            System.out.println("\nDo you want to add another cast member?");
            System.out.println("1 - No");
            System.out.println("2 - Yes");
            System.out.print("Option: ");
            try{
                toggle = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException err){
                System.out.println("Invalid choice entered. Saving movie information....");
                break;
            }
        }

        movieStore.addMovie(movie);
        movie.printMovie();
        System.out.println("Movie has been added.");
    }

    /**
     * Functionality to update a movie
     */
    default void updateMovie() {
        MovieStore movieStore = MovieStore.getInstance();


        System.out.println("\n--------Update Movie Module--------");


        // Display all movie name, movie status and showing status
        for (Movie movie: movieStore.getMovieHashMap().values()) {
            System.out.println("-".repeat(29));
            System.out.println(movie.getMovieName());
            System.out.printf("Movie ID: %s\t Status: %s\n", movie.getMovieID(), movie.getShowingStatus().toString());
        }
        System.out.print("Enter movie ID you wish to update: ");
        String movieID = sc.nextLine();
        while (!movieStore.getMovieHashMap().containsKey(movieID)) {
            System.out.println("Movie ID does not exist, please key in a valid Movie ID:");
            movieID = sc.nextLine();
        }
        Movie movie = movieStore.searchMovie(movieID);
        movie.printMovie();
        int choice, length;
        String temp;
        System.out.println("What do you want to update:");
        System.out.println("1 - Movie name");
        System.out.println("2 - Movie duration");
        System.out.println("3 - Showing status");
        System.out.println("4 - Movie synopsis");
        System.out.println("5 - Viewing mode");
        System.out.println("6 - Movie status");
        System.out.println("7 - Age rating");
        System.out.println("8 - Movie personnel");
        System.out.println("0 - Quit");
        System.out.print("\nChoice: ");
        choice = sc.nextInt();
        sc.nextLine();
        while (choice != 0) {
            switch(choice) {
                case 1:
                    System.out.printf("Current movie name: %s\n", movie.getMovieName());
                    System.out.println("Enter the new movie name:");
                    temp = sc.nextLine();
                    movie.setMovieName(temp);
                    System.out.printf("New movie name: %s\n", movie.getMovieName());
                    break;
                case 2:
                    System.out.println("Current movie duration: " + movie.getMovieDuration().toMinutes() + " minutes");
                    System.out.println("Enter the new movie duration:");
                    temp = sc.nextLine();
                    movie.setMovieDuration(Duration.ofMinutes(Long.parseLong(temp)));
                    System.out.println("New movie duration: " + movie.getMovieDuration().toMinutes() + " minutes");
                    break;
                case 3:
                    System.out.println("Current showing status: " + movie.getShowingStatus());
                    System.out.println("Enter the new showing status:");
                    System.out.println("1 - Coming Soon");
                    System.out.println("2 - Preview");
                    System.out.println("3 - Now Showing");
                    System.out.println("4 - End of Showing");
                    temp = sc.nextLine();
                    switch(temp){
                        case "1":
                            movie.setShowingStatus(Status.COMINGSOON);
                            break;
                        case "2":
                            movie.setShowingStatus(Status.PREVIEW);
                            break;
                        case "3":
                            movie.setShowingStatus(Status.NOWSHOWING);
                            break;
                        case "4":
                            movie.setShowingStatus(Status.ENDOFSHOWING);
                            break;
                        default:
                            movie.setShowingStatus(Status.COMINGSOON);
                    }
                    System.out.println("New showing status: " + movie.getShowingStatus());
                    break;
                case 4:
                    System.out.println("Current sypnosis: " + movie.getSynopsis());
                    System.out.println("Enter the new synopsis:");
                    temp = sc.nextLine();
                    movie.setSynopsis(temp);
                    System.out.println("New sypnosis: " + movie.getSynopsis());
                    break;
                case 5:
                    System.out.println("Current viewing mode: " + movie.getViewingMode());
                    System.out.println("Enter the new viewing mode:");
                    System.out.println("1 - 2D");
                    System.out.println("2 - 3D");
                    temp = sc.nextLine();
                    switch(temp) {
                        case "1":
                            movie.setViewingMode(View._2D);
                            break;
                        case "2":
                            movie.setViewingMode(View._3D);
                            break;
                        default:
                            movie.setViewingMode(View._2D);
                    }
                    System.out.println("New viewing mode: " + movie.getViewingMode());
                    break;
                case 6:
                    System.out.println("Current movie status: " + movie.getMovieHype());
                    System.out.println("Enter the new movie status:");
                    System.out.println("1 - Regular");
                    System.out.println("2 - Blockbuster");
                    temp = sc.nextLine();
                    switch(temp) {
                        case "1":
                            movie.setMovieHype(Hype.REGULAR);
                            break;
                        case "2":
                            movie.setMovieHype(Hype.BLOCKBUSTER);
                            break;
                        default:
                            movie.setMovieHype(Hype.REGULAR);
                    }
                    System.out.println("New movie status: " + movie.getMovieHype());
                    break;
                case 7:
                    System.out.println("Current age rating: " + movie.getAgeRating());
                    System.out.println("Enter the new age rating:");
                    System.out.println("1 - G");
                    System.out.println("2 - PG");
                    System.out.println("3 - PG13");
                    System.out.println("4 - NC16");
                    System.out.println("5 - M18");
                    System.out.println("6 - R21");
                    temp = sc.nextLine();
                    switch(temp) {
                        case "1":
                            movie.setAgeRating(AgeEnum.G);
                            break;
                        case "2":
                            movie.setAgeRating(AgeEnum.PG);
                            break;
                        case "3":
                            movie.setAgeRating(AgeEnum.PG13);
                            break;
                        case "4":
                            movie.setAgeRating(AgeEnum.NC16);
                            break;
                        case "5":
                            movie.setAgeRating(AgeEnum.M18);
                            break;
                        case "6":
                            movie.setAgeRating(AgeEnum.R21);
                            break;
                        default:
                            movie.setAgeRating(AgeEnum.G);
                    }
                    System.out.println("New age rating: " + movie.getAgeRating());
                    break;
                case 8:
                    System.out.println("Do you want to:");
                    System.out.println("1 - Update director/cast name");
                    System.out.println("2 - Remove cast member");
                    System.out.println("3 - Add cast member");
                    temp = sc.nextLine();
                    switch(temp) {
                        case "1":
                            System.out.println("Select personnel to update");
                            length = movie.printMoviePersonnelIndex();
                            temp = sc.nextLine();
                            choice = Integer.parseInt(temp) - 1;
                            if (0<=choice && choice<length) {
                                MoviePersonnel moviePersonnel = movie.getMoviePersonnel(choice);
                                System.out.println("Current name: " + moviePersonnel.getName());
                                System.out.println("Enter new name:");
                                temp = sc.nextLine();
                                moviePersonnel.setName(temp);
                                System.out.println("New name: " + moviePersonnel.getName());
                                break;
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                        case "2":
                            System.out.println("Select personnel to remove (Note: Each movie is required to have a Director and 2 cast members)");
                            length = movie.printMoviePersonnelIndex();
                            temp = sc.nextLine();
                            choice = Integer.parseInt(temp) - 1;
                            if (choice == 0) {
                                System.out.println("Director cannot removed. Try updating the director's name instead.");
                            }
                            else if (0<choice && choice<length && length > 3) {
                                movie.removeMoviePersonnel(choice);
                                System.out.println("Cast member has been removed.");
                            }
                            else {
                                System.out.println("Invalid input.");
                            }
                            break;
                        case "3":
                            System.out.println("Enter new cast member's name:");
                            temp = sc.nextLine();
                            movie.addMoviePersonnel(temp, Role.CAST);
                            System.out.println("Cast member has been added.");
                            break;
                        default:
                            System.out.println("Invalid input.");
                    }
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
            movie.printMovie();
            System.out.println("What do you want to update:");
            System.out.println("1 - Movie name");
            System.out.println("2 - Movie duration");
            System.out.println("3 - Showing status");
            System.out.println("4 - Movie synopsis");
            System.out.println("5 - Viewing mode");
            System.out.println("6 - Movie status");
            System.out.println("7 - Age rating");
            System.out.println("8 - Movie personnel");
            System.out.println("0 - Quit");
            choice = sc.nextInt();
            sc.nextLine();
        }
        movie.printMovie();
        System.out.println("Movie has been updated.");
    }

    /**
     * Functionality to remove a movie from MovieStore
     */
    default void removeMovie() {
        MovieStore movieStore = MovieStore.getInstance();

        System.out.println("\n--------Remove Movie Module--------");

        // Display all movie name, movie status and showing status
        for (Movie movie: movieStore.getMovieHashMap().values()) {
            System.out.println("-".repeat(29));
            System.out.println(movie.getMovieName());
            System.out.printf("Movie ID: %s\t Status: %s\n", movie.getMovieID(), movie.getShowingStatus().toString());
        }

        System.out.print("Enter movie ID you wish to remove: ");
        String movieID = sc.nextLine();

        Movie removeMovie = movieStore.searchMovie(movieID);
        if (removeMovie == null)
            System.out.println("Error: movie not found");
        else {
            removeMovie.setShowingStatus(Status.ENDOFSHOWING);
            System.out.println(removeMovie.getMovieName());
            System.out.printf("Movie ID: %s\t Status: %s\n", removeMovie.getMovieID(), removeMovie.getShowingStatus().toString());
        }
    }

    // Admin implementation of printAll movies shows ALL details
    /**
     * Prints all movies in MovieStore with their relevant details
     */
    default void printAllMovies() {
        HashMap<String, Movie> movies = MovieStore.getInstance().getMovieHashMap();
        for (Movie movie: movies.values()) {
            movie.printMovie();
        }
    }
}
