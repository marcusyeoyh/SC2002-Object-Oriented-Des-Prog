import java.util.Arrays;
import java.util.Comparator;

public class Plane {
    //instance variables
    private PlaneSeat[] seat = new PlaneSeat[12];
    private int numEmptySeat = 12;

    //constructor
    public Plane(){
        for(int i = 0; i<seat.length; i++){
            this.seat[i] = new PlaneSeat(i+1);
        }
    }

    //class methods
    private PlaneSeat[] sortSeats(){
        PlaneSeat[] tempseat = new PlaneSeat[12];
        tempseat = Arrays.copyOf(seat, 12);
        Arrays.sort(tempseat, new SortbycustID());
        return tempseat;
    }
    public void showNumEmptySeats(){
        System.out.println("\nThere are " + numEmptySeat + " empty seats");
    }
    public void showEmpySeats(){
        for (int i = 0; i<seat.length; i++){
            if (seat[i].isOccupied() == false){
                System.out.println("SeatID " + seat[i].getSeatID());
            }
        }
    }
    public void showAssignedSeats(boolean bySeatId){
        if (bySeatId == true){
            for(int i=0; i<seat.length; i++){
                if (seat[i].isOccupied()==true){
                    System.out.println("SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + seat[i].getCustomerID());
                }
            }
        }
        else{
            PlaneSeat[] temp = this.sortSeats();
            for(int i=0; i<temp.length; i++){
                if (temp[i].isOccupied()==true){
                    System.out.println("SeatID " + temp[i].getSeatID() + " assigned to CustomerID " + temp[i].getCustomerID());
                }
            }
        }
    }
    public void assignSeat(int seatId, int cust_id){
        for (int i = 0; i<seat.length; i++){
            if (seat[i].getSeatID()==seatId && seat[i].isOccupied()==true){
                System.out.println("Seat already assigned to a customer");
                break;
            }
            else if (seat[i].getSeatID()==seatId && seat[i].isOccupied()==false){
                seat[i].assign(cust_id);
                this.numEmptySeat--;
                System.out.println("Seat assigned!");
                break;
            }
        }
    }
    public void unAssignSeat(int seatId){
        for (int i = 0; i<seat.length; i++){
            if (seat[i].getSeatID()==seatId){
                seat[i].unAssign();
                this.numEmptySeat++;
                System.out.println("Seat unassigned!\n");
                break;
            }
        }
    }
}

class SortbycustID implements Comparator<PlaneSeat>{
    public int compare(PlaneSeat a, PlaneSeat b){
        return a.getCustomerID() - b.getCustomerID();
    }
}
