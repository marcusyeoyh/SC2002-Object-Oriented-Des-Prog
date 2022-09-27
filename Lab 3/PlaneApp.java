import java.util.Scanner;

public class PlaneApp {
    public static void main(String[] args){
        int choice, var1, var2;
        Scanner sc = new Scanner(System.in);
        Plane plane = new Plane();

        do {
            System.out.println("\nPlaneApp Options:");
            System.out.println("(1) Show the number of empty seats");
            System.out.println("(2) Show the list of empty seats");
            System.out.println("(3) Show the list of seat assignments by seat ID");
            System.out.println("(4) Show the list of seat assignments by customer ID");
            System.out.println("(5) Assign a customer to a seat");
            System.out.println("(6) Remove a seat assignment");
            System.out.println("(7) Quit");
            System.out.println("Enter the number of your choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                plane.showNumEmptySeats();
                break;
                case 2:
                System.out.println("\nThe following seats are empty: ");
                plane.showEmpySeats();
                break;
                case 3:
                plane.showAssignedSeats(true);
                break;
                case 4:
                plane.showAssignedSeats(false);
                break;
                case 5: 
                System.out.println("Please enter SeatID: ");
                var1 = sc.nextInt();
                System.out.println("Please enter Customer ID: ");
                var2 = sc.nextInt();
                plane.assignSeat(var1, var2);
                break;
                case 6:
                System.out.println("Enter SeatID to unassign customer from: ");
                var1 = sc.nextInt();
                plane.unAssignSeat(var1);
                break;
                case 7: System.out.println("Program terminating");
            }
        }
        while (choice<7);
    }
}
